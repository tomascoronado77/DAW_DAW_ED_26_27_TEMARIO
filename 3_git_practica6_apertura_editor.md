
## ¿Por qué se abre un editor de texto al hacer `git revert`?

Cuando ejecutas:

```bash
git revert <id_commit>
```

Git **no borra** la versión anterior del proyecto.

En su lugar:

1. Calcula qué cambios hizo ese commit
2. Crea automáticamente un nuevo commit con los cambios contrarios
3. Te pide que confirmes ese nuevo commit con un mensaje

Por ese motivo se abre una ventana del editor con un archivo llamado:

```
.git/COMMIT_EDITMSG
```

Esto NO es un error, es el funcionamiento normal de `git revert`.

---

## ¿Qué estás viendo en ese archivo?

El contenido suele ser parecido a:

```
Revert "C: Añadida la línea 3"

This reverts commit 5916f83...
```

Debajo aparecen líneas con `#` que son **comentarios informativos** y NO se guardan en el commit.

Este archivo sirve para escribir el mensaje del nuevo commit que Git va a crear.

---

## ¿Qué debes hacer tú?

👉 Escribir un mensaje corto explicando por qué estás deshaciendo ese commit.

Ejemplos válidos:

```
Revert "C: Añadida la línea 3"
Motivo: esa línea no debe aparecer en la versión final
```

o

```
Revert del commit C porque añadía información incorrecta
```

Después:

✅ Guardas el archivo
✅ Cierras el editor

Al hacer esto, Git terminará el revert y creará el nuevo commit.

---

## ¿Qué pasa si…?

### ❓ Borras todo el contenido y guardas *(o dejas solo comentarios, es decir, líneas con el símbolo `#`)*

Git abortará el revert y NO creará el commit.

### ❓ Dejas el mensaje por defecto *(guardar y cerrar el editor)*

Git usará ese mensaje por defecto que te propone y continuará normalmente.

---

## ¿Por qué Git te pide esto?

Porque `git revert` siempre:

* **Crea un nuevo commit**
* Registra en la historia del proyecto que se ha deshecho algo
* Necesita un mensaje que describa el motivo

Así el historial queda claro y cualquier persona *(o tú mismo más adelante)* podrá entender qué ha pasado.

---

## Idea clave

> `git revert` NO borra la historia.
> Crea un nuevo commit que deshace los cambios anteriores, y tú debes escribir el mensaje que lo explica.

