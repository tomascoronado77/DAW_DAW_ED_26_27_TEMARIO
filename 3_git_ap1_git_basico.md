
# 1. Git básico (trabajo en local)

---

## 1.1 ¿Qué es Git?

**Git** es un sistema de **control de versiones distribuido**, diseñado para registrar los cambios en los archivos de un proyecto a lo largo del tiempo.
Permite que varios desarrolladores trabajen sobre el mismo código **sin sobrescribir su trabajo**, guardando versiones seguras de cada modificación.

A diferencia de otros sistemas más antiguos, Git no guarda archivos completos en cada versión, sino **instantáneas (snapshots)** de los cambios, lo que lo hace extremadamente rápido y eficiente.

> 🧠 En pocas palabras: Git es como una “máquina del tiempo” para el código, que permite guardar, comparar y restaurar versiones de un proyecto.

---

## 1.2 Instalación y configuración inicial

Antes de usar Git, es necesario verificar que está instalado y configurado correctamente.

### 🔹 Verificar instalación

```bash
git --version
```

Si no aparece un número de versión, instala Git desde:

* **Windows:** [git-scm.com/download/win](https://git-scm.com/download/win)
* **macOS:** `brew install git`
* **Linux (Debian/Ubuntu):** `sudo apt install git`

---

### 🔹 Configuración básica de usuario

Estos datos se asocian a cada *commit* que realices:

```bash
git config --global user.name "Tu Nombre"
git config --global user.email "tu_usuario@g.educaand.es"
```

Puedes comprobar tus ajustes en cualquier momento con:

```bash
git config --list
```

---

## 1.3 Iniciar un repositorio local

Un **repositorio Git** es una carpeta del proyecto que contiene un subdirectorio oculto `.git`, donde se almacena toda la información del control de versiones (historial, configuración, ramas, etc.).

Para crear un nuevo repositorio:

```bash
cd ruta/del/proyecto
git init
```

Esto inicializa el control de versiones en esa carpeta.
A partir de este momento, Git comenzará a **vigilar** los cambios en los archivos.

---

## 1.4 Las tres áreas de trabajo en Git

Git organiza y gestiona los archivos del proyecto en **tres áreas o zonas principales**.
Comprender su función es esencial para entender cómo se guardan los cambios y cómo se construyen las versiones.

---

### 🧑‍💻 1️⃣ Área de trabajo (*Working Directory*)

**Qué es:**
Es la **carpeta del proyecto en tu equipo** donde editas, creas y eliminas archivos libremente.
Contiene la versión actual del proyecto que estás desarrollando.

**Qué hace Git aquí:**
Git **detecta los archivos modificados, nuevos o eliminados**, pero **no los guarda automáticamente en ninguna versión**.
Todo lo que hagas en esta área es “temporal” hasta que decidas preparar o confirmar los cambios.

**Comandos relacionados:**

```bash
git status         # Ver qué archivos han cambiado
git diff           # Ver diferencias entre versiones
```

**Ejemplo de estado:**

```
modified: main.py
untracked: utils.py
```

👉 Los archivos aquí **aún no están listos para ser versionados**.

---

### 📦 2️⃣ Área de preparación (*Staging Area* o *Index*)

**Qué es:**
Es una **zona intermedia** donde se guardan los archivos que deseas incluir en el **próximo commit**.
Funciona como una “lista de espera” o un “carrito de la compra” de los cambios que quieres confirmar.

**Qué hace Git aquí:**
Git **almacena una copia exacta de los archivos** tal como estaban cuando hiciste `git add`.
Si modificas un archivo después, **debes volver a hacer `git add`** para incluir esos nuevos cambios.

**Comandos relacionados:**

```bash
git add archivo.py     # Añade un archivo al área de preparación
git add .              # Añade todos los cambios
git restore --staged archivo.py  # Quita un archivo del área de preparación
```

**Ejemplo:**

```
Changes to be committed:
  new file:   main.py
  modified:   menu.py
```

👉 En esta área se decide **qué formará parte del próximo commit**.

---

### 🏛️ 3️⃣ Repositorio local (*Local Repository*)

**Qué es:**
Es la **base de datos interna de Git** donde se almacenan todos los **commits confirmados** (versiones del proyecto).
Cada commit representa una **fotografía exacta** del proyecto en un momento determinado.

**Qué hace Git aquí:**
Git guarda la historia completa del proyecto:
quién hizo cada cambio, cuándo, y en qué archivos.
Permite **consultar versiones anteriores**, **volver atrás**, o **fusionar cambios** entre ramas.

**Comandos relacionados:**

```bash
git commit -m "Mensaje descriptivo"  # Crea un nuevo commit
git log --oneline                    # Muestra el historial
git show <id_commit>                 # Detalla un commit concreto
```

**Ejemplo:**

```
f3d2a1e (HEAD -> main) Añadido módulo de login
a7c2d55 Creada estructura inicial del proyecto
```

👉 Aquí ya tienes **versiones seguras y permanentes** de tu proyecto.

---

### 🔁 Esquema visual del flujo entre áreas

```text
┌──────────────────────┐
│   Área de trabajo    │
│  (Working Directory) │
│                      │
│ Editas archivos aquí │
└──────────┬───────────┘
           │ git add
           ▼
┌──────────────────────┐
│  Área de preparación │
│   (Staging Area)     │
│                      │
│ Archivos listos para │
│ el próximo commit    │
└──────────┬───────────┘
           │ git commit
           ▼
┌──────────────────────┐
│ Repositorio local    │
│  (Local Repository)  │
│                      │
│ Historial de commits │
│ con versiones seguras│
└──────────────────────┘
```

---

### 🧠 En resumen

| Área                   | Nombre técnico         | Qué contiene                    | Comandos principales                |
| ---------------------- | ---------------------- | ------------------------------- | ----------------------------------- |
| 🧑‍💻 Área de trabajo  | *Working Directory*    | Archivos que editas             | `git status`, `git diff`            |
| 📦 Área de preparación | *Staging Area / Index* | Cambios listos para confirmar   | `git add`, `git restore --staged`   |
| 🏛️ Repositorio local  | *Local Repository*     | Versiones confirmadas (commits) | `git commit`, `git log`, `git show` |

---

### 💡 Ejemplo típico de flujo completo

```bash
# 1️⃣ Modifico archivos
# (aún están en el área de trabajo)
git status

# 2️⃣ Los preparo para el commit
git add .

# 3️⃣ Confirmo la versión
git commit -m "Añadido sistema de login"

# 4️⃣ Verifico que el commit se guardó
git log --oneline
```

---

## 1.5 Ignorar archivos innecesarios (.gitignore)

En muchos proyectos hay archivos que **no deben versionarse**, como binarios, temporales o configuraciones locales.

Para ello se utiliza un archivo especial llamado **`.gitignore`**, donde se listan los patrones que Git debe ignorar.

Ejemplo:

```
# Archivos de Eclipse
.classpath
.project
.settings/

# Archivos temporales
*.log
*.tmp

# Carpeta de compilación
/bin/
```

> 🧠 El `.gitignore` se guarda también en el repositorio, para que todos los colaboradores usen las mismas reglas.

---

## 1.6 Comandos esenciales del trabajo en local

| Comando                          | Descripción                                                            | Cuándo se usa                           |
| -------------------------------- | ---------------------------------------------------------------------- | --------------------------------------- |
| `git init`                       | Inicia un nuevo repositorio local.                                     | Al comenzar un proyecto desde cero.     |
| `git status`                     | Muestra el estado actual (archivos modificados, añadidos, pendientes). | Antes de cada commit.                   |
| `git add <archivo>`              | Mueve un archivo al área de preparación.                               | Antes de confirmar los cambios.         |
| `git commit -m "mensaje"`        | Crea un nuevo commit con los cambios preparados.                       | Para guardar una versión del proyecto.  |
| `git log --oneline`              | Muestra el historial resumido de commits.                              | Para revisar la evolución del proyecto. |
| `git diff`                       | Compara los cambios entre versiones o entre áreas.                     | Para ver qué ha cambiado exactamente.   |
| `git restore --staged <archivo>` | Quita un archivo del área de preparación.                              | Si lo añadiste por error con `git add`. |

---

## 1.7 Buenas prácticas

* Realiza **commits frecuentes** con mensajes claros y descriptivos.
  Evita mensajes genéricos como “update” o “cambios varios”. Cada commit debe explicar **qué has hecho** y **por qué**.

* No confirmes código que **no compila o no funciona**, a menos que necesites **guardar tu progreso temporalmente** para continuar más tarde desde otro ordenador.
  En ese caso, puedes hacer un **commit provisional** con un mensaje claro, como
  `git commit -m "Versión intermedia: pendiente de prueba final"`
  o usar `git stash` si solo deseas guardar los cambios localmente sin subirlos.

* Agrupa los commits por **funcionalidad o mejora concreta**, no por archivo.
  Ejemplo:
  ✅ “Añadido módulo de autenticación”
  ❌ “Modificado main.java y utils.java”

* Mantén limpio tu repositorio con un **archivo `.gitignore`** para excluir binarios, carpetas temporales o configuraciones locales.

* Comprueba siempre el estado del proyecto antes de confirmar:

  ```bash
  git status
  ```

  Este comando debe convertirse en tu rutina habitual antes de cada commit.


¿Quieres que te prepare ahora el **apartado 2: “Conexión con GitHub”**, adaptado al mismo formato (con introducción, pasos, ejemplos y tablas), o prefieres que primero añadamos un pequeño bloque de práctica guiada al final de este apartado 1 (para que practiquen los comandos básicos en local)?
