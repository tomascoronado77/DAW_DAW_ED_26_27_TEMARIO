## 11) Ejercicio práctico — Eclipse para programar, terminal para Git

En este ejercicio comprobarás que tu conexión **SSH** con GitHub funciona correctamente. Crearás el programa Java desde **Eclipse**, pero todos los comandos de **Git** los ejecutarás **desde la terminal**.

---

### 🔹 Paso 1. Verifica la conexión SSH

```bash
ssh -T git@github.com
```

Deberías ver:

```
Hi tu_usuario! You've successfully authenticated, but GitHub does not provide shell access.
```

Si aparece, tu conexión SSH está lista.

---

### 🔹 Paso 2. Crear o localizar el repositorio en GitHub

1. Crea un repositorio llamado `RamasGitJavaDAM` que contenga simplemente un fichero README.
2. Copia la **URL SSH** (empieza por `git@github.com:`).

Ejemplo:

```bash
git@github.com:tu_usuario/RamasGitJavaDAM.git
```

---

### 🔹 Paso 3. Clonar el repositorio en el workspace de Eclipse

1. Abre **Git Bash** y colócate en tu workspace de Eclipse:

   ```bash
   cd "C:\Users\alumno\eclipse-workspace"
   ```
2. Clona el repositorio por SSH:

   ```bash
   git clone git@github.com:tu_usuario/RamasGitJavaDAM.git
   ```
3. Comprueba que se ha creado la carpeta:

   ```bash
   ls -l
   # Deberías ver la carpeta RamasGitJavaDAM
   ```

---

### 🔹 Paso 4. Crear el proyecto Java dentro de la carpeta clonada (en Eclipse)

1. En **Eclipse** → *File → New → Java Project*.
2. **Desmarca** *Use default location*.
3. En *Location*, selecciona la carpeta clonada:

   ```
   C:\Users\alumno\eclipse-workspace\RamasGitJavaDAM
   ```
4. Nombre del proyecto: `RamasGitJavaDAM` → **Finish**.
5. Eclipse creará `src/`, `.project` y `.classpath` dentro de esa carpeta clonada.
6. Crea la clase principal: *File → New → Class*

   * Package: `edu.alumno.ssh`
   * Name: `Main`
   * Marca **public static void main(String[] args)**.

7. Copia este código:

   ```java
   package edu.alumno.ssh;

   import java.util.Random;

   public class Main {
       public static void main(String[] args) {
           //Investiga el uso de random y genera por pantalla los números de la lotería primitiva.
           Random random = new Random();

           System.out.println("Números de la Lotería Primitiva:");
           // Genera 5 números principales

           // Número complementario

       }
   }
   ```

8. Ejecuta el programa en Eclipse (*Run As → Java Application*) para comprobar que funciona correctamente.

---

### 🔹 Paso 5. Control de versiones con Git (solo terminal)

A partir de aquí, **no uses las opciones gráficas de Eclipse para Git**. Trabajarás únicamente desde la terminal.

1. Abre Git Bash y colócate en la carpeta del proyecto:

   ```bash
   cd "C:\Users\alumno\eclipse-workspace\RamasGitJavaDAM"
   ```
2. Crea un `.gitignore` básico para Eclipse:

   ```bash
   echo "/bin/" > .gitignore
   ```
3. Añade los archivos al repositorio y haz el primer commit:

   ```bash
   git add .
   git commit -m "Proyecto Java creado desde Eclipse y gestionado por terminal (SSH listo)"
   ```

4. Sube el proyecto a GitHub:

   ```bash
   git branch -M main
   git push -u origin main
   ```

---

### 🔹 Paso 6. Verifica en GitHub

1. Abre tu repositorio `RamasGitJavaDAM` en GitHub.
2. Comprueba que aparecen las carpetas `src/` y el archivo `Main.java`.
3. Verifica que el push se ha realizado sin pedir usuario ni contraseña.

---

### 🔹 Paso 7. Modificación, commit local y sincronización con cambios remotos

1. Desde **Eclipse**, modifica `Main.java` y añade esta línea al final del método `main`:

   ```java
   System.out.println("¡Buena suerte en el sorteo!");
   ```

2. Guarda el archivo y realiza el commit y push desde la terminal:

   ```bash
   cd "C:\Users\alumno\eclipse-workspace\RamasGitJavaDAM"
   git add src/edu/alumno/ssh/Main.java
   git commit -m "Añadido mensaje final de suerte"
   git push
   ```

3. Ahora entra en tu repositorio de **GitHub** y realiza un pequeño cambio **directamente desde la web**:

   * Abre el archivo `Main.java`.
   * Haz clic en el icono del lápiz (✏️ *Edit this file*).
   * Añade una línea más al final del programa, por ejemplo:

     ```java
     System.out.println("Cambio realizado desde GitHub.");
     ```
     
   * Baja hasta el final y haz clic en **Commit changes**.

4. Vuelve a tu terminal y sincroniza los cambios remotos ejecutando:

   ```bash
   git pull origin main
   ```

   Esto descargará el cambio hecho desde GitHub y lo fusionará en tu copia local.

5. Comprueba en Eclipse que `Main.java` incluye las tres líneas de salida y vuelve a ejecutar el programa para confirmar.

---

### 🔹 Paso 8. Entrega

Sube a Classroom una **captura de pantalla** mostrando:

* Tu código en Eclipse con la clase `Main.java`.
* La salida del programa ejecutado en la consola de Eclipse.
* Tu terminal con los comandos `git status`, `git log --oneline` y `git push` correctamente ejecutados.
