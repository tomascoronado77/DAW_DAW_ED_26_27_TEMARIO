
# Práctica 4: Rebase con conflicto en Git

> Objetivo: entender cómo funciona `git rebase` cuando la rama principal y una rama secundaria modifican **la misma línea** de un archivo, provocando un conflicto que hay que resolver manualmente.

---

## 1. Preparación inicial

1. Crea una carpeta para la práctica:

```bash
mkdir git-rebase-conflicto
cd git-rebase-conflicto
```

2. Inicializa un repositorio Git vacío:

```bash
git init
```

3. Crea un fichero de texto llamado `notas.txt` con este contenido inicial:

```text
Línea 1: Proyecto de prueba con rebase.
Línea 2: Esta es la versión inicial del texto.
```

4. Añade y confirma el primer commit:

```bash
git add notas.txt
git commit -m "A: Versión inicial de notas.txt"
```

En este momento solo tienes **un commit (A)** en la rama principal (`main` o `master`).

---

## 2. Crear una rama secundaria y modificar el texto

1. Crea una rama nueva llamada `estilo_texto` y cámbiate a ella:

```bash
git switch -c estilo_texto
```

2. Edita `notas.txt` y modifica la **línea 2** para dejarla así:

```text
Línea 1: Proyecto de prueba con rebase.
Línea 2: Texto centrado en el estilo y la presentación.
```

3. Guarda y realiza un commit:

```bash
git add notas.txt
git commit -m "B: Modificada la línea 2 con un nuevo texto de estilo"
```

4. Ahora añade una **línea 3** al final del archivo:

```text
Línea 3: Pendiente revisar posibles mejoras visuales.
```

5. Guarda y realiza otro commit:

```bash
git add notas.txt
git commit -m "C: Añadida una línea sobre mejoras visuales"
```

La situación ahora es:

```text
main:          A
                 \
estilo_texto:     B - C
```

---

## 3. Avanzar la rama principal (`main`) modificando la misma línea

Ahora vamos a simular que en la rama principal alguien también ha tocado la **línea 2**, pero con otro texto distinto.

1. Cambia de vuelta a la rama principal:

```bash
git switch main
```

2. Edita `notas.txt` y modifica la **línea 2**, pero con este texto:

```text
Línea 1: Proyecto de prueba con rebase.
Línea 2: Esta es la versión principal del proyecto en desarrollo.
```

(Importante: **la línea 2** queda diferente de la que pusiste en `estilo_texto`).

3. Guarda y realiza un nuevo commit:

```bash
git add notas.txt
git commit -m "D: Modificada la línea 2 en la rama principal"
```

Estado del repo:

```text
main:          A - D
                 \
estilo_texto:     B - C
```

---

## 4. Ver el historial antes del rebase

1. Ejecuta en la terminal el comando adecuado para mostrar la historia de **todas las ramas** de forma gráfica y resumida.

2. Comprueba que:

* `main` tiene los commits A y D.
* `estilo_texto` tiene los commits B y C, partiendo de A.

3. Realiza una captura de pantalla de la terminal mostrando:

* El comando utilizado.
* El historial.
* La ruta del proyecto.
* Tu usuario de sistema.

*(Usa un único comando para mostrar el historial.)*

---

## 5. Rebase de `estilo_texto` sobre `main` (aquí aparece el conflicto)

Ahora queremos que los cambios de `estilo_texto` (commits B y C) se **reapliquen encima de la versión actual de `main` (commit D)**.

Esto provocará un **conflicto** porque ambas ramas han modificado la **misma línea 2** con textos distintos.

1. Cámbiate a la rama `estilo_texto`:

```bash
git switch estilo_texto
```

2. Ejecuta el siguiente comando:

```bash
git rebase main
```

> ### 💬 Nota sobre `git rebase` y commits específicos
>
> Aunque normalmente el comando `git rebase` se aplica sobre el último commit de `main`, también es posible indicar **un commit concreto** sobre el que quieres reconstruir tu trabajo.
>
> ✅ Esto permite reorganizar una rama apoyándola en una versión anterior del proyecto.
> ✅ No siempre tienes que rebasar contra el `HEAD` de `main`.
>
> 👉 Si quieres entender **cómo funciona exactamente esta opción y cuándo usarla**, consulta:
> **[Rebase sobre un commit específico](3_git_practica4_opcion_rebase_commit_especifico.md)**

---

3. Git intentará aplicar los commits B y C después de D.

   Al aplicar el commit B, detectará que la **línea 2** también se modificó en `main` y mostrará un mensaje de conflicto similar a:

```text
Auto-merging notas.txt
CONFLICT (content): Merge conflict in notas.txt
error: could not apply 9341b26... B: Modificada la línea 2 con un nuevo texto de estilo
hint: Resolve all conflicts manually, mark them as resolved with
hint: "git add/rm <conflicted_files>", then run "git rebase --continue".
hint: You can instead skip this commit: run "git rebase --skip".
hint: To abort and get back to the state before "git rebase", run "git rebase --abort".
hint: Disable this message with "git config advice.mergeConflict false"
Could not apply 9341b26... B: Modificada la línea 2 con un nuevo texto de estilo
```

4. Comprueba el estado:

```bash
git status
```

Verás que `notas.txt` está en conflicto.

---

## 6. Resolver el conflicto en `notas.txt`

1. Abre `notas.txt`. Verás algo parecido a:

```text
Línea 1: Proyecto de prueba con rebase.
<<<<<<< HEAD
Línea 2: Esta es la versión principal del proyecto en desarrollo.
=======
Línea 2: Texto centrado en el estilo y la presentación.
>>>>>>> bbbbbbb (B: Modificada la línea 2 con un nuevo texto de estilo)
```

2. Elimina las marcas del conflicto (`<<<<<<<`, `=======`, `>>>>>>>`) y decide **qué versión de la línea 2 se queda**, o combina ambas.

Por ejemplo, puedes dejar una combinación:

```text
Línea 1: Proyecto de prueba con rebase.
Línea 2: Texto centrado en el estilo y la presentación.
```

3. Guarda el archivo.

4. Marca el conflicto como resuelto y continúa el rebase:

```bash
git add notas.txt
git rebase --continue
```

Si no hay más conflictos, el rebase terminará correctamente.

---

## 7. Ver el historial después del rebase

1. Vuelve a ejecutar el mismo comando que usaste antes para ver la historia de todas las ramas.

2. Ahora deberías ver algo parecido a:

```text
main:          A - D
                    \
estilo_texto:         B' - C'
```

(B’ y C’ son los commits reaplicados después de D.)

3. Haz otra captura de pantalla mostrando el nuevo historial tras resolver el conflicto.

---

## 8. Preguntas cortas de comprensión

Responde debajo de cada una:

1. ¿Desde qué rama ejecutaste el comando `git rebase main`? ¿Por qué desde esa y no desde `main`?
2. ¿Por qué se ha producido el conflicto en `notas.txt` al hacer el rebase?
3. ¿Qué habría pasado si hubieras borrado una de las versiones sin fijarte bien en el texto?
4. ¿En qué se diferencia este caso de un conflicto de `merge`? (a nivel práctico, en lo que tú has tenido que hacer).

---

## 🧑‍🏫 Solución orientativa (resumen de comandos)

```bash
# Preparación
mkdir git-rebase-conflicto
cd git-rebase-conflicto
git init

# Commit A
# (crear notas.txt con 2 líneas)
git add notas.txt
git commit -m "A: Versión inicial de notas.txt"

# Rama estilo_texto: commits B y C
git switch -c estilo_texto
# (modificar línea 2)
git add notas.txt
git commit -m "B: Modificada la línea 2 con nuevo texto de estilo"
# (añadir línea 3)
git add notas.txt
git commit -m "C: Añadida línea sobre mejoras visuales"

# Volver a main y commit D
git switch main
# (modificar línea 2 de otra forma)
git add notas.txt
git commit -m "D: Modificada la línea 2 en la rama principal"

# Ver historial
git log --oneline --graph --all

# Rebase con conflicto
git switch estilo_texto
git rebase main

# Resolver conflicto en notas.txt
# (editar, dejar versión combinada)
git add notas.txt
git rebase --continue

# Ver historial final
git log --oneline --graph --all
```

---
