# Práctica 3: Ramas y GitHub (Java + Eclipse, **Git por terminal**)

> **Objetivo:** trabajar con **ramas** de Git, resolver conflictos y enlazar con **GitHub** usando **SSH**. La **programación** se hace en **Eclipse**; **todo Git** se gestiona **desde la terminal** (Git Bash).

---

## 0) Requisitos previos

* Git instalado y configurado (`user.name`, `user.email`).
* Clave SSH operativa con GitHub (ver Práctica 3). Comprobar:

  ```bash
  ssh -T git@github.com
  ```
* Eclipse instalado.

---

## 🔐 Conectar Git local ↔ GitHub por **HTTPS** con **token (PAT)**

> Alternativa a SSH (ya vista en la Práctica 2). Con **HTTPS + PAT** no necesitas claves SSH: Git usará un **token temporal** como contraseña.

### a) Crear un **Personal Access Token** (expirable)

1. En GitHub: **avatar → Settings → Developer settings → Personal access tokens**.
2. Elige **Fine-grained tokens** *(recomendado)* o **Tokens (classic)**.
3. **Expiration**: selecciona una duración corta (p. ej., **7 o 30 días**).
4. **Permisos mínimos**:

   * Fine-grained: selecciona tu repo y activa **Repository permissions → Contents: Read and write**.
   * Classic: marca **repo**.
5. Crea el token y **cópialo** (solo se muestra una vez).

> ⚠️ El token **sustituye a tu contraseña** de GitHub cuando uses HTTPS.

### b) Configurar el remoto por **HTTPS**

* Si tu remoto aún no existe:

  ```bash
  git remote add origin https://github.com/tu_usuario/RamasGitJavaDAM.git
  ```
* Si ya lo tenías en SSH y quieres cambiar a HTTPS:

  ```bash
  git remote set-url origin https://github.com/tu_usuario/RamasGitJavaDAM.git
  ```

### c) Primer **push** usando el token

```bash
git branch -M main
git push -u origin main
```

Cuando pregunte **Username** → tu usuario GitHub.
Cuando pregunte **Password** → **pega el token** (PAT).

> 💾 **Guardar credenciales** (opcional):
>
> * **Windows**: `git config --global credential.helper manager`
> * **macOS**: `git config --global credential.helper osxkeychain`
> * **Linux**: `git config --global credential.helper store` *(guarda en texto plano)*

---

## 1) Crear repositorio remoto y clonar en el *workspace*

1. Crea en GitHub un repo **vacío** llamado `RamasGitJavaDAM` (sin README).
2. Copia la **URL SSH**:

   ```
   git@github.com:tu_usuario/RamasGitJavaDAM.git
   ```

3. Abre terminal en tu *workspace* de Eclipse y clona:

   ```bash
   cd "C:\\Users\\alumno\\eclipse-workspace"
   git clone git@github.com:tu_usuario/RamasGitJavaDAM.git
   cd RamasGitJavaDAM
   ```

### ⚠️ Importante: Clonar repositorios vacíos en GitHub

Cuando se crea un repositorio en GitHub **sin ningún archivo**
(sin README, sin `.gitignore`, sin licencia…), ese repositorio:

* ✅ existe en GitHub
* ❌ pero NO contiene ningún commit

Git necesita que exista **al menos un commit inicial** para poder clonar un repositorio.

### ¿Qué ocurre si se intenta clonar un repositorio vacío?

Al ejecutar:

```bash
git clone git@github.com:tu_usuario/RamasGitJavaDAM.git
```

Git puede mostrar mensajes como:

```
fatal: repository 'git@github.com:tu_usuario/RamasGitJavaDAM.git' does not contain any commits
```

o

```
warning: You appear to have cloned an empty repository.
```

Esto significa que GitHub todavía no tiene ninguna versión del proyecto y, por tanto, no puede generar una copia local.

### Formas correctas de trabajar

Existen dos métodos válidos para evitar este problema:

#### ✅ Opción 1: Crear el repositorio en GitHub con README.md (más sencilla)

1. Crear el repositorio en GitHub
2. Activar la opción **"Add README.md"**
3. Clonar normalmente:

```bash
git clone git@github.com:tu_usuario/RamasGitJavaDAM.git
```

Esta opción funciona porque GitHub ya contiene:

* un primer archivo
* un commit inicial
* la rama `main`

#### ✅ Opción 2: Repositorio vacío en GitHub + inicialización en local (más profesional)

Este método es el más similar al flujo de trabajo real en desarrollo:
primero se crea el proyecto en local y después se sincroniza con GitHub.

1. Crear un repositorio vacío en GitHub
   *(sin README, sin `.gitignore`, sin licencia)*

2. Crear un **proyecto en Eclipse** dentro del *workspace*
   Por ejemplo, un proyecto Java llamado `RamasGitJavaDAM` cómo se comenta en el siguiente apartado, a partir del punto 4 [Crear la clase y el código inicial](#crear_clase)

   > Eclipse generará la carpeta del proyecto en tu ordenador

3. Abrir una terminal dentro de la carpeta del proyecto

   Ejemplo en Windows:

   ```bash
   cd "C:\\Users\\alumno\\eclipse-workspace\\RamasGitJavaDAM"
   ```

4. Inicializar Git en el proyecto:

   ```bash
   git init
   ```

5. Confirmar la primera versión:

   ```bash
   git add .
   git commit -m "Commit inicial"
   ```

6. Vincular el proyecto local con el repositorio remoto de GitHub:

   ```bash
   git remote add origin git@github.com:tu_usuario/RamasGitJavaDAM.git
   ```

7. Subir la primera versión:

   ```bash
   git branch -M main
   git push -u origin main
   ```
---

## 2) Crear el proyecto **Java** dentro de la carpeta clonada *(si optas por la opción 1 importante hacer bien el punto 2)*

1. Eclipse → **File → New → Java Project**.
2. **Desmarca** *Use default location* y en **Location** elige la carpeta clonada `RamasGitJavaDAM`.
3. Nombre del proyecto: `RamasGitJavaDAM` → **Finish**.
4. Crea la clase: *File → New → Class* <a id="crear_clase"></a>

   * Package: `edu.alumno.ramas`
   * Name: `Main`
   * Marca *public static void main(String[] args)*.

5. Código inicial:

   ```java
   package edu.alumno.ramas;

   import java.util.Random;
   import java.util.HashSet;

   public class Main {
       public static void main(String[] args) {
           // Lotería Primitiva: 5 números principales (1..49) + 1 complementario
           HashSet<Integer> bolas = new HashSet<>();
           Random r = new Random();
           while (bolas.size() < 5) {
               bolas.add(r.nextInt(49) + 1);
           }
           System.out.println("Números principales: " + bolas);
           int complementario = r.nextInt(49) + 1;
           System.out.println("Complementario: " + complementario);
       }
   }
   ```
6. Ejecuta en Eclipse (*Run As → Java Application*).

---

## 3) Poner bajo control de versiones y primer *commit* (solo terminal)

Desde la carpeta del repo (terminal):

```bash
echo "/bin/" > .gitignore

git add .
git commit -m "Estructura Eclipse + Main con Lotería (inicial)"
git branch -M main
git push -u origin main
```

---

## 4) Ramas: crear, cambiar y trabajar en paralelo

> Usaremos `git switch` (más claro que `checkout`).

1. Crear una rama de desarrollo y cambiarte a ella:

   ```bash
   git switch -c desarrollo/ej1
   ```
2. En Eclipse, modifica `Main` para **mostrar también la suma de las 5 bolas**. Por ejemplo:

   ```java
   int suma = bolas.stream().mapToInt(Integer::intValue).sum();
   System.out.println("Suma de principales: " + suma);
   ```
3. Guardar y *commit* en la rama:

   ```bash
   git add src/edu/alumno/ramas/Main.java
   git commit -m "Suma de números principales"
   ```
4. Cambiar a `main` y comprobar que **ahí no está** la suma:

   ```bash
   git switch main
   ```

---

## 5) Fusionar (merge) en `main`

1. Estando en `main`, fusiona la rama de trabajo:

   ```bash
   git merge desarrollo/ej1
   ```
2. Si no hay conflictos, sube:

   ```bash
   git push
   ```

---

## 6) Forzar un **conflicto** y resolverlo

1. Crea **otra rama** y cámbiate:

   ```bash
   git switch -c feature/mensaje
   ```
2. En Eclipse, cambia la línea de salida principal, por ejemplo:

   ```java
   System.out.println("¡Suerte en el sorteo, DAM!");
   ```

   *Commit*:

   ```bash
   git add src/edu/alumno/ramas/Main.java
   git commit -m "Mensaje en consola (rama feature/mensaje)"
   ```
3. Vuelve a `main` y edita **la misma línea** con otro texto distinto:

   * Edita en Eclipse la misma `println` con otro mensaje.
   * *Commit* en `main`:

   ```bash
   git add src/edu/alumno/ramas/Main.java
   git commit -m "Mensaje alternativo en main"
   ```
4. Intenta fusionar `feature/mensaje` en `main`:

   ```bash
   git merge feature/mensaje
   ```

   Verás marcas de conflicto en el fichero:

   ```
   <<<<<<< HEAD
   (versión en main)
   =======
   (versión en feature/mensaje)
   >>>>>>> feature/mensaje
   ```
5. Abre el archivo en Eclipse, **resuelve** dejando la versión correcta (o combinada), **elimina las marcas** y guarda.
6. Finaliza el merge desde terminal:

   ```bash
   git add src/edu/alumno/ramas/Main.java
   git commit -m "Merge: resuelto conflicto en Main.java"
   git push
   ```

---

## 7) `stash`: aparcar cambios sin *commit*

Cuando necesites cambiar de rama pero tienes cambios a medias:

```bash
git stash save "WIP: refactor salida"
# Cambias de rama, haces lo necesario…
git switch main
git stash list
git stash pop   # recupera y aplica el último
```

---

## 8) Sincronizar con GitHub y *pull* de cambios remotos

1. Sube tu rama de trabajo (opcional):

   ```bash
   git push -u origin desarrollo/ej1
   ```
2. Haz un **cambio directo en GitHub** (icono ✏️ sobre `Main.java`) añadiendo una línea, por ejemplo:

   ```java
   System.out.println("Cambio realizado desde GitHub");
   ```

   Confirma el *commit* en la web.
3. Vuelve a la terminal local (en `main`) y sincroniza:

   ```bash
   git pull origin main
   ```
4. Ejecuta en Eclipse para verificar que aparece la nueva línea.

---

## 9) Comandos de referencia

```bash
git status
git log --oneline --graph --decorate

git branch                # listar ramas
git switch -c nombre      # crear y cambiar a rama
git switch nombre         # cambiar de rama

git add -A
git commit -m "mensaje"

git merge <rama>

git stash / git stash pop / git stash list

git push -u origin main
git pull origin main
```

---

## 10) Entrega

Sube a Moodle:

* Captura de Eclipse ejecutando `Main`.
* Captura de terminal con: `git log --oneline --graph`, un *merge* resuelto, y un *pull* desde GitHub.
* Enlace al repositorio GitHub.
