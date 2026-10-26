
# Guía de ayuda (pistas escalonadas)

## Actividad 1: Logger (Singleton)

### Nivel 1 (pista básica)

* Necesitas una clase `Logger`.
* Todas las clases deben usar **el mismo objeto**.

### Nivel 2

* Guarda los mensajes en una colección.
* Añade un método para registrar mensajes.
* Añade otro para mostrarlos.

### Nivel 3

* No debe poder hacerse `new Logger()` desde fuera.
* Solo puede existir una instancia.

### Nivel 4

* Crea un atributo estático que guarde la instancia.
* Crea un método estático que la devuelva.
* Si no existe, la crea.

### Nivel 5

* Dos clases distintas deben registrar mensajes.
* Ambas deben usar el mismo logger.
* Comprueba que todos los mensajes se acumulan juntos.

---

## Actividad 2: Notificaciones (Factory)

### Nivel 1 (pista básica)

* Hay varios tipos de notificación, pero todas hacen lo mismo: enviar un mensaje.

### Nivel 2

* Crea una interfaz común con un método `enviar`.
* Cada tipo de notificación será una clase distinta.

### Nivel 3

* Crea un `enum` para representar los tipos.
* Evita usar Strings directamente.

### Nivel 4

* Crea una clase factoría.
* Recibe el tipo de notificación.
* Devuelve el objeto adecuado.

### Nivel 5

* En `Main`, no debes usar `new EmailNotificacion()`.
* Solo debes usar la factoría.

### Nivel 6

* Si cambias la lógica de creación, solo debe cambiar la factoría.
* El resto del programa no debería modificarse.

---

## Actividad 3: Pedido (Builder)

### Nivel 1 (pista básica)

* El objeto tiene datos obligatorios y opcionales.

### Nivel 2

* Crear un constructor con muchos parámetros no es buena idea.
* Busca una forma de construir el objeto paso a paso.

### Nivel 3

* Crea una clase `Builder` dentro de `Pedido`.
* El builder tendrá los mismos atributos.

### Nivel 4

* El constructor del builder recibe los datos obligatorios.
* Los opcionales se añaden con métodos.

### Nivel 5

* Cada método opcional debe devolver el builder.
* Así podrás encadenar llamadas.

### Nivel 6

* El builder debe tener un método `build()`.
* Ese método crea el objeto final.

### Nivel 7

* El constructor de `Pedido` no debe ser público.
* Debe construirse a partir del builder.
