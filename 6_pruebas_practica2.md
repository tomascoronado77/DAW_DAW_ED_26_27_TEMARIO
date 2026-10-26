# P2. Tests parametrizados con JUnit 5 en Eclipse

## 1. Objetivo de la práctica

En la práctica anterior aprendimos a:

* crear una clase de prueba,
* escribir métodos anotados con `@Test`,
* usar `assertEquals`,
* usar `assertThrows`,
* y ejecutar pruebas unitarias en Eclipse.

En esta segunda práctica vamos a dar un paso más.

Vamos a aprender a usar **tests parametrizados**, que permiten ejecutar **la misma prueba varias veces con distintos datos de entrada**.

Esto es útil cuando tenemos muchos casos parecidos y no queremos repetir el mismo código una y otra vez.

---

## 2. Qué son los tests parametrizados

Los tests parametrizados en JUnit 5 permiten:

> ejecutar una misma prueba con múltiples conjuntos de datos de entrada y verificar el comportamiento del código bajo diferentes escenarios.

Esto evita tener que escribir un método de prueba independiente para cada caso cuando la lógica del test es la misma.

A tener en cuenta:

* si el método de prueba necesita **un solo parámetro**, usamos `@ValueSource`;
* si necesita **más de un parámetro**, usamos `@CsvSource`.

---

## 3. Qué vamos a hacer en esta práctica

Vamos a reutilizar la misma clase de la práctica anterior:

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

En la práctica 1 escribimos varios métodos `@Test`, uno por cada caso.
Ahora vamos a **reagrupar casos similares** usando tests parametrizados.

---

## 4. Qué problema queremos resolver

En la práctica anterior teníamos varios tests como estos:

```java
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
```

Y así con más notas.

Si observamos bien, en todos esos tests:

* se crea el mismo objeto,
* se llama al mismo método,
* se usa la misma aserción,
* y **solo cambian los datos**.

Por tanto, tiene sentido agruparlos.

---

## 5. Idea clave de esta práctica

> Si varios tests tienen la misma estructura y solo cambian los valores de entrada o el resultado esperado, podemos transformarlos en un test parametrizado.

---

## 6. Cuándo usar `@ValueSource`

Usamos `@ValueSource` cuando el método de prueba tiene **un solo parámetro**.

Por tanto, es adecuado cuando solo cambia:

* un número,
* una cadena,
* un booleano,
* etc.

### En nuestro caso

Lo utilizaremos para probar los valores que deben lanzar excepción:

* `-1`
* `11`

En esos casos solo necesitamos un dato de entrada: la nota.

---

## 7. Cuándo usar `@CsvSource`

Usamos `@CsvSource` cuando el método de prueba necesita **más de un parámetro**.

En nuestro caso lo utilizaremos cuando necesitemos pasar:

* la nota,
* y la calificación esperada.

Por ejemplo:

* `0, Suspenso`
* `5, Aprobado`
* `7, Notable`
* `9, Sobresaliente`

---

## 8. Crear la nueva clase de test

Vamos a crear una nueva clase en la carpeta `test`, dentro del mismo paquete:

```text
dam2526.pruebas.test
```

### Nombre recomendado

```text
EvaluadorNotasParameterizedTest
```

Esto deja claro que se trata de una clase de pruebas parametrizadas.

---

## 9. Imports necesarios

En esta práctica ya no usaremos `@Test` para todos los casos, sino `@ParameterizedTest`.

Los imports que necesitamos son estos:

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import dam2526.pruebas.EvaluadorNotas;
```

### Explicación

* `assertEquals` se usará para comparar el resultado esperado con el real.
* `assertThrows` se usará para comprobar excepciones.
* `@ParameterizedTest` marca un test que se ejecutará varias veces.
* `@CsvSource` aporta varios pares de datos.
* `@ValueSource` aporta una lista de valores simples.

---

## 10. Código completo de la práctica

```java
package dam2526.pruebas.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import dam2526.pruebas.EvaluadorNotas;

public class EvaluadorNotasParameterizedTest {

    @ParameterizedTest
    @CsvSource({
        "0, Suspenso",
        "4, Suspenso",
        "5, Aprobado",
        "6, Aprobado",
        "7, Notable",
        "8, Notable",
        "9, Sobresaliente",
        "10, Sobresaliente"
    })
    void clasificarNota_devuelveCalificacionEsperada(int nota, String esperado) {
        EvaluadorNotas evaluador = new EvaluadorNotas();
        String resultado = evaluador.clasificarNota(nota);
        assertEquals(esperado, resultado);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void clasificarNota_fueraDeRango_lanzaExcepcion(int nota) {
        EvaluadorNotas evaluador = new EvaluadorNotas();
        assertThrows(IllegalArgumentException.class, () -> evaluador.clasificarNota(nota));
    }
}
```

---

## 11. Explicación detallada del primer test parametrizado

### Código

```java
@ParameterizedTest
@CsvSource({
    "0, Suspenso",
    "4, Suspenso",
    "5, Aprobado",
    "6, Aprobado",
    "7, Notable",
    "8, Notable",
    "9, Sobresaliente",
    "10, Sobresaliente"
})
void clasificarNota_devuelveCalificacionEsperada(int nota, String esperado) {
    EvaluadorNotas evaluador = new EvaluadorNotas();
    String resultado = evaluador.clasificarNota(nota);
    assertEquals(esperado, resultado);
}
```

### Qué hace `@ParameterizedTest`

Indica que ese método no se ejecutará una sola vez, sino varias veces.

Cada ejecución usará un conjunto distinto de datos.

### Qué hace `@CsvSource`

Aporta varias filas de datos.
Cada fila contiene dos valores:

* la nota,
* y la cadena esperada.

Por ejemplo:

```java
"5, Aprobado"
```

significa:

* `nota = 5`
* `esperado = "Aprobado"`

### Qué pasa en cada ejecución

JUnit tomará una fila del `@CsvSource`, llamará al método con esos valores y ejecutará la aserción.

Por tanto, este único método equivale a varios tests del tipo:

* probar 0 → `"Suspenso"`
* probar 4 → `"Suspenso"`
* probar 5 → `"Aprobado"`
* etc.

### Idea clave

No estamos haciendo menos pruebas.
Estamos escribiendo el código de prueba de una forma más compacta y organizada.

---

## 12. Explicación detallada del segundo test parametrizado

### Código

```java
@ParameterizedTest
@ValueSource(ints = {-1, 11})
void clasificarNota_fueraDeRango_lanzaExcepcion(int nota) {
    EvaluadorNotas evaluador = new EvaluadorNotas();
    assertThrows(IllegalArgumentException.class, () -> evaluador.clasificarNota(nota));
}
```

### Qué hace `@ValueSource`

Aporta una lista de valores simples.
En este caso, dos enteros:

* `-1`
* `11`

JUnit ejecutará dos veces el mismo test, una con cada valor.

### Qué comprobamos aquí

Queremos verificar que cualquier nota fuera de rango produzca una excepción.

### Por qué aquí usamos `@ValueSource`

Porque el test solo necesita **un parámetro**: la nota.

No necesitamos un segundo valor esperado, porque lo que esperamos no es una cadena, sino una excepción.

---

## 13. Relación con el material de clase

Esta práctica sigue exactamente la idea del apartado `16. Tests parametrizados` de los apuntes:

* `@ParameterizedTest` para repetir una misma prueba con varios datos.
* `@ValueSource` cuando el test recibe un solo dato.
* `@CsvSource` cuando recibe más de uno.

También sigue el mismo criterio que aparece en los ejemplos de los apuntes:

* agrupar entradas que producen el mismo tipo de comportamiento,
* evitar repetir métodos casi idénticos,
* y mantener la lógica del test clara.

---

## 14. Qué estamos aprendiendo realmente

Con esta práctica debemos entender que:

### 1. Un test parametrizado no cambia el objetivo de la prueba

Seguimos comprobando si el método funciona bien.

### 2. Un test parametrizado mejora la organización

En lugar de varios métodos casi iguales, tenemos uno solo con varios datos.

### 3. Los casos de prueba siguen existiendo

Aunque ahora estén agrupados, cada fila de `@CsvSource` o cada valor de `@ValueSource` sigue representando un caso de prueba.

### 4. El razonamiento sigue siendo importante

Antes de escribir el test parametrizado, hay que pensar igualmente:

* qué valores quiero probar,
* qué resultado espero,
* y por qué esos casos son relevantes.

---

## 15. Qué buenas prácticas estamos aplicando aquí

### 1. Evitar repetición innecesaria

Si la estructura del test es siempre la misma, tiene sentido parametrizar.

### 2. Agrupar casos del mismo tipo

En esta práctica:

* un test parametrizado para resultados válidos,
* otro test parametrizado para excepciones.

### 3. Mantener nombres descriptivos

Los nombres siguen siendo importantes:

```java
clasificarNota_devuelveCalificacionEsperada
clasificarNota_fueraDeRango_lanzaExcepcion
```

### 4. No mezclar demasiadas ideas en un mismo método

Aunque sea parametrizado, cada test sigue teniendo una intención clara.

---

## 16. Cuándo no conviene parametrizar

No siempre interesa convertir todo en parametrizado.

**No conviene cuando:**

* los casos tienen lógicas distintas,
* el test deja de ser comprensible,
* o aún no dominamos bien la estructura básica de una prueba.

---

## 17. Cómo crear esta clase en Eclipse

### Paso 1

Dentro de la carpeta `test`, en el paquete `dam2526.pruebas.test`, crea una clase normal llamada:

```text
EvaluadorNotasParameterizedTest
```

### Paso 2

Copia el código completo proporcionado.

### Paso 3

Asegúrate de que Eclipse resuelve correctamente los imports. Si alguno aparece con error, usa:

```text
Ctrl + 1
```

para que Eclipse proponga el import adecuado.

### Paso 4

Ejecuta la clase:

```text
Run As → JUnit Test
```

---

## 18. Qué debe observar al ejecutar

Aunque la clase tenga solo **dos métodos**, en realidad JUnit ejecutará más comprobaciones:

* una por cada fila del `@CsvSource`,
* una por cada valor del `@ValueSource`.

Esto ayudará a entender que:

> un método parametrizado puede equivaler a varios casos de prueba reales.

---

## 19. Entregable de la práctica

Debeis entregar:

### 1. Proyecto Eclipse actualizado

Con:

* `EvaluadorNotas.java`
* `EvaluadorNotasParameterizedTest.java`

### 2. Captura de pantalla

Donde se vea la ejecución correcta de los tests en verde.

### 3. Ampliación obligatoria

Añadir nuevos valores a los tests parametrizados:

* en `@CsvSource`, añadir al menos dos casos más ya cubiertos por el método;
* en `@ValueSource`, añadir al menos dos valores inválidos adicionales.

Por ejemplo:

* `1, Suspenso`
* `3, Suspenso`
* `-5`
* `20`

---

Si quieres, el siguiente paso lógico es que te prepare la **Práctica 3** sobre el apartado 9 de tu fuente: **pruebas de números decimales con delta**, manteniendo el mismo estilo guiado.
