# 6. Pruebas de Software

## 1. Introducción: calidad y por qué probamos.

El desarrollo de software profesional no consiste únicamente en escribir código que **“funcione”**, sino en construir sistemas fiables, mantenibles y preparados para evolucionar. Un programa puede arrancar y, aun así:

* devolver resultados incorrectos,
* fallar con ciertos datos,
* comportarse bien en casos normales pero mal en casos límite,
* dejar de funcionar tras una modificación.

Por eso, en desarrollo de software no basta con programar: **hay que comprobar sistemáticamente que el programa funciona como debe**.

Una **prueba de software** consiste en comprobar el comportamiento de un programa para detectar errores y aumentar la confianza en su calidad. JUnit, por ejemplo, es precisamente un framework pensado para automatizar pruebas en Java, y su documentación oficial lo sitúa dentro del ecosistema actual de pruebas automatizadas sobre la plataforma JUnit. ([docs.junit.org][1])

En este contexto, `las pruebas de software` se convierten en una actividad esencial dentro del ciclo de vida del desarrollo.

Probar un programa no significa simplemente ejecutarlo para ver si **“da error”**, sino diseñar de forma sistemática un **conjunto de casos de prueba** que permitan detectar defectos, validar requisitos y garantizar que el sistema se comporta como se espera ante distintas situaciones.

En el ámbito profesional actual, las pruebas han pasado de ser una fase final del desarrollo a integrarse de forma continua en el proceso de construcción del software. Metodologías modernas como el **desarrollo ágil** y prácticas como **TDD** *(Test-Driven Development)* refuerzan la idea de que probar no es una tarea opcional, sino parte del propio diseño del programa.

Desde el punto de vista de la `Ingeniería del Software`, las pruebas forman parte del **proceso de aseguramiento de la calidad**. No solo permiten detectar errores antes de que el producto llegue al usuario final, sino que:

* Mejoran la confianza en el sistema.

* Facilitan el mantenimiento y la evolución del código.

* Reducen el coste de corrección de errores.

* Sirven como documentación viva del comportamiento esperado del programa.

En esta unidad estudiaremos:

* Qué significa verificar y validar software.

* Cómo se diseñan casos de prueba.

* Qué tipos de pruebas existen.

* Qué técnicas se emplean para probar correctamente.

* Cómo automatizar pruebas en Java utilizando JUnit 5.

El objetivo no es únicamente aprender a usar una herramienta, sino comprender cómo se piensa cuando se prueba software, adoptando una mentalidad profesional orientada a la calidad.

### Idea clave

Probar software no es “ejecutar un programa a ver qué pasa”, sino:

1. pensar qué debería ocurrir,
2. preparar datos de entrada,
3. ejecutar,
4. comparar el resultado real con el esperado.

Eso es un **caso de prueba**.

---

## 2. Verificación y validación

Aquí conviene ser muy claro, porque es un clásico.

### Verificación

Responde a la pregunta:

**¿Estamos construyendo el producto correctamente?**

Consiste en comprobar si el software se ajusta a lo especificado: diseño, reglas, comportamiento esperado, estructura, etc.

### Validación

Responde a la pregunta:

**¿Estamos construyendo el producto correcto?**

Consiste en comprobar si el software realmente satisface las necesidades del usuario o del cliente.

### Ejemplo sencillo

Imagina una aplicación de notas:

* El requisito dice: “La nota debe estar entre 0 y 10”.
* Tu método rechaza valores menores que 0 y mayores que 10.

Si haces una prueba y ves que eso se cumple, estás **verificando**.

Pero si el profesor necesitaba además poder introducir notas con dos decimales y tu aplicación no lo permite, entonces quizá el producto no satisface bien la necesidad real: ahí falla la **validación**.

---

## 3. Qué es una prueba y qué es un caso de prueba

### Prueba

Es el proceso de comprobar el comportamiento de un programa para detectar errores.

### Caso de prueba

Es una situación concreta preparada para probar algo. Incluye:

* datos de entrada,
* condiciones de ejecución,
* resultado esperado.

### Ejemplo

Método:

```java
boolean esPar(int n)
```

Casos de prueba:

* entrada: `0` → esperado: `true`
* entrada: `2` → esperado: `true`
* entrada: `5` → esperado: `false`
* entrada: `-4` → esperado: `true`

---

## 4. Principios básicos de las pruebas

Aquí puedes mantener la idea del material original, pero explicándola mejor.

### 1. Las pruebas muestran la presencia de errores, no su ausencia

Si todos los tests pasan, eso no demuestra que el programa sea perfecto. Solo demuestra que **ha funcionado bien en los casos probados**.

### 2. No se puede probar todo

El número de combinaciones posibles de entrada puede ser enorme. Por eso hay que **seleccionar casos representativos**.

### 3. Conviene probar cuanto antes

Detectar un error en una función recién creada es mucho más barato que detectarlo cuando el programa completo ya depende de ella.

### 4. Los errores suelen concentrarse

No todos los módulos fallan igual. Suele haber partes más problemáticas que otras.

### 5. Los tests deben revisarse

Si siempre repites exactamente las mismas pruebas, llegará un momento en que ya no descubrirán errores nuevos.

### 6. Las pruebas dependen del contexto

No se prueba igual una calculadora, un videojuego, una app bancaria o un sistema médico.

### 7. Que todo pase no significa que el software sea útil

Puede que pase todos los tests y, aun así, no responda a la necesidad real del usuario.

---

## 5. Tipos generales de prueba: estáticas y dinámicas

## Pruebas estáticas

No ejecutan el programa. Revisan artefactos del desarrollo:

* requisitos,
* diseño,
* código fuente leído sin ejecutar,
* documentación.

Ejemplos:

* revisar código en busca de errores lógicos,
* inspeccionar documentación,
* detectar incoherencias en un UML.

## Pruebas dinámicas

Sí ejecutan el software.

Ejemplos:

* ejecutar una función con distintos datos,
* lanzar la aplicación y comprobar salidas,
* verificar que una excepción se lanza cuando corresponde.

JUnit se usa para **pruebas dinámicas automatizadas** en Java. ([docs.junit.org][1])

---

## 6. Niveles de prueba: unitarias, integración, sistema, aceptación y regresión

Aquí está una de las claves que debes dejar muy bien separadas de caja blanca/caja negra.

## 6.1 Pruebas unitarias

Comprueban una unidad pequeña y aislada del software, normalmente:

* un método,
* una clase,
* una función.

### Ejemplo

Probar solo este método:

```java
int sumar(int a, int b)
```

sin ejecutar toda la aplicación.

### Objetivo

Verificar que esa unidad concreta funciona bien por sí sola.

---

## 6.2 Pruebas de integración

Comprueban que varias partes que ya funcionan por separado también funcionan correctamente **juntas**.

### Ejemplo

Tienes:

* una clase `Calculadora`
* una clase `HistorialOperaciones`

Cada una puede pasar sus pruebas unitarias, pero al integrarlas puede aparecer un error.

---

## 6.3 Pruebas de sistema

Comprueban el sistema completo en un entorno parecido al real.

### Ejemplo

Probar una aplicación completa con:

* interfaz,
* base de datos,
* lógica de negocio,
* persistencia.

---

## 6.4 Pruebas de aceptación

Comprueban si el sistema satisface lo que necesita el cliente o usuario final.

### Ejemplo

“El usuario debe poder registrar una nota y verla después en pantalla”.

Si el cliente prueba eso y funciona como esperaba, hablamos de aceptación.

---

## 6.5 Pruebas de regresión

Comprueban que un cambio no ha roto lo que antes funcionaba.

### Ejemplo

Corriges un error en el cálculo de descuentos. Después repites tests antiguos para verificar que no se han roto:

* el total,
* el IVA,
* el cálculo final,
* otros descuentos.

Este es uno de los grandes beneficios de tener tests automatizados: puedes reejecutarlos tras cada cambio. ([docs.junit.org][1])

---

## 7. La gran duda: caja blanca, caja negra y su relación con las pruebas unitarias

## 7.1 Qué significa “caja negra”

En **caja negra** no nos fijamos en cómo está implementado el programa por dentro. Nos importa:

* qué entrada recibe,
* qué salida produce.

Tratamos la unidad como una “caja cerrada”.

### Ejemplo

Método:

```java
boolean esPar(int n)
```

No miro su código interno. Solo pruebo:

* `0` → `true`
* `2` → `true`
* `3` → `false`
* `-8` → `true`

Estoy comprobando el comportamiento externo.

## 7.2 Qué significa “caja blanca”

En **caja blanca** sí nos fijamos en la lógica interna del programa:

* decisiones,
* caminos,
* condiciones,
* bucles,
* ramas del `if`,
* excepciones.

### Ejemplo

Método:

```java
String clasificarEdad(int edad) {
    if (edad < 0) {
        throw new IllegalArgumentException();
    }
    if (edad < 18) {
        return "menor";
    }
    return "adulto";
}
```

Si diseño pruebas pensando en cubrir:

* rama de excepción,
* rama menor,
* rama adulto,

estoy haciendo caja blanca, porque me fijo en la estructura interna.

---

## 7.3 Entonces, ¿una prueba unitaria con JUnit es caja blanca?

**No necesariamente.**

JUnit es una **herramienta para automatizar pruebas**, sobre todo unitarias en este contexto. Pero el enfoque puede ser:

* **unitaria de caja negra**, si pruebas entradas y salidas sin pensar en la implementación;
* **unitaria de caja blanca**, si diseñas los tests para cubrir ramas, condiciones, bucles o excepciones del código interno.

### Resumen

* **Unitaria** responde a: **qué parte del software pruebo**
* **Caja negra / caja blanca** responde a: **cómo diseño esa prueba**

---

## 8. Ejemplos comparativos claros

## 8.1 Ejemplo 1: función `esPar`

Código:

```java
boolean esPar(int n) {
    return n % 2 == 0;
}
```

### Enfoque de caja negra

Pienso en el comportamiento esperado:

* `0` → `true`
* `2` → `true`
* `5` → `false`
* `-4` → `true`

No necesito mirar el código.

### Enfoque de caja blanca

Miro la lógica interna:

```java
return n % 2 == 0;
```

Aquí realmente hay una condición booleana. Intento cubrir resultados de la condición:

* condición verdadera → `2`
* condición falsa → `3`

Es una caja blanca muy sencilla, porque el método es simple.

---

## 8.2 Ejemplo 2: función `factorial`

Código:

```java
int factorial(int n) {
    if (n < 0) {
        throw new IllegalArgumentException("No se admite negativo");
    }

    int resultado = 1;
    for (int i = 1; i <= n; i++) {
        resultado *= i;
    }
    return resultado;
}
```

### Caja negra

Pienso en clases de entrada:

* negativos → excepción
* cero → 1
* positivos pequeños → factorial correcto
* positivos mayores → comprobar resultado

Casos:

* `-1` → excepción
* `0` → `1`
* `1` → `1`
* `5` → `120`

### Caja blanca

Pienso en estructura interna:

* rama del `if` que lanza excepción
* caso donde el `for` no entra (`n = 0`)
* caso donde el `for` entra una vez (`n = 1`)
* caso donde el `for` entra varias veces (`n = 5`)

Aquí se ve muy bien la diferencia.

---

## 8.3 Ejemplo 3: clasificar una nota

Código:

```java
String clasificarNota(double nota) {
    if (nota < 0 || nota > 10) {
        throw new IllegalArgumentException("Nota no válida");
    }

    if (nota < 5) {
        return "Suspenso";
    } else if (nota < 6) {
        return "Aprobado";
    } else if (nota < 7) {
        return "Bien";
    } else if (nota < 9) {
        return "Notable";
    } else {
        return "Sobresaliente";
    }
}
```

### Caja negra

Pienso en intervalos:

* menor que 0 → inválida
* 0 a 4.99 → suspenso
* 5 a 5.99 → aprobado
* 6 a 6.99 → bien
* 7 a 8.99 → notable
* 9 a 10 → sobresaliente
* mayor que 10 → inválida

### Caja blanca

Quiero cubrir todas las ramas del `if / else if / else`:

* excepción por debajo de 0
* suspenso
* aprobado
* bien
* notable
* sobresaliente
* excepción por encima de 10

---

## 9. Técnicas de caja negra

## 9.1 Particiones de equivalencia

Consiste en dividir el dominio de entrada en grupos donde esperamos un comportamiento similar.

### Ejemplo

Método:

```java
String permisoAcceso(int edad)
```

Reglas:

* edad < 0 → inválida
* 0 a 17 → menor
* 18 o más → adulto

Clases de equivalencia:

* inválida: `-5`
* válida menor: `10`
* válida adulta: `25`

No hace falta probar todos los números posibles. Elegimos representantes de cada clase.

---

## 9.2 Análisis de valores límite

Los errores suelen concentrarse en los bordes.

Siguiendo el ejemplo anterior:

* `-1`
* `0`
* `17`
* `18`

Esos valores son más interesantes que `8` o `35`, porque están cerca de los cambios de comportamiento.

### Ejemplo típico

Si una nota válida está entre 0 y 10:

* justo por debajo: `-0.1`
* límite inferior: `0`
* valor interior: `5`
* límite superior: `10`
* justo por encima: `10.1`

---

## 10. Técnicas de caja blanca

## 10.1 Cobertura de sentencias

Objetivo: ejecutar al menos una vez todas las sentencias.

## 10.2 Cobertura de decisiones o ramas

Objetivo: que cada decisión tome al menos una vez el resultado verdadero y falso.

### Ejemplo

```java
if (edad >= 18) {
    return "adulto";
}
return "menor";
```

Necesitas al menos:

* `18` o `20` para la rama verdadera
* `17` para la rama falsa

## 10.3 Prueba de bucles

Interesa comprobar:

* cero iteraciones,
* una iteración,
* varias iteraciones,
* límite del bucle.

### Ejemplo

```java
int sumaHasta(int n) {
    int suma = 0;
    for (int i = 1; i <= n; i++) {
        suma += i;
    }
    return suma;
}
```

Casos útiles:

* `0` → no entra en el bucle
* `1` → una iteración
* `5` → varias iteraciones

---

## 11. JUnit 5: qué es y por qué usarlo

JUnit es el framework más usado en Java para automatizar tests. La plataforma actual distingue varios componentes, y **JUnit Jupiter** es el modelo de programación principal para escribir tests con anotaciones como `@Test` y `@ParameterizedTest`. La guía oficial también documenta el uso de aserciones y pruebas parametrizadas. ([docs.junit.org][1])

### Ventajas

* obliga a pensar casos de prueba,
* automatiza comprobaciones,
* evita repetir pruebas manuales,
* facilita detectar regresiones,
* da disciplina al desarrollo.

---

## 12. Estructura básica de un test con JUnit

### Clase a probar

```java
public class Calculadora {
    public int sumar(int a, int b) {
        return a + b;
    }
}
```

### Clase de test

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CalculadoraTest {

    @Test
    void sumaDosPositivos() {
        Calculadora calc = new Calculadora();
        int actual = calc.sumar(2, 3);
        assertEquals(5, actual);
    }
}
```

---

## 13. Anotaciones principales de JUnit 5

La guía oficial de JUnit Jupiter documenta, entre otras, estas anotaciones y mecanismos de ciclo de vida para las pruebas. ([docs.junit.org][1])

### `@Test`

Marca un método como prueba.

### `@BeforeEach`

Se ejecuta antes de cada test.

### `@AfterEach`

Se ejecuta después de cada test.

### `@BeforeAll`

Se ejecuta una vez antes de todos los tests.

### `@AfterAll`

Se ejecuta una vez al final.

### `@ParameterizedTest`

Permite ejecutar el mismo test con varios datos de entrada.

### `@DisplayName`

Permite dar un nombre más descriptivo al test.

---

## 14. Aserciones más importantes

JUnit Jupiter ofrece aserciones para comprobar condiciones esperadas en los tests. ([docs.junit.org][1])

### `assertEquals`

Comprueba igualdad.

### `assertTrue`

Comprueba que una condición es verdadera.

### `assertFalse`

Comprueba que una condición es falsa.

### `assertNull`

Comprueba que algo es `null`.

### `assertNotNull`

Comprueba que algo no es `null`.

### `assertThrows`

Comprueba que se lanza una excepción.

### `assertArrayEquals`

Comprueba arrays.

---

## 15. Ejemplos completos con JUnit

## 15.1 Ejemplo de caja negra unitaria con JUnit

Método:

```java
public class Numero {
    public boolean esPar(int n) {
        return n % 2 == 0;
    }
}
```

Test:

```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NumeroTest {

    Numero numero = new Numero();

    @Test
    void ceroEsPar() {
        assertTrue(numero.esPar(0));
    }

    @Test
    void dosEsPar() {
        assertTrue(numero.esPar(2));
    }

    @Test
    void cincoNoEsPar() {
        assertFalse(numero.esPar(5));
    }

    @Test
    void menosCuatroEsPar() {
        assertTrue(numero.esPar(-4));
    }
}
```

Aquí el diseño es principalmente **de caja negra**: entradas y salidas.

---

## 15.2 Ejemplo de caja blanca unitaria con JUnit

Método:

```java
public class Edad {
    public String clasificar(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("Edad no válida");
        }
        if (edad < 18) {
            return "menor";
        }
        return "adulto";
    }
}
```

Test:

```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EdadTest {

    Edad edad = new Edad();

    @Test
    void ramaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> edad.clasificar(-1));
    }

    @Test
    void ramaMenor() {
        assertEquals("menor", edad.clasificar(10));
    }

    @Test
    void ramaAdulto() {
        assertEquals("adulto", edad.clasificar(18));
    }
}
```

Aquí sí estamos cubriendo ramas internas, así que el diseño tiene un claro enfoque de **caja blanca**.

---

## 16. Tests parametrizados

La documentación oficial de JUnit recoge `@ParameterizedTest` y fuentes de argumentos como `@ValueSource` y `@CsvSource`, muy útiles cuando un mismo comportamiento debe comprobarse con múltiples datos. ([docs.junit.org][1])

## 16.1 `@ValueSource`

Cuando el test recibe un solo parámetro.

```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumeroParameterizedTest {

    Numero numero = new Numero();

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4, -8})
    void numerosPares(int valor) {
        assertTrue(numero.esPar(valor));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -7})
    void numerosImpares(int valor) {
        assertFalse(numero.esPar(valor));
    }
}
```

## 16.2 `@CsvSource`

Cuando el test necesita varios parámetros.

```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculadoraTest {

    Calculadora calc = new Calculadora();

    @ParameterizedTest
    @CsvSource({
        "2, 3, 5",
        "10, 20, 30",
        "-1, 1, 0"
    })
    void probarSumas(int a, int b, int esperado) {
        assertEquals(esperado, calc.sumar(a, b));
    }
}
```

---

## 17. Números decimales y tolerancia

Con números en coma flotante no conviene comparar siempre con igualdad exacta. JUnit ofrece sobrecargas de `assertEquals` con delta o tolerancia para este caso. ([docs.junit.org][2])

Ejemplo:

```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DecimalTest {

    @Test
    void comprobarDivision() {
        double actual = 10.0 / 3.0;
        assertEquals(3.333, actual, 0.001);
    }
}
```

Eso significa que admitimos una diferencia pequeña entre el valor esperado y el real.

---

## 18. Cómo usar JUnit 5 en Eclipse

Eclipse dispone de soporte para crear tests JUnit Jupiter, añadir la librería JUnit al proyecto y ejecutar los tests desde el propio IDE. El asistente “New JUnit Test Case” y la adición de la librería desde el build path están documentados en recursos oficiales de Eclipse. ([Eclipse Foundation][3])

### Opción 1: crear directamente una clase de test

1. Crea tu proyecto Java.
2. Crea un paquete para los tests, por ejemplo: `test`.
3. Botón derecho sobre el paquete `test`.
4. `New` → `JUnit Test Case`.
5. Selecciona **JUnit Jupiter** o JUnit 5 si aparece esa opción.
6. Eclipse puede ofrecer añadir la librería automáticamente.

### Opción 2: añadir la librería manualmente

1. Botón derecho sobre el proyecto.
2. `Build Path` → `Configure Build Path`.
3. Pestaña `Libraries`.
4. `Add Library`.
5. `JUnit`.
6. Selecciona JUnit 5.
7. `Finish` y `Apply and Close`.

### Ejecutar los tests

* Botón derecho sobre la clase de test.
* `Run As` → `JUnit Test`.

### Interpretar resultados

* Verde: test superado.
* Rojo: test fallado.
* Si falla, Eclipse muestra:

  * nombre del test,
  * valor esperado,
  * valor real,
  * traza del error o excepción.

---

## 19. Buenas prácticas al escribir tests

### 1. Un test, una idea

Cada test debe comprobar una sola cosa.

### 2. Nombre descriptivo

Mejor:

```java
void devuelveAdultoConEdad18()
```

que:

```java
void test1()
```

### 3. Preparar, ejecutar, comprobar

Estructura mental:

* Arrange: preparo datos y objetos
* Act: ejecuto
* Assert: compruebo

### 4. Cubrir normales, límite e inválidos

No basta con casos fáciles.

### 5. No imprimir por consola para verificar

La comprobación debe hacerla una aserción.

---

## Fuentes consultadas

La estructura y terminología actual de JUnit Jupiter, junto con anotaciones, aserciones y pruebas parametrizadas, están recogidas en la guía oficial de JUnit. El soporte de Eclipse para crear y ejecutar tests JUnit 5 aparece documentado por Eclipse. ([docs.junit.org][1])

[1]: https://docs.junit.org/6.0.3/overview.html?utm_source=chatgpt.com "Overview :: JUnit User Guide"
[2]: https://docs.junit.org/5.9.0-M1/user-guide/index.html?utm_source=chatgpt.com "JUnit 5 User Guide"
[3]: https://www.eclipse.org/community/eclipse_newsletter/2017/october/article5.php?utm_source=chatgpt.com "Embracing JUnit 5 with Eclipse"
