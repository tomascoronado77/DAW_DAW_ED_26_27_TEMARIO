# Resolver con Patrones de Diseño Creacionales

Podéis leer cada actividad e intentar resolverla sin ayudas. 

Pero para que no tengáis tanta tentación de resolverlo directamente con la ayuda de la IA, aquí tenéis un par de guías de ayuda para resolver las actividades:

* Opción recomendada: [Guía de ayuda con pistas escalonadas](8_patrones_actividades_guia_resumen.md)
* Opción con más ayuda: [Guía de ayuda más detallada](8_patrones_actividades_guia_detallada.md)

---

## Actividad 1: Gestor de registros (Logger)

### Enunciado

Se desea implementar un sistema de registro (log) para una aplicación.

El sistema debe:

* Registrar mensajes de eventos importantes.
* Mostrar todos los mensajes registrados.
* Garantizar que **solo existe un único gestor de logs en toda la aplicación**.

### Tareas

1. Implementa una clase `Logger` usando el patrón Singleton.

2. La clase debe:

* Almacenar los mensajes en una lista.
* Tener un método:

```java
void registrar(String mensaje);
```

* Tener un método:

```java
void mostrarLogs();
```

3. Crea dos clases diferentes:

* `ServicioUsuarios`
* `ServicioPedidos`

Cada una debe:

* Obtener la instancia del `Logger`
* Registrar al menos 2 mensajes

4. En `Main`:

* Llama a ambos servicios
* Muestra todos los logs al final

### Ejemplo de salida

```text
[INFO] Usuario creado
[INFO] Usuario eliminado
[INFO] Pedido creado
[INFO] Pedido enviado
```

### Pista

* Usa una lista (`ArrayList<String>`)
* El método `getInstancia()` debe devolver siempre el mismo objeto
* Todas las clases deben usar ese mismo objeto

---

## Actividad 2: Sistema de notificaciones

### Enunciado

Se desea crear un sistema de notificaciones que pueda enviar mensajes por distintos medios:

* Email
* SMS
* Push

El sistema debe decidir automáticamente qué tipo de notificación crear según el parámetro de entrada recibido en el método. Puedes usar un tipo Enumerado.

### Tareas

1. Crea una interfaz `Notificacion` con el método:

```java
void enviar(String mensaje);
```

2. Implementa las clases:

* `EmailNotificacion`
* `SmsNotificacion`
* `PushNotificacion`

3. Crea una clase `NotificacionFactory` con un método:

```java
Notificacion crearNotificacion(String tipo);
```

4. Crea una clase `Main` que:

   * pida el tipo de notificación
   * cree la notificación usando la factoría
   * envíe un mensaje

### Ejemplo de uso

```java
Notificacion n = factory.crearNotificacion("email");
n.enviar("Hola");
```

---

## Actividad 3: Creación de pedido

### Enunciado

Se desea representar un `Pedido` de una tienda online.

Un pedido tiene:

* `cliente` (obligatorio)
* `direccion` (obligatorio)
* `telefono` (opcional)
* `email` (opcional)
* `observaciones` (opcional)

### Problema

Crear el objeto con muchos parámetros resulta confuso.

### Tareas

1. Implementa la clase `Pedido` usando el patrón Builder.
2. El constructor debe ser privado.
3. La clase `Builder` debe:

   * recibir los datos obligatorios
   * permitir añadir los opcionales
4. Implementa un método `toString()` para mostrar el pedido.
5. Crea un `Main` donde construyas al menos:

   * un pedido básico
   * un pedido completo

### Ejemplo de uso

```java
Pedido p = new Pedido.Builder("Juan", "Calle Real 5")
                .setTelefono("123456789")
                .setEmail("juan@email.com")
                .build();
```

