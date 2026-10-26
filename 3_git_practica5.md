
# Práctica 5: Entendiendo `git reset`

## 🎯 Objetivo

Comprender, con un ejemplo muy simple, qué hacen las tres variantes de `git reset`:

* `git reset --soft`
* `git reset --mixed` (por defecto)
* `git reset --hard`

y observar qué ocurre con:

* el **historial de commits**,
* el **área de preparación (staging)**,
* el **área de trabajo (working directory)**.

Todo se realiza **en la misma rama (`main`) y en local**.

---

## 0️⃣ Preparación inicial – Crear el repositorio

1. Crea una carpeta para la práctica y entra en ella:

```bash
mkdir pruebaReset
cd pruebaReset
```

2. Inicializa el repositorio Git:

```bash
git init
```

3. Comprueba el estado:

```bash
git status
```

---

## 1️⃣ Crear 4 versiones del fichero `pruebas.txt` (commits A, B, C y D)

Vamos a simular cuatro versiones consecutivas del fichero.

### 🔹 Commit A — “Versión 1”

1. Crea el fichero `pruebas.txt` con este contenido:

```text
Versión 1
```

2. Añade y confirma:

```bash
git add pruebas.txt
git commit -m "A: Versión 1"
```

### 🔹 Commit B — “Versión 2”

Edita `pruebas.txt` para que quede:

```text
Versión 1
Versión 2
```

Confirma:

```bash
git add pruebas.txt
git commit -m "B: Versión 2"
```

### 🔹 Commit C — “Versión 3”

Edita `pruebas.txt`:

```text
Versión 1
Versión 2
Versión 3
```

Confirma:

```bash
git add pruebas.txt
git commit -m "C: Versión 3"
```

### 🔹 Commit D — “Versión 4”

Edita `pruebas.txt`:

```text
Versión 1
Versión 2
Versión 3
Versión 4
```

Confirma:

```bash
git add pruebas.txt
git commit -m "D: Versión 4"
```

### 🔍 Ver historial inicial

```bash
git log --oneline --graph --all
```

Deberías ver algo parecido a:

```text
* ddddddd (HEAD -> main) D: Versión 4
* cccccc c C: Versión 3
* bbbbbb b B: Versión 2
* aaaaaaa A: Versión 1
```

---

## 2️⃣ Probar `git reset --soft` hacia el commit B

### 2.1. Localizar el commit B

Muestra el historial resumido:

```bash
git log --oneline
```

Localiza el identificador (SHA corto) del commit **B: Versión 2**
(lo llamaremos aquí `bbbbbbb`, sustituye por el tuyo real).

### 2.2. Ejecutar `reset --soft B`

```bash
git reset --soft bbbbbbb
```

### 2.3. Analizar el resultado (HEAD, staging y working directory)

Ahora vamos a comprobar qué ha pasado en los tres niveles.

#### a) Ver qué contiene el último commit (HEAD)

```bash
git show HEAD:pruebas.txt
```

Deberías ver solo:

```text
Versión 1
Versión 2
```

👉 Esto es lo que Git tiene guardado en el **último commit** (B) después del reset.

#### b) Ver qué contiene el archivo en tu carpeta (working directory)

```bash
cat pruebas.txt
```

Deberías ver:

```text
Versión 1
Versión 2
Versión 3
Versión 4
```

👉 Aunque el historial ha retrocedido a B, **tu archivo sigue como en la Versión 4**.
No has perdido el trabajo de C y D.

#### c) Ver qué hay en el staging (index)

1. Estado actual:

```bash
git status
```

Verás `pruebas.txt` como **“changes to be committed”** (preparado para commit).

2. Ver exactamente qué cambios hay preparados respecto a B:

```bash
git diff --cached
```

Deberían aparecer las líneas añadidas:

```diff
+Versión 3
+Versión 4
```

👉 Esto significa:

* HEAD (B) solo conoce hasta “Versión 2”.
* El staging contiene los cambios que añadirían “Versión 3” y “Versión 4”.
* El fichero real ya está en el estado final (como en D).

### 2.4. Reconstruir la situación original (volver a D)

Para dejar el repo como al principio (otra vez con D como último commit), puedes:

```bash
git commit -m "C y D: reconstruidos tras reset --soft"
```

(En un caso real podrías re-hacer los commits separados, pero para esta práctica basta con uno solo o repetir C y D si quieres volver exáctamente a la situación original.)

---

## 3️⃣ Probar `git reset --mixed` hacia B

> `--mixed` es el modo por defecto de `git reset`.

### 3.1. Asegúrate de que estás de nuevo en D

```bash
git log --oneline
cat pruebas.txt
```

Debes tener otra vez las cuatro versiones y el último commit D.

### 3.2. Ejecutar `reset --mixed B`

De nuevo, identifica el SHA de B (`bbbbbbb`) y ejecuta:

```bash
git reset --mixed bbbbbbb
# o simplemente:
git reset bbbbbbb
```

### 3.3. Analizar el resultado

1. Historial:

```bash
git log --oneline
```

Verás solo A y B.

2. Staging y working directory:

```bash
git status
```

Verás `pruebas.txt` como **“modified”**, pero ya no en “changes to be committed”.
Es decir, los cambios de C y D están **en tu carpeta**, pero no en staging.

3. Contenido del archivo:

```bash
cat pruebas.txt
```

Sigue estando:

```text
Versión 1
Versión 2
Versión 3
Versión 4
```

👉 Diferencia clave con `--soft`:

* Con `--soft`, los cambios de C y D estaban **en staging**.
* Con `--mixed`, los cambios de C y D están solo **en el área de trabajo** (sin preparar).

### 3.4. Reconstruir la situación (volver a D)

```bash
git add pruebas.txt
git commit -m "C y D: reconstruidos tras reset --mixed"
```

Otra vez tu historial tendrá A, B y el nuevo commit con las cuatro líneas.

## 4️⃣ Probar `git reset --hard` hacia B

⚠️ Este modo sí eliminará los cambios de C y D del área de trabajo.

### 4.1. Asegúrate de que vuelves a tener las cuatro líneas

```bash
cat pruebas.txt
git log --oneline
```

### 4.2. Ejecutar `reset --hard B`

```bash
git reset --hard bbbbbbb
```

### 4.3. Analizar el resultado

1. Historial:

```bash
git log --oneline
```

Solo A y B.

2. Estado:

```bash
git status
```

Working directory limpio (no hay cambios pendientes).

3. Contenido del archivo:

```bash
cat pruebas.txt
```

Ahora verás únicamente:

```text
Versión 1
Versión 2
```

👉 Aquí sí han desaparecido físicamente del archivo “Versión 3” y “Versión 4”.

---

## 5️⃣ Resumen conceptual (para cerrar la práctica)

Puedes cerrar la sesión con esta tabla:

| Modo                  | HEAD (rama) | Staging (index)                          | Working directory         | Efecto práctico                                      |
| --------------------- | ----------- | ---------------------------------------- | ------------------------- | ---------------------------------------------------- |
| `git reset --soft B`  | Vuelve a B  | Mantiene cambios de C y D **preparados** | Mantiene cambios de C y D | Deshace commits, pero deja todo listo para re-commit |
| `git reset --mixed B` | Vuelve a B  | Limpia staging (como en B)               | Mantiene cambios de C y D | Deshace commits, deja cambios sin añadir             |
| `git reset --hard B`  | Vuelve a B  | Como en B                                | Como en B (borra C y D)   | Deshace commits y borra cambios locales              |

---

## **ENTREGA DE LA PRÁCTICA**

Al finalizar la práctica, debes entregar **únicamente un archivo de texto** con los comandos ejecutados durante la misma.

## ✅ **1. Exporta tu historial de comandos**

En tu terminal (Git Bash, Linux o macOS), ejecuta:

```bash
history > AAA_practica5.txt
```

Donde:

* **AAA** son tus **iniciales** en el orden
  **Primer apellido – Segundo apellido – Nombre**
  (Ej.: *GGR_practica5.txt* para *García Gómez Roberto*).

Este archivo debe contener **todos los comandos que has utilizado** durante la práctica.

> ⚠️ **IMPORTANTE:**
> Para asegurarte de que el historial incluye los últimos comandos, **cierra la terminal**, vuelve a abrirla, y entonces ejecuta el comando `history > AAA_practica5.txt`.

---

## ✔️ **Sube el archivo a Moodle**

* `AAA_practica5.txt`

Sin este archivo, la práctica no se considerará entregada.
