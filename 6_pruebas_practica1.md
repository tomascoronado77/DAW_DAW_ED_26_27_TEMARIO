# P1. Introducción guiada a las pruebas unitarias con JUnit 5 en Eclipse

## 1. Objetivo de la práctica

En esta práctica vamos a aprender, paso a paso, cómo se crea y se ejecuta una prueba unitaria en Java usando **JUnit 5** dentro de **Eclipse**.

No se trata solo de copiar código. El objetivo es entender:

* qué es una prueba unitaria,
* cómo se organiza un proyecto con código y tests separados,
* cómo se diseña un caso de prueba,
* cómo se escribe un test con JUnit,
* qué hace cada anotación y cada aserción,
* y cómo interpretar el resultado de la ejecución.

---

## 2. Qué es una prueba unitaria

Una **prueba unitaria** es una comprobación automática de una parte pequeña del programa, normalmente un método o un comportamiento muy concreto.

Una prueba unitaria:

1. prepara unos datos,
2. ejecuta un método,
3. compara el resultado obtenido con el esperado.

Si el resultado coincide con lo esperado, la prueba pasa.
Si no coincide, la prueba falla.

### Idea clave

Una prueba unitaria no consiste en “probar manualmente a ver qué pasa”, sino en **hacer que el propio programa compruebe automáticamente si funciona bien**.

---

## 3. Qué vamos a probar en esta práctica

Vamos a crear una clase llamada `EvaluadorNotas` que tendrá un método para clasificar una nota entera.

La regla será la siguiente:

* si la nota es menor que 0 o mayor que 10, se lanzará una excepción;
* de 0 a 4, devolverá `"Suspenso"`;
* de 5 a 6, devolverá `"Aprobado"`;
* de 7 a 8, devolverá `"Notable"`;
* de 9 a 10, devolverá `"Sobresaliente"`.

Este ejemplo es bueno para empezar porque:

* tiene varias ramas,
* permite pensar distintos casos de prueba,
* incluye una excepción,
* y permite usar varias aserciones de JUnit.

---

## 4. Organización del proyecto

En esta práctica vamos a separar:

* el código de la aplicación,
* y el código de las pruebas.

La estructura será esta:

```text
DAM2526_PruebasJUnit
│
├── src
│   └── dam2526.pruebas
│       └── EvaluadorNotas.java
│
└── test
    └── dam2526.pruebas.test
        └── EvaluadorNotasTest.java
```

### Por qué hacemos esto

Es una buena práctica separar:

* `src` para el código del programa,
* `test` para las pruebas.

De esta forma:

* el proyecto está más ordenado,
* no mezclamos responsabilidades,
* y se entiende mejor qué código pertenece a la aplicación y cuál a su comprobación.

---

## 5. Crear el proyecto en Eclipse

### Paso 1. Crear un proyecto Java

1. Abrir Eclipse.
2. Crear un proyecto Java nuevo.
3. Asignarle un nombre, por ejemplo:

```text
DAM2526_PruebasJUnit
```

---

## 6. Crear la carpeta `test`

Normalmente Eclipse crea la carpeta `src` al generar el proyecto.
Ahora vamos a crear también una carpeta separada para los tests.

### Paso 2. Crear la carpeta `test`

1. Botón derecho sobre el proyecto.
2. Seleccionar:

```text
New → Source Folder
```

3. Escribir como nombre:

```text
test
```

Al terminar, el proyecto debe tener al menos dos carpetas de código fuente:

* `src`
* `test`

---

## 7. Crear los paquetes

### Paso 3. Crear el paquete del código

Dentro de `src`, crear el paquete:

```text
dam2526.pruebas
```

### Paso 4. Crear el paquete de los tests

Dentro de `test`, crear el paquete:

```text
dam2526.pruebas.test
```

### Por qué usamos estos nombres

Es una buena práctica que el paquete de test refleje el paquete del código. Así:

* `dam2526.pruebas` contiene la clase real,
* `dam2526.pruebas.test` contiene la clase de pruebas.

---

## 8. Añadir JUnit 5 al proyecto

JUnit es la librería que nos permite escribir y ejecutar pruebas unitarias en Java.

### Paso 5. Añadir JUnit 5

1. Botón derecho sobre el proyecto.
2. Seleccionar:

```text
Build Path → Add Libraries
```

3. Elegir `JUnit`.
4. Seleccionar **JUnit 5** o **JUnit Jupiter**.
5. Finalizar.

### Qué significa JUnit Jupiter

JUnit 5 está organizado en varios componentes. El que usaremos aquí es **JUnit Jupiter**, que es el modelo actual de JUnit para escribir tests con anotaciones como `@Test`.

---

## 9. Crear la clase que vamos a probar

### Paso 6. Crear `EvaluadorNotas.java`

Dentro del paquete `dam2526.pruebas`, crear la clase `EvaluadorNotas` y escribir este código:

```java
package dam2526.pruebas;

public class EvaluadorNotas {

    public String clasificarNota(int nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 10.");
        }

        if (nota <= 4) {
            return "Suspenso";
        } else if (nota <= 6) {
            return "Aprobado";
        } else if (nota <= 8) {
            return "Notable";
        } else {
            return "Sobresaliente";
        }
    }
}
```

---

## 10. Entender el método antes de probarlo

Antes de escribir un test, hay que pensar qué hace el método.

El método `clasificarNota(int nota)`:

* recibe una nota entera,
* comprueba si está dentro del rango válido,
* y devuelve una cadena según el valor recibido.

### Ramas del método

Este método tiene varios caminos posibles:

1. nota menor que 0 → excepción
2. nota mayor que 10 → excepción
3. nota entre 0 y 4 → `"Suspenso"`
4. nota entre 5 y 6 → `"Aprobado"`
5. nota entre 7 y 8 → `"Notable"`
6. nota entre 9 y 10 → `"Sobresaliente"`

Esto nos servirá para pensar los casos de prueba.

---

## 11. Diseñar los casos de prueba antes de programar

Antes de escribir JUnit, debemos pensar qué situaciones queremos comprobar.

### Casos propuestos

| ID caso | Qué se quiere probar    | Entrada | Resultado esperado |
| ------- | ----------------------- | ------: | ------------------ |
| CP-01   | Nota negativa           |      -1 | Excepción          |
| CP-02   | Nota mínima válida      |       0 | Suspenso           |
| CP-03   | Último suspenso         |       4 | Suspenso           |
| CP-04   | Primer aprobado         |       5 | Aprobado           |
| CP-05   | Último aprobado         |       6 | Aprobado           |
| CP-06   | Primer notable          |       7 | Notable            |
| CP-07   | Último notable          |       8 | Notable            |
| CP-08   | Primer sobresaliente    |       9 | Sobresaliente      |
| CP-09   | Nota máxima válida      |      10 | Sobresaliente      |
| CP-10   | Nota superior al máximo |      11 | Excepción          |

### Qué enfoque estamos usando aquí

Aquí estamos razonando sobre todo con enfoque de **caja negra**, porque pensamos en:

* entradas válidas,
* entradas inválidas,
* valores límite,
* y resultados esperados.

Es decir, estamos pensando en **qué debería hacer el método**, no todavía en cómo está implementado internamente.

### Observación importante

Más adelante, al mirar el código, también podremos ver que estos casos ayudan a cubrir las ramas internas del método. Pero el razonamiento inicial nace del comportamiento esperado.

---

## 12. Crear la clase de test en Eclipse

### Paso 7. Crear la clase `EvaluadorNotasTest`

Dentro del paquete `dam2526.pruebas.test`:

1. Botón derecho.
2. Seleccionar:

```text
New → JUnit Test Case
```

### En la ventana de creación

Configurar así:

* **Test type**: `New JUnit Jupiter test`
* **Source folder**: `test`
* **Package**: `dam2526.pruebas.test`
* **Name**: `EvaluadorNotasTest`

### Qué no hay que marcar

No marques por ahora:

* `@BeforeAll`
* `@AfterAll`
* `@BeforeEach`
* `@AfterEach`
* `Generate comments`

### Por qué no lo marcamos

Porque en esta primera práctica queremos entender lo básico sin añadir complejidad extra.

---

## 13. Código inicial que genera Eclipse

Eclipse generará algo parecido a esto:

```java
package dam2526.pruebas.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EvaluadorNotasTest {

    @Test
    void test() {
        fail("Not yet implemented");
    }
}
```

### Qué significa ese código

Ese método no es una prueba real. Solo es una **plantilla** que Eclipse crea para que la clase compile, pero que falle siempre.

La línea:

```java
fail("Not yet implemented");
```

significa:

> esta prueba no está implementada todavía y debe fallar.

Por tanto, **hay que sustituir ese código** por nuestras pruebas reales.

---

## 14. Escribir la clase de test completa

### Paso 8. Sustituir el código generado por este

```java
package dam2526.pruebas.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import dam2526.pruebas.EvaluadorNotas;

public class EvaluadorNotasTest {

    @Test
    void notaCero_devuelveSuspenso() {
        EvaluadorNotas evaluador = new EvaluadorNotas();
        String resultado = evaluador.clasificarNota(0);
        assertEquals("Suspenso", resultado);
    }

    @Test
    void notaCinco_devuelveAprobado() {
        EvaluadorNotas evaluador = new EvaluadorNotas();
        String resultado = evaluador.clasificarNota(5);
        assertEquals("Aprobado", resultado);
    }

    @Test
    void notaSiete_devuelveNotable() {
        EvaluadorNotas evaluador = new EvaluadorNotas();
        String resultado = evaluador.clasificarNota(7);
        assertEquals("Notable", resultado);
    }

    @Test
    void notaNueve_devuelveSobresaliente() {
        EvaluadorNotas evaluador = new EvaluadorNotas();
        String resultado = evaluador.clasificarNota(9);
        assertEquals("Sobresaliente", resultado);
    }

    @Test
    void notaNegativa_lanzaExcepcion() {
        EvaluadorNotas evaluador = new EvaluadorNotas();
        assertThrows(IllegalArgumentException.class, () -> evaluador.clasificarNota(-1));
    }

    @Test
    void notaMayorQueDiez_lanzaExcepcion() {
        EvaluadorNotas evaluador = new EvaluadorNotas();
        assertThrows(IllegalArgumentException.class, () -> evaluador.clasificarNota(11));
    }
}
```

---

## 15. Explicación detallada del código del test

---

### 15.1 El paquete

```java
package dam2526.pruebas.test;
```

Indica que esta clase pertenece al paquete de pruebas.

---

### 15.2 Los imports

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import dam2526.pruebas.EvaluadorNotas;
```

#### `import org.junit.jupiter.api.Test;`

Es necesario para poder usar la anotación `@Test`.

Sin este import, Eclipse no sabrá qué significa `@Test`.

#### `import static ... assertEquals;`

Permite usar directamente `assertEquals(...)` sin escribir el nombre completo de la clase `Assertions`.

#### `import static ... assertThrows;`

Permite usar `assertThrows(...)` para comprobar excepciones.

#### `import dam2526.pruebas.EvaluadorNotas;`

Permite usar la clase que vamos a probar.

---

## 16. Qué hace `@Test`

La anotación `@Test` indica a JUnit que ese método es una prueba.

Ejemplo:

```java
@Test
void notaCero_devuelveSuspenso() {
    ...
}
```

Si un método no lleva `@Test`, JUnit no lo ejecutará como prueba.

### Idea clave

`@Test` marca el método como un caso de prueba ejecutable.

---

## 17. Estructura de una buena prueba

Cada test de esta práctica sigue la misma estructura:

1. **Preparación**
2. **Ejecución**
3. **Comprobación**

### Ejemplo

```java
@Test
void notaCinco_devuelveAprobado() {
    EvaluadorNotas evaluador = new EvaluadorNotas();
    String resultado = evaluador.clasificarNota(5);
    assertEquals("Aprobado", resultado);
}
```

#### Preparación

```java
EvaluadorNotas evaluador = new EvaluadorNotas();
```

Creamos el objeto.

#### Ejecución

```java
String resultado = evaluador.clasificarNota(5);
```

Llamamos al método con un valor concreto.

#### Comprobación

```java
assertEquals("Aprobado", resultado);
```

Comparamos el resultado esperado con el real.

---

## 18. Qué hace `assertEquals`

`assertEquals` compara dos valores:

```java
assertEquals(valorEsperado, valorReal);
```

Si ambos coinciden, la prueba pasa.
Si no coinciden, la prueba falla.

### Ejemplo

```java
assertEquals("Notable", resultado);
```

Aquí estamos comprobando que el método devuelva exactamente `"Notable"`.

### Idea clave

`assertEquals` sirve para comprobar igualdad.

---

## 19. Qué hace `assertThrows`

`assertThrows` sirve para comprobar que un método lanza una excepción.

Ejemplo:

```java
assertThrows(IllegalArgumentException.class, () -> evaluador.clasificarNota(-1));
```

Aquí estamos diciendo:

> al ejecutar `clasificarNota(-1)`, debe lanzarse una `IllegalArgumentException`.

Si no se lanza esa excepción, la prueba falla.

### Idea clave

No solo se prueban resultados normales. También hay que probar comportamientos inválidos.

---

## 20. Otras aserciones frecuentes en JUnit

Además de `assertEquals` y `assertThrows`, existen muchas otras. Estas son algunas de las más usadas:

### `assertTrue`

Comprueba que una condición sea verdadera.

```java
assertTrue(5 > 3);
```

### `assertFalse`

Comprueba que una condición sea falsa.

```java
assertFalse(2 > 10);
```

### `assertNull`

Comprueba que un valor sea `null`.

```java
assertNull(objeto);
```

### `assertNotNull`

Comprueba que un valor no sea `null`.

```java
assertNotNull(objeto);
```

### `assertNotEquals`

Comprueba que dos valores sean distintos.

```java
assertNotEquals(5, resultado);
```

### `assertArrayEquals`

Comprueba si dos arrays son iguales.

```java
assertArrayEquals(esperado, real);
```

### `fail`

Fuerza que una prueba falle.

```java
fail("Este punto no debería alcanzarse");
```

### Recomendación

En esta práctica nos centraremos sobre todo en:

* `assertEquals`
* `assertThrows`

porque son las más adecuadas para empezar.

---

## 21. Importancia del nombre del test

Los nombres de las pruebas deben ser **semánticos y descriptivos**.

### Mal ejemplo

```java
@Test
void test1() {
}
```

Ese nombre no dice nada.

### Buen ejemplo

```java
@Test
void notaMayorQueDiez_lanzaExcepcion() {
}
```

Ese nombre ya explica:

* la situación probada,
* y el comportamiento esperado.

### Buena práctica

Un nombre de test debería permitir entender el caso sin necesidad de leer todo el cuerpo del método.

---

## 22. Ejecutar las pruebas

### Paso 9. Ejecutar la clase de test

1. Botón derecho sobre `EvaluadorNotasTest`.
2. Seleccionar:

```text
Run As → JUnit Test
```

### Resultado esperado

* Si todo es correcto, los tests aparecerán en **verde**.
* Si alguna prueba falla, aparecerá en **rojo**.

---

## 23. Comprobar que el test detecta errores

Para entender bien para qué sirve un test, vamos a provocar un fallo intencionadamente.

### Paso 10. Cambiar temporalmente una aserción

Por ejemplo, en este test:

```java
assertEquals("Aprobado", resultado);
```

puedes cambiarlo por:

```java
assertEquals("Suspenso", resultado);
```

Ahora vuelve a ejecutar la prueba.

### Qué debe ocurrir

El test debe fallar, porque el resultado real no coincide con el esperado.

### Conclusión

Esto demuestra que la prueba unitaria:

* no es decorativa,
* no es un comentario,
* sino una comprobación real.

---

## 24. Qué buenas prácticas estamos aplicando ya

En esta práctica ya estamos aprendiendo varias buenas prácticas importantes:

### 1. Diseñar los casos antes de escribir los tests

No se debe programar el test “a ciegas”.

### 2. Un test debe comprobar una sola idea

Cada método prueba un caso concreto.

### 3. Los nombres deben ser claros

El nombre del test debe explicar la situación y el resultado esperado.

### 4. El código de test debe ser simple

No debe contener lógica complicada innecesaria.

### 5. El test debe estar aislado

En esta práctica la prueba depende solo de:

* la clase `EvaluadorNotas`,
* y de los datos que le pasamos.

No depende de:

* archivos,
* base de datos,
* red,
* otras clases complejas.

---

## 25. Relación con caja negra y caja blanca

En esta práctica estamos trabajando sobre todo con enfoque de **caja negra**, porque diseñamos los casos a partir del comportamiento esperado:

* nota válida,
* nota inválida,
* distintos resultados posibles,
* límites del problema.

Sin embargo, al observar el código también podemos ver que estamos cubriendo ramas internas, así que estos tests también ayudan a hablar de **caja blanca**.

### Idea importante

Lo que define si el enfoque es caja negra o caja blanca no es solo el dato usado, sino **por qué se ha elegido ese dato**.

---

## 26. Entregable de la práctica

Debeis entregar lo siguiente:

### 1. Proyecto Eclipse completo

Debe contener:

* la carpeta `src`,
* la carpeta `test`,
* la clase `EvaluadorNotas`,
* y la clase `EvaluadorNotasTest`.

### 2. Captura de pantalla

Una imagen donde se vea la ejecución de los tests en verde.

### 3. Ampliación obligatoria

Añadir al menos **cuatro tests más**, usando los casos que aún no están implementados en la tabla de casos de prueba.

Por ejemplo:

* nota 4 → `"Suspenso"`
* nota 6 → `"Aprobado"`
* nota 8 → `"Notable"`
* nota 10 → `"Sobresaliente"`

---

## 27. Resumen final

En esta práctica hemos aprendido a:

* crear un proyecto con `src` y `test`,
* añadir JUnit 5,
* crear una clase de prueba en Eclipse,
* sustituir la plantilla automática por tests reales,
* usar `@Test`,
* usar `assertEquals`,
* usar `assertThrows`,
* diseñar casos de prueba,
* y escribir tests claros, aislados y bien nombrados.

### Idea final importante

Una buena prueba unitaria no consiste solo en ejecutar código, sino en **pensar bien el caso, expresar claramente qué se espera y dejar la comprobación automatizada**.

---
