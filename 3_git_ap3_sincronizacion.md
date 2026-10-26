# 3. Sincronización con repos remotos

---

## 3.1 Concepto de repositorio remoto

Un **repositorio remoto** es una **copia del proyecto almacenada en un servidor externo**, accesible por Internet, que permite:

* Hacer copias de seguridad del código.
* Trabajar desde distintos dispositivos.
* Colaborar con otros desarrolladores.

Git permite sincronizar el repositorio local con uno remoto (por ejemplo, en **GitHub**, **GitLab** o **Bitbucket**) mediante comandos que envían o traen información.

> 💡 Git no sincroniza automáticamente: debes indicar explícitamente cuándo subir (`push`) o traer (`pull`) los cambios.

---

## 3.2 Repositorio local vs remoto

```text
┌────────────────────┐          ┌──────────────────────┐
│  Repositorio local │          │  Repositorio remoto  │
│ (Tu ordenador)     │  <---->  │ (GitHub / Servidor)  │
└────────────────────┘          └──────────────────────┘
       ↑    ↑                         ↑
       │    └── git pull ─────────────┘ (descargar cambios)
       └────── git push ─────────────>  (subir cambios)
```

---

## 3.3 Asociar un repositorio remoto

Cuando trabajas en local y quieres vincular tu proyecto a un repositorio en GitHub, debes asociarlo mediante un **nombre de remoto**.
El nombre más común es `origin`.

### 🔹 Añadir el remoto por primera vez

```bash
git remote add origin git@github.com:usuario/repositorio.git
```

O si usas HTTPS:

```bash
git remote add origin https://github.com/usuario/repositorio.git
```

Después puedes comprobarlo con:

```bash
git remote -v
```

Ejemplo de salida:

```
origin  git@github.com:usuario/repositorio.git (fetch)
origin  git@github.com:usuario/repositorio.git (push)
```

---

### 🔹 Cambiar la URL del remoto existente

Si ya tenías el repositorio configurado por HTTPS y quieres pasar a SSH (o viceversa), no debes crear un nuevo remoto, sino **actualizar la URL**:

```bash
git remote set-url origin git@github.com:usuario/repositorio.git
```

> 💡 `git remote add` crea el remoto.
> `git remote set-url` lo actualiza si ya existía.

---

## 3.4 Subir cambios al repositorio remoto (`git push`)

El comando `git push` **envía los commits del repositorio local al remoto**.

### 🔹 Primer envío

La primera vez que subes tu rama principal (por ejemplo, `main`), debes incluir la opción `-u` (abreviatura de `--set-upstream`) para vincularla:

```bash
git push -u origin main
```

Esto crea en GitHub la rama `main` y la asocia con tu rama local.

> 📌 A partir de este momento, puedes usar simplemente:
>
> ```bash
> git push
> ```
>
> y Git sabrá a qué remoto y a qué rama subir los cambios.

---

### 🔹 Subidas posteriores

Cada vez que confirmes nuevos commits en tu proyecto, podrás sincronizarlos con GitHub ejecutando:

```bash
git push
```

Git enviará únicamente los cambios pendientes desde tu última subida.

---

### 🔹 Ejemplo completo

```bash
# Confirmar cambios en local
git add .
git commit -m "Actualizada la clase Usuario"

# Subir al remoto
git push
```

---

## **3.5 Upstream: vinculación entre ramas locales y remotas**

Para que Git pueda sincronizar correctamente una rama, necesita saber **a qué rama remota debe enviar (push)** y **de dónde debe recibir (pull)** los cambios.

A esta relación se le llama **upstream** o **rama de seguimiento (tracking branch)**.

---

### 🔹 ¿Qué es el upstream?

> El *upstream* es la **rama remota asociada** a una rama local.
> Es el “destino por defecto” de `git push` y la “fuente por defecto” de `git pull`.

Ejemplo típico:

```
Rama local: main
Upstream:   origin/main
```

Esto significa:

* `git push` → sube automáticamente a `origin/main`
* `git pull` → trae automáticamente de `origin/main`

Sin upstream, Git no sabe dónde sincronizar.

---

### 🔹 ¿Cuándo se crea el upstream?

La primera vez que subes una rama al repositorio remoto.

Por eso el primer push se hace así:

```bash
git push -u origin nombre_rama
```

`-u` es exactamente igual que `--set-upstream`.

Esto configura:

```
nombre_rama (local)  ↔  origin/nombre_rama (remoto)
```

A partir de aquí, todo es mucho más fácil:

```bash
git push
git pull
```

Git ya recuerda la vinculación.

---

### 🔹 ¿Qué pasa si intento hacer `git push` sin upstream?

Git mostrará:

```
fatal: The current branch <rama> has no upstream branch.
```

Y te sugerirá exactamente el comando correcto:

```
git push --set-upstream origin <rama>
```

---

### 🔹 Comprobar el upstream actual

```bash
git status
```

Si la rama está asociada, verás algo como:

```
Your branch is up to date with 'origin/main'.
```

O:

```
Your branch is ahead of 'origin/factorial_recursivo' by 2 commits.
```

---

### 🔹 Resumen visual (muy útil para alumnos)

```text
          (Se crea con: git push -u origin rama)
┌────────────────┐            ┌──────────────────────────┐
│   rama local   │  <------>  │   rama remota (GitHub)   │
│   main         │            │   origin/main            │
└────────────────┘            └──────────────────────────┘
```

---

### 🔹 Resumen conceptual

| Concepto                | Explicación                                     |
| ----------------------- | ----------------------------------------------- |
| Upstream                | Relación entre una rama local y su rama remota  |
| `git push -u`           | Crea la rama remota y establece el upstream     |
| `git push` / `git pull` | Funciona automáticamente después del upstream   |
| Error típico            | “has no upstream branch” → falta la vinculación |

---

## 3.6 Descargar y fusionar cambios (`git pull`)

El comando `git pull` hace el proceso inverso a `push`:
**descarga los commits del remoto** y los **fusiona** con tu rama local.

```bash
git pull
```

Si tu rama está vinculada (gracias al `-u` anterior), no necesitas especificar nada más.

Internamente, `git pull` realiza dos pasos:

1. `git fetch` → descarga los cambios del remoto, sin aplicarlos.
2. `git merge` → fusiona esos cambios en tu rama actual.

Por tanto:

```bash
git pull = git fetch + git merge
```

---

### 🔹 Cuando hay conflictos

Si otro usuario ha modificado el mismo archivo que tú y ambos intentáis subir los cambios, Git te pedirá **resolver los conflictos** antes de hacer `push`.

Verás algo como:

```
Auto-merging src/Main.java
CONFLICT (content): Merge conflict in src/Main.java
```

Para solucionarlo:

1. Abre el archivo y busca las marcas de conflicto (`<<<<<<<`, `=======`, `>>>>>>>`).
2. Elimina las marcas y deja la versión correcta.
3. Guarda el archivo y confirma con:

```bash
git add archivo_conflictivo
git commit
```

Después podrás volver a subirlo con:

```bash
git push
```

---

## 3.7 Descargar sin fusionar (`git fetch`)

`git fetch` **solo descarga** los cambios del remoto, pero **no los mezcla aún** con tu rama local.
Esto permite revisar los cambios antes de integrarlos.

```bash
git fetch
git log origin/main --oneline
```

Si todo está correcto, puedes decidir fusionarlos manualmente con:

```bash
git merge origin/main
```

> 🧠 `fetch` es ideal cuando trabajas en proyectos colaborativos y quieres inspeccionar primero qué ha cambiado antes de mezclarlo.

---

## 3.8 Clonar un repositorio existente (`git clone`)

Cuando el proyecto ya está en GitHub y quieres tener una copia local, se utiliza `git clone`.

```bash
git clone git@github.com:usuario/repositorio.git
```

Este comando:

1. Descarga todo el historial del repositorio.
2. Crea una carpeta local con el mismo nombre.
3. Configura automáticamente el remoto `origin`.

Ejemplo:

```bash
git clone https://github.com/dcsibon/ejemplo.git
cd ejemplo
git remote -v
```

Salida:

```
origin  https://github.com/dcsibon/ejemplo.git (fetch)
origin  https://github.com/dcsibon/ejemplo.git (push)
```

---

## 3.9 Crear un repositorio en GitHub y vincularlo desde local

Cuando el proyecto se inicia en tu ordenador y quieres subirlo por primera vez:

```bash
git init
git add .
git commit -m "Versión inicial"
```

Luego, crea **un repositorio vacío** en GitHub (sin README, .gitignore, ni licencia) y enlázalo:

```bash
git remote add origin git@github.com:usuario/repositorio.git
git branch -M main
git push -u origin main
```

Después podrás seguir usando simplemente:

```bash
git push
git pull
```

---

## 3.10 Crear una copia de otro proyecto público (`fork`)

Un **fork** (bifurcación) es una **copia de un repositorio remoto** dentro de tu propia cuenta de GitHub.
Se usa cuando quieres modificar un proyecto sin afectar al original.

1. En GitHub, pulsa el botón **Fork** en el repositorio público.
2. GitHub creará una copia bajo tu usuario.
3. Clónala con `git clone` y trabaja libremente en tu versión.

> 💡 Ideal para proyectos open source o trabajos en grupo donde cada alumno trabaja su copia.

---

## 3.11 Comandos esenciales de sincronización

| Comando                           | Descripción                                     | Cuándo se usa                             |
| --------------------------------- | ----------------------------------------------- | ----------------------------------------- |
| `git remote -v`                   | Muestra los repositorios remotos configurados.  | Para verificar la conexión con GitHub.    |
| `git remote add origin <url>`     | Asocia un remoto por primera vez.               | Al conectar el proyecto local con GitHub. |
| `git remote set-url origin <url>` | Actualiza la URL del remoto existente.          | Al cambiar de HTTPS a SSH.                |
| `git push -u origin main`         | Sube la rama principal y la deja vinculada.     | Primer envío.                             |
| `git push`                        | Sube los commits de la rama activa.             | Envíos posteriores.                       |
| `git pull`                        | Descarga y fusiona los cambios del remoto.      | Para actualizar el proyecto local.        |
| `git fetch`                       | Descarga los cambios sin fusionarlos.           | Para revisar antes de integrar.           |
| `git clone <url>`                 | Crea una copia local de un repo remoto.         | Para empezar desde un proyecto existente. |
| `git fork` (en GitHub)            | Crea una copia personal del repositorio remoto. | Para modificar sin alterar el original.   |

---

## 3.12 Buenas prácticas de sincronización

* Antes de hacer `push`, realiza siempre un `git pull` para asegurar que trabajas sobre la última versión.
* Evita hacer `push` directamente sobre ramas que usan otros compañeros: crea ramas secundarias y usa `merge` o `pull request`.
* Comprueba el remoto actual con `git remote -v` antes de subir o descargar cambios.
* Si trabajas desde distintos ordenadores, asegúrate de **subir siempre los cambios antes de cerrar la sesión** y hacer `pull` al comenzar en el otro equipo.
* No modifiques directamente archivos desde la web de GitHub si el proyecto está activo en local, salvo que entiendas las consecuencias del conflicto que puede generar.

---
