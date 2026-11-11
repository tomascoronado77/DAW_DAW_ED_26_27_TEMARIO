
# 2. IDE: Eclipse.

## **1. Introducción**

El código en Java se puede escribir en cualquier editor de texto y, para compilarlo a **bytecodes**, basta con instalar la versión del **JDK** correspondiente. Sin embargo, escribir y compilar programas de esta forma resulta incómodo, ya que obliga a gestionar manualmente la compilación y la ejecución desde la línea de comandos.

Por ello surgieron los **IDE (Integrated Development Environment)** o entornos de desarrollo integrados, que consisten básicamente en un editor de código, un compilador y un depurador, además de otras herramientas.

**Ventajas principales de un IDE:**

* **Facilidades para escribir código**: coloreado de palabras clave, autocompletado, sugerencias de corrección, uso de abreviaturas.
* **Herramientas de depuración**, para detectar y corregir errores durante la ejecución.
* **Facilidad de configuración** del sistema y de integración con librerías externas.
* **Organización estructurada** de los proyectos y sus archivos.
* **Exportación e importación sencilla** de proyectos.

Algunos IDEs muy utilizados para programar en Java son **Eclipse**, **NetBeans** e **IntelliJ IDEA**.

En esta asignatura utilizaremos **Eclipse**, que puede descargarse desde la página oficial:  
👉 [https://www.eclipse.org/downloads/eclipse-packages/](https://www.eclipse.org/downloads/eclipse-packages/)

Se recomienda instalar la versión más reciente de **Eclipse IDE for Java Developers**, escogiendo la del sistema operativo correspondiente.

Una vez instalado, es importante definir en Eclipse la versión del compilador de Java:

* `Window → Preferences → Java → Compiler` → seleccionar la versión de JDK deseada.
* `Window → Preferences → Java → Installed JREs` → marcar la versión como predeterminada.

Además, el alumno puede personalizar la apariencia del entorno:

* `Window → Preferences → General → Appearance → Theme`, seleccionando un tema oscuro o claro según preferencias.

---

## **2. Workspace**

Al iniciarse, Eclipse solicita la selección de un **workspace**, es decir, el directorio que funcionará como área de trabajo donde se guardarán todos los proyectos.

* Se puede aceptar la ruta predeterminada o establecer otra distinta.
* Es posible mantener **varios workspaces** para proyectos de distinta naturaleza.

Para cambiar de área de trabajo:
👉 `File → Switch Workspace → Other`.

---

## **3. UTF-8**

**UTF-8** *(Unicode Transformation Format-8)* es un formato de codificación de caracteres en el que cada carácter se representa mediante una 
secuencia de entre **1 y 4 bytes**. Los caracteres ASCII básicos (como letras, números y símbolos comunes) se codifican con un único byte (8 bits), 
mientras que otros caracteres Unicode más complejos (acentos, símbolos especiales, alfabetos no latinos) pueden necesitar 2, 3 o 4 bytes.

También existen otras variantes como **UTF-16** y **UTF-32**. La ventaja de UTF-8 respecto a estas es que resulta más eficiente cuando se 
trabaja con textos que contienen principalmente caracteres ASCII y, además, es compatible con versiones anteriores de ASCII.

**Características principales de UTF-8:**

* Representa cualquier carácter Unicode.
* Longitud variable (1 a 4 bytes por carácter).
* Compatible con US-ASCII de 7 bits: cualquier texto ASCII se representa sin cambios.
* Evita ambigüedades: la secuencia de bytes de un carácter no puede confundirse con parte de otro carácter.

**Cómo configurar Eclipse en UTF-8:**

* `Window → Preferences → General → Workspace → Text file encoding → UTF-8`.
* También puede configurarse por proyecto en `Project → Properties → Resource → Text file encoding → UTF-8`.

**Ejemplos de codificación UTF-8**

| Carácter | Descripción            | Bytes en UTF-8 | Hexadecimal   | Binario (bits)                        |
| -------- | ---------------------- | -------------- | ------------- | ------------------------------------- |
| `A`      | Letra ASCII básica     | 1 byte         | `41`          | `01000001`                            |
| `ñ`      | Letra latina acentuada | 2 bytes        | `C3 B1`       | `11000011 10110001`                   |
| `€`      | Símbolo del euro       | 3 bytes        | `E2 82 AC`    | `11100010 10000010 10101100`          |
| 😀       | Emoji (cara sonriente) | 4 bytes        | `F0 9F 98 80` | `11110000 10011111 10011000 10000000` |

---

## **4. Editores, vistas y perspectivas**

La interfaz de usuario de **Eclipse** se organiza en torno a **editores**, **vistas** y **perspectivas**:

* Un **editor** es la parte de la interfaz que permite crear, modificar y guardar archivos (por ejemplo, el editor de código Java).
* Una **vista** es una ventana auxiliar que muestra información adicional o permite realizar tareas de apoyo (ejemplo: la consola, el explorador de paquetes, las variables en depuración).
* Una **perspectiva** es una disposición o agrupación de editores y vistas diseñada para apoyar una tarea completa del proceso de desarrollo.

### Perspectivas principales en Eclipse

* **Java**: diseñada para trabajar con proyectos Java (muestra el editor de código, el explorador de paquetes, la consola, etc.).
* **Debug**: enfocada en la depuración de programas (muestra vistas como Variables, Breakpoints, Consola, etc.).

Estas perspectivas se pueden abrir en:
👉 `Window → Perspective → Open Perspective`
o desde los botones de la **barra de perspectivas** en la esquina superior derecha de la interfaz.

Además, el usuario puede personalizar una perspectiva (añadir/quitar vistas, reorganizar paneles) y guardarla en:
👉 `Window → Perspective → Save Perspective As`.

Las vistas también se pueden abrir directamente en:
👉 `Window → Show View`.
Por ejemplo, la **Consola** se abre en `Window → Show View → Console`.

---

## **5. Package Explorer**

En la columna izquierda de la interfaz suele encontrarse la vista **Package Explorer** (Explorador de Paquetes).

Esta vista muestra la **estructura de proyectos, paquetes y clases Java** que forman parte del área de trabajo (workspace). Permite navegar fácilmente por los archivos de un proyecto, abrir clases en el editor y gestionar recursos.

Al ser una vista, puede abrirse también en:
👉 `Window → Show View → Package Explorer`.

---

## **6. CamelCase**

**CamelCase** es un estilo de escritura utilizado en programación para nombrar identificadores compuestos (clases, métodos, variables). Consiste en unir varias palabras y escribir la primera letra de cada palabra en mayúscula (excepto, en ocasiones, la primera).

El nombre se debe a que las mayúsculas intermedias recuerdan a las “jorobas” de un camello 🐪.

Existen dos variantes:

* **UpperCamelCase (PascalCase):** la primera letra de cada palabra va en mayúscula.
  👉 Ejemplo: `PrimerPrograma`, `EjemploDeUpperCamelCase`.
* **lowerCamelCase:** igual que la anterior, pero la primera palabra empieza en minúscula.
  👉 Ejemplo: `primerPrograma`, `ejemploDeLowerCamelCase`.

La notación CamelCase se usa en muchos lenguajes de programación, incluido **Java**:

* **Clases e interfaces** → `UpperCamelCase` (`MiClase`, `CalculadoraSimple`).
* **Variables y métodos** → `lowerCamelCase` (`contador`, `sumarNumeros()`).

---

## **7. Proyectos, paquetes y clases**

### Proyectos

Un **proyecto Java** en Eclipse puede considerarse como una carpeta organizada que contiene todo lo necesario para desarrollar una aplicación.
Un proyecto incluye:

* Archivos **.java** (código fuente).
* Archivos **.class** (código compilado en bytecode).
* Documentación y otros recursos asociados (imágenes, librerías, etc.).

Cuando se crea un proyecto Java en Eclipse, este se guarda en el **workspace** y genera por defecto dos subcarpetas:

* **src** (source): contiene los archivos `.java` organizados en paquetes.
* **bin** (binary): contiene los archivos `.class` generados al compilar el código.

---

### Paquetes

Los **paquetes** (*packages*) permiten **organizar las clases** dentro de la carpeta `src` de un proyecto Java.

* Un paquete se corresponde con una **subcarpeta** en `src`.
* Dentro de cada clase Java se debe indicar al inicio el paquete al que pertenece con la palabra clave `package`.
* El nombre de un paquete suele seguir una **estructura jerárquica** que identifica la procedencia del código *(por ejemplo, el país, el centro educativo, el módulo y el tema)*.

Ejemplo con clase en un paquete:

#### Estructura en Eclipse

Cuando se crea un paquete, Eclipse genera automáticamente la carpeta correspondiente en el sistema de archivos.
Por ejemplo, el paquete:

```
es.iessaladillo.dam1.ed.u1
```

corresponde a la ruta:

```
src/es/iessaladillo/dam1/ed/u1/
```

**Ejemplo con clase en un paquete:**

```java
package es.iesfernandoaguilar.dam1.ed.u1; // Especificación del paquete

public class Integers {
    public static void main(String[] args) {
        // código...
    }
}
```

#### Paquete por defecto (default package)

Si la clase se guarda directamente en `src` sin indicar ningún paquete, se dice que pertenece al **paquete por defecto** (*default package*).
En este caso, el archivo no lleva ninguna declaración `package` al inicio:

```java
// Clase en el default package
public class Integers {
    public static void main(String[] args) {
        // código...
    }
}
```

👉 Aunque Eclipse permite usar el *default package*, **no se recomienda en proyectos grandes** porque **dificulta la organización y la importación de clases** desde otros paquetes.

#### Recomendación práctica (para clase)

En el IES Saladillo, los paquetes pueden seguir esta convención:

```
es.iessaladillo.dam1.ed.uX
```

donde:

* `es` → país (España)
* `iessaladillo` → centro educativo
* `dam1` → ciclo y curso
* `ed` → módulo *Entornos de Desarrollo*
* `uX` → número de unidad o tema (por ejemplo, `u1`, `u2`, etc.)

---

### Clases

Las **clases** son los archivos `.java` que contienen el código fuente. Cada clase debe tener un nombre que siga la notación **UpperCamelCase** (primera letra de cada palabra en mayúscula).

Ejemplo: `PrimerPrograma`, `CalculadoraSimple`.

---

### Operaciones en Eclipse

**Crear un proyecto Java**

1. Menú `File → New → Java Project`.
2. En **Project name**, escribir el nombre del proyecto (conviene que empiece en mayúscula).
3. Desactivar la opción *Create module-info.java file* (no se usará en este módulo).
4. Pulsar *Finish*.

⚠️ Nota: si el proyecto ya existe en el workspace, Eclipse lo abrirá con todos los datos guardados previamente.

---

**Borrar un proyecto**

1. Seleccionar el proyecto en el **Package Explorer**.
2. Botón derecho → `Delete`.
3. Eclipse preguntará si quieres borrarlo solo del explorador o también físicamente del workspace.

---

**Crear un paquete**

1. En **Package Explorer**, situarse sobre el proyecto o sobre su carpeta `src`.
2. Botón derecho → `New → Package`.
3. Escribir el nombre (todo en minúsculas, se permiten números y `_` pero no caracteres especiales).

   * Ejemplo: `tema1_1`.
4. Pulsar *Finish*.

---

**Crear una clase**

1. En **Package Explorer**, situarse sobre el paquete donde se quiere crear.
2. Botón derecho → `New → Class`.
3. En **Name**, escribir el nombre de la clase (usar UpperCamelCase).
4. Si se quiere que sea ejecutable, marcar la casilla **public static void main(String[] args)**.
5. Pulsar *Finish*.

---

## **8. Build Path**

El **Build Path** (ruta de compilación) en Eclipse define **qué necesita un proyecto para compilarse y ejecutarse correctamente**.
Incluye:

* La carpeta de **código fuente** (`src`, con los archivos `.java`).
* La carpeta de **clases compiladas** (`bin`, con los archivos `.class`).
* Las **librerías necesarias** (por ejemplo, el JRE de Java o archivos JAR externos).

### 8.1. ¿Qué es el JRE System Library?

Todo proyecto Java necesita tener asociada la librería básica del lenguaje: **JRE System Library**.
Esta librería contiene las clases fundamentales de Java, como:

* `String`
* `System`
* `ArrayList`
* `Scanner`
* …y muchas más de la API estándar.

👉 Si un proyecto no tiene asignada esta librería, Eclipse marcará errores incluso en instrucciones tan simples como `System.out.println()` porque no sabrá dónde encontrar esas clases.

### 8.2. Relación JDK – JRE – JVM

Cuando instalamos **Java** en nuestro ordenador, lo que realmente descargamos es el **JDK (Java Development Kit)**.

El JDK incluye:

* **Compilador** (`javac`), para traducir `.java` → `.class`.
* **Herramientas de desarrollo** (javadoc, jar, debugger, etc.).
* **JRE (Java Runtime Environment)**, que a su vez contiene:

  * La **JVM (Java Virtual Machine)**, que ejecuta el bytecode en cualquier sistema.
  * La **biblioteca estándar de clases de Java** (colecciones, E/S, utilidades, etc.).

Por tanto, cuando en Eclipse añadimos la **JRE System Library** al Build Path, en realidad le estamos diciendo:
👉 “Este proyecto usará la librería estándar incluida en el JDK que tienes instalado.”

### 8.3. Configurar el Build Path en Eclipse

1. Seleccionar el proyecto en el **Package Explorer**.
2. Botón derecho → `Build Path → Configure Build Path`.
3. En la pestaña **Libraries**, comprobar si ya aparece la **JRE System Library**.

   * Si no aparece o quieres cambiar la versión:

     * Clic en **Add Library → JRE System Library**.
     * Selecciona usar el **Workspace default JRE** (el que configuraste en `Window → Preferences → Java → Installed JREs`) o bien otro JDK instalado en tu equipo.
4. Pulsar *Finish*.

Además del JRE, en esta misma pestaña se pueden añadir **librerías externas** (Add External JARs) que el proyecto necesite, como frameworks o APIs adicionales.

---

## **9. Importar en Eclipse**

En Eclipse, **importar** significa traer al workspace elementos ya existentes (clases, paquetes o proyectos) para poder trabajar con ellos dentro del IDE.

### 🔹 Importar clases sueltas

Si tenemos archivos `.java` creados previamente y queremos añadirlos a un paquete existente:

1. En el **Package Explorer**, situarse encima del paquete donde se van a importar las clases.
2. Botón derecho → `Import`.
3. `General → File System → Next`.
4. En **From Directory**, pulsar *Browse* y seleccionar la carpeta donde están las clases.
5. Marcar las clases que se quieren importar → *Finish*.

👉 Nota:

* Si marcas la opción **Create top-level folder**, Eclipse crea también la carpeta de origen como paquete dentro de `src`. Esto es útil si quieres que las clases mantengan el mismo paquete original.

---

### 🔹 Importar paquetes completos

Si quieres traer paquetes ya creados (con sus clases dentro):

1. En el **Package Explorer**, situarse encima de `src`.
2. Botón derecho → `Import`.
3. `General → File System → Next`.
4. Seleccionar en **From Directory** la carpeta donde están los paquetes.
5. Marcar los paquetes deseados → *Finish*.

👉 Resultado: Eclipse creará esos paquetes dentro de `src` y colocará dentro las clases correspondientes.

---

### 🔹 Importar proyectos completos

Si ya existe un proyecto creado en otro workspace o en otra carpeta:

1. Si el proyecto no existe todavía en tu workspace, puedes crearlo primero con el mismo nombre (vacío).
2. En el **Package Explorer**, botón derecho → `Import`.
3. `General → File System → Next`.
4. En **From Directory**, seleccionar la carpeta raíz del proyecto.
5. Selecciona el proyecto entero y pulsa *Finish*.
6. Si Eclipse pregunta “¿Sobrescribir archivos?”, normalmente se elige **No to All** para evitar reemplazar lo que ya exista en el workspace.

👉 Consejo: en lugar de `File System`, para proyectos completos es más cómodo usar:

* `File → Import → Existing Projects into Workspace`.
  Así Eclipse detecta automáticamente el proyecto (con su `.project` y `.classpath`) y lo añade tal cual al workspace.

---

## **10. Mi primer programa en Java**

```java
package tema2_Eclipse;

public class MyFirstProgramme {

    public static void main(String[] args) {
        System.out.println("¡Mi primer programa!");
    }

}
```

Este programa muestra por pantalla el texto **¡Mi primer programa!**.

### Explicación del código:

* `public class MyFirstProgramme`: declara la clase pública llamada `MyFirstProgramme`.
  ⚠️ El nombre del archivo debe coincidir exactamente con el de la clase (`MyFirstProgramme.java`).
* `public static void main(String[] args)`: es el **método principal**. Todo programa en Java comienza su ejecución en el `main`.
* `System.out.println("¡Mi primer programa!");`: instrucción que escribe un texto en pantalla. Como se trata de una cadena, debe ir entre comillas.

👉 En Eclipse existen **plantillas de código** que facilitan escribir instrucciones repetitivas. La más común es:

* Escribir `sysout` → pulsar `Ctrl + Espacio` → Eclipse lo reemplaza por `System.out.println();`.
  Además de `sysout`, existen muchas otras plantillas e incluso se pueden crear nuevas.

---

## **11. Cambiar el nombre a los elementos de Java**

Al renombrar manualmente una clase, método o variable, podemos romper el programa si ese nombre aparece en varios lugares. Para evitarlo, Eclipse ofrece la herramienta de **refactorización**:

* Seleccionar el elemento (clase, variable, método, etc.).
* Botón derecho → `Refactor → Rename` (o pulsar **Alt+Shift+R**).
* Eclipse cambiará el nombre automáticamente en todos los sitios donde se use.

👉 Si se trata de una **clase**, también se renombra el archivo `.java` correspondiente y viceversa.

---

## **12. Escritura de programas en Java**

Al escribir programas en Java hay que tener en cuenta:

* Los archivos de código fuente terminan en `.java`.
* Java distingue entre **mayúsculas y minúsculas** (`Variable` ≠ `variable`).
* Cada instrucción debe terminar con `;`.
* Una instrucción puede ocupar varias líneas, y es válido usar espacios o tabuladores para mejorar la legibilidad.

### Comentarios en Java:

Los comentarios son anotaciones ignoradas por el compilador y sirven para explicar el código:

* **Una sola línea:**

  ```java
  // Comentario de una línea
  ```
* **Varias líneas:**

  ```java
  /* Comentario
     de varias líneas */
  ```

👉 Los comentarios deben aportar claridad, sobre todo en código complejo. La legibilidad es clave para el mantenimiento de una aplicación.

---

## **13. Comentarios en Eclipse**

Algunas configuraciones útiles:

* Cuando se crea una clase, Eclipse genera un comentario por defecto. Esto se configura en:
  `Window → Preferences → Java → Code Style → Code Templates → Code → Method Body → Edit`.
* Para desactivar la corrección ortográfica en los comentarios (que a veces subraya palabras técnicas):
  `Window → Preferences → General → Editors → Text Editors → Spelling → deshabilitar "Enable Spell Checking"`.
* Para comentar o descomentar rápidamente un bloque de código:

  * Seleccionarlo y pulsar **Ctrl + Shift + C** o **Ctrl + 7**.

---

## **14. Dar formato al código**

En Java, el código se organiza en **bloques** delimitados por llaves `{ ... }`. Para mejorar la legibilidad se utiliza la **indentación** (sangrado), moviendo el código hacia la derecha dentro de cada bloque.

Ejemplo sin formatear:

```java
public static void main(String[] args) {
        int x=3;
    System.out.print("Este código no está");
System.out.print(" muy bien ");
            System.out.print("tabulado");
  x=7;
}
```

Ejemplo formateado automáticamente en Eclipse:

```java
public static void main(String[] args) {
    int x = 3;
    System.out.print("Este código no está");
    System.out.print(" muy bien ");
    System.out.print("tabulado");
    x = 7;
}
```

### Cómo dar formato al código en Eclipse:

* Pulsar **Ctrl + Shift + F**.
* O bien: `Menú Source → Format` o `Botón derecho → Source → Format`.

Además, Eclipse permite configurar el estilo de formateo:

* `Window → Preferences → Java → Code Style → Formatter`.
* Crear un nuevo perfil y editar opciones como:

  * Desactivar *Enable block comment formatting* (para que no toque los comentarios de varias líneas).
  * Desactivar *Enable line comment formatting* (para que no toque los comentarios de una sola línea).

---

## **15. Ejecución de programas en Java**

Solo se pueden ejecutar los programas que contienen el método principal:

```java
public static void main(String[] args)
```

Este método es el punto de entrada de toda aplicación Java.

Una de las ventajas de Eclipse es que permite ejecutar el programa **sin salir del entorno**. Hay varias formas de hacerlo:

* En el **Package Explorer**:
  Seleccionar el archivo `.java` → Botón derecho → `Run As → Java Application`.

* Desde la **barra de herramientas**:
  Pulsar en el icono ▶️ (triángulo blanco).
  Si el programa ya se ejecutó antes, bastará con hacer clic directamente en ese botón.
  Si no, abrir el menú desplegable (triángulo negro) y elegir `Run As → Java Application`.

El resultado aparecerá en la **vista Consola**, normalmente en la parte inferior del entorno.
Ahí se muestran los mensajes generados por `System.out.println()` o por la JVM.

**Personalización de la consola:**
Para cambiar el tamaño o la fuente:

```
Window → Preferences → General → Appearance → Colors and Fonts → Debug → Console Font
```

> 💡 Consejo: si no ves la consola, puedes abrirla desde
> `Window → Show View → Console`.

---

## **16. Errores**

Una de las mayores ventajas de Eclipse es su **compilación incremental**, que analiza el código en tiempo real.
Los errores aparecen a medida que se escribe, igual que un corrector ortográfico.

Eclipse diferencia entre:

| Tipo                      | Símbolo                 | Color    | Significado                                                              |
| ------------------------- | ----------------------- | -------- | ------------------------------------------------------------------------ |
| **Error**                 | ❌ (X roja)              | Rojo     | Impide compilar el programa.                                             |
| **Advertencia (Warning)** | ⚠️ (triángulo amarillo) | Amarillo | No detiene la compilación, pero señala algo potencialmente problemático. |

Si un proyecto tiene errores:

* En el **Package Explorer**, el proyecto mostrará una **X roja**.
* Si solo tiene advertencias, aparecerá un **triángulo amarillo**.
* En el código fuente, se marcan en el margen izquierdo junto con una **bombilla 💡**, que sugiere correcciones automáticas.

---

### 🧩 Ejemplo de advertencia

```java
public static void main(String[] args) {
    int x = 3; // The value of the local variable x is not used
    x = 7;  
}
```

🔸 Eclipse avisa porque `x` se declara pero nunca se usa.

---

### 🧨 Ejemplo de error de compilación

```java
public static void main(String[] args) {
    int x = 3;
    y = 7; // y cannot be resolved to a variable
}
```

🔹 La variable `y` no está declarada, por lo que el compilador no puede continuar.

---

### ⚙️ Errores de ejecución (Runtime errors)

Cuando no hay errores de compilación, el programa puede ejecutarse, pero aún pueden surgir **errores en tiempo de ejecución** que detienen la ejecución del código.

Ejemplo:

```java
public class EjemploErrorEjecucion {
    public static void main(String[] args) {        
        System.out.println(5 / 0);
    }
}
```

Salida en la consola:

```
Exception in thread "main" java.lang.ArithmeticException: / by zero
    at EjemploErrorEjecucion.main(EjemploErrorEjecucion.java:6)
```

**Interpretación:**

* Tipo de error: `ArithmeticException`
* Causa: `/ by zero` (división entre 0)
* Localización: archivo `EjemploErrorEjecucion.java`, línea 6

---

## **17. Ayudas al escribir código**

Eclipse incluye múltiples herramientas de asistencia que hacen más eficiente la escritura.

---

### **17.1 Esquema flotante (Quick Outline)**

Permite ver un resumen de todos los métodos, atributos y estructuras de una clase, facilitando la navegación dentro del código.

* Menú: `Navigate → Quick Outline`
* Atajo: **Ctrl + O**

> Ideal para clases largas: permite saltar rápidamente a cualquier método con doble clic.

---

### **17.2 Asistente de contenido (Content Assist)**

Ayuda a escribir código más rápido y con menos errores.

* Atajo: **Ctrl + Espacio**
* Funciona mientras se escribe, mostrando sugerencias de:

  * Métodos, variables y clases disponibles.
  * Plantillas de código (ver siguiente punto).

Ejemplo:

1. Escribir `sy`
2. Pulsar **Ctrl + Espacio**
3. Aparece una lista de sugerencias → elegir `System`
4. Escribir `.` → aparecen los miembros de `System`
5. Elegir `out`
6. Escribir `.` → aparecen los métodos → elegir `println`

> 💡 Consejo: si el Content Assist no aparece, revisar en
> `Window → Preferences → Java → Editor → Content Assist`.

---

### **17.3 Plantillas de código (Code Templates)**

Las plantillas sustituyen abreviaturas por fragmentos completos de código.

* Ejemplo clásico:
  Escribir `sysout` → **Ctrl + Espacio**
  Eclipse inserta automáticamente:

  ```java
  System.out.println();
  ```

* Existen muchas más (`main`, `for`, `while`, etc.), y se pueden crear nuevas desde:
  `Window → Preferences → Java → Editor → Templates`

---

## **18. Modificar las preferencias del editor**

Eclipse permite modificar casi cualquier aspecto del editor de código Java, desde su comportamiento hasta su apariencia.
Estas configuraciones se gestionan desde:

```
Window → Preferences → Java → Editor
```

Desde este menú se accede a numerosos apartados, que detallamos a continuación:

---

### **18.1 Opciones generales**

En `Java → Editor` encontramos ajustes básicos del editor.

* **Report problems as you type**:
  Activa la comprobación en tiempo real del código (muestra subrayados rojos o amarillos).
  
* **Bracket highlighting → Matching bracket**:
  Permite que, al seleccionar una llave `{`, paréntesis `()` o corchete `[]`, Eclipse resalte su pareja correspondiente.
  Esto ayuda a identificar fácilmente dónde empieza y termina cada bloque de código.

> 💡 *Consejo*: estas funciones son muy útiles, la primera evita esperar a compilar para detectar errores y la segunda evita que nos perdamos entre llaves o paréntesis mal cerrados.

---

### **18.2 Realizar acciones al guardar (Save Actions)**

Eclipse puede realizar acciones automáticas cada vez que guardamos un archivo (`Ctrl + S`).
Esto se configura en:

```
Window → Preferences → Java → Editor → Save Actions
```

Opciones más interesantes:

* **Format source code**: formatea automáticamente el código con sangrías correctas.
* **Organize imports**: añade o elimina automáticamente las sentencias `import`.

  * Si se importan varios elementos del mismo paquete, Eclipse puede agruparlos usando `*`.
  * El número mínimo de importaciones necesarias para sustituir por `*` se configura en:
    `Window → Preferences → Java → Code Style → Organize Imports`.

> 🔹 Ejemplo:
>
> ```java
> import java.util.*;
> ```

---

### **18.3 Asistente de contenido (Content Assist)**

Configurable en:

```
Window → Preferences → Java → Editor → Content Assist
```

Funciones más importantes:

* **Hide forbidden references**: oculta en la ayuda elementos obsoletos o prohibidos.

* **Auto activation delay**: tiempo de espera (en milisegundos) para mostrar sugerencias.

  * Recomendado: `0` para que aparezcan al instante.

* **Auto activation triggers for Java**: define los caracteres que activan el autocompletado.

  * Por defecto, el punto (`.`).
  * Ejemplo: al escribir `System.` se despliega la lista de miembros (`out`, `err`, `in`, etc.).

* **Auto activation triggers for Javadoc**: por defecto `@`.

  * Al escribir `@` en un comentario Javadoc, aparecen etiquetas sugeridas como `@param`, `@return`, `@author`.

---

### **18.4 Coloreado de la sintaxis (Syntax Coloring)**

Permite personalizar el color y estilo de cada elemento del lenguaje: palabras clave, comentarios, literales, etc.
Ruta:

```
Window → Preferences → Java → Editor → Syntax Coloring
```

> 💡 *Recomendación*: usar un esquema de colores con buen contraste (especialmente en el tema oscuro) para evitar confusión entre comentarios y código activo.

---

### **18.5 Marcar apariciones (Mark Occurrences)**

Funcionalidad muy útil que resalta todas las apariciones de una variable, método o clase cuando el cursor se sitúa sobre su nombre.
Activa o desactiva esta opción en:

```
Window → Preferences → Java → Editor → Mark Occurrences
```

Por defecto, el color de resaltado es **gris claro**, pero se puede personalizar.
Facilita enormemente la depuración y comprensión del código.

> 💡 *Recomendación*: mantenerlo activado, salvo en equipos con bajo rendimiento.

#### 🔹 Uso manual si está desactivado
Si la opción **“Mark Occurrences”** está desactivada, Eclipse permite **buscar y resaltar manualmente todas las referencias** a una variable, método o clase:

- **Atajo:**  
  ```
  Ctrl + Shift + U
  ```
- **Menú contextual:**  
  ```
  Click derecho → References → File / Project / Workspace
  ```

Esto realiza una **búsqueda puntual** (no dinámica) y muestra las referencias tanto **resaltadas en el editor** como **listadas en la vista *Search***, que se abre automáticamente en la parte inferior del entorno.  
Desde esta vista se pueden **navegar fácilmente por todas las apariciones** localizadas dentro del archivo, el proyecto o el workspace completo.

#### 🔹 Diferencias entre ambos modos

| Modo | Tipo de búsqueda | Actualización | Alcance | Resultado | Ideal para... |
|------|------------------|----------------|----------|-------------|----------------|
| **Mark Occurrences** | Automática | Dinámica (al mover el cursor) | Archivo actual | Resalta en el editor | Revisar rápidamente el uso de variables o métodos |
| **Search References (`Ctrl + Shift + U`)** | Manual | Estática (búsqueda puntual) | Archivo, proyecto o workspace | Resalta y muestra resultados en la vista *Search* | Analizar dependencias o apariciones en todo el proyecto |

#### 🔹 Otros atajos relacionados

| Acción | Atajo | Descripción |
|--------|--------|-------------|
| **Buscar referencias en archivo actual** | `Ctrl + Shift + U` | Muestra todas las apariciones del símbolo seleccionado y abre la vista *Search* |
| **Buscar referencias en el proyecto** | `Ctrl + Alt + G` | Busca la variable o método en todo el proyecto |
| **Abrir declaración** | `Ctrl + Click` | Salta a la definición de la variable o método |
| **Mostrar jerarquía de llamadas (Call Hierarchy)** | `Ctrl + Alt + H` | Muestra quién llama a un método y a quién llama ese método |

✅ **Conclusión:**  
> “Mark Occurrences” es la forma más rápida de resaltar automáticamente las apariciones de un símbolo en el código.  
> Cuando se necesita un análisis más completo, puede complementarse con la búsqueda manual de referencias (`Ctrl + Shift + U`), que además de resaltar las coincidencias, **abre la vista *Search*** para listar y navegar entre todas las referencias encontradas.

---

### **18.6 Apartado tecleo (Typing)**

Ubicación:

```
Window → Preferences → Java → Editor → Typing
```

Aquí se definen ayudas automáticas mientras escribimos código:

* **Auto close**:
  Cierra automáticamente comillas, paréntesis, llaves y corchetes.
  Evita errores comunes de sintaxis.

* **Auto insert semicolons and braces**:
  Inserta automáticamente `;` o `{}`.
  
> 💡 *Recomendación*:  No se recomienda activar el de `;`, ya que Eclipse no siempre determina su posición correcta.

* **Indentation on Enter**:
  Ajusta automáticamente la sangría al pulsar `Enter`.

Ejemplo:

```java
if (a < 8) {   // Al pulsar Enter:
    // Cursor se coloca aquí si la opción está activada
// Aquí si está desactivada
}
```

Además, Eclipse puede **reajustar la indentación y los imports al pegar código**.

---

### **18.7 Plantillas (Templates)**

Las plantillas convierten abreviaturas en fragmentos completos de código.
Ruta:

```
Window → Preferences → Java → Editor → Templates
```

Ejemplo clásico:

* Escribir `sysout` → **Ctrl + Espacio**
  Eclipse inserta automáticamente:

  ```java
  System.out.println();
  ```

Cada plantilla puede incluir **variables** entre `${}` que se sustituyen según el contexto:

| Variable            | Descripción                                           |
| ------------------- | ----------------------------------------------------- |
| `${word_selection}` | Texto seleccionado actualmente.                       |
| `${}`               | Espacio vacío editable.                               |
| `${cursor}`         | Posición final del cursor tras insertar la plantilla. |

📘 **Ejemplo explicado:**

Plantilla por defecto `sysout` equivale a:

```java
System.out.println(${word_selection}${});${cursor}
```

* Si hay texto seleccionado, lo incluye dentro del `println`.
* El cursor queda entre paréntesis para escribir más texto.
* Al pulsar *Tab*, salta a la posición final.

📁 **Ruta para gestionar las plantillas:**

```
Window → Preferences → Java → Editor → Templates
```

Desde esta ventana se pueden **consultar las plantillas existentes**, **editar las ya creadas**, **importar o exportar plantillas** y **añadir nuevas plantillas personalizadas** que se insertan al escribir su abreviatura y pulsar `Ctrl + Espacio`.

---

### **18.8 Estilo del código (Code Style)**

Ubicación:

```
Window → Preferences → Java → Code Style
```

Opciones destacadas:

* **Use ‘is’ prefix for boolean getters**:
  Hace que los métodos que devuelven `boolean` empiecen con `is` (por ejemplo, `isVisible()`).

* **Add @Override annotation**:
  Añade automáticamente la anotación `@Override` al sobrescribir métodos.
  Esto ayuda al compilador a detectar errores si el método no existe en la superclase.

* **Exception variable name**:
  Define el nombre por defecto de las variables `catch`, normalmente `e`.

> 💡 *Ejemplo práctico*:
>
> ```java
> try {
>     int x = 5 / 0;
> } catch (ArithmeticException e) {
>     System.out.println("Error: " + e.getMessage());
> }
> ```

---

✅ **Resumen del apartado 18**

| Área                 | Qué permite configurar                              |
| -------------------- | --------------------------------------------------- |
| **General**          | Errores en tiempo real y resaltado de llaves        |
| **Save Actions**     | Formato y organización automática del código        |
| **Content Assist**   | Sugerencias inteligentes y autocompletado           |
| **Syntax Coloring**  | Colores y estilos de sintaxis                       |
| **Mark Occurrences** | Resalta todas las apariciones de un elemento        |
| **Typing**           | Cierre automático de símbolos y sangría             |
| **Templates**        | Creación y gestión de plantillas de código          |
| **Code Style**       | Reglas de estilo, anotaciones y nombres por defecto |

---

## **19. Depuración**

La **depuración (debug)** es una herramienta fundamental que permite ejecutar un programa paso a paso, observar el valor de las variables en cada momento y detectar fácilmente los errores lógicos o de ejecución.

---

### **Paso 1. Colocar los breakpoints (puntos de interrupción)**

Un **breakpoint** indica la línea de código donde el programa se detendrá durante la ejecución.
En ese punto podrás **analizar el contenido de las variables, ejecutar paso a paso** o incluso **modificar valores**.

* Para colocar un breakpoint: haz **doble clic** en el margen izquierdo del editor (junto al número de línea).
  Aparecerá un **círculo azul** indicando el punto de parada.
* Para eliminarlo, vuelve a hacer doble clic o usa:
  `Botón derecho → Toggle Breakpoint`.

> 💡 Consejo: usa breakpoints en las zonas donde sospeches un error o quieras comprobar cómo cambia el valor de una variable.

---

### **Paso 2. Abrir la perspectiva Debug**

Para visualizar correctamente las herramientas de depuración, abre la **perspectiva Debug**:

```
Window → Perspective → Open Perspective → Debug
```

También se puede acceder desde los **iconos de perspectivas** en la esquina superior derecha del entorno.

Esta perspectiva incluye varias **vistas importantes**:

---

#### 🧭 **Vista Debug**

Muestra los programas que están en ejecución o ya han finalizado.
Puedes eliminar ejecuciones antiguas con:

* `Remove Launch` (borra la seleccionada)
* `Remove All Terminated Launches` (borra todas las terminadas)

---

#### 🔍 **Vista Variables**

Aquí se muestra cada variable, su tipo y su valor actual.
También puedes:

* Colocar el ratón sobre una variable para ver su valor.
* Editar un valor directamente durante la depuración.
* Activar **Show Logical Structure** para ver el contenido interno de estructuras (listas, mapas, arrays...).

---

#### 🎯 **Vista Breakpoints**

Lista todos los puntos de interrupción del proyecto.
Desde aquí puedes:

* Activar/desactivar breakpoints sin borrarlos.
* Configurar condiciones o contadores (*Hit count*).
* Borrar todos los breakpoints con un clic.

---

#### 🧮 **Vista Expressions**

Permite **añadir expresiones o variables** para evaluarlas durante la depuración.
Ejemplo: añadir `i % 3` para ver cómo cambia en cada iteración del bucle.

---

### **Paso 3. Empezar a depurar**

Para iniciar la depuración:

* Haz clic en el icono del **bicho verde 🐞 (Debug)** junto al botón de *Run*.
* Si Eclipse pregunta si quieres cambiar a la perspectiva Debug, acepta y marca
  “Remember my decision”.

Si la aplicación no comienza, abre el menú del triángulo negro junto al icono y elige
`Debug As → Java Application`.

El programa se detendrá en el primer breakpoint, y la línea activa aparecerá **resaltada en verde**.

---

### **Controles básicos del depurador**

| Opción                      | Tecla | Descripción                                    |
| --------------------------- | ----- | ---------------------------------------------- |
| ▶️ **Resume**               | F8    | Continúa hasta el siguiente breakpoint.        |
| ⏹ **Terminate**             | —     | Detiene completamente la ejecución.            |
| ↪️ **Step Over**            | F6    | Ejecuta la línea actual sin entrar en métodos. |
| ↘️ **Step Into**            | F5    | Entra dentro del método llamado en esa línea.  |
| ↩️ **Step Return**          | F7    | Termina el método actual y vuelve al anterior. |
| 🚫 **Skip All Breakpoints** | —     | Desactiva temporalmente todos los breakpoints. |

---

### **Configuración avanzada de Breakpoints**

* **Hit Count**: se detiene solo cuando el breakpoint se ha ejecutado *n* veces.
* **Conditional**: se detiene solo si se cumple una condición lógica (`x == 0`, `i % 10 == 0`, etc.).
* **Suspend when value changes**: pausa la ejecución cuando cambia el valor de una variable.

> 💡 Ejemplo: detenerse cuando `x` cambia de valor en un bucle `for`.

---

### **Formateador de detalles (Detail Formatter)**

Permite personalizar la forma en que Eclipse muestra los valores de los objetos.
Ruta:

```
Window → Preferences → Java → Debug → Detail Formatters
```

Ejemplo:

Para la clase `Vehicle`, puedes mostrar su información formateada así:

```java
"Número de ruedas: " + wheelCount + 
"\nVelocidad: " + speed +
"\nColor: " + colour;
```

Activando esta opción, cuando inspecciones un objeto `Vehicle`, Eclipse mostrará directamente sus valores de forma legible.

---

✅ **Resumen de depuración**

* La depuración permite ejecutar el programa paso a paso.
* Se detiene donde hay *breakpoints*.
* Permite inspeccionar y modificar variables.
* Facilita detectar errores lógicos o de ejecución.

---

## **20. Creación de una librería `.jar`**

Una **librería JAR** (Java ARchive) es un archivo comprimido que agrupa **clases compiladas (`.class`)**, **recursos** y **metadatos**.
Sirve para **reutilizar y distribuir código Java** de manera organizada y portable.

---

### **¿Qué contiene un archivo `.jar`?**

* Clases Java compiladas (`.class`)
* Archivos de configuración (`.properties`, `.xml`, etc.)
* Recursos (imágenes, sonidos, textos…)
* Un fichero `MANIFEST.MF` que describe el contenido del JAR.

---

### **Ventajas**

* ✅ Facilita la **reutilización de código** (por ejemplo, utilidades o librerías propias).
* ✅ Mejora la **modularidad** y **mantenibilidad**.
* ✅ Simplifica la **distribución de proyectos** Java.
* ✅ Puede integrarse fácilmente en otros proyectos.

---

### **Cómo crear una librería `.jar` en Eclipse**

1. Selecciona el proyecto en el **Package Explorer**.
2. Botón derecho → `Export`.
3. En el asistente:
   `Java → JAR file` → **Next**.
4. Marca las clases o paquetes que quieras incluir.
5. Indica la **carpeta destino** donde se guardará el `.jar`.
6. Pulsa **Finish**.

Se generará un archivo `.jar` con las clases compiladas.

---

### **Cómo usar una librería `.jar` en otro proyecto**

1. Selecciona el proyecto destino.
2. Botón derecho → `Build Path → Configure Build Path`.
3. En la pestaña **Libraries**, selecciona el tipo de incorporación:

* **Add JARs** → si la librería está dentro del *workspace*.
* **Add External JARs** → si está fuera del *workspace* (por ejemplo, en tu escritorio o carpeta compartida).

4. Pulsa **Apply and Close**.

A partir de ese momento, las clases del `.jar` estarán disponibles en el proyecto.

---

### **Relación con Maven y Gradle**

En proyectos más avanzados, el manejo de librerías JAR se automatiza con **Maven** o **Gradle**, herramientas que:

* Descargan las dependencias automáticamente.
* Evitan conflictos de versiones.
* Generan `.jar` o `.war` al compilar.

---

✅ **Resumen**

| Concepto             | Descripción                                           |
| -------------------- | ----------------------------------------------------- |
| `.jar`               | Archivo que agrupa clases compiladas y recursos.      |
| **Ventajas**         | Reutilización, modularidad y distribución del código. |
| **Crear JAR**        | Exportar proyecto desde Eclipse.                      |
| **Usar JAR**         | Añadirlo al *Build Path*.                             |
| **Gestión avanzada** | Maven y Gradle automatizan dependencias.              |

---
