
# 6. Hojas resumen del tema (Chuletas de Git + GitHub)

---

## 6.1 Estructura general de trabajo con Git

```text
┌────────────────────────────────────────────┐
│      PROYECTO LOCAL EN TU ORDENADOR        │
│                                            │
│   [1] Área de trabajo (Working Directory)  │
│   [2] Área de preparación (Staging Area)   │
│   [3] Repositorio local (Commits)          │
│                                            │
│        ↓ Push / ↑ Pull (sincroniza)        │
└────────────────────────────────────────────┘
                     │
                     ▼
┌────────────────────────────────────────────┐
│          REPOSITORIO REMOTO (GitHub)       │
│   Copia del proyecto accesible en la nube  │
└────────────────────────────────────────────┘
```

---

## 6.2 Flujo de trabajo básico

```bash
# 1️⃣ Crear el repositorio local
git init

# 2️⃣ Ver estado de archivos
git status

# 3️⃣ Preparar cambios
git add .

# 4️⃣ Confirmar versión (commit)
git commit -m "Mensaje descriptivo"

# 5️⃣ Ver historial
git log --oneline

# 6️⃣ Vincular con GitHub (una sola vez)
git remote add origin git@github.com:usuario/repositorio.git

# 7️⃣ Subir cambios
git push -u origin main

# 8️⃣ Descargar actualizaciones
git pull
```

---

## 6.3 Las tres áreas de trabajo en resumen

| Área                   | Nombre técnico         | Qué contiene                  | Comandos principales                    |
| ---------------------- | ---------------------- | ----------------------------- | --------------------------------------- |
| 🧑‍💻 Área de trabajo  | *Working Directory*    | Archivos en edición           | `git status`, `git diff`, `git restore` |
| 📦 Área de preparación | *Staging Area / Index* | Cambios listos para confirmar | `git add`, `git restore --staged`       |
| 🏛️ Repositorio local  | *Local Repository*     | Commits confirmados           | `git commit`, `git log`, `git show`     |

---

## 6.4 Sincronización con repos remotos

| Comando                           | Descripción                                    | Cuándo se usa                      |
| --------------------------------- | ---------------------------------------------- | ---------------------------------- |
| `git remote -v`                   | Muestra los repositorios remotos configurados. | Verificar conexión.                |
| `git remote add origin <url>`     | Añade un remoto nuevo.                         | Primer enlace con GitHub.          |
| `git remote set-url origin <url>` | Cambia la URL del remoto existente.            | Al pasar de HTTPS a SSH.           |
| `git push -u origin main`         | Sube la rama principal y la vincula.           | Primer envío.                      |
| `git push`                        | Sube los commits de la rama activa.            | Envíos posteriores.                |
| `git pull`                        | Descarga y fusiona los cambios remotos.        | Para actualizar el proyecto local. |
| `git fetch`                       | Descarga los cambios sin aplicarlos.           | Revisar antes de fusionar.         |
| `git clone <url>`                 | Clona un repositorio remoto.                   | Para obtener una copia inicial.    |
| `git push origin --delete <rama>` | Elimina una rama remota.                       | Limpieza tras merges.              |

---

## 6.5 Ramas: creación, cambio y fusión

| Comando                           | Descripción                          | Ejemplo                          |
| --------------------------------- | ------------------------------------ | -------------------------------- |
| `git branch`                      | Lista todas las ramas locales.       | `git branch`                     |
| `git switch -c <nombre>`          | Crea y cambia a una nueva rama.      | `git switch -c login`            |
| `git switch <nombre>`             | Cambia de rama.                      | `git switch main`                |
| `git merge <rama>`                | Fusiona otra rama en la actual.      | `git merge login`                |
| `git branch -d <rama>`            | Elimina una rama local ya fusionada. | `git branch -d login`            |
| `git push -u origin <rama>`       | Sube una nueva rama al remoto.       | `git push -u origin login`       |
| `git push origin --delete <rama>` | Elimina la rama del remoto.          | `git push origin --delete login` |

---

### 🧭 Esquema visual de ramas y fusiones

```text
main
 │
 ├───► feature-login      (rama secundaria)
 │         │
 │         └─── commits de desarrollo
 │
 └───► git merge feature-login
           │
           ▼
        main actualizado
```

---

## 6.6 Deshacer cambios y gestión del historial

| Situación                                         | Comando                          | Qué hace                                       |
| ------------------------------------------------- | -------------------------------- | ---------------------------------------------- |
| Deshacer cambios sin añadir                       | `git restore <archivo>`          | Revierte el archivo a su versión anterior.     |
| Quitar un archivo del staging area                | `git restore --staged <archivo>` | Lo saca del área de preparación.               |
| Ver historial resumido                            | `git log --oneline`              | Muestra commits breves.                        |
| Ver diferencias                                   | `git diff`                       | Muestra líneas modificadas.                    |
| Ver un commit concreto                            | `git show <id>`                  | Detalla los cambios de un commit.              |
| Deshacer un commit (sin borrar historia)          | `git revert <id>`                | Crea un nuevo commit que anula el anterior.    |
| Volver a una versión anterior eliminando historia | `git reset --hard <id>`          | ⚠️ Borra commits posteriores.                  |
| Revisar commits borrados                          | `git reflog`                     | Permite recuperar versiones recientes.         |
| Moverse temporalmente a otro commit               | `git checkout <id>`              | Cambia al estado de ese commit (modo lectura). |
| Reorganizar una rama (avanzado)                   | `git rebase main`                | Reubica commits sobre otra base.               |

---

### 🔍 Diferencias clave entre revert, reset y rebase

| Comando      | Qué hace                                 | Modifica el historial | Uso recomendado                               |
| ------------ | ---------------------------------------- | --------------------- | --------------------------------------------- |
| `git revert` | Crea un nuevo commit que revierte otro.  | ❌ No                  | Deshacer de forma segura tras un push.        |
| `git reset`  | Elimina commits posteriores al indicado. | ✅ Sí                  | Deshacer commits locales antes de subir.      |
| `git rebase` | Reorganiza commits sobre otro punto.     | ✅ Sí                  | Mantener historia lineal en ramas personales. |

---

## 6.7 Ejemplo completo del flujo de trabajo

```bash
# Crear repositorio
git init

# Configurar usuario (solo la primera vez)
git config --global user.name "Tu Nombre"
git config --global user.email "tu_usuario@g.educaand.es"

# Añadir archivos y confirmar versión
git add .
git commit -m "Versión inicial"

# Crear una rama y trabajar en ella
git switch -c feature-login
git add .
git commit -m "Formulario de login"

# Volver a main y fusionar
git switch main
git merge feature-login
git branch -d feature-login

# Subir los cambios a GitHub
git remote add origin git@github.com:usuario/proyecto.git
git push -u origin main
```

---

## 6.8 Chuleta rápida de conexiones y autenticación

| Método                | Puerto   | Autenticación          | Ventajas                             | Limitaciones                         |
| --------------------- | -------- | ---------------------- | ------------------------------------ | ------------------------------------ |
| **HTTPS + Token**     | 443      | Usuario + Token        | Universal, fácil de usar             | Hay que renovar el token.            |
| **SSH (22)**          | 22       | Claves pública/privada | Segura, sin contraseñas              | Puede estar bloqueado en redes.      |
| **SSH (443)**         | 443      | Claves pública/privada | Evita bloqueos (Andared)             | Requiere configurar `~/.ssh/config`. |
| **GitHub CLI (`gh`)** | 443      | Token o SSH            | Crea y gestiona repos desde terminal | Requiere instalar `gh`.              |
| **EGit (Eclipse)**    | 443 / 22 | Según configuración    | Interfaz visual cómoda               | Menos control técnico.               |

---

## 6.9 Buenas prácticas generales

✅ **Antes de cada commit:**

* Comprueba el estado con `git status`.
* Añade solo los archivos necesarios.
* Usa mensajes descriptivos en presente:
  `git commit -m "Agrega validación de usuario"`

✅ **Durante el trabajo diario:**

* Realiza commits frecuentes y funcionales.
* Usa `.gitignore` para excluir binarios, logs, etc.
* Crea ramas para nuevas funcionalidades (`feature-*`).
* Fusiona solo cuando el código compile y funcione.
* Realiza `git pull` antes de cada `git push`.

✅ **Para trabajar desde distintos equipos:**

* Sube los cambios antes de cerrar sesión (`push`).
* Descarga los últimos cambios al empezar (`pull`).
* Si la red bloquea el puerto 22, usa SSH por 443.

✅ **Para evitar errores comunes:**

* No uses `reset --hard` en ramas compartidas.
* Prefiere `revert` si ya has hecho push.
* Usa `switch` en lugar de `checkout` para moverte entre ramas.
* Borra ramas innecesarias tras fusionar.

---

## 6.10 Esquema general de todo el flujo Git + GitHub

```text
(1) Crear proyecto local
      ↓
  git init
      ↓
  git add .
      ↓
  git commit -m "Inicial"
      ↓
(2) Conectar con remoto
      ↓
  git remote add origin git@github.com:usuario/repositorio.git
      ↓
(3) Subir por primera vez
      ↓
  git push -u origin main
      ↓
(4) Trabajo diario
      ↓
  git add .
  git commit -m "Nueva función"
  git push
      ↓
(5) Si hay cambios remotos
      ↓
  git pull
      ↓
(6) Crear rama para nueva función
      ↓
  git switch -c feature-login
      ↓
  git add . / git commit / git push
      ↓
(7) Fusionar en main y eliminar rama
      ↓
  git switch main
  git merge feature-login
  git branch -d feature-login
  git push origin --delete feature-login
```

---

## 6.11 Recomendación final

> 🧩 **Git no es solo un sistema de guardado, sino un registro completo de la evolución de tu proyecto.**
>
> Dominar sus comandos y entender su flujo te permite trabajar con libertad, colaborar con otros sin miedo a perder nada y mantener un código ordenado, profesional y trazable.

---
