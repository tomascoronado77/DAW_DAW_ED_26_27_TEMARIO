# Guía práctica: Conexiones entre Git y GitHub

Cuando se trabaja con **Git y GitHub**, el código puede almacenarse tanto de forma local, como en la nube.
Para sincronizar ambos entornos, Git necesita **autenticarse** ante GitHub, igual que cuando se inicia sesión en una web.
Existen diferentes **métodos de conexión**, cada uno con sus ventajas, requisitos y particularidades.

---

## 1. Conexión HTTPS

La conexión **HTTPS** es la más universal, porque utiliza el mismo puerto que todas las páginas web (443) y no suele estar bloqueada por cortafuegos ni redes corporativas.

### 🔹 Ejemplo de URL HTTPS

```
https://github.com/usuario/repositorio.git
```

Al usar HTTPS, Git pedirá autenticación para asegurarse de que el usuario tiene permiso para acceder al repositorio.
Esta autenticación puede realizarse de varias formas.

---

### 🔐 A. HTTPS con usuario y contraseña (obsoleto)

Hasta 2021, bastaba con escribir:

```
Usuario: tu_usuario_de_github
Contraseña: tu_contraseña_de_github
```

Sin embargo, **GitHub eliminó este método de autenticación**, ya que las contraseñas no eran seguras para su uso en entornos automatizados o públicos.
Actualmente **solo funciona si el usuario no tiene activada la verificación en dos pasos (2FA)**, algo poco habitual y nada recomendable.
Por tanto, este método ha quedado obsoleto.

---

### 🔐 B. HTTPS con Token Personal de Acceso (PAT)

El **Token Personal de Acceso (Personal Access Token o PAT)** sustituye a la contraseña tradicional.
Es una clave generada desde GitHub, con permisos y duración configurables.

**Creación del token:**

1. En GitHub, acceder a
   **Settings → Developer settings → Personal Access Tokens → Tokens (classic)**
2. Seleccionar **Generate new token**.
3. Activar los permisos necesarios (por ejemplo, *repo* para acceso a repositorios).
4. Copiar el token generado, que tiene un aspecto como el siguiente:

   ```
   ghp_dJf9zqK12ABC34xyZ56LMnoP78qrstuV9Wx
   ```

A partir de entonces, al realizar un `git push` o `git pull`, se introduce:

```
Username for 'https://github.com': tu_usuario
Password for 'https://github.com': <token_personal>
```

El token reemplaza la contraseña.

---

## 2. El Credential Helper

El **credential helper** es una utilidad integrada en Git que **almacena las credenciales** (usuario y token) para no tener que escribirlas cada vez.

### 🔹 Configuración más habitual:

```bash
git config --global credential.helper store
```

Después de ejecutar este comando, la primera vez que Git solicite el usuario y el token los guardará automáticamente en un archivo de texto plano:

* **Windows:**
  `C:\Users\<usuario>\.git-credentials`
* **macOS / Linux:**
  `~/.git-credentials`

El contenido del archivo será similar a:

```
https://usuario:ghp_dJf9zqK12ABC34xyZ56LMnoP78qrstuV9Wx@github.com
```

Desde ese momento, Git utilizará automáticamente estas credenciales.
También existen otras variantes como `osxkeychain` o `wincred`, que almacenan las credenciales de forma cifrada en el sistema operativo.

---

## 3. Duración y gestión de los tokens

Los tokens pueden configurarse para expirar automáticamente o permanecer activos indefinidamente.
Al generarlos, GitHub permite elegir su duración: 7, 30, 90 días o sin caducidad.
En entornos educativos o de formación se recomienda establecer una duración limitada y eliminar los tokens al finalizar el curso o trimestre.

---

## 4. Conexión SSH

La conexión **SSH (Secure Shell)** usa un sistema de **claves asimétricas** para autenticar al usuario sin necesidad de escribir usuario o contraseña.
Cuando se configura correctamente, es el método más cómodo y seguro.

### 🔹 Ejemplo de URL SSH

```
git@github.com:usuario/repositorio.git
```

### 🔹 Claves necesarias

1. **Clave privada**, almacenada en el equipo local (nunca se comparte).
2. **Clave pública**, registrada en la cuenta de GitHub (*Settings → SSH and GPG keys*).

Cuando se realiza un `git push`, Git usa la clave privada para identificarse, y GitHub valida la operación con la clave pública registrada.

---

## 5. SSH y el puerto 443

El protocolo SSH utiliza el **puerto 22**, pero algunas redes educativas o corporativas *(como Andared)* lo bloquean por seguridad.
Para evitar este problema, GitHub permite usar **SSH sobre el puerto 443**, el mismo que usa HTTPS.

### 🔹 Configuración por sistema operativo

* **Windows:**
  Editar o crear el archivo
  `C:\Users\<usuario>\.ssh\config`

* **macOS / Linux:**
  Editar o crear el archivo
  `~/.ssh/config`

* **Contenido del archivo:**

  ```bash
  Host github.com
    Hostname ssh.github.com
    Port 443
    User git
  ```

Después de guardar los cambios, se puede comprobar la conexión con:

```bash
ssh -T -p 443 git@ssh.github.com
```

Si aparece el mensaje:

```
Hi usuario! You've successfully authenticated...
```

la configuración es correcta.

---

## 6. Creación y sincronización de repositorios

Git permite **clonar repositorios existentes** o **crear nuevos repositorios locales** para después conectarlos con GitHub.

---

### 🧱 A. Clonar un repositorio existente

Cuando el proyecto ya existe en GitHub, se puede clonar con HTTPS o SSH:

```bash
git clone https://github.com/usuario/repositorio.git
```

o

```bash
git clone git@github.com:usuario/repositorio.git
```

El comando `git clone` crea una carpeta local con el contenido del repositorio y configura un remoto llamado `origin`, que apunta a GitHub.

---

### 🧱 B. Crear un repositorio local y conectarlo a GitHub

Cuando el proyecto se inicia desde el equipo local:

```bash
git init
git add .
git commit -m "Primer commit"
```

A continuación, se crea un repositorio **vacío** en GitHub ***(sin README, ni licencia, ni .gitignore, ...)*** y se vincula:

```bash
git remote add origin https://github.com/usuario/repositorio.git
```

Finalmente, se sube el contenido:

```bash
git branch -M main
git push -u origin main
```

La opción `-u` en **git push** significa `--set-upstream` y se utiliza la primera vez que envías una rama a un repositorio remoto. 
Esta opción vincula tu rama local con la rama remota, permitiendo que en futuras operaciones *(git push o git pull)* no tengas que 
especificar el repositorio y la rama de origen, ya que Git sabrá a dónde enviar los cambios. 

---

## 🧩 7. Por qué a veces la rama principal es `master` en lugar de `main`

Antiguamente, Git creaba la rama principal con el nombre **`master`**.
Desde 2020, GitHub y la comunidad adoptaron **`main`** como nueva denominación estándar, tanto por motivos de inclusión como por coherencia.

No obstante, cuando se crea un repositorio **vacío** en GitHub y se sube contenido desde un equipo local con una configuración antigua de Git, la primera rama que se envía conserva su nombre original (`master`).
GitHub la acepta y la considera principal.

Para comprobar qué rama se crea por defecto en un equipo local:

```bash
git config --global init.defaultBranch
```

Si el valor es `master` o está vacío, se puede cambiar para que todos los repositorios nuevos usen `main`:

```bash
git config --global init.defaultBranch main
```

De esta forma, cualquier `git init` creará automáticamente la rama `main`, garantizando compatibilidad con GitHub.

---

## 8. Git CLI y GitHub CLI

### 🔹 Qué significa CLI

**CLI** proviene del inglés *Command Line Interface*, que significa **Interfaz de Línea de Comandos**.
Es el entorno textual en el que se introducen instrucciones mediante comandos, en contraste con las interfaces gráficas (GUI, *Graphical User Interface*) que usan menús y botones.

Ejemplo de uso de la CLI de Git:

```bash
git init
git add .
git commit -m "Mensaje de commit"
git push
```

Trabajar con Git CLI ofrece más control y transparencia sobre las operaciones que se realizan en el repositorio.

---

### 🔹 GitHub CLI (`gh`)

El **GitHub CLI** es una herramienta oficial que amplía las capacidades de Git para interactuar directamente con la plataforma GitHub sin necesidad de navegador.

Permite, entre otras funciones:

* Crear repositorios:

  ```bash
  gh repo create nombre --public
  ```

* Clonar repositorios:

  ```bash
  gh repo clone usuario/repositorio
  ```

* Crear *pull requests* o *issues*:

  ```bash
  gh pr create --title "Nueva funcionalidad"
  gh issue list
  ```

El GitHub CLI **no sustituye a Git**, sino que lo complementa.
Git sigue gestionando el control de versiones, mientras que `gh` automatiza la interacción con GitHub (creación de repos, autenticación, gestión de PRs, etc.).

---

## 9. Eclipse con GitHub sin tener instalado Git

Eclipse incorpora un sistema de control de versiones basado en **EGit**, un componente escrito en Java que implementa internamente la funcionalidad de Git.
Por ello, es posible realizar operaciones como *commit*, *push* o *pull* desde Eclipse **sin tener instalado Git externamente en el sistema operativo**.

**EGit** utiliza los mismos conceptos y estructura de Git *(repositorio local, carpeta `.git/`, ramas, remotos, etc.)*, pero ofrece una **interfaz gráfica (GUI)** que simplifica el trabajo.

### 🔹 Diferencias prácticas

| Aspecto              | Git CLI (terminal)         | EGit (Eclipse)              |
| -------------------- | -------------------------- | --------------------------- |
| Interfaz             | Línea de comandos (CLI)    | Gráfica (GUI)               |
| Instalación          | Requiere Git en el sistema | Integrado en Eclipse        |
| Archivos de control  | Usa `.git/`                | Usa `.git/` (idéntico)      |
| Nivel de control     | Total                      | Limitado a opciones del IDE |
| Curva de aprendizaje | Mayor                      | Más intuitiva               |

EGit facilita tareas como compartir un proyecto en GitHub, realizar commits o gestionar ramas, sin necesidad de abrir la consola.
No obstante, conocer la CLI de Git sigue siendo esencial, ya que proporciona un control completo del flujo de trabajo y ayuda a entender qué operaciones realiza realmente el entorno gráfico.

---

## 10. Comandos esenciales de conexión remota

| Comando                           | Descripción                                              | Cuándo se usa                           |
| --------------------------------- | -------------------------------------------------------- | --------------------------------------- |
| `git remote -v`                   | Muestra las URLs remotas asociadas al repositorio local. | Para verificar configuración.           |
| `git remote add origin <url>`     | Asocia un repositorio remoto al proyecto local.          | Al conectar por primera vez con GitHub. |
| `git remote set-url origin <url>` | Cambia la URL del remoto `origin`.                       | Al pasar de SSH a HTTPS o viceversa.    |
| `git push -u origin main`         | Sube la rama principal y la deja vinculada.              | Primer envío al remoto.                 |
| `git pull`                        | Descarga y fusiona cambios del remoto.                   | Para mantener actualizado el proyecto.  |
| `git push`                        | Sube los commits de la rama activa a su rama remota asociada. | En envíos posteriores, cuando ya existe el enlace de seguimiento. |

---

## 11. Comparativa de métodos de conexión

| Método         | Puerto   | Autenticación          | Ventajas                           | Limitaciones                        |
| -------------- | -------- | ---------------------- | ---------------------------------- | ----------------------------------- |
| HTTPS + Token  | 443      | Usuario + Token PAT    | Compatible en cualquier red        | Requiere generar y guardar token    |
| SSH (22)       | 22       | Claves pública/privada | Seguro y sin contraseñas           | Puede estar bloqueado               |
| SSH (443)      | 443      | Claves pública/privada | Evita bloqueos de red              | Requiere configurar `~/.ssh/config` |
| GitHub CLI     | 443      | Token o SSH            | Permite crear repos desde terminal | Necesita la herramienta `gh`        |
| EGit (Eclipse) | 443 o 22 | Según configuración    | Interfaz gráfica intuitiva         | Menos control avanzado              |

---

## 12. Conclusión

En entornos educativos o corporativos, el método más fiable es **HTTPS con token personal**, ya que evita los bloqueos del puerto 22 y no requiere configuración adicional.
En equipos personales, **SSH** sigue siendo la opción más cómoda y segura.

Tanto la **CLI (Command Line Interface)** como las interfaces gráficas como **EGit** o **GitHub Desktop** comparten el mismo objetivo: mantener sincronizado el código entre el repositorio local y el remoto.
La diferencia radica en el nivel de control y transparencia que cada interfaz ofrece.

---
