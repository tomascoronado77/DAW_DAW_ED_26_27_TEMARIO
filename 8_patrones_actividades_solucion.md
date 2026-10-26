# SOLUCIONES

## Actividad 1: Gestor de registros (Logger)

### Clase `Logger`

```java
package actividad1;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private static Logger instancia;
    private List<String> mensajes;

    private Logger() {
        mensajes = new ArrayList<>();
    }

    public static Logger getInstancia() {
        if (instancia == null) {
            instancia = new Logger();
        }
        return instancia;
    }

    public void registrar(String mensaje) {
        mensajes.add(mensaje);
    }

    public void mostrarLogs() {
        for (String mensaje : mensajes) {
            System.out.println(mensaje);
        }
    }
}
```

### Clase `ServicioUsuarios`

```java
package actividad1;

public class ServicioUsuarios {

    public void ejecutar() {
        Logger logger = Logger.getInstancia();
        logger.registrar("[INFO] Usuario creado");
        logger.registrar("[INFO] Usuario eliminado");
    }
}
```

### Clase `ServicioPedidos`

```java
package actividad1;

public class ServicioPedidos {

    public void ejecutar() {
        Logger logger = Logger.getInstancia();
        logger.registrar("[INFO] Pedido creado");
        logger.registrar("[INFO] Pedido enviado");
    }
}
```

### Clase `Main`

```java
package actividad1;

public class Main {

    public static void main(String[] args) {

        ServicioUsuarios servicioUsuarios = new ServicioUsuarios();
        ServicioPedidos servicioPedidos = new ServicioPedidos();

        servicioUsuarios.ejecutar();
        servicioPedidos.ejecutar();

        Logger logger = Logger.getInstancia();
        logger.mostrarLogs();
    }
}
```

---

## Actividad 2: Sistema de notificaciones

### Enum `TipoNotificacion`

```java
package actividad2;

public enum TipoNotificacion {
    EMAIL,
    SMS,
    PUSH
}
```

---

### Interfaz `Notificacion`

```java
package actividad2;

public interface Notificacion {
    void enviar(String mensaje);
}
```

---

### Clase `EmailNotificacion`

```java
package actividad2;

public class EmailNotificacion implements Notificacion {

    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando EMAIL: " + mensaje);
    }
}
```

### Clase `SmsNotificacion`

```java
package actividad2;

public class SmsNotificacion implements Notificacion {

    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando SMS: " + mensaje);
    }
}
```

### Clase `PushNotificacion`

```java
package actividad2;

public class PushNotificacion implements Notificacion {

    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando PUSH: " + mensaje);
    }
}
```

### Clase `NotificacionFactory`

```java
package actividad2;

public class NotificacionFactory {

    public Notificacion crearNotificacion(TipoNotificacion tipo) {

        return switch (tipo) {
            case EMAIL -> new EmailNotificacion();
            case SMS -> new SmsNotificacion();
            case PUSH -> new PushNotificacion();
        };
    }
}
```

### Clase `Main`

```java
package actividad2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        NotificacionFactory factory = new NotificacionFactory();

        System.out.println("Tipo de notificación:");
        System.out.println("1 - EMAIL");
        System.out.println("2 - SMS");
        System.out.println("3 - PUSH");

        int opcion = sc.nextInt();

        TipoNotificacion tipo = null;

        switch (opcion) {
            case 1 -> tipo = TipoNotificacion.EMAIL;
            case 2 -> tipo = TipoNotificacion.SMS;
            case 3 -> tipo = TipoNotificacion.PUSH;
            default -> System.out.println("Opción no válida");
        }

        if (tipo != null) {
            sc.nextLine(); // limpiar buffer
            System.out.print("Mensaje: ");
            String mensaje = sc.nextLine();

            Notificacion notificacion = factory.crearNotificacion(tipo);
            notificacion.enviar(mensaje);
        }
    }
}
```

---

## Actividad 3: Creación de pedido

### Clase `Pedido`

```java
package actividad3;

public class Pedido {

    private String cliente;
    private String direccion;
    private String telefono;
    private String email;
    private String observaciones;

    private Pedido(Builder builder) {
        this.cliente = builder.cliente;
        this.direccion = builder.direccion;
        this.telefono = builder.telefono;
        this.email = builder.email;
        this.observaciones = builder.observaciones;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "cliente='" + cliente + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }

    public static class Builder {

        private String cliente;
        private String direccion;
        private String telefono;
        private String email;
        private String observaciones;

        public Builder(String cliente, String direccion) {
            this.cliente = cliente;
            this.direccion = direccion;
        }

        public Builder setTelefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setObservaciones(String observaciones) {
            this.observaciones = observaciones;
            return this;
        }

        public Pedido build() {
            return new Pedido(this);
        }
    }
}
```

### Clase `Main`

```java
package actividad3;

public class Main {

    public static void main(String[] args) {

        Pedido pedidoBasico = new Pedido.Builder("Juan Pérez", "Calle Real 5")
                .build();

        Pedido pedidoCompleto = new Pedido.Builder("Ana López", "Avenida Andalucía 12")
                .setTelefono("123456789")
                .setEmail("ana@email.com")
                .setObservaciones("Entregar por la tarde")
                .build();

        System.out.println("Pedido básico:");
        System.out.println(pedidoBasico);

        System.out.println();

        System.out.println("Pedido completo:");
        System.out.println(pedidoCompleto);
    }
}
```

