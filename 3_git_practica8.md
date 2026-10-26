(DCS.2025.12.04 - En preparación, pero es muy largo... no lo he terminado, lo dejo para otro año... guardo la base, aunque falta modificarla)

# Práctica 8 - Trabajo en grupo de 4-5 alumnos: Calculadora con ramas y fusión en Git-GitHub

**Objetivo:**
Trabajar en equipo usando **ramas de Git**, realizar cambios en paralelo sobre el mismo proyecto y gestionar al final una **fusión con conflicto** que deberá resolver el coordinador del grupo.

**Tecnologías:**
Java (POO), Eclipse, Git, GitHub.

**Duración orientativa:** 50–60 minutos.

---

## 1. Organización del grupo y roles

Se trabaja en grupos de **4-5 personas**.

Lista de componentes de cada grupo: *(si falta algún componente, su tarea la asumirá otro componente del grupo)*

* **GRUPO 1**:
   - Coordinador: **Jorge**
   - Programadora1: **Rocío**
   - Programador2: **Aidan**
   - Programador3: **Alex**
   - Programador4: **Jhonatan**

* **GRUPO 2**:
   - Coordinadora: **Andrea**
   - Programadora1: **Alba**
   - Programador2: **Alejandro**
   - Programador3: **Antonio Mancilla**
   - Programadora4: **Claudia**

* **GRUPO 3**:
   - Coordinador: **Daniel Gallardo**
   - Programador1: **Hugo**
   - Programador2: **Dani Vázquez**
   - Programador3: **Antonio**
   - Programador4: **Ángel**

* **GRUPO 4**:
   - Coordinadora: **Sara**
   - Programador1: **Adrián**
   - Programador2: **Álvaro**
   - Programador3: **Oleh**
   - Programador4: *(alguien del grupo hace también esta tarea)*

* **GRUPO 5**:
   - Coordinador: **Daniel Madrid**
   - Programador1: **Israel**
   - Programador2: **Sergio**
   - Programador3: **Juanma**
   - Programador4: **Federico**

* **GRUPO 6**:
   - Coordinador: **Santiago**
   - Programadora1: **Raquel**
   - Programadora2: **Esther**
   - Programador3: **Samuel**
   - Programador4: **Cristian**

Roles:

1. **Coordinador/a**

   * Crea el repositorio en GitHub.
   * Crea el proyecto Java en Eclipse con el código base.
   * Inicializa Git en el proyecto, hace el primer commit y sincroniza con GitHub.
   * Agrega como colaboradores al resto del grupo.
   * Se encarga de **fusionar las ramas** y resolver los conflictos.

2. **Programadores/as** (4 alumnos restantes)

   * Cada uno trabaja en una **rama distinta**.
   * Implementan pequeñas partes de la calculadora.
   * Suben sus cambios a GitHub en su rama.

> 🔎 Importante: todo el trabajo debe quedar registrado con **commits claros** y **ramas diferenciadas**.

---

## 1. Descripción del programa a desarrollar

Se va a implementar una **calculadora por consola**, orientada a objetos, con estas características:

* Una clase `Calculadora` que tendrá las siguientes características:

  * `resultado` *(tipo `Double`)*, que empezará en `null` y se actualizará tras cada operación.
  * `numeros` *(ArrayList<Double>)* con todos los números introducidos.
  * `operaciones` *(ArrayList<String>)* con las operaciones realizadas (`+`, `-`, `*`, `/`).

* Una clase `Consola` para gestionar:

  * El **menú** de opciones.
  * La lectura de números y opciones por teclado.
  * La **limpieza “simulada”** de la consola *(imprimiendo varias líneas en blanco)*.

* La aplicación ofrecerá un **menú en bucle**, con opciones:

  1. Sumar
  2. Restar
  3. Multiplicar
  4. Dividir
  5. Mostrar resultado e historial
  6. Resetear calculadora (borrar resultado e historial)
  7. Salir

* Comportamiento clave de las operaciones:

  * Si `resultado` es `null`:

    * Se piden **dos números**.
    * Se realiza la operación entre esos dos números.

  * Si `resultado` NO es `null`:

    * Se muestra el resultado actual.
    * Solo se pide **un número nuevo**.
    * Se aplica la operación entre `resultado` y ese nuevo número.

* Después de cada operación:

  * Se actualiza `resultado`.
  * Se añade el número o números a la lista `numeros`.
  * Se añade el símbolo de la operación a `operaciones`.
  * Se muestra por pantalla:
    `RES = <resultado_actual>`

* En la opción **“Mostrar resultado e historial”**:

  * Se mostrará una expresión tipo:
    `RES = 4 + 11 - 8 x 2 = 14`

---

## 2. Código base (lo prepara el coordinador)

El coordinador creará el proyecto en Eclipse y pegará el siguiente código base simplificado.

### 2.1 Clase `Calculadora`

```java
import java.util.ArrayList;
import java.util.List;

public class Calculadora {

    private Double resultado;                 // null al inicio o tras reset
    private List<Double> numeros;
    private List<String> operaciones;         // "+", "-", "*", "/"

    public Calculadora() {
        this.resultado = null;
        this.numeros = new ArrayList<>();
        this.operaciones = new ArrayList<>();
    }

    // Getters y setters básicos

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public List<Double> getNumeros() {
        return numeros;
    }

    public List<String> getOperaciones() {
        return operaciones;
    }

    // Añadir número y operación al historial
    public void agregarOperacion(Double numero, String operacion) {
        numeros.add(numero);
        operaciones.add(operacion);
    }

    // Resetear calculadora
    public void reset() {
        resultado = null;
        numeros.clear();
        operaciones.clear();
    }

    // TODO: implementar métodos sumar, restar, multiplicar, dividir
    // según la lógica acordada
    // (estos métodos los completarán distintos miembros del grupo)

    // TODO: toString debe devolver el historial completo:
    // Por ejemplo: "RES = 4 + 11 - 8 x 2 = 14"
    @Override
    public String toString() {
        // De momento, implementación simple:
        if (resultado == null) {
            return "RES = (sin operaciones todavía)";
        } else {
            return "RES = " + resultado;
        }
    }
}
```

---

### 2.2 Clase `Consola`

```java
import java.util.Scanner;

public class Consola {

    private static final Scanner sc = new Scanner(System.in);

    // Constante con el menú
    private static final String[] MENU = {
        "0. Salir",
        "1. Sumar",
        "2. Restar",
        "3. Multiplicar",
        "4. Dividir",
        "5. Mostrar resultado completo",
        "6. Reset"
    };

    // Constante para simular la limpieza de la consola
    private static final int LINEAS_LIMPIEZA = 50;

    // Mostrar el menú
    public static void mostrarMenu() {
        System.out.println("===== CALCULADORA =====");
        for (String opcion : MENU) {
            System.out.println(opcion);
        }
        System.out.print("Seleccione una opción: ");
    }

    // Pedir una opción del menú y validarla
    public static int pedirOpcion(int min, int max) {
        int opcion;

        while (true) {
            String entrada = sc.nextLine();

            try {
                opcion = Integer.parseInt(entrada);

                if (opcion < min || opcion > max) {
                    System.out.print("Opción fuera de rango. Inténtelo de nuevo: ");
                } else {
                    return opcion;
                }

            } catch (NumberFormatException e) {
                System.out.print("Debe introducir un número entero. Inténtelo de nuevo: ");
            }
        }
    }

    // Pedir un número (entero o decimal)
    public static double pedirNumero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine();

            try {
                return Double.parseDouble(entrada);

            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debe introducir un número.");
            }
        }
    }

    // Limpiar la consola simulando scroll
    public static void limpiarConsola() {
        for (int i = 0; i < LINEAS_LIMPIEZA; i++) {
            System.out.println();
        }
    }

    // Pausar hasta que el usuario pulse ENTER
    public static void esperarEnter() {
        System.out.println("Pulse ENTER para continuar...");
        sc.nextLine();
    }
}
```

---

### 2.3 Clase `Main`

```java
public class Main {

    public static void main(String[] args) {

        Calculadora calc = new Calculadora();
        boolean salir = false;

        while (!salir) {
            Consola.limpiar();
            Consola.mostrarMenu();
            int opcion = Consola.pedirOpcion();

            switch (opcion) {
                case 1:
                    // TODO: lógica para sumar (se completará en una rama)
                    break;
                case 2:
                    // TODO: lógica para restar
                    break;
                case 3:
                    // TODO: lógica para multiplicar
                    break;
                case 4:
                    // TODO: lógica para dividir
                    break;
                case 5:
                    // TODO: mostrar resultado e historial con toString()
                    break;
                case 6:
                    // TODO: resetear calculadora
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    Consola.pausa();
            }
        }

        System.out.println("Fin del programa.");
    }
}
```

---

## 3. Trabajo con Git y GitHub (coordinador)

1. En GitHub, crea un repositorio llamado `CalculadoraRamasGrupo<N>`.

2. En Eclipse, crea el proyecto Java y pega el código anterior.

3. Desde la carpeta del proyecto (en terminal): hacer una primera versión con la descripción `Versión inicial calculadora con código base` y subirla al remoto.

4. Agrega como **colaboradores** en GitHub a los otros 4 compañeros del grupo.

---

## 4. Ramas y tareas de cada miembro del grupo

Todos los miembros del grupo clonarán el repositorio en su PC:

> 💡 Cada alumno trabajará en una rama distinta, con su propio commit.

### Alumno 1 – Rama `feature-resultado-reset`

* Crear rama: feature-resultado-reset

* Implementar en `Main`:

  * La opción **5** → mostrar resultado e historial usando `calc.toString()`.

* Hacer commit y push: "Implementación de la opción 5: Mostrar resultado e historial"

* Implementar en `Main`:

  * La opción **6** → llamar a `calc.reset()` y mostrar un mensaje indicando que la calculadora se ha reiniciado.

* Hacer commit y push: "Implementación de la opción 6: Reset y mensaje de confirmación"

---

### Alumno 2 – Rama `feature-suma-resta`

* Crear rama: feature-suma-resta

* Implementar en `Calculadora` el método sumar, que debe re

  ```java
  public void sumar(double num) {
      //TODO: Si resultado es nulo, asignar num a resultado, sino sumar num a resultado.
      //TODO: Llamar al método agregarOperacion pasándole num y la operación
  }

  public void restar(double num) {
      //TODO: Si resultado es nulo, asignar num a resultado, sino sumar num a resultado.
      //TODO: Llamar al método agregarOperacion pasándole num y la operación
      if (resultado == null) {
          resultado = num; // Por ejemplo, primera operación resta
      } else {
          resultado = resultado - num;
      }
      agregarOperacion(num, "-");
  }
  ```

* Implementa en `Main` la lógica de menú para sumar y restar:

  * Si `calc.getResultado()` es `null`, pedir dos números y hacer la operación.
  * Si NO es `null`, pedir un solo número y usar `calc.sumar()` o `calc.restar()`.

* Commit + push:

  ```bash
  git add .
  git commit -m "C: Implementadas operaciones de suma y resta"
  git push -u origin feature-suma-resta
  ```

---

### Alumno 3 – Rama `feature-multi-div`

* Crea rama:

  ```bash
  git switch -c feature-multi-div
  ```

* Implementa en `Calculadora`:

  ```java
  public void multiplicar(double num) {
      if (resultado == null) {
          resultado = num;
      } else {
          resultado = resultado * num;
      }
      agregarOperacion(num, "*");
  }

  public void dividir(double num) {
      if (num == 0) {
          System.out.println("No se puede dividir entre 0.");
          return;
      }
      if (resultado == null) {
          resultado = num;
      } else {
          resultado = resultado / num;
      }
      agregarOperacion(num, "/");
  }
  ```

* Implementa en `Main` la lógica de menú de multiplicar y dividir (mismo esquema que suma/resta).

* Commit + push:

  ```bash
  git add .
  git commit -m "D: Implementadas operaciones de multiplicación y división"
  git push -u origin feature-multi-div
  ```

---

### Alumno 4 – Rama `feature-historial-formato1`

* Crea rama:

  ```bash
  git switch -c feature-historial-formato1
  ```

* Implementa `toString()` en `Calculadora` para mostrar el historial:

  ```java
  @Override
  public String toString() {
      if (resultado == null || numeros.isEmpty()) {
          return "RES = (sin operaciones todavía)";
      }

      StringBuilder sb = new StringBuilder();
      sb.append("RES = ");

      // Suponemos que numeros y operaciones tienen la misma longitud
      // y que el primer número es el origen del cálculo
      // Ejemplo: numeros = [4, 11, 8, 2], operaciones = ["+", "-", "*"]

      if (!numeros.isEmpty()) {
          sb.append(numeros.get(0));
      }

      for (int i = 1; i < numeros.size(); i++) {
          String op = operaciones.get(i - 1);
          double num = numeros.get(i);

          // Cambiar * por x para mostrarla mejor
          if (op.equals("*")) {
              sb.append(" x ");
          } else {
              sb.append(" ").append(op).append(" ");
          }

          sb.append(num);
      }

      sb.append(" = ").append(resultado);

      return sb.toString();
  }
  ```

* Commit + push:

  ```bash
  git add .
  git commit -m "E: Implementado historial completo en toString (formato 1)"
  git push -u origin feature-historial-formato1
  ```

---

### Alumno 5 – Rama `feature-historial-formato2` (CONFLICTO INTENCIONADO)

Este alumno va a modificar **el mismo método `toString()`** que el alumno 4, para provocar un conflicto al fusionar.

* Crea rama:

  ```bash
  git switch -c feature-historial-formato2
  ```

* Implementa una versión alternativa de `toString()` (mismo método, distinto texto):

  ```java
  @Override
  public String toString() {
      if (resultado == null || numeros.isEmpty()) {
          return "RES = (ninguna operación registrada)";
      }

      StringBuilder sb = new StringBuilder();
      sb.append("RES = ");

      if (!numeros.isEmpty()) {
          sb.append(numeros.get(0));
      }

      for (int i = 1; i < numeros.size(); i++) {
          String op = operaciones.get(i - 1);
          double num = numeros.get(i);

          switch (op) {
              case "+":
                  sb.append(" + ");
                  break;
              case "-":
                  sb.append(" - ");
                  break;
              case "*":
                  sb.append(" * ");
                  break;
              case "/":
                  sb.append(" / ");
                  break;
              default:
                  sb.append(" ? ");
          }

          sb.append(num);
      }

      sb.append(" = ").append(resultado);
      return sb.toString();
  }
  ```

* Commit + push:

  ```bash
  git add .
  git commit -m "F: Implementado historial completo en toString (formato 2)"
  git push -u origin feature-historial-formato2
  ```

> 🔥 **Importante**: ahora hay **dos ramas distintas** que modifican la **misma zona de código** (`toString()`), lo que provocará un **conflicto** al fusionarlas.

---

## 5. Fusión de ramas y resolución de conflicto (coordinador)

El coordinador vuelve a trabajar en su máquina, en la carpeta del proyecto.

1. Actualiza la rama `main` con lo que haya en remoto:

   ```bash
   git switch main
   git pull
   ```

2. Trae las ramas del resto de compañeros:

   ```bash
   git fetch --all
   ```

3. Fusiona, una a una, las ramas **sin conflicto** primero:

   ```bash
   git merge feature-resultado-reset
   git merge feature-suma-resta
   git merge feature-multi-div
   ```

   Si todo va bien, no habrá conflicto en estos merges.

4. Fusiona la primera rama de historial:

   ```bash
   git merge feature-historial-formato1
   ```

   Debería aplicarse sin conflicto (si se hace primero).

5. Ahora intenta fusionar la segunda rama de historial:

   ```bash
   git merge feature-historial-formato2
   ```

   Aquí Git debería mostrar un **conflicto** en `Calculadora.java` (en el método `toString()`).

---

### 5.1 Resolver el conflicto

1. Abrir `Calculadora.java` y localizar las marcas:

   ```text
   <<<<<<< HEAD
   // Versión de toString procedente de main (con formato 1)
   =======
   // Versión alternativa de toString procedente de feature-historial-formato2
   >>>>>>> feature-historial-formato2
   ```

2. Editar el archivo para dejar una **única versión** de `toString()`:

   * Puede mantenerse una versión.
   * Se puede mezclar lo mejor de las dos.
   * Pero al final **solo debe quedar un método toString sin marcas**.

3. Guardar el archivo y marcar el conflicto como resuelto:

   ```bash
   git add src/Calculadora.java
   git commit -m "Merge: combinado historial de operaciones tras resolver conflicto en toString"
   ```

4. Subir todos los cambios a GitHub:

   ```bash
   git push
   ```

---

## 6. Comprobación final

* Ejecutar el programa desde Eclipse.
* Probar varias operaciones:

  * Primera operación con dos números.
  * Operaciones encadenadas usando el `resultado`.
  * Mostrar el resultado e historial.
  * Hacer reset y probar de nuevo.

---


* Convertir esto en **versión examen** (sin tantas pistas ni nombres de ramas).
* O simplificar el código para 1º DAM si crees que van demasiado justos de Java.
