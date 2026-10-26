
# Práctica 6: Entendiendo `git revert` (deshacer cambios sin borrar historia)

## 🎯 Objetivo

Comprender cómo funciona el comando:

```bash
git revert <id_commit>
```

y ver que:

* **no borra** commits antiguos,
* crea un **nuevo commit** que “deshace” otro,
* se puede revertir el **último commit** o uno **más antiguo**,
* puede producir conflictos si el código ha cambiado mucho.

Todo se realiza **en local**, en una única rama (`main`).

---

## 0️⃣ Preparación inicial – Crear el repositorio

1. Crea una carpeta para la práctica y entra en ella:

```bash
mkdir pruebaRevert
cd pruebaRevert
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

## 1️⃣ Crear 3 versiones del fichero `notas.txt` (commits A, B y C)

Vamos a crear un archivo de texto y hacer **3 commits** que lo vayan modificando.

### 🔹 Commit A — Versión inicial

Crea el fichero `notas.txt` con este contenido:

```text
Línea 1: Versión inicial
Línea 2: Texto original
```

Confirma:

```bash
git add notas.txt
git commit -m "A: Versión inicial"
```

### 🔹 Commit B — Modificar la línea 2

Edita `notas.txt` para que quede:

```text
Línea 1: Versión inicial
Línea 2: Texto modificado en B
```

Confirma:

```bash
git add notas.txt
git commit -m "B: Cambiada la línea 2"
```

### 🔹 Commit C — Añadir una línea nueva

Edita `notas.txt`:

```text
Línea 1: Versión inicial
Línea 2: Texto modificado en B
Línea 3: Añadida en el commit C
```

Confirma:

```bash
git add notas.txt
git commit -m "C: Añadida la línea 3"
```

---

### 🔍 Ver historial inicial

```bash
git log --oneline --graph --all
```

Deberías ver algo parecido a:

```text
* cccccc C: Añadida la línea 3
* bbbbbb B: Cambiada la línea 2
* aaaaaa A: Versión inicial
```

Y el archivo actual:

```bash
cat notas.txt
```

Debe mostrar las 3 líneas.

---

## 2️⃣ Revertir el último commit (revert de C)

Vamos a deshacer el último commit **C**, pero sin borrar su existencia del historial.

### 2.1 Ejecutar `git revert C`

1. Localiza el identificador de C (`cccccc`) con:

```bash
git log --oneline
```

2. Ejecuta:

```bash
git revert cccccc
```

Git abrirá el editor de mensajes de commit (o usará uno por defecto) y creará un nuevo commit que “deshace” C.

> ### 💬 Nota importante sobre `git revert`
>
> Cuando ejecutes `git revert`, es normal que se abra una ventana del editor de texto pidiéndote escribir un mensaje de commit.
>
> ✅ No es un error
> ✅ Forma parte del funcionamiento de Git
>
> 👉 Si quieres entender **por qué ocurre esto y qué debes hacer exactamente**, consulta:
> 🔗 [¿Por qué se abre un editor de texto al hacer `git revert`?](3_git_practica6_apertura_editor.md)

### 2.2 Analizar el resultado

#### a) Historial

```bash
git log --oneline --graph --all
```

Deberías ver algo como:

```text
* dddddd Revert "C: Añadida la línea 3"
* cccccc C: Añadida la línea 3
* bbbbbb B: Cambiada la línea 2
* aaaaaa A: Versión inicial
```

👉 Fíjate:
C **sigue existiendo** en el historial, pero ahora hay un commit nuevo (“Revert C”) que deshace sus cambios.

#### b) Contenido del archivo

```bash
cat notas.txt
```

Deberías ver ahora:

```text
Línea 1: Versión inicial
Línea 2: Texto modificado en B
```

👉 El archivo ha vuelto al estado anterior a C,
pero **el historial no ha desaparecido**, solo hemos añadido un commit que “revierte” C.

---

## 3️⃣ Revertir un commit antiguo (revert de B)

Ahora vamos a deshacer un commit que **no es el último**: el commit B.

### 3.1 Localizar el commit B

```bash
git log --oneline
```

Identifica el SHA de B (`bbbbbb`).

En este momento:

* El archivo está así (después de revertir C):

  ```text
  Línea 1: Versión inicial
  Línea 2: Texto modificado en B
  ```

* Y el historial contiene A, B, C y el revert de C.

### 3.2 Ejecutar `git revert B`

```bash
git revert bbbbbb
```

Git creará **otro commit nuevo** que deshace los cambios del commit B.

### 3.3 Analizar el resultado

#### a) Historial

```bash
git log --oneline --graph --all
```

Verás algo similar a:

```text
* eeeeee Revert "B: Cambiada la línea 2"
* dddddd Revert "C: Añadida la línea 3"
* cccccc C: Añadida la línea 3
* bbbbbb B: Cambiada la línea 2
* aaaaaa A: Versión inicial
```

👉 Observa:

* Ni B ni C han desaparecido.
* Simplemente hemos creado commits nuevos que “los deshacen”.

#### b) Contenido del archivo

```bash
cat notas.txt
```

Debería volver a:

```text
Línea 1: Versión inicial
Línea 2: Texto original
```

Es decir, prácticamente el estado del commit A, **pero A no se ha repetido**, y B y C siguen estando en la historia.

---

## 4️⃣ Comparación con reset

1. Con `reset`, movíamos la rama hacia atrás y **podíamos borrar commits de la historia**.
2. Con `revert`, **no se borra nada**:

   * La historia se mantiene,
   * solo se añaden commits que deshacen cambios anteriores.

---

## 5️⃣ Ver un revert con conflicto

Este bloque sirve para ver que `revert` también puede generar conflictos.

### 5.1 Preparar la situación

Partimos de este estado en el archivo *(después de los reverts anteriores)*:

```text
Línea 1: Versión inicial
Línea 2: Texto original
```

Haz ahora un nuevo commit D modificando otra vez la línea 2:

1. Edita `notas.txt`:

```text
Línea 1: Versión inicial
Línea 2: Texto cambiado en el commit D
```

2. Confirma:

```bash
git add notas.txt
git commit -m "D: Cambio nuevo en la línea 2"
```

### 5.2 Intentar revertir otra vez B

Ahora intenta esto:

```bash
git revert bbbbbb
```

(Si B está muy atrás y sus cambios no se encuentran tal cual, es posible que Git **no pueda aplicar el parche inverso sin ayuda**.)

Si se produce un conflicto, Git lo indicará:

```text
Auto-merging notas.txt
CONFLICT (content): Merge conflict in notas.txt
error: could not revert c5499e2... B: Cambiada la línea 2
hint: After resolving the conflicts, mark them with
hint: "git add/rm <pathspec>", then run
hint: "git revert --continue".
hint: You can instead skip this commit with "git revert --skip".
hint: To abort and get back to the state before "git revert",
hint: run "git revert --abort".
hint: Disable this message with "git config advice.mergeConflict false"
```

El archivo `notas.txt` contendrá algo como:

```text
Línea 1: Versión inicial
<<<<<<< HEAD
Línea 2: Texto cambiado en el commit D
=======
Línea 2: Texto original
>>>>>>> parent of bbbbbbb (B: Cambiada la línea 2)
```

Tendrás que:

1. Editar el archivo `notas.txt`y dejar la versión correcta.

Por ejemplo, mantener el texto original del commit A:

```
Línea 1: Versión inicial
Línea 2: Texto original
```

3. Ejecutar:

```bash
git add notas.txt
git revert --continue
```

### 🧠 Mensaje clave

> `revert` es seguro a nivel de historia (no borra commits),
> pero también puede producir conflictos si los cambios que intenta deshacer han sido modificados en commits posteriores.

---

## 6️⃣ Resumen final

* `git revert <id_commit>`:

  * **NO borra** el commit indicado.
  * Crea un **nuevo commit** que aplica el “contracambio”.

* Se puede revertir:

  * el último commit (HEAD),
  * o uno más antiguo del historial.

* Es seguro usarlo después de hacer `push` a GitHub.
* Puede generar conflictos si el código ha cambiado mucho desde el commit que queremos revertir.
* En caso de conflicto:

  * resolver en el archivo,
  * `git add`,
  * `git revert --continue`.

---

## **ENTREGA DE LA PRÁCTICA**

Al finalizar la práctica, debes entregar **únicamente un archivo de texto** con los comandos ejecutados durante la misma.

### ✅ **1. Exporta tu historial de comandos**

En tu terminal (Git Bash, Linux o macOS), ejecuta:

```bash
history > AAA_practica6.txt
```
Donde:

* **AAA** son tus **iniciales** en el orden
  **Primer apellido – Segundo apellido – Nombre**
  (Ej.: *GGR_practica5.txt* para *García Gómez Roberto*).

Este archivo debe contener **todos los comandos que has utilizado** durante la práctica.

> ⚠️ **IMPORTANTE:**
> Para asegurarte de que el historial incluye los últimos comandos, **cierra la terminal**, vuelve a abrirla, y entonces ejecuta el comando `history > AAA_practica6.txt`.

### ✅ **2. Captura de la historia de commits al finalizar la práctica**

<img width="931" height="204" alt="image" src="https://github.com/user-attachments/assets/470d81eb-19ed-4928-af6b-0a5bf1c6aa23" />

---

## ✔️ **Sube los archivos a Moodle**

* `AAA_practica6.txt`
* captura_final.jpg

