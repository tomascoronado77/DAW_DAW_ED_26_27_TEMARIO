# Guía de ayuda para resolver las actividades de Patrones de Diseño Creacionales

## Actividad 1: Gestor de registros (Logger) con Singleton

### Qué debes conseguir

Debes crear una clase que actúe como un **registro único compartido** por toda la aplicación. Todas las clases que quieran guardar mensajes deben trabajar sobre el mismo objeto.

### Paso 1. Piensa qué debe almacenar la clase

Tu clase `Logger` debe guardar varios mensajes. Para ello, necesitas una estructura que permita almacenar varios textos.

Pista:

* piensa en una colección de Java adecuada para guardar cadenas;
* cada vez que se registre un mensaje, deberás añadirlo a esa colección.

### Paso 2. Evita que puedan crearse varios objetos

Si quieres aplicar Singleton, no debe ser posible hacer libremente `new Logger()` desde otras clases.

Pista:

* revisa qué modificador de acceso se usa en el constructor de un Singleton;
* ese constructor no debe estar accesible desde fuera de la propia clase.

### Paso 3. Guarda la única instancia

La clase debe tener un atributo que almacene la única instancia que existirá del logger.

Pista:

* ese atributo debe pertenecer a la clase, no al objeto;
* por tanto, piensa si debe ser de instancia o estático.

### Paso 4. Crea el método de acceso

Necesitas un método que devuelva siempre la misma instancia.

Pistas:

* si la instancia aún no existe, se crea;
* si ya existe, se devuelve la que ya estaba creada;
* este método debe poder llamarse sin necesidad de tener un objeto previo.

### Paso 5. Implementa el registro de mensajes

Debes crear un método `registrar(String mensaje)`.

Piensa:

* ese método recibirá un texto;
* su trabajo consistirá en guardarlo dentro de la colección de mensajes.

### Paso 6. Implementa la visualización de mensajes

Debes crear un método `mostrarLogs()`.

Piensa:

* tendrá que recorrer la colección;
* debe mostrar todos los mensajes guardados hasta ese momento.

### Paso 7. Crea dos clases distintas que usen el logger

`ServicioUsuarios` y `ServicioPedidos` deben obtener la instancia del logger y registrar mensajes.

Pista:

* en ambas clases tienes que pedir la instancia del `Logger`;
* no debes crear un logger nuevo;
* las dos clases deben trabajar sobre el mismo objeto.

### Paso 8. Comprueba que realmente es Singleton

Desde `Main`, ejecuta ambos servicios y después muestra los logs.

Debes observar que:

* los mensajes de ambos servicios aparecen juntos;
* eso significa que ambos han usado el mismo logger.

### Errores que debes evitar

No hagas esto:

* crear el logger con `new` desde otras clases;
* crear una lista de mensajes distinta en cada servicio;
* hacer que el método que devuelve la instancia cree un objeto nuevo siempre.

---

## Actividad 2: Sistema de notificaciones con Factory

### Qué debes conseguir

Debes crear un sistema donde el programa pueda enviar notificaciones de distintos tipos, pero sin que el cliente cree directamente los objetos concretos.

### Paso 1. Busca qué tienen en común los distintos tipos de notificación

Tienes varios tipos:

* email;
* sms;
* push.

Todos comparten una misma idea: enviar un mensaje.

Pista:

* crea una interfaz común;
* esa interfaz debe declarar el método que todos deben implementar.

### Paso 2. Crea las clases concretas

Debes tener una clase para cada tipo de notificación.

Piensa:

* cada clase implementa la interfaz;
* cada una mostrará un mensaje distinto al enviar.

Pista:

* no hace falta complicarlo;
* basta con que cada clase escriba por pantalla el tipo de notificación y el mensaje recibido.

### Paso 3. Representa correctamente los tipos posibles

En lugar de usar textos sueltos, usa un tipo enumerado.

Pista:

* crea un `enum` con los valores posibles;
* así evitas errores al escribir cadenas incorrectas.

### Paso 4. Diseña la factoría

Debes crear una clase `NotificacionFactory`.

Su responsabilidad será:

* recibir el tipo de notificación;
* decidir qué objeto concreto crear;
* devolverlo como tipo general, no como clase concreta.

Pista:

* el método de la factoría debe devolver `Notificacion`;
* dentro decidirás qué subtipo crear según el enum recibido.

### Paso 5. Piensa qué patrón se está aplicando realmente

El cliente no debe hacer directamente:

* crear un email;
* crear un sms;
* crear un push.

En lugar de eso, el cliente le pide a la factoría que le devuelva una notificación.

La idea importante es esta:

* el cliente conoce la factoría y la interfaz;
* no necesita conocer las clases concretas.

### Paso 6. Haz el `Main`

Desde `Main`:

* muestra un pequeño menú;
* pide al usuario el tipo de notificación;
* transforma esa opción al enum correspondiente;
* llama a la factoría;
* pide un mensaje;
* envía la notificación.

### Paso 7. Comprueba que el `Main` no depende de las clases concretas

Revisa tu código.

Hazte esta pregunta:

* ¿en `Main` estoy escribiendo `new EmailNotificacion()` o algo parecido?

Si la respuesta es sí, no lo estás haciendo como pide el patrón.

### Errores que debes evitar

No hagas esto:

* usar `String` libremente si te han pedido enum;
* crear las clases concretas directamente desde `Main`;
* hacer que la factoría devuelva siempre el mismo tipo;
* olvidar implementar la interfaz común en todas las clases.

---

## Actividad 3: Creación de pedido con Builder

### Qué debes conseguir

Debes construir un objeto con datos obligatorios y opcionales, evitando un constructor demasiado largo y haciendo la creación más clara.

### Paso 1. Identifica los datos obligatorios y opcionales

Antes de programar, separa bien:

* obligatorios;
* opcionales.

En esta actividad, no todos los atributos deben pedirse igual.

Pista:

* los obligatorios se suelen exigir desde el principio;
* los opcionales se añaden solo si se necesitan.

### Paso 2. Diseña la clase `Pedido`

Piensa qué atributos tendrá el pedido.

Después decide:

* cuáles deben declararse en la clase principal;
* cómo vas a mostrarlos en el `toString()`.

### Paso 3. Evita que el pedido se cree directamente desde fuera

Si usas Builder, el objeto principal no debe construirse libremente con un constructor público normal.

Pista:

* revisa qué modificador suele tener el constructor de la clase producto;
* la idea es que el objeto se construya a través del builder.

### Paso 4. Crea la clase interna `Builder`

Debes crear una clase `Builder` dentro de `Pedido`.

Piensa:

* el builder tendrá también atributos;
* esos atributos irán guardando temporalmente la información;
* al final, con `build()`, se construirá el objeto definitivo.

### Paso 5. Haz que el builder reciba los datos obligatorios

El constructor del builder debe exigir los datos mínimos necesarios para crear un pedido válido.

Pista:

* no pongas los opcionales en ese constructor;
* esos se añadirán con métodos separados.

### Paso 6. Crea métodos para los opcionales

Cada dato opcional debe tener su método correspondiente en el builder.

Piensa cómo deben funcionar esos métodos:

* deben guardar el valor recibido;
* deben devolver el propio builder para poder encadenar llamadas.

Ese detalle es importante para que luego se pueda escribir de forma fluida.

### Paso 7. Implementa `build()`

El builder debe tener un método final que construya el pedido.

Piensa:

* ese método no muestra nada;
* no pide datos;
* simplemente crea y devuelve el objeto final.

### Paso 8. Haz que `Pedido` se construya a partir del builder

La clase `Pedido` necesita alguna forma de copiar los datos que ha ido guardando el builder.

Pista:

* piensa en un constructor que reciba un builder;
* desde ahí puedes tomar los valores y asignarlos a los atributos del pedido.

### Paso 9. Implementa `toString()`

Debes mostrar la información del pedido.

Piensa si quieres:

* mostrar todos los campos siempre;
* o solo los que tengan contenido.

Para este nivel, puedes mostrar todos, aunque algunos queden vacíos o nulos, o puedes intentar mejorar la salida.

### Paso 10. Crea ejemplos en `Main`

Debes construir al menos:

* un pedido básico;
* un pedido completo.

Eso te ayudará a comprobar que el patrón tiene sentido.

Hazte esta pregunta:

* ¿se entiende mejor crear el pedido así que con un constructor larguísimo?

Si la respuesta es sí, vas bien.

### Errores que debes evitar

No hagas esto:

* poner todos los atributos en un único constructor enorme;
* hacer públicos los atributos;
* olvidar que los métodos del builder deben devolver el builder;
* crear el pedido final sin usar `build()`.

---

## Comprobación final

Antes de entregar, revisa lo siguiente:

### Actividad 1

* Existe una única instancia del logger.
* Todas las clases usan esa misma instancia.
* Los mensajes se guardan y se muestran al final.

### Actividad 2

* Existe una interfaz común.
* Cada tipo de notificación tiene su clase.
* La factoría crea el objeto adecuado.
* `Main` no crea directamente las clases concretas.

### Actividad 3

* `Pedido` tiene constructor no público.
* Existe una clase `Builder`.
* Los obligatorios se piden en el builder al principio.
* Los opcionales se añaden con métodos encadenados.
* El objeto final se crea con `build()`.

---

# Consejo final

En las tres actividades no te centres solo en que “funcione”. Intenta entender **qué problema resuelve cada patrón**:

* Singleton: evitar varias instancias de algo que debe ser único.
* Factory: separar la creación de objetos del código cliente.
* Builder: construir objetos complejos de forma clara y ordenada.

