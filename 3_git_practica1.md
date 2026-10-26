# Práctica 1 - Uso básico de Git

En esta práctica vamos a gestionar un proyecto con el control de versiones **Git**.

## Introducción y comandos básicos para trabajar con el control de versiones Git

A continuación, se ofrece una guía paso a paso para aquellos usuarios principiantes interesados en trabajar con un repositorio de Git en local.

El sistema de control de versiones Git es una herramienta fundamental para muchos desarrolladores, especialmente cuando colaboran en un proyecto. Git ayuda a mantener una visión de conjunto, a preservar las versiones antiguas y a integrar los cambios de manera coherente. Para ello, Git agrupa una serie de programas de línea de comandos y crea un efectivo entorno de trabajo.

Git permite trabajar con **repositorios locales**, lo que significa que las versiones del proyecto se guardan en tu propio ordenador.  
Más adelante podrás sincronizar tu proyecto con **GitHub**, lo que te permitirá mantener una copia en la nube y colaborar con otros compañeros.

---

### 1. Instalar Git

La forma más oficial está disponible para ser descargada en el sitio web de Git. Solo tienes que visitar [Download](http://git-scm.com/download/), seleccionar tu sistema operativo y la descarga empezará automáticamente.

Una vez que hayamos instalado Git en nuestro ordenador, abriremos la aplicación **Git Bash**, o desde Linux, podremos abrir una terminal y ejecutar el comando `git` para interactuar con Git.

En Windows, Git Bash es la herramienta de línea de comandos que permite a los usuarios utilizar las funciones de Git en un entorno similar a Linux. Incluye utilidades de Unix (como `ls`, `cat`, `cd`, etc.) y te permitirá usar los mismos comandos tanto en Windows como en sistemas basados en Unix.

---

### 2. Configurar Git y crear nuestro primer proyecto

1. Primero tenemos que definir nuestra identidad, para ello en la línea de comandos escribiremos las siguientes instrucciones utilizando nuestro usuario iPasen y correo `xxxxx@g.educaand.es`.

```bash
git config --global user.name "dcansib483"
git config --global user.email "dcansib483@g.educaand.es"
```

2. Algunos comandos básicos para navegar y trabajar con ficheros y carpetas:

* `clear`: limpia la pantalla de la consola.  
* `pwd`: muestra el directorio actual.  
* `ls`: lista los ficheros y directorios (`-l` muestra más detalles, `-a` incluye archivos ocultos).  
* `cd`: moverse entre directorios (`cd ..` para subir un nivel).  
* `mkdir`: crear un directorio.  
* `rmdir`: borrar un directorio.  
* `cat`: mostrar el contenido de un archivo.  
* `touch`: crear un archivo vacío.  
* Flechas ↑ ↓: recuperar comandos anteriores.  
* Tabulador: autocompletar nombres de archivos o carpetas.  

3. El fichero de configuración de Git, donde se almacena tu identidad, está en un archivo oculto en tu carpeta de usuario llamado `.gitconfig`.  
Intenta encontrarlo y visualizar su contenido con el comando:

```bash
cat ~/.gitconfig
```

4. Accedemos a la carpeta `Documents` y creamos el directorio `ProgPython`:

```bash
cd Documents
mkdir ProgPython
```

5. En esta carpeta vamos a crear nuestro proyecto de Git. Inicializamos Git en este directorio:

```bash
cd ProgPython  
git init
```

---

### ¿Cómo vamos a trabajar con Git?

De manera muy básica, en un proyecto de Git vamos a trabajar con **tres áreas principales**:   

- **Área de trabajo (Working Directory)**: creamos y modificamos archivos.  
- **Área de preparación (Staging Area)**: seleccionamos los cambios que queremos guardar.  
- **Repositorio local (.git)**: donde se almacenan las versiones confirmadas (commits).

![Secciones principales de un proyecto de Git](https://git-scm.com/book/en/v2/images/areas.png)

---

### Tabla resumen de comandos básicos

| Acción | Comando | Descripción |
|--------|----------|-------------|
| Ver cambios pendientes | `git status` | Muestra qué ha cambiado |
| Preparar cambios | `git add archivo` | Pasa cambios al área de preparación |
| Confirmar versión | `git commit -m "mensaje"` | Guarda una versión en el repositorio |
| Ver historial | `git log` | Muestra los commits realizados |

---

### 3. Crear y versionar nuestro primer programa

1. Creamos un archivo de Python y lo ejecutamos:

```bash
touch holamundo.py
```

2. Editamos su contenido desde la consola:

```bash
nano holamundo.py
```

Dentro del archivo escribimos:
```python
print("Hola mundo DAM-DAW!")
```

Guardamos y salimos (Ctrl + O, Enter, Ctrl + X).

3. Mostramos su contenido:

```bash
cat holamundo.py
```

4. Ejecutamos el programa:

```bash
python holamundo.py
```

---

### 4. Primer seguimiento con Git

1. Comprobamos el estado del proyecto:

```bash
git status
```

2. Añadimos el archivo al área de preparación:

```bash
git add holamundo.py
```

3. Confirmamos los cambios (commit):

```bash
git commit -m "Primera versión de hola mundo"
```

4. Comprobamos que no hay cambios pendientes:

```bash
git status
```

---

### 5. Segundo programa y uso de `.gitignore`

1. Creamos un directorio `ejercicios1` y un programa `prueba1.py`:

```bash
mkdir ejercicios1
touch ejercicios1/prueba1.py
```

2. Contenido del programa:

```python
edad = int(input("Introduzca su edad: "))  
if edad >= 18:  
    print("Toma una cerveza!")  
else:  
    print(f"Toma un zumo de piña, con {edad} años eres menor.")
```

3. Si usamos PyCharm o Visual Studio Code, puede generarse una carpeta `.idea`.  
Para que Git la ignore, creamos un archivo `.gitignore`:

```bash
nano .gitignore
```

Contenido:
```
.idea
```

4. Añadimos y confirmamos:

```bash
git add .gitignore
git add ejercicios1
git commit -m "Primera versión de la carpeta ejercicios1"
```

---

### 6. Ver historial y deshacer errores

1. Comprobamos el historial de commits:

```bash
git log --oneline
```

2. Modificamos el programa introduciendo un error:

```python
edad = input("Introduzca su edad: ")  
if edad >= 18:  
    print("Toma una cerveza!")  
else:  
    print(f"Toma un zumo de piña, con {edad} años eres menor.")
```

3. Añadimos y confirmamos el cambio erróneo:

```bash
git add ejercicios1
git commit -m "Segunda versión con un error"
```

4. El programa falla al ejecutarse.  
Para volver a la versión anterior:

```bash
git log --oneline
git reset --hard <commit_hash>
```

> ⚠️ **Atención:** `git reset --hard` elimina todos los commits posteriores al indicado.  
> Úsalo solo si estás seguro de querer borrar esos cambios, ya que no se puede deshacer.

5. Si ejecutas de nuevo el programa, verás que ha vuelto a la versión funcional.

---

### 7. Comandos últiles

* Añadir todos los archivos del directorio:  
  `git add .`

* Añadir todos los cambios pendientes:  
  `git add -A`

* Deshacer un `git add` antes del commit:  
  `git reset nombreArchivo` o simplemente `git reset`

* Cambiar el nombre de la rama principal a `main`:  
  `git branch -m main`

---

### Reto final — Modificar y versionar un programa

Vamos a realizar una pequeña modificación en el programa `holamundo.py` y a guardar una nueva versión en Git.  
No necesitas saber Python; simplemente sigue las instrucciones paso a paso.

1. Abre el archivo `holamundo.py` con el editor de texto desde la terminal:

```bash
nano holamundo.py
```

2. Sustituye su contenido por el siguiente código:

```python
nombre = input("¿Cómo te llamas? ")
print(f"Hola, {nombre}! Bienvenido al módulo de Programación.")
```

*(Si usas un teclado sin `ñ`, puedes escribir `nombre = input("Como te llamas? ")`.)*

3. Guarda los cambios y sal de `nano`  
(Ctrl + O → Enter → Ctrl + X)

4. Comprueba el estado del proyecto:

```bash
git status
```

5. Ve las diferencias respecto a la versión anterior:

```bash
git diff
```

6. Añade el archivo al área de preparación:

```bash
git add holamundo.py
```

7. Crea un nuevo commit con un mensaje descriptivo:

```bash
git commit -m "Versión personalizada de Hola Mundo con nombre del usuario"
```

8. Muestra el historial resumido de tus versiones:

```bash
git log --oneline
```

9. Ejecuta el programa para ver el resultado final:

```bash
python holamundo.py
```

Introduce tu nombre cuando te lo pida y comprueba que muestra un saludo personalizado.

---

## 📚 Recursos recomendados

* [Ayuda visual de Git](https://ndpsoftware.com/git-cheatsheet.html#loc=index)
* [Guía rápida oficial de GitHub](https://training.github.com/downloads/es_ES/github-git-cheat-sheet/)
* [Documentación de referencia de Git](https://git-scm.com/docs)
* [Libro de Git (versión en español)](https://git-scm.com/book/es/v2)

