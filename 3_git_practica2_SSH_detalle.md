
## 1. Qué son realmente las dos claves

Cuando generas tu par de claves SSH:

```bash
ssh-keygen -t ed25519 -C "tu_usuario@g.educaand.es"
```

se crean dos archivos:

| Tipo                 | Archivo                 | Dónde está        | Se comparte            |
| -------------------- | ----------------------- | ----------------- | ---------------------- |
| 🔑 Clave **privada** | `~/.ssh/id_ed25519`     | Solo en tu equipo | ❌ **Nunca**            |
| 🪪 Clave **pública** | `~/.ssh/id_ed25519.pub` | Se puede copiar   | ✅ Sí, se sube a GitHub |

Ambas forman un **par matemático**:
lo que una cifra, solo la otra puede descifrar.

---

## 2. Qué ocurre cuando te conectas a GitHub por SSH

Cuando haces, por ejemplo:

```bash
ssh -T git@github.com
```

ocurren **varios pasos invisibles pero muy importantes:**

1. **Tu cliente SSH (en tu equipo)** contacta con el servidor de GitHub (`git@github.com`).

2. GitHub revisa su base de datos interna:

   * Busca todas las **claves públicas** asociadas a tu cuenta.

3. El servidor **elige una de esas claves públicas** y crea un **mensaje aleatorio (reto)** que cifra **usando tu clave pública**.

4. Ese mensaje cifrado viaja hasta tu ordenador.

5. Tu ordenador (cliente SSH):

   * Usa tu **clave privada** (que nunca sale de tu equipo)
   * Desencripta el mensaje.
   * Envía la **respuesta descifrada** de vuelta a GitHub.

6. GitHub comprueba:

   > “La respuesta es correcta → este usuario posee la clave privada correspondiente a la pública que tengo registrada.”

✅ **Autenticación completada sin necesidad de contraseña.**

---

## 3. Por qué esto es seguro

* Tu **clave privada nunca viaja** por la red.
* La clave pública **no sirve para acceder** al servidor:
  con ella solo se pueden **verificar firmas**, no generarlas.
* Incluso si alguien copia tu clave pública de GitHub o de tu ordenador, **no puede hacer nada** sin la clave privada.

💡 **Analogía simple:**

> Piensa en la clave pública como un **candado abierto** que cualquiera puede tener.
> Pero solo tú tienes la **llave que lo abre** (la clave privada).
> GitHub pone tu candado, tú demuestras que tienes la llave correcta.

---

## 4. Qué comprueba exactamente SSH

Cuando haces `git push`, el proceso es este:

| Paso | Qué hace                                                                               |
| ---- | -------------------------------------------------------------------------------------- |
| 1️⃣  | Tu cliente SSH usa la clave privada para firmar un mensaje.                            |
| 2️⃣  | GitHub usa la clave pública que tiene guardada para verificar esa firma.               |
| 3️⃣  | Si la verificación es válida, GitHub sabe que **eres tú** (dueño de la clave).         |
| 4️⃣  | Se establece una conexión cifrada segura para enviar los datos (`push`, `pull`, etc.). |

---

## 5. Si alguien roba o copia la clave pública

No hay peligro.

* **La clave pública sola no sirve para autenticarse.**
* No se puede “invertir” matemáticamente para obtener la privada (con algoritmos como RSA o Ed25519, sería imposible con la potencia actual de cómputo).
* Puedes subir la misma clave pública en varios servidores; todos reconocerán tu misma identidad.

El **riesgo real** sería que alguien copie tu **clave privada**, porque entonces **podría hacerse pasar por ti**.
Por eso debe:

* Estar solo en tu usuario.
* Tener permisos `600` (solo lectura/escritura para el dueño).
* Y opcionalmente una **contraseña** (passphrase) al crearla.

---

## 6. Qué guarda GitHub internamente

GitHub no guarda contraseñas ni hashes de ellas cuando usas SSH.
Guarda únicamente **la parte pública de tu clave**.

Así, cuando te conectas, **GitHub no “compara” claves directamente**, sino que **verifica una firma criptográfica** generada con tu privada.

---

## 7. Ejemplo visual simplificado

```
Tu equipo                              GitHub
-----------                            ---------
Tienes clave privada                   Guarda tu clave pública

↓ 1. Envías solicitud SSH  →           "¿Tienes la clave que corresponde a esta?"
                                         ↓
                                     Cifra un mensaje con tu clave pública
                                         ↓
← 2. Recibes mensaje cifrado

3. Desencriptas con tu clave privada
4. Envías el resultado
                                         ↓
                                     Verifica que coincide
                                         ↓
                                     ✅ Acceso concedido
```

---

## RESUMEN

| Concepto             | Explicación                                          |
| -------------------- | ---------------------------------------------------- |
| Clave pública        | Identifica tu equipo, se sube a GitHub               |
| Clave privada        | Demuestra tu identidad, se guarda en tu PC           |
| Qué se compara       | No se comparan claves, se verifica una firma digital |
| Si copian la pública | No pasa nada                                         |
| Si copian la privada | Pueden suplantarte (¡nunca compartirla!)             |

