# Resumen UML

## 1. Idea general de UML

UML (Unified Modeling Language) es un lenguaje gráfico que permite representar:

* clases
* relaciones entre clases
* estructura del sistema

👉 No representa código exacto, sino **diseño y relaciones conceptuales**

---

## 2. Tipos de relaciones en UML

Las relaciones indican **cómo se conectan las clases**. Existen varios tipos, que se diferencian principalmente por:

* **fuerza de la relación**
* **tipo de uso**
* **símbolo gráfico**

---

## 3. Dependencia vs Asociación (CLAVE)

### DEPENDENCIA

**Concepto**

Una clase **usa a otra de forma puntual**, pero no la guarda.

**Ejemplo**

```java
void metodo(Cliente c) { }
```

👉 Se usa `Cliente`, pero no es atributo.

**UML**

```text
A - - - - > B
```

* Línea discontinua
* Flecha simple

**Interpretación**

> “A usa B en algún momento”

**Características**

* Relación débil
* Uso temporal
* No forma parte de la estructura

### ASOCIACIÓN

**Concepto**

Una clase **tiene relación estructural con otra** (la guarda como atributo).

**Ejemplo**

```java
class Pedido {
    Cliente cliente;
}
```

**UML**

```text
Pedido -------- Cliente
```

* Línea continua

**Interpretación**

> “Pedido tiene un Cliente”

**Características**

* Relación fuerte
* Permanente
* Forma parte del diseño

### DIFERENCIA CLAVE (MUY IMPORTANTE)

| Concepto | Dependencia       | Asociación           |
| -------- | ----------------- | -------------------- |
| Tipo     | Uso puntual       | Relación estructural |
| Código   | Variable local    | Atributo             |
| UML      | Línea discontinua | Línea continua       |
| Fuerza   | Débil             | Fuerte               |

### REGLA DE ORO

> ✔ Si lo guardas → Asociación
> ✔ Si solo lo usas → Dependencia

---

## 4. Tipos de Asociación

### Asociación simple

Relación sin más detalle:

```text
A -------- B
```

### Agregación

**Concepto**

Relación “tiene un” pero **débil**

👉 Las partes pueden existir sin el todo

**UML**

```text
Equipo ◇-------- Jugador
```

* Rombo blanco

### Composición

**Concepto**

Relación fuerte:

👉 El todo controla el ciclo de vida

**UML**

```text
Casa ◆-------- Habitacion
```

* Rombo negro

---

## 5. Navegabilidad en Asociación

### Qué es

Indica **en qué dirección se puede acceder**

### UML

```text
A --------> B
```

### Interpretación

> Desde A puedo acceder a B

---

### IMPORTANTE

* Solo se aplica a **asociaciones**
* También en:

  * agregación
  * composición

### NO es lo mismo que dependencia

```text
A - - - - > B
```

👉 Aquí la flecha significa “uso”, no navegabilidad

---

## 6. Dirección de las flechas (CLAVE)

| Relación       | Dirección               |
| -------------- | ----------------------- |
| Dependencia    | quien usa → usado       |
| Asociación     | quien conoce → conocido |
| Agregación     | todo → partes           |
| Composición    | todo → partes           |
| Herencia       | hijo → padre            |
| Implementación | clase → interfaz        |

---

## 7. Símbolos UML (RESUMEN VISUAL)

| Relación       | Línea       | Flecha           |
| -------------- | ----------- | ---------------- |
| Dependencia    | Discontinua | Flecha simple    |
| Asociación     | Continua    | (opcional)       |
| Navegabilidad  | Continua    | Flecha simple    |
| Agregación     | Continua    | Rombo blanco     |
| Composición    | Continua    | Rombo negro      |
| Herencia       | Continua    | Triángulo blanco |
| Implementación | Discontinua | Triángulo blanco |

---

## 8. Representación de patrones en UML

### 8.1 Singleton

**Idea**

Una sola instancia

**UML**

```text
Logger
- instancia: Logger <<static>>
- Logger()
+ getInstancia(): Logger <<static>>
```

**Claves**

* atributo estático privado
* constructor privado
* método estático público

**IMPORTANTE**

* No se representa con relaciones
* Es una estructura interna

---

### 8.2 Factory

**Idea**

Una clase crea objetos

**UML**

```text
ProductoFactory -----> Producto
```

**Elementos**

* interfaz o clase base
* clases concretas
* factory
* dependencia

**Interpretación**

> “Delegamos la creación de objetos”

### 8.3 Builder

**Idea**

Construir objetos complejos paso a paso

**UML (simplificado)**

```text
Director ------> Builder
Builder --------▷ Producto
```

**Elementos**

* Builder (interfaz)
* ConcreteBuilder
* Producto
* Director

**Interpretación**

> “Separar construcción de representación”

---

## 9. Diferencia conceptual clave

### Dependencia

> Relación de **uso temporal**

### Asociación

> Relación **estructural permanente**

### Resumen

* Dependencia → “lo necesito ahora”
* Asociación → “forma parte de mí”

---

## 10. Ideas clave que os pueden ayudar

* “La flecha no siempre significa lo mismo”
* “Dependencia es uso, asociación es estructura”
* “Singleton es cómo se crea, no cómo se relaciona”
* “Si lo guardas → asociación”
* “Si solo lo usas → dependencia”
* “La navegabilidad indica hacia dónde puedo ir”

---

## 11. Conclusión

UML no solo dibuja clases, sino que expresa:

* cómo se relacionan
* con qué fuerza
* en qué dirección

Entender bien:

* **Dependencia vs Asociación**
* **Tipos de asociación**
* **Dirección de las flechas**

es fundamental para:

👉 diseñar bien
👉 leer diagramas correctamente
👉 evitar errores conceptuales

