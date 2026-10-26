
# 4. Gestión de ramas en Git

---

## 4.1 Concepto de rama

En Git, una **rama (branch)** es una **línea independiente de desarrollo** dentro del mismo proyecto.
Permite trabajar en nuevas funciones o pruebas sin afectar al código principal.

> 🧠 Piensa en las ramas como “versiones paralelas” del proyecto: puedes experimentar, hacer cambios y luego fusionarlos (merge) cuando estén listos.

Por defecto, todo repositorio tiene una primera rama principal:

* antiguamente llamada **`master`**,
* hoy en día denominada **`main`**.

---

## 4.2 ¿Por qué usar ramas?

Trabajar con ramas aporta ventajas fundamentales:

* **Aislar cambios:** cada rama puede representar una funcionalidad, corrección o versión diferente.
* **Evitar errores en el código estable:** la rama principal (`main`) se mantiene limpia y funcional.
* **Fomentar la colaboración:** varios desarrolladores pueden trabajar simultáneamente sin pisarse.
* **Facilitar revisiones y pruebas:** se pueden crear ramas de prueba o integración temporal.

---

## 4.3 Crear, listar y cambiar de rama

Git mantiene un puntero especial llamado `HEAD`, que indica **en qué rama estás trabajando** en ese momento.

### 🔹 Crear una nueva rama

```bash
git branch nueva_rama
```

Esto **solo crea la rama**, pero **no cambia** tu posición actual.

Para comprobar todas las ramas disponibles:

```bash
git branch
```

Salida típica:

```
* main
  nueva_rama
```

El asterisco (`*`) marca la rama activa.

---

## 4.4 Cambiar de rama

### 🔹 Opción 1: `git switch` (recomendada)

```bash
git switch nueva_rama
```

Git moverá el puntero `HEAD` a esa rama, cambiando el contenido del directorio de trabajo para reflejar los archivos de dicha rama.

> 💡 `switch` es el **comando moderno y más intuitivo** para moverse entre ramas.
> Fue introducido en versiones recientes de Git (≈ 2.23) para sustituir el uso de `checkout` en este contexto.

---

### 🔹 Opción 2: `git checkout` (antiguo, pero aún válido)

```bash
git checkout nueva_rama
```

`checkout` tiene un comportamiento más complejo: también puede usarse para cambiar de rama o para restaurar versiones de archivos antiguos.
Por ello, puede resultar confuso para principiantes.

> 🧠 En resumen:
>
> * Usa `git switch` para **moverte entre ramas**.
> * Usa `git restore` para **recuperar archivos antiguos**.
> * Evita `checkout` salvo en situaciones concretas o legadas.

---

## 4.5 Crear y cambiar de rama en un solo paso

Puedes crear y moverte a una nueva rama con un solo comando:

```bash
git switch -c nueva_rama
```

(`-c` significa *create*)

---

## 4.6 Fusionar ramas (`merge`)

Cuando termines de trabajar en una rama secundaria y quieras integrar los cambios en la principal (`main`), utiliza el comando `merge`.

### 🔹 Ejemplo paso a paso

```bash
# 1️⃣ Cambiar a la rama principal
git switch main

# 2️⃣ Fusionar los cambios de la rama secundaria
git merge nueva_rama
```

Git intentará combinar los commits de ambas ramas.

Si no hay conflictos, el resultado será:

```
Updating a1b2c3d..e4f5g6h
Fast-forward
 src/App.java | 5 ++++-
```

---

## 4.7 Tipos de fusión

* **Fast-forward merge:**
  Si no se han hecho nuevos commits en `main`, Git simplemente “avanza” el puntero para incluir los cambios.
  No crea un nuevo commit de fusión.

* **Merge con commit automático:**
  Si ambas ramas han cambiado desde la última fusión, Git crea un **nuevo commit de merge** para unir las historias.

---

## 4.8 Conflictos de fusión

Si un mismo archivo fue modificado en ambas ramas, Git no sabrá cuál conservar y pedirá intervención manual.

Ejemplo de conflicto:

```text
<<<<<<< HEAD
System.out.println("Hola mundo");
=======
System.out.println("Hola mundo!");
>>>>>>> nueva_rama
```

### 🔹 Resolución

1. Edita el archivo para dejar la versión correcta.
2. Elimina las marcas (`<<<<<<<`, `=======`, `>>>>>>>`).
3. Guarda y confirma:

```bash
git add archivo_conflictivo
git commit
```

---

## 4.9 Eliminar una rama

Una vez fusionados sus cambios, puedes eliminar la rama secundaria local con:

```bash
git branch -d nueva_rama
```

Si Git avisa de que la rama no está fusionada, y aun así deseas borrarla:

```bash
git branch -D nueva_rama
```

---

## 4.10 Eliminar una rama remota

Si la rama fue subida al repositorio remoto (por ejemplo, GitHub), puedes eliminarla allí con:

```bash
git push origin --delete nueva_rama
```

> 💡 Esta práctica es recomendable tras fusionar ramas en `main`, para mantener el repositorio remoto limpio.

---

## 4.11 Subir ramas al remoto (`push`)

Por defecto, cuando haces `git push`, solo se envía la **rama activa**.

Si quieres subir una nueva rama al remoto:

```bash
git push -u origin nueva_rama
```

Esto crea la rama en GitHub y la vincula a tu rama local (como `main`).

> A partir de ese momento podrás usar simplemente:
>
> ```bash
> git push
> git pull
> ```

---

## 4.12 Sincronización tras fusionar ramas

Cuando realizas una fusión (`merge`) en local, los cambios quedan confirmados **solo en tu repositorio local**.
Para que GitHub refleje esa fusión:

```bash
git push
```

De esta forma, la rama `main` del remoto quedará actualizada.

> 🧠 Si también quieres eliminar la rama secundaria en GitHub, deberás hacerlo manualmente o con `git push origin --delete`.

---

## 4.13 Visualizar ramas y fusiones

Para ver el historial de commits con su estructura de ramas, puedes usar:

```bash
git log --oneline --graph --all
```

Ejemplo de salida:

```text
* 7c9f3e2 (HEAD -> main) Merge branch 'login'
|\  
| * 5a2c7b4 (login) Añadido formulario de autenticación
* | e4d8a90 Creado menú principal
|/  
* 2a9b5f0 Estructura inicial del proyecto
```

---

## 4.14 Rebase: alternativa avanzada al merge

El comando `rebase` **reorganiza la base de una rama** para que sus commits parezcan haberse hecho a partir del último punto de otra rama.

```bash
git switch feature
git rebase main
```

Esto **no fusiona** las ramas, sino que “reubica” los commits de `feature` sobre los de `main`.

> ⚠️ Aunque útil, `rebase` debe usarse con precaución.
> Se recomienda únicamente en ramas **locales y personales**, no en trabajo colaborativo, ya que altera la historia del proyecto.

---

## 4.15 Resumen visual del flujo de ramas

```text
       main
        │
        │       (1) Creas una rama secundaria
        ├───► feature-login
        │
        │       (2) Trabajas y haces commits
        │       (3) Fusionas con main
        └───► git merge feature-login
                │
                ▼
            main actualizado
```

---

## 4.16 Chuleta de comandos de ramas

| Comando                           | Descripción                             | Cuándo se usa                             |
| --------------------------------- | --------------------------------------- | ----------------------------------------- |
| `git branch`                      | Lista las ramas existentes.             | Para ver todas las ramas del proyecto.    |
| `git branch nombre`               | Crea una nueva rama.                    | Para iniciar una nueva funcionalidad.     |
| `git switch nombre`               | Cambia de rama activa.                  | Al pasar de `main` a otra rama.           |
| `git switch -c nombre`            | Crea y cambia a la nueva rama.          | Para ahorrar pasos.                       |
| `git merge nombre`                | Fusiona la rama indicada con la actual. | Al integrar cambios en `main`.            |
| `git branch -d nombre`            | Elimina la rama local.                  | Después de fusionarla.                    |
| `git push -u origin nombre`       | Sube una nueva rama al remoto.          | Cuando deseas publicar una rama nueva.    |
| `git push origin --delete nombre` | Elimina la rama del remoto.             | Para limpiar GitHub tras el merge.        |
| `git log --oneline --graph --all` | Muestra el historial visual de ramas.   | Para analizar la estructura del proyecto. |
| `git rebase main`                 | Reorganiza una rama sobre otra.         | Uso avanzado; solo en ramas locales.      |

---

## 4.17 Buenas prácticas con ramas

* Crea una nueva rama para cada **funcionalidad o corrección**.
* Nombra las ramas de forma descriptiva (`feature-login`, `fix-bug-01`, `docs-readme`).
* Fusiona los cambios con `main` solo cuando el código compile y haya sido probado.
* Tras hacer `merge`, elimina la rama local y remota si ya no es necesaria.
* Antes de fusionar, realiza un `git pull` para asegurar que tu `main` está actualizado.
* Usa `switch` en lugar de `checkout` para evitar confusiones.

---

## 4.18 Ejemplo completo de flujo con ramas

```bash
# Crear el repositorio
git init
git add .
git commit -m "Versión inicial"

# Crear una nueva rama
git switch -c feature-login

# Realizar cambios y confirmarlos
git add .
git commit -m "Añadido formulario de login"

# Volver a la rama principal y fusionar
git switch main
git merge feature-login

# Eliminar la rama ya fusionada
git branch -d feature-login

# Subir los cambios a GitHub
git push
git push origin --delete feature-login
```

---

✅ **Conclusión**

Trabajar con ramas es la mejor forma de organizar el desarrollo de un proyecto.
Permite probar nuevas ideas sin riesgo, colaborar de manera eficiente y mantener una historia de cambios limpia y comprensible.

---
