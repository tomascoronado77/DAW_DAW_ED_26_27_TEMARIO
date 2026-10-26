
# Fake y Mockito

## 1. Pruebas Unitarias usando un Fake

### 1.1. Objetivo

Mediante un ejemplo vamos a aprender a probar una clase que necesita **leer datos del usuario**, pero **sin usar el teclado real**.

Para ello utilizaremos un **Fake**, es decir, una implementación sencilla que simula el comportamiento real.

### 1.2. Problema inicial

Imagina una clase que lee datos del usuario:

```java
public class ServicioUsuarios {

    public String pedirNombre() {
        EntradaSalida es = EntradaSalida.getInstancia();
        return es.leerTexto("Nombre: ");
    }
}
```

#### 1.3. Problemas para hacer pruebas

* Usa `Scanner` internamente (teclado real)
* El test se queda esperando entrada
* No se puede automatizar
* No es una prueba unitaria real (no está aislada)

**Idea clave**  

> Para poder probar una clase correctamente, debemos evitar depender del teclado, archivos o red.

### 1.4. Paso 1: Crear una interfaz

Definimos una interfaz para representar la entrada/salida:

```java
public interface IEntradaSalida {

    String leerTexto(String mensaje);

    void escribirLinea(String texto);
}
```

**¿Por qué hacemos esto?** 

Porque ahora podremos tener:

* una implementación real (con teclado)
* y una implementación falsa (para pruebas)

### 1.5. Paso 2: Implementación real (Singleton)

```java
public class EntradaSalida implements IEntradaSalida {

    private static EntradaSalida instancia;

    private EntradaSalida() {
    }

    public static EntradaSalida getInstancia() {
        if (instancia == null) {
            instancia = new EntradaSalida();
        }
        return instancia;
    }

    @Override
    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    @Override
    public void escribirLinea(String texto) {
        System.out.println(texto);
    }
}
```

### 1.6. Paso 3: Modificar la clase a probar

En lugar de usar directamente el Singleton, lo recibimos por constructor:

```java
public class ServicioUsuarios {

    private IEntradaSalida entradaSalida;

    public ServicioUsuarios(IEntradaSalida entradaSalida) {
        this.entradaSalida = entradaSalida;
    }

    public String pedirNombre() {
        return entradaSalida.leerTexto("Nombre: ");
    }
}
```

**Idea clave**  

> Ahora la clase no depende directamente del teclado, sino de una interfaz.

### 1.7. Paso 4: Crear un Fake

El Fake simula el comportamiento real, pero de forma controlada:

```java
public class EntradaSalidaFake implements IEntradaSalida {

    @Override
    public String leerTexto(String mensaje) {
        return "Ana";
    }

    @Override
    public void escribirLinea(String texto) {
        // No hace nada
    }
}
```

**Qué es un Fake**  

Un **Fake** es una implementación sencilla que:

* simula el comportamiento real
* no usa recursos externos
* permite controlar los resultados

### 1.8. Paso 5: Crear el test unitario

```java
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ServicioUsuariosTest {

    @Test
    void pedirNombre_devuelveNombreSimulado() {

        IEntradaSalida entradaSalida = new EntradaSalidaFake();
        ServicioUsuarios servicio = new ServicioUsuarios(entradaSalida);

        String resultado = servicio.pedirNombre();

        assertEquals("Ana", resultado);
    }
}
```

**Qué está pasando en el test**

1. No usamos teclado real
2. No usamos Scanner
3. El Fake devuelve siempre `"Ana"`
4. Podemos comprobar el resultado automáticamente

### 1.9. Ventajas de usar Fake

✔ No depende del teclado
✔ No requiere intervención del usuario
✔ Es rápido y automático
✔ Permite pruebas repetibles
✔ Mantiene el test aislado

> Para poder hacer buenas pruebas unitarias, las clases no deben depender directamente de elementos externos como teclado, archivos o red.  
> En las pruebas unitarias no queremos datos reales, queremos datos controlados.

### 1.10. Resumen

* Creamos una interfaz (`IEntradaSalida`)
* Implementamos la versión real (`EntradaSalida`)
* Creamos una versión falsa (`EntradaSalidaFake`)
* Inyectamos la dependencia en la clase
* Probamos usando el Fake

---

## 2. Pruebas unitarias usando Mockito

### 2.1. Objetivo

En el apartado anterior hemos visto cómo probar una clase usando un **Fake**. Ahora vamos a ver una alternativa más profesional: usar **Mockito**.

Mockito es un framework de mocking para Java que permite crear objetos simulados para probar una clase de forma aislada. Su objetivo es sustituir dependencias reales por objetos controlados durante el test. ([site.mockito.org][1])

### 2.2. Qué problema resuelve Mockito

Supongamos que tenemos esta interfaz:

```java
public interface IEntradaSalida {

    String leerTexto(String mensaje);

    void escribirLinea(String texto);
}
```

Y esta clase que queremos probar:

```java
public class ServicioUsuarios {

    private IEntradaSalida entradaSalida;

    public ServicioUsuarios(IEntradaSalida entradaSalida) {
        this.entradaSalida = entradaSalida;
    }

    public String pedirNombre() {
        return entradaSalida.leerTexto("Nombre: ");
    }
}
```

La clase `ServicioUsuarios` depende de `IEntradaSalida`.

En una prueba unitaria no queremos usar teclado real, ni `Scanner`, ni consola. Queremos controlar qué devuelve `leerTexto`.

Con un Fake crearíamos una clase nueva. Con Mockito no hace falta: el mock se crea directamente en el test.

### 2.3. Diferencia entre Fake y Mock

| Concepto | Idea principal                                  |
| -------- | ----------------------------------------------- |
| Fake     | Creamos una clase falsa manualmente             |
| Mock     | Mockito crea un objeto simulado automáticamente |

Con Fake escribimos esto:

```java
public class EntradaSalidaFake implements IEntradaSalida {

    @Override
    public String leerTexto(String mensaje) {
        return "Ana";
    }

    @Override
    public void escribirLinea(String texto) {
    }
}
```

Con Mockito no necesitamos crear esa clase.

### 2.4. Añadir Mockito al proyecto

Para usar Mockito, el proyecto debe tener añadida la librería de Mockito. En un proyecto sencillo de Eclipse sin Maven, habría que añadir los `.jar` necesarios al Build Path.

Si el proyecto usa Maven, se añadiría Mockito como dependencia de test en el `pom.xml`. En el contexto de JUnit 5, suele usarse junto con el soporte de Mockito para Jupiter, que permite inicializar mocks con la extensión de JUnit 5. ([Baeldung on Kotlin][2])

Para esta explicación nos centraremos en el código del test.

### 2.5. Test usando Mockito de forma sencilla

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class ServicioUsuariosMockitoTest {

    @Test
    void pedirNombre_devuelveNombreSimulado() {
        IEntradaSalida entradaSalida = mock(IEntradaSalida.class);

        when(entradaSalida.leerTexto("Nombre: ")).thenReturn("Ana");

        ServicioUsuarios servicio = new ServicioUsuarios(entradaSalida);

        String resultado = servicio.pedirNombre();

        assertEquals("Ana", resultado);
    }
}
```

### 2.6. Explicación del test

#### Crear el mock

```java
IEntradaSalida entradaSalida = mock(IEntradaSalida.class);
```

Mockito crea un objeto simulado que implementa `IEntradaSalida`.

No es la consola real.
No lee del teclado.
No usa `Scanner`.

#### Definir su comportamiento

```java
when(entradaSalida.leerTexto("Nombre: ")).thenReturn("Ana");
```

Esto significa:

> Cuando alguien llame a `leerTexto("Nombre: ")`, devuelve `"Ana"`.

#### Inyectar el mock

```java
ServicioUsuarios servicio = new ServicioUsuarios(entradaSalida);
```

La clase que queremos probar recibe el mock por constructor.

#### Comprobar el resultado

```java
assertEquals("Ana", resultado);
```

Comprobamos que `pedirNombre()` devuelve el valor simulado.

### 2.7. Verificar que se ha llamado al mock

Mockito también permite comprobar si un método del mock ha sido llamado.

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class ServicioUsuariosMockitoTest {

    @Test
    void pedirNombre_leeTextoConMensajeCorrecto() {
        IEntradaSalida entradaSalida = mock(IEntradaSalida.class);

        when(entradaSalida.leerTexto("Nombre: ")).thenReturn("Ana");

        ServicioUsuarios servicio = new ServicioUsuarios(entradaSalida);

        String resultado = servicio.pedirNombre();

        assertEquals("Ana", resultado);
        verify(entradaSalida).leerTexto("Nombre: ");
    }
}
```

La línea:

```java
verify(entradaSalida).leerTexto("Nombre: ");
```

comprueba que el método `leerTexto` fue llamado con ese mensaje.

Mockito permite crear mocks, definir comportamiento y verificar interacciones, que son tres de sus usos principales. ([Javadoc][3])

### 2.8. Cuándo usar Mockito

Mockito es útil cuando una clase depende de otra clase o interfaz y queremos probarla de forma aislada.

Por ejemplo, cuando hay dependencias como:

* entrada/salida por consola,
* lectura de archivos,
* bases de datos,
* servicios externos,
* repositorios,
* APIs.

### 2.9. Idea clave

> Mockito no prueba la dependencia.
> Mockito sustituye la dependencia para poder probar la clase principal.

En este ejemplo, no estamos probando `EntradaSalida`.

Estamos probando `ServicioUsuarios`, controlando lo que devuelve `IEntradaSalida`.

### 2.10. Comparación entre Fake y Mockito

| Aspecto                      | Fake        | Mockito          |
| ---------------------------- | ----------- | ---------------- |
| ¿Creamos una clase manual?   | Sí          | No               |
| ¿Controlamos el resultado?   | Sí          | Sí               |
| ¿Permite verificar llamadas? | Manualmente | Sí, con `verify` |
| Dificultad inicial           | Baja        | Media            |
| Uso profesional              | Útil        | Muy habitual     |

### 2.11. Conclusión

Primero hemos aprendido a usar un **Fake**, que es una forma sencilla y muy clara de sustituir una dependencia real.

Después, Mockito nos permite hacer algo parecido, pero de forma más flexible:

* crear objetos simulados,
* definir qué devuelven,
* y comprobar si se han usado correctamente.

La idea más importante sigue siendo la misma:

> Para hacer buenas pruebas unitarias, debemos aislar la clase que queremos probar.

[1]: https://site.mockito.org/?utm_source=chatgpt.com "Mockito framework site"
[2]: https://www.baeldung.com/mockito-junit-5-extension?utm_source=chatgpt.com "Mockito and JUnit 5 - Using ExtendWith"
[3]: https://javadoc.io/doc/org.mockito/mockito-core/5.0.0/org/mockito/Mockito.html?utm_source=chatgpt.com "Mockito (Mockito 5.0.0 API)"
