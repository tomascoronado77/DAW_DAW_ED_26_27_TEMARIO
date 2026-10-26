# 7. Principios SOLID

Buenas prácticas para un software mantenible y flexible

---

## 1. Introducción: calidad del software y diseño

Cuando desarrollamos software profesional, no basta con que “funcione”. Debe ser:

* Mantenible
* Escalable
* Flexible ante cambios
* Fácil de probar
* Comprensible por otros desarrolladores

El modelo de calidad **ISO/IEC 25010** establece que la mantenibilidad y la modificabilidad son factores clave de calidad.

En este contexto surgen los principios **SOLID**, formulados por **Robert C. Martin**, como una guía para diseñar sistemas orientados a objetos robustos y preparados para crecer.

SOLID es el acrónimo de cinco principios:

* S – Single Responsibility Principle (SRP)
* O – Open/Closed Principle (OCP)
* L – Liskov Substitution Principle (LSP)
* I – Interface Segregation Principle (ISP)
* D – Dependency Inversion Principle (DIP)

En este apartado nos centraremos especialmente en **SRP y DIP**, por su enorme impacto en la calidad del software.

---

## 2. SRP – Single Responsibility Principle

**Definición**

> Una clase debe tener una única razón para cambiar.

Esto no significa “hacer clases pequeñas”, sino que cada clase debe tener **una única responsabilidad conceptual**.

### Ejemplo – Violación del SRP

```java
public class Factura {

    private double importe;

    public Factura(double importe) {
        this.importe = importe;
    }

    public double calcularIVA() {
        return importe * 0.21;
    }

    public void guardarEnBaseDatos() {
        System.out.println("Guardando factura en la BD...");
    }

    public void generarPDF() {
        System.out.println("Generando PDF...");
    }
}
```

Problema:

* Lógica de negocio
* Persistencia
* Generación de documentos

Todo mezclado en la misma clase.

Tiene varias razones para cambiar:

* Cambio en impuestos
* Cambio en base de datos
* Cambio en formato PDF

### Refactorización aplicando SRP

```java
public class Factura {

    private double importe;

    public Factura(double importe) {
        this.importe = importe;
    }

    public double calcularIVA() {
        return importe * 0.21;
    }

    public double getImporte() {
        return importe;
    }
}
```

```java
public class FacturaRepositorio {

    public void guardar(Factura factura) {
        System.out.println("Guardando factura en la BD...");
    }
}
```

```java
public class FacturaPDF {

    public void generar(Factura factura) {
        System.out.println("Generando PDF...");
    }
}
```

Ahora:

* Factura → modelo
* FacturaRepositorio → persistencia
* FacturaPDF → generación de documentos

Cada clase tiene una única responsabilidad.

---

## 3. DIP – Dependency Inversion Principle

### Definición

1. Los módulos de alto nivel no deben depender de módulos de bajo nivel.
2. Ambos deben depender de abstracciones.
3. Las abstracciones no deben depender de detalles.
4. Los detalles deben depender de abstracciones.

### Ejemplo – Violación del DIP

```java
public class EmailService {

    public void enviar(String mensaje) {
        System.out.println("Enviando email: " + mensaje);
    }
}

public class Notificador {

    private EmailService emailService = new EmailService();

    public void notificar(String mensaje) {
        emailService.enviar(mensaje);
    }
}

public class EmailService {

    public void enviar(String mensaje) {
        System.out.println("Enviando email: " + mensaje);
    }
}
```

Problema:

Notificador depende directamente de EmailService.
Si queremos cambiar a SMS → debemos modificar la clase.

### Aplicando DIP con interfaz

```java
public interface IServicioNotificacion {
    void enviar(String mensaje);
}
```

```java
public class EmailService implements IServicioNotificacion {

    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando email: " + mensaje);
    }
}
```

```java
public class SMSService implements IServicioNotificacion {

    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando SMS: " + mensaje);
    }
}
```

```java
public class Notificador {

    private IServicioNotificacion servicio;

    public Notificador(IServicioNotificacion servicio) {
        this.servicio = servicio;
    }

    public void notificar(String mensaje) {
        servicio.enviar(mensaje);
    }
}
```

Ahora:

* Notificador depende de una abstracción.
* Podemos cambiar implementación sin modificar la clase.

---

## 4. OCP – Open/Closed Principle

> Abierto a extensión, cerrado a modificación.

Evitar modificar código existente para añadir nuevas funcionalidades, es decir, el software debe poder ampliarse sin tener que modificar el código ya existente.

### Ejemplo – Violación del OCP

```java
public class CalculadoraDescuento {

    public double calcularDescuento(String tipoCliente, double importe) {

        if (tipoCliente.equals("NORMAL")) {
            return importe * 0.05;
        } else if (tipoCliente.equals("VIP")) {
            return importe * 0.10;
        } else if (tipoCliente.equals("EMPLEADO")) {
            return importe * 0.20;
        }

        return 0;
    }
}
```

### Problema

Si aparece un nuevo tipo de cliente:

```
PREMIUM
```

Tenemos que **modificar la clase** y añadir otro `if`.

Cada nuevo tipo obliga a tocar el código existente → viola OCP.

### Refactorización aplicando OCP

Creamos una abstracción:

```java
public interface Descuento {
    double aplicar(double importe);
}
```

Implementaciones:

```java
public class DescuentoNormal implements Descuento {

    @Override
    public double aplicar(double importe) {
        return importe * 0.05;
    }
}
```

```java
public class DescuentoVIP implements Descuento {

    @Override
    public double aplicar(double importe) {
        return importe * 0.10;
    }
}
```

```java
public class DescuentoEmpleado implements Descuento {

    @Override
    public double aplicar(double importe) {
        return importe * 0.20;
    }
}
```

La calculadora ahora:

```java
public class CalculadoraDescuento {

    public double calcular(Descuento descuento, double importe) {
        return descuento.aplicar(importe);
    }
}
```

Ahora:

* Para añadir `DescuentoPremium`
* Solo creamos una nueva clase
* No modificamos `CalculadoraDescuento`

✔ Abierto a extensión
✔ Cerrado a modificación

---

## 5. LSP – Liskov Substitution Principle

> Una subclase debe poder sustituir a su clase base sin alterar el comportamiento esperado.

### Ejemplo clásico – Violación del LSP

```java
public class Rectangulo {

    protected int ancho;
    protected int alto;

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int calcularArea() {
        return ancho * alto;
    }
}
```

Ahora creamos:

```java
public class Cuadrado extends Rectangulo {

    @Override
    public void setAncho(int ancho) {
        this.ancho = ancho;
        this.alto = ancho;
    }

    @Override
    public void setAlto(int alto) {
        this.alto = alto;
        this.ancho = alto;
    }
}
```

Problema:

```java
Rectangulo r = new Cuadrado();
r.setAncho(5);
r.setAlto(4);

System.out.println(r.calcularArea()); // Esperaríamos 20
```

Pero el área será:

```
16
```

El comportamiento cambia inesperadamente.

El `Cuadrado` no puede sustituir correctamente a `Rectangulo`.

### Solución correcta

Separar jerarquía:

```java
public interface Figura {
    int calcularArea();
}
```

```java
public class Rectangulo implements Figura {

    private int ancho;
    private int alto;

    public Rectangulo(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
    }

    @Override
    public int calcularArea() {
        return ancho * alto;
    }
}
```

```java
public class Cuadrado implements Figura {

    private int lado;

    public Cuadrado(int lado) {
        this.lado = lado;
    }

    @Override
    public int calcularArea() {
        return lado * lado;
    }
}
```

Ahora:

```java
Figura f = new Cuadrado(4);
System.out.println(f.calcularArea());
```

✔ Sustituibilidad correcta
✔ Sin comportamientos inesperados

---

## 6. ISP – Interface Segregation Principle

> Es mejor tener varias interfaces pequeñas y específicas que una interfaz grande y obligatoria para todos.

### Ejemplo – Violación del ISP

```java
public interface Trabajador {
    void trabajar();
    void comer();
}
```

Implementación:

```java
public class Robot implements Trabajador {

    @Override
    public void trabajar() {
        System.out.println("Robot trabajando...");
    }

    @Override
    public void comer() {
        throw new UnsupportedOperationException("Un robot no come");
    }
}
```

Problema:

* Robot está obligado a implementar `comer()`
* Pero no tiene sentido

Interfaz demasiado general.

### Aplicando ISP correctamente

Separar interfaces:

```java
public interface Trabajador {
    void trabajar();
}
```

```java
public interface SerHumano {
    void comer();
}
```

Implementaciones:

```java
public class Persona implements Trabajador, SerHumano {

    @Override
    public void trabajar() {
        System.out.println("Persona trabajando...");
    }

    @Override
    public void comer() {
        System.out.println("Persona comiendo...");
    }
}
```

```java
public class Robot implements Trabajador {

    @Override
    public void trabajar() {
        System.out.println("Robot trabajando...");
    }
}
```

Ahora:

✔ Cada clase implementa solo lo que necesita
✔ No hay métodos “falsos”
✔ Diseño más limpio

---

## 7. Conclusión

Los principios SOLID no son reglas rígidas, sino guías para:

* Reducir acoplamiento
* Aumentar cohesión
* Facilitar pruebas
* Preparar el sistema para crecer


