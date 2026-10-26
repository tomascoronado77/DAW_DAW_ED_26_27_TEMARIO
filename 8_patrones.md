
# 8. ¿Qué es un patrón de diseño?

Un **patrón de diseño** es una **solución reutilizable y general** a un problema común en el desarrollo de software.

* No es código concreto, sino una plantilla.
* Recoge buenas prácticas.
* Se puede aplicar en distintos lenguajes.

Un patrón describe un problema que aparece frecuentemente y propone una solución general adaptable.

---

## 8.1. ¿Para qué y por qué se utilizan?

Se utilizan para:

* Evitar reinventar soluciones.
* Mejorar la mantenibilidad.
* Reducir el acoplamiento.
* Facilitar la reutilización.
* Hacer el código más claro.

No se deben usar siempre, solo cuando el problema lo requiere.

---

## 8.2. Clasificación de patrones

### Patrones creacionales

Se centran en la creación de objetos.

* Singleton
* Factory
* Builder

---

### Patrones estructurales

Se ocupan de la organización de clases.

* Adapter
* Decorator
* Facade

---

### Patrones de comportamiento

Se centran en la interacción entre objetos.

* Strategy
* Observer
* Command

---

### Patrones arquitectónicos

Definen la estructura global del sistema.

* MVC
* Arquitectura en capas

---

## 8.3. Patrón Singleton

### Propósito

Garantizar que una clase tenga solo una instancia y proporcionar acceso global.

---

### Implementación

```java
public class Singleton {

    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public String showMessage() {
        return "¡Hola! Soy la instancia Singleton.";
    }
}
```

---

### Ejemplo real: Configuración de la aplicación

En una aplicación real suele existir una clase de configuración que contiene valores como:

* idioma
* modo debug
* rutas de archivos

Tiene sentido que exista **una única configuración en toda la aplicación**.

```java
public class Configuracion {

    private static Configuracion instancia;

    private boolean modoDebug;

    private Configuracion() {}

    public static Configuracion getInstancia() {
        if (instancia == null) {
            instancia = new Configuracion();
        }
        return instancia;
    }

    public boolean isModoDebug() {
        return modoDebug;
    }

    public void setModoDebug(boolean modoDebug) {
        this.modoDebug = modoDebug;
    }
}
```

Uso en distintas partes del programa:

```java
Configuracion config = Configuracion.getInstancia();
config.setModoDebug(true);
```

```java
Configuracion config = Configuracion.getInstancia();

if (config.isModoDebug()) {
    System.out.println("Modo debug activado");
}
```

Ambas clases están trabajando sobre **la misma instancia**, lo que garantiza coherencia en toda la aplicación.

---

### Ventajas

* Control de instancia única
* Acceso global

---

### Consideraciones

* Puede generar acoplamiento
* Puede dificultar pruebas
* Problemas en multihilo si no se controla

---

## 8.4. Patrón Factory

### Propósito

Delegar la creación de objetos en una clase fábrica para que el cliente no decida qué objeto crear.

---

### Ejemplo base (idea general)

El cliente no hace:

```java
new Coche();
```

Sino:

```java
factory.crearVehiculo();
```

---

### Ejemplo real: Sistema de recomendación de vehículos

Una aplicación recomienda un vehículo en función de:

* número de personas
* distancia al trabajo

El cliente no debe saber si se crea un coche, una moto o una bicicleta.

---

### Producto

```java
public interface Vehiculo {
    void mostrarTipo();
}
```

---

### Clases concretas

```java
public class Coche implements Vehiculo {
    public void mostrarTipo() {
        System.out.println("Coche");
    }
}

public class Moto implements Vehiculo {
    public void mostrarTipo() {
        System.out.println("Moto");
    }
}

public class Bicicleta implements Vehiculo {
    public void mostrarTipo() {
        System.out.println("Bicicleta");
    }
}
```

---

### Fábrica

```java
public class VehiculoFactory {

    public Vehiculo crearVehiculo(int personas, int distancia) {

        if (personas == 1 && distancia < 10) {
            return new Bicicleta();
        } else if (personas <= 2) {
            return new Moto();
        } else {
            return new Coche();
        }
    }
}
```

---

### Uso

```java
VehiculoFactory factory = new VehiculoFactory();

Vehiculo v = factory.crearVehiculo(3, 20);
v.mostrarTipo();
```

---

### Explicación

* El cliente no conoce las clases concretas.
* La lógica de creación está en la fábrica.
* Si cambia la lógica, solo se modifica la fábrica.

---

### Ventajas

* Encapsulación de la creación
* Bajo acoplamiento
* Flexibilidad

---

## 8.5. Patrón Builder

### Propósito

Construir objetos complejos paso a paso.

---

### Problema

Clases con muchos parámetros opcionales generan:

* constructores muy largos
* objetos difíciles de leer

---

### Ejemplo real: Creación de usuario

Un usuario puede tener:

* nombre (obligatorio)
* email (opcional)
* edad (opcional)

---

### Implementación

```java
public class Usuario {

    private String nombre;
    private String email;
    private int edad;

    private Usuario(Builder builder) {
        this.nombre = builder.nombre;
        this.email = builder.email;
        this.edad = builder.edad;
    }

    public static class Builder {

        private String nombre;
        private String email;
        private int edad;

        public Builder(String nombre) {
            this.nombre = nombre;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setEdad(int edad) {
            this.edad = edad;
            return this;
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }
}
```

---

### Uso

```java
Usuario u = new Usuario.Builder("Juan")
                .setEmail("juan@email.com")
                .setEdad(30)
                .build();
```

---

### Explicación

* Se crea un builder con los datos obligatorios.
* Se añaden opcionales paso a paso.
* Se construye el objeto final con `build()`.

---

### Ventajas

* Código más legible
* Evita constructores complejos
* Permite objetos inmutables

---

## 7. Comparativa final

| Patrón    | Problema que resuelve | Idea clave               |
| --------- | --------------------- | ------------------------ |
| Singleton | Una única instancia   | Objeto global único      |
| Factory   | Creación de objetos   | Delegar creación         |
| Builder   | Objetos complejos     | Construcción paso a paso |

---

## 8. Idea final

Los patrones de diseño son soluciones probadas a problemas comunes. Su correcta aplicación mejora la calidad del software, pero deben utilizarse solo cuando el problema lo requiere.
