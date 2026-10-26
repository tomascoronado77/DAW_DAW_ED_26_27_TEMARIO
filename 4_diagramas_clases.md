
# 4. Diagrama de Clases

## 1. Introducción

Los diagramas de clases son una herramienta fundamental en la modelización de sistemas orientados a objetos. Permiten representar la estructura estática de un sistema, mostrando las clases del sistema, sus atributos, métodos y las relaciones entre ellas.

Se utilizan principalmente durante la fase de **diseño del software**, ya que ayudan a:

* Comprender el sistema antes de implementarlo.
* Detectar errores de diseño de forma temprana.
* Comunicar la arquitectura entre los miembros del equipo.
* Servir como documentación técnica del proyecto.

El diagrama de clases forma parte del estándar **UML (Unified Modeling Language)**, el lenguaje de modelado más utilizado en ingeniería del software.

Es importante entender que un diagrama **no sustituye al código**, sino que actúa como una representación simplificada del mismo.

---

## 2. Clases

Para representar una clase se utiliza una caja que es dividida en tres zonas utilizando para ello líneas horizontales:

La primera de ellas se utiliza para el nombre de la clase.
El nombre debe escribirse en singular y con la primera letra en mayúscula, siguiendo la convención habitual de los lenguajes orientados a objetos como Java.

La segunda se utiliza para escribir los atributos de la clase utilizando el siguiente formato:

```
visibilidad nombre_atributo : tipo = valor-inicial
```

La visibilidad se indica con los siguientes símbolos:

* Pública (public en Java): +
* Privada (private en Java): -
* Protegida (protected en Java): #

Si no se indica nada, entonces es *friendly* (visibilidad de paquete en Java).

> En la práctica profesional se recomienda que los atributos sean **privados**, aplicando el principio de encapsulación.

La tercera se utiliza para escribir los métodos utilizando el siguiente formato:

```
visibilidad nombre_método(nombre_parámetro:tipo,...):tipo_devolucion
```

No es obligatorio incluir todos los getters y setters en el diagrama. Normalmente solo se representan los métodos relevantes para el comportamiento del sistema.

Ejemplo:

<img width="261" height="214" alt="image" src="https://github.com/user-attachments/assets/e806fdf0-edd5-4b5c-a0f4-6ee83180304c" />

---

## 3. Clases abstractas

Las clases abstractas y los métodos abstractos se pueden poner en cursiva o bien se les puede poner la palabra reservada *abstract*.

Ambas opciones son válidas, aunque en muchos diagramas actuales se prefiere usar la palabra **abstract** para mejorar la legibilidad.

Una clase abstracta no puede ser instanciada y sirve como base para que otras clases hereden de ella.

Ejemplo:

<img width="261" height="214" alt="image" src="https://github.com/user-attachments/assets/64f83bd3-aaab-4d4e-b95e-4921863c23e6" />

---

## 4. Herencia

La herencia representa una relación **“es-un”** (is-a). Por ejemplo, si una clase `Coche` hereda de `Vehiculo`, significa que un coche es un tipo de vehículo.

La flecha que se utiliza para la herencia es una línea continua con un triángulo vacío:

<img width="137" height="35" alt="image" src="https://github.com/user-attachments/assets/8206232f-af69-4531-8fa4-7f762fc6eaa3" />

La punta de la flecha se coloca en el padre (superclase).

Ejemplo:

<img width="271" height="161" alt="image" src="https://github.com/user-attachments/assets/9522f174-9f67-42b2-8fc1-c54388b693af" />

> Es recomendable evitar jerarquías de herencia demasiado profundas, ya que dificultan el mantenimiento del software.

---

## 5. Interfaces

Su representación es similar a las clases, pero indicando arriba la palabra `<<interface>>`.

La flecha que se utiliza para indicar que una clase implementa una interfaz es similar a la flecha de herencia pero discontinua y la punta de la flecha se coloca en la interfaz.

La clase que implementa la interfaz tiene que implementar los métodos de la interfaz, pero en el diagrama no se ponen los métodos en la clase porque ya se sabe por el hecho de implementar la interfaz.

Los métodos de la interfaz son abstractos y públicos por defecto.

La palabra reservada abstract no hace falta ponerla en el diagrama, pero el símbolo + de visibilidad pública se puede usar para mejorar la legibilidad.

Ejemplo:

<img width="691" height="184" alt="image" src="https://github.com/user-attachments/assets/35ca115b-fed5-48f1-ab29-f2f1928727e2" />

Si lo que queremos modelar es una herencia entre interfaces, se hace exactamente igual que la herencia entre clases.

En el ejemplo, GasolineMotor hereda de ActionsVehicle, es decir, ActionsVehicle es la interfaz padre:

<img width="221" height="284" alt="image" src="https://github.com/user-attachments/assets/b5e706a6-22e9-408c-8415-f95dce7ecb0d" />

> En el diseño moderno se recomienda **priorizar interfaces frente a herencia** cuando sea posible, ya que reducen el acoplamiento entre clases.

---

## 6. Tipos enumerados

Su representación es similar a las clases, pero indicando arriba la palabra `<<enumeration>>`. Además, tienen una zona para indicar las constantes.

Los tipos enumerados se utilizan cuando existe un conjunto **cerrado y conocido de valores**, como por ejemplo:

* Días de la semana
* Estados de un pedido
* Tipos de usuario

Ejemplo:

<img width="264" height="184" alt="image" src="https://github.com/user-attachments/assets/d61d522b-fada-4b18-a65f-5c318e8f5dfe" />

---

## 7. Asociaciones

La asociación es una relación estructural entre clases.

Se tienen que indicar las cardinalidades o multiplicidades, es decir el número de instancias de cada clase que intervienen en la relación.

Se indican con el siguiente rango:

```
mínimo..máximo
```

Ejemplos habituales:

* `1` → exactamente uno
* `0..1` → opcional
* `1..*` → uno o muchos
* `*` → muchos

Ejemplo:

<img width="348" height="51" alt="image" src="https://github.com/user-attachments/assets/8e983db9-2320-4345-b5aa-907acec940f7" />

Si consideramos que es importante reflejar en el diagrama que el empleado puede en un momento dado quedarse sin trabajo, entonces le ponemos el mínimo cero:

<img width="348" height="51" alt="image" src="https://github.com/user-attachments/assets/4fa7760b-0e0a-4630-8a29-8a486bc88f77" />

### Navegabilidad

La navegabilidad se refiere a la capacidad de una clase para acceder a otra clase a través de una asociación.

Por ejemplo, en la asociación entre las clases Empresa y Empleado, la navegabilidad indica si desde una instancia de la clase Empresa se puede acceder a una instancia de la clase Empleado y viceversa.

Esto debe reflejar cómo se diseñará realmente el sistema.

Si tenemos una pantalla donde aparece la información de una empresa y podemos ver desde ahí cuáles son sus empleados, entonces significa que una empresa puede acceder a sus empleados, por lo que se coloca una flecha que va desde la Empresa hasta el Empleado:

<img width="348" height="51" alt="image" src="https://github.com/user-attachments/assets/b58eb859-6c4d-4bec-8b57-58beb519490f" />


Para ello, la empresa tiene que tener conocimiento de cuáles son sus empleados, así que se coloca un atributo en la empresa con una lista de empleados:

<img width="401" height="47" alt="image" src="https://github.com/user-attachments/assets/9ee1a1a9-57a8-4184-976d-48b0513c3faa" />


Si en el diagrama de pantallas existe una pantalla con la información de un empleado y desde ahí se puede consultar en qué empresa trabaja, entonces hay que colocar una flecha que vaya desde el Empleado hasta la Empresa:

<img width="340" height="45" alt="image" src="https://github.com/user-attachments/assets/532a49d2-65e3-4c6b-bdc9-e9880957a9c3" />


Para ello, el empleado tiene que tener conocimiento de cuál es su empresa, así que se coloca un atributo en el empleado con su empresa:

<img width="370" height="49" alt="image" src="https://github.com/user-attachments/assets/247541a2-6608-4846-a756-69285e48d4c2" />


Incluso nos podemos encontrar que se produzcan ambas cosas por lo que la navegabilidad sería bidireccional, lo que indica que ambas clases pueden acceder a la otra:

<img width="431" height="50" alt="image" src="https://github.com/user-attachments/assets/da126dbe-1c61-4af9-b78a-525ded225c30" />


> En la práctica profesional se recomienda evitar asociaciones bidireccionales innecesarias, ya que aumentan el acoplamiento.

---

## 8. Agregación y composición

Representan relaciones de partes-todo entre clases.

Se representan con una línea con un rombo en un extremo, apuntando hacia la clase que representa el todo.

### Agregación

Las partes pueden existir independientemente del todo.

Por ejemplo, un ordenador está formado por una torre, un monitor, un ratón y un teclado. Si las partes pueden cambiarse de un ordenador a otro, entonces sería una agregación:

<img width="481" height="151" alt="image" src="https://github.com/user-attachments/assets/38fc9eb1-4ae8-41b9-9967-878588e9e481" />


### Composición

Las partes no pueden existir sin el todo. Cuando el todo desaparece, las partes también.

Si por ejemplo están las partes atadas con una brida y no se pueden mover a otro ordenador, entonces sería una composición:

<img width="481" height="151" alt="image" src="https://github.com/user-attachments/assets/1a1314de-a099-400e-844c-dc42d98f1ece" />


> Muchos equipos de desarrollo utilizan la composición como alternativa a la herencia para construir sistemas más flexibles.

---

## 9. Dependencias

Cuando una clase utiliza o crea una instancia de otra clase, entonces tiene una dependencia con dicha clase.

Esto ocurre cuando:

* La recibe por parámetro en un método.
* La devuelve como tipo de retorno.
* La crea internamente.
* La utiliza de forma puntual.

Se representa con una línea discontinua acabada en una flecha apuntando hacia la clase de la cual se depende.

Por ejemplo, una persona que alquila un vehículo tiene un método alquilar que recibe dicho vehículo por parámetro. Entonces, la clase Persona tiene una dependencia con la clase Vehículo:

<img width="441" height="90" alt="image" src="https://github.com/user-attachments/assets/800fd801-aa0f-43f0-890b-571a7eb4bafb" />


Veamos otro ejemplo donde una fábrica de coches crea un vehículo. En este caso, la fábrica tiene un método construir que devuelve un vehículo. Entonces, la clase Fábrica de coches tiene una dependencia con la clase Vehículo:

<img width="441" height="90" alt="image" src="https://github.com/user-attachments/assets/c586c8e1-6c6a-48a1-aa3c-628010cfd90d" />


Las interfaces también pueden tener dependencias:

* Cuando definen un método que reciba otra clase o interfaz como parámetro.
* Cuando definen un método que devuelva una instancia de otra clase o interfaz.

> Reducir dependencias innecesarias mejora la mantenibilidad del software.

---
