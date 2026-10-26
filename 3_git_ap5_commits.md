
# 5. Gestión del historial de commits

---

## 5.1 Introducción

En Git, cada vez que se ejecuta un comando `commit`, se crea una **versión exacta del proyecto** en ese momento: una *fotografía del estado del código*.
Todas esas versiones quedan registradas en el **historial del repositorio local**, que Git almacena en la carpeta oculta `.git`.

El historial de commits permite:

* Consultar quién hizo cada cambio y cuándo.
* Volver a una versión anterior.
* Deshacer errores sin perder trabajo.
* Comparar y fusionar ramas con precisión.

> 🧠 Un buen control del historial de commits te da la capacidad de viajar en el tiempo dentro del proyecto, sin miedo a romper nada.

---

## 5.2 Ver el historial de commits

### 🔹 Ver historial básico

```bash
git log
```

Muestra la lista completa de commits con su autor, fecha y mensaje.

### 🔹 Ver historial simplificado

```bash
git log --oneline
```

Ejemplo:

```
e8f2a91 (HEAD -> main) Añadido sistema de login
4a9b0cc Creada interfaz gráfica básica
a7d4b3e Estructura inicial del proyecto
```

### 🔹 Ver historial con estructura de ramas

```bash
git log --oneline --graph --all
```

Ejemplo:

```text
* e8f2a91 (HEAD -> main, origin/main) Merge branch 'feature-login'
|\  
| * b1f3c8a (feature-login) Añadido formulario de login
* | a7d4b3e Ajustado layout de pantalla principal
|/  
* 2d9a1b0 Versión inicial del proyecto
```

---

## 5.3 Ver los detalles de un commit

Cada commit tiene un identificador único (*hash*).
Puedes consultar los detalles de uno concreto con:

```bash
git show <id_commit>
```

Ejemplo:

```bash
git show e8f2a91
```

Mostrará los archivos modificados y las diferencias respecto al commit anterior.

---

## 5.4 Moverse entre versiones con `checkout`

Puedes moverte temporalmente a una versión anterior del proyecto:

```bash
git checkout <id_commit>
```

Esto coloca tu `HEAD` en ese commit concreto y cambia los archivos del directorio de trabajo para reflejar ese estado.

> ⚠️ En este punto estás en un estado **detached HEAD**
> (sin rama activa, solo “visitando” una versión pasada).

### 🔹 Usos típicos de `checkout <id_commit>`

* Revisar cómo era el código en una fecha anterior.
* Ejecutar pruebas o inspeccionar un comportamiento pasado.
* Comparar versiones sin modificar el historial.

### 🔹 Volver a la rama principal

Cuando termines de revisar, vuelve al trabajo normal con:

```bash
git switch main
```

> 💡 Mientras estés en `detached HEAD`, evita hacer commits nuevos, ya que no estarán ligados a ninguna rama.

---

## 5.5 Deshacer cambios recientes

Si cometiste un error antes de confirmar los cambios, Git ofrece distintas formas de deshacerlos según la situación.

### 🔹 Deshacer cambios sin haber hecho `add`

```bash
git restore <archivo>
```

Revierte el archivo a su última versión confirmada (elimina los cambios en el área de trabajo).

---

### 🔹 Deshacer un `git add`

Si añadiste un archivo por error al *staging area*:

```bash
git restore --staged <archivo>
```

El archivo volverá al área de trabajo sin perder su contenido.

---

## 5.6 Volver a un commit anterior sin borrar la historia (`git revert`)

El comando `git revert` **crea un nuevo commit que deshace los cambios de un commit anterior**, pero **sin eliminarlo del historial**.

Es una operación **segura** y reversible, ideal cuando el proyecto ya está sincronizado con GitHub o cuando se trabaja en equipo.

---

### 🔹 Ejemplo visual

```text
Historia original:

A --- B --- C --- D   (main)
```

Si revertimos el commit **C**:

```bash
git revert <id_C>
```

Git crea un nuevo commit **E**, que **invierte los cambios** de C:

```text
Resultado:

A --- B --- C --- D --- E
                     ↑
        E deshace los cambios introducidos por C
```

> 🧠 La historia completa se conserva:
> `C` sigue existiendo, pero sus efectos se anulan con `E`.

---

### 🔹 Cuándo usar `revert`

* Cuando ya se ha hecho `push` al remoto.
* Cuando no quieres alterar la historia del proyecto.
* Cuando necesitas deshacer uno o varios commits manteniendo la trazabilidad.

### 🔹 Revertir varios commits a la vez

```bash
git revert <id_inicial>^..<id_final>
```

Esto deshará todos los commits entre esos dos puntos.

---

## 5.7 Volver a una versión anterior eliminando historia (`git reset`)

`git reset` mueve el puntero `HEAD` y la rama actual a un commit anterior.
A diferencia de `revert`, **sí modifica el historial**.

> ⚠️ Por tanto, debe usarse con precaución, especialmente si el proyecto ya se ha subido al remoto.

---

### 🔹 Tipos de `reset`

| Tipo                      | Qué hace                                                                               | Qué mantiene                                |
| ------------------------- | -------------------------------------------------------------------------------------- | ------------------------------------------- |
| `--soft`                  | Mueve el HEAD al commit indicado, pero mantiene los cambios en el área de preparación. | Cambios listos para nuevo commit.           |
| `--mixed` *(por defecto)* | Mueve el HEAD y borra el área de preparación.                                          | Mantiene los cambios en el área de trabajo. |
| `--hard`                  | Elimina los commits posteriores y todos los cambios del área de trabajo.               | ⚠️ No recuperable.                          |

---

### 🔹 Ejemplo de `reset --hard`

```bash
git log --oneline
# e8f2a91 (HEAD -> main) Añadido login
# 4a9b0cc Creada interfaz
# a7d4b3e Estructura inicial

# Volver al commit inicial
git reset --hard a7d4b3e
```

Resultado:

```text
A7d4b3e Estructura inicial
```

Los commits posteriores (interfaz y login) se eliminan del historial local.

> 🚨 Si haces `push` después de un `reset --hard`, eliminarás también esos commits del remoto.
> En entornos colaborativos, **nunca uses reset en ramas compartidas.**

---

## 5.8 Recuperar commits eliminados (caso avanzado)

Si haces un `reset --hard` o pierdes commits recientes, puedes intentar recuperarlos con el registro de referencias:

```bash
git reflog
```

Git mantiene un historial temporal de todos los movimientos del `HEAD`.
Cada línea incluye un identificador del commit anterior.
Puedes volver a uno de ellos con:

```bash
git checkout <id_reflog>
```

---

## 5.9 Volver a una versión anterior para probar (sin borrar historia)

A veces quieres “volver atrás” momentáneamente sin perder commits recientes.
Esto se puede hacer con `checkout`:

```bash
git checkout <id_antiguo>
```

o, si solo quieres restaurar un archivo concreto:

```bash
git restore --source=<id_commit> <archivo>
```

> 💡 Este comando restaura el archivo tal como estaba en ese commit, sin afectar al resto del proyecto.

---

## 5.10 Reorganizar commits con `rebase` (concepto básico)

El comando `rebase` permite **recolocar los commits de una rama sobre otra base**.
Se usa, por ejemplo, cuando una rama secundaria se ha quedado desactualizada respecto a `main`.

### 🔹 Ejemplo

```text
Antes del rebase:

main:    A --- B --- C
feature:       \--- D --- E
```

Si hacemos:

```bash
git switch feature
git rebase main
```

La historia se reescribe así:

```text
Después del rebase:

main:    A --- B --- C
feature:               \--- D' --- E'
```

Los commits D y E se vuelven a aplicar sobre el último commit de `main`.

> ⚠️ `rebase` cambia los identificadores de commit.
> Por eso **solo debe usarse en ramas locales personales**, no en ramas compartidas o ya subidas a GitHub.

---

## 5.11 Comparativa entre `revert`, `reset` y `rebase`

| Comando      | Qué hace                                           | Efecto en la historia               | Cuándo usarlo                                         |
| ------------ | -------------------------------------------------- | ----------------------------------- | ----------------------------------------------------- |
| `git revert` | Crea un nuevo commit que deshace otro.             | No borra commits anteriores.        | Para deshacer cambios de forma segura (tras un push). |
| `git reset`  | Mueve el puntero HEAD y borra commits posteriores. | ⚠️ Borra historia local.            | Para deshacer commits locales aún no compartidos.     |
| `git rebase` | Reubica commits de una rama sobre otra.            | Reescribe la historia (nuevos IDs). | Para mantener una historia lineal en ramas locales.   |

---

## 5.12 Ejemplo comparativo visual

### 🔹 Situación inicial

```text
A --- B --- C --- D  (main)
```

### 🔹 `git revert C`

```text
A --- B --- C --- D --- E
                  ↑
        E revierte los cambios de C
```

La historia completa se conserva.

---

### 🔹 `git reset --hard B`

```text
A --- B
```

Los commits C y D desaparecen de la historia local.
(⚠️ Irreversible si no se han subido al remoto.)

---

### 🔹 `git rebase main` (desde otra rama)

```text
main:    A --- B --- C
feature:       \--- D --- E
↓
main:    A --- B --- C
                     \
feature:               D' --- E'
```

Reubica los commits D y E después de C para mantener una línea continua.

---

## 5.13 Buenas prácticas con el historial

* Usa `git log --oneline` con frecuencia para revisar el estado del proyecto.
* Prefiere `git revert` en lugar de `reset` en proyectos compartidos.
* No uses `reset --hard` si el código ya se ha subido al remoto.
* Crea mensajes de commit claros: resumen en presente, sin puntos finales.

  * ✅ `Añade validación de usuario`
  * ❌ `He añadido la validación del usuario.`

* Evita hacer demasiados commits triviales (“prueba”, “update”); agrupa los cambios significativos.
* Usa `rebase` solo en ramas personales antes de fusionarlas con `main`.

---

## 5.14 Ejemplo práctico completo

Supongamos que el historial actual es:

```text
A --- B --- C --- D  (HEAD -> main)
```

Y queremos volver atrás un paso, revisarlo y luego corregirlo.

### 🔹 Paso 1: ver el historial

```bash
git log --oneline
```

### 🔹 Paso 2: moverse temporalmente al commit B

```bash
git checkout B
# Revisas los archivos o pruebas el código
```

### 🔹 Paso 3: volver a main

```bash
git switch main
```

### 🔹 Paso 4: deshacer los cambios de C sin borrar la historia

```bash
git revert C
```

Git crea un nuevo commit E que revierte C.

### 🔹 Paso 5: subir al remoto

```bash
git push
```

---

✅ **Conclusión**

Dominar el historial de commits es esencial para trabajar con seguridad y confianza en Git.
Conocer la diferencia entre **revertir, resetear y reescribir** te permite corregir errores sin miedo y mantener una historia clara y coherente en el proyecto.

---
