
# 5. Documentación con JavaDoc.

## 1. Introducción: para qué sirve documentar

Cuando trabajas en un proyecto real, el código no lo “consumes” leyendo su implementación, sino **usando su API** (clases, métodos públicos, interfaces…). JavaDoc está pensado para eso: **explicar cómo se usa** el código y cuál es su **contrato**.

La guía de Oracle (referencia clásica) insiste en que los *doc comments* describen lo que el usuario del método necesita saber (API), no los detalles internos del algoritmo. ([Oracle][1])

Además, los IDE (Eclipse/IntelliJ) muestran la JavaDoc como ayuda emergente (tooltips), por lo que una buena documentación **reduce errores** y acelera el desarrollo. ([JetBrains][2])

---

## 2. Documentar vs comentar

* **Comentar**: explica *cómo* está hecho (implementación, decisiones internas).
* **Documentar (JavaDoc)**: explica *qué hace* y *cómo se usa* (contrato).

Ejemplo (comentario interno):

```java
// Si es negativa, no permitimos acelerar
if (amount < 0) throw new IllegalArgumentException();
```

Ejemplo (JavaDoc para quien usa el método):

```java
/**
 * Increases the current speed.
 *
 * @param amount increase in km/h; must be non-negative
 * @throws IllegalArgumentException if {@code amount} is negative
 */
public void accelerate(double amount) { ... }
```

---

## 3. Qué documentar (y qué no)

Regla práctica en entornos profesionales:

### Documenta SIEMPRE

* **Clases públicas** y su propósito.
* **Interfaces** (qué contrato definen).
* **Métodos públicos/protegidos** (contrato completo).
* **Constructores públicos** (qué crean y restricciones).
* **Enums** y significado de cada constante si aporta valor.

### Normalmente NO documentes

* Métodos **privados** (mejor comentario puntual si hace falta).
* Getters/setters triviales (solo si hay reglas: unidades, rangos, efectos).
* Cosas obvias (JavaDoc que repite el nombre del método).

---

## 4. Cómo se escribe JavaDoc

Un comentario JavaDoc empieza por `/**` y termina en `*/`.

```java
/**
 * Texto de documentación.
 */
```

### 4.1 La primera frase (summary) es crítica

El comando `javadoc` y los IDE usan la **primera frase** como resumen en listados e índices. Por eso debe ser breve y significativa. ([docs.oracle.com][3])

Ejemplo:

```java
/**
 * Returns the current speed in km/h.
 */
public double getSpeed() { ... }
```

---

## 5. Documentación de clases

Una JavaDoc de clase debería incluir:

* **Qué representa** y **para qué existe**.
* Reglas importantes (invariantes).
* Cómo se usa (a veces un ejemplo corto).
* Relaciones (si es clave para entenderla).

Ejemplo “bien”:

```java
/**
 * Represents a vehicle with a speed and a color.
 * <p>
 * Speed is measured in km/h and is never negative.
 */
public class Vehicle { ... }
```

> Nota: JavaDoc soporta HTML básico para párrafos y formato, y los IDE lo respetan. ([JetBrains][2])

### Etiquetas típicas en clase

* `@since` (muy útil cuando hay versiones).
* `@deprecated` (si procede).
* `@author`, `@version` son más académicas; en equipos modernos a veces se omiten (Git ya cubre eso).

---

## 6. Documentación de atributos (campos)

Documenta campos cuando:

* Son parte de la API pública, o
* Tienen reglas/semántica que no se ve en el tipo (unidades, rangos…).

Ejemplo:

```java
/** Current speed in km/h. Never negative. */
private double speed;
```

Si el campo es privado y trivial, muchas veces no hace falta.

---

## 7. Documentación de métodos (el “contrato”)

Aquí está lo más importante de JavaDoc: **la especificación del método**.

### 7.1 Qué debe incluir un método

1. Resumen (1ª frase)
2. Descripción (si hace falta)
3. Parámetros (`@param`)
4. Retorno (`@return`)
5. Excepciones (`@throws`)
6. Notas de versión (`@since`) / obsolescencia (`@deprecated`)

### 7.2 Ejemplo completo y profesional

```java
/**
 * Transfers money from this account to the target account.
 *
 * @param target account that receives the funds
 * @param amount amount in euros; must be greater than zero
 * @throws NullPointerException if {@code target} is {@code null}
 * @throws IllegalArgumentException if {@code amount <= 0}
 * @throws InsufficientFundsException if balance is lower than {@code amount}
 */
public void transferTo(Account target, BigDecimal amount) { ... }
```

**Claves didácticas:**

* No dice *cómo* transfiere, solo *qué garantiza*.
* Los `@throws` explican **la condición**, no solo el nombre.
* Usa `{@code ...}` para literales y nombres de variables (estilo muy usado). ([chromium.googlesource.com][4])

---

## 8. Etiquetas esenciales (las más usadas)

### 8.1 `@param`

Describe el significado del parámetro y restricciones.

Mal (vacío/obvio):

```java
@param amount amount
```

Bien:

```java
@param amount increase in km/h; must be non-negative
```

### 8.2 `@return`

Solo cuando aporte valor semántico.

```java
/**
 * Returns the current speed in km/h.
 *
 * @return current speed; never negative
 */
public double getSpeed() { ... }
```

### 8.3 `@throws`

Explica **cuándo** se lanza (no solo “lanza X”).

```java
@throws IllegalArgumentException if {@code amount} is negative
```

---

## 9. Enlaces: `@see` vs `{@link}`

* `{@link ...}` crea un enlace **inline**, dentro del texto.
* `@see` crea una sección “See Also”.

Diferencia funcional conocida y ampliamente usada en práctica. ([Stack Overflow][5])

Ejemplo con `{@link}`:

```java
/**
 * Stores vehicles in a {@link java.util.List}.
 */
```

Ejemplo con `@see`:

```java
/**
 * Vehicle list manager.
 *
 * @see Vehicle
 * @see java.util.List
 */
```

**En código moderno, `{@link}` suele aparecer más porque mejora la lectura dentro del párrafo.**

---

## 10. Etiquetas y recursos avanzados útiles

### 10.1 `{@code ...}` (muy recomendable)

Para marcar:

* literales: `{@code null}`, `{@code true}`
* nombres de variables
* pequeños fragmentos

Esto lo verás en guías reales de equipos grandes (p.ej., Gradle). ([chromium.googlesource.com][4])

### 10.2 `{@inheritDoc}`

Cuando una clase implementa una interfaz o sobrescribe un método, puedes heredar documentación sin duplicarla.

Esto está soportado por la herramienta `javadoc`. ([manpages.debian.org][6])

```java
/** {@inheritDoc} */
@Override
public void accelerate(double amount) { ... }
```

Además, Oracle explica que si no escribes doc comment en un override, el `javadoc` puede reutilizar el texto del método original en ciertos casos. ([Oracle][1])

### 10.3 `@deprecated` (y cómo hacerlo bien)

El `javadoc` recomienda que el primer texto indique alternativa y, si procede, un `{@link}` al reemplazo. ([docs.oracle.com][3])

```java
/**
 * @deprecated Use {@link #accelerate(double)} instead.
 */
@Deprecated
public void speedUp(double amount) { ... }
```

---

## 11. Documentación de interfaces

Una interfaz es un **contrato**. Su JavaDoc debe dejar claro:

* qué comportamiento promete
* qué significa cada método
* qué restricciones deben respetar las clases implementadoras

Ejemplo:

```java
/**
 * Defines movement operations for vehicles.
 * <p>
 * Implementations must ensure that speed never becomes negative.
 */
public interface VehicleActions {

    /**
     * Increases the speed by the given amount.
     *
     * @param amount increase in km/h; must be non-negative
     * @throws IllegalArgumentException if {@code amount} is negative
     */
    void accelerate(double amount);

    /**
     * Decreases the speed by the given amount.
     *
     * @param amount decrease in km/h; must be non-negative
     * @throws IllegalArgumentException if {@code amount} is negative
     */
    void brake(double amount);
}
```

---

## 12. Formato y legibilidad (reglas simples)

* Párrafos con `<p>` si el texto es largo. ([JetBrains][2])
* Ejemplos cortos con `<pre>` o `{@code ...}`.
* Evita JavaDoc “de relleno”.
* Si algo cambia en el código, **la JavaDoc debe actualizarse** (si no, estorba).

---

## 13. Generación de documentación

### 13.1 Con la herramienta `javadoc`

En proyectos reales, además del IDE, se automatiza en build (Maven/Gradle). A nivel formativo puedes mostrar:

* Qué se genera: HTML navegable por paquetes/clases/métodos.
* Que la JavaDoc cobra sentido al verla renderizada.

### 13.2 Desde el IDE

* Eclipse: Generate Javadoc (como tienes en tu texto).
* IntelliJ también ofrece generación y, sobre todo, lectura/visualización de JavaDoc integrada. ([JetBrains][2])

---

## 14. Checklist de ayuda para la buena documentación

Antes de dar por “bien documentado” un método público:

1. ¿La **primera frase** resume de verdad? ([docs.oracle.com][3])
2. ¿Los `@param` explican significado y restricciones?
3. ¿El `@return` aporta semántica (no obviedad)?
4. ¿Los `@throws` indican **condición**?
5. ¿He evitado repetir el nombre del método?
6. ¿Hay enlaces `{@link}` cuando ayudan?

---

## 15. Ejemplo final completo (clase + métodos)

```java
/**
 * Represents a vehicle with speed and color.
 * <p>
 * Speed is measured in km/h and is never negative.
 */
public class Vehicle implements VehicleActions {

    /** Current speed in km/h. Never negative. */
    private double speed;

    /** Vehicle color (e.g., "red", "blue"). */
    private String color;

    /**
     * Creates a vehicle with the given initial color.
     *
     * @param color initial color; must not be {@code null}
     * @throws NullPointerException if {@code color} is {@code null}
     */
    public Vehicle(String color) {
        this.color = java.util.Objects.requireNonNull(color);
        this.speed = 0.0;
    }

    /**
     * Returns the current speed in km/h.
     *
     * @return current speed; never negative
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Returns the current color.
     *
     * @return current color; never {@code null}
     */
    public String getColor() {
        return color;
    }

    /**
     * Changes the vehicle color.
     *
     * @param color new color; must not be {@code null}
     * @throws NullPointerException if {@code color} is {@code null}
     */
    public void setColor(String color) {
        this.color = java.util.Objects.requireNonNull(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accelerate(double amount) {
        if (amount < 0) throw new IllegalArgumentException("amount < 0");
        speed += amount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void brake(double amount) {
        if (amount < 0) throw new IllegalArgumentException("amount < 0");
        speed = Math.max(0, speed - amount);
    }
}
```

---

[1]: https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html?utm_source=chatgpt.com "How to Write Doc Comments for the Javadoc Tool - Oracle"
[2]: https://www.jetbrains.com/help/idea/javadocs.html?utm_source=chatgpt.com "Javadocs | IntelliJ IDEA Documentation"
[3]: https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html?utm_source=chatgpt.com "javadoc"
[4]: https://chromium.googlesource.com/external/github.com/gradle/gradle.git/%2B/8dfb6fea4acb490631d71f10e00ad7acade5ffb0/JavadocStyleGuide.md?utm_source=chatgpt.com "Gradle Javadoc Style Guide"
[5]: https://stackoverflow.com/questions/10097199/javadoc-see-or-link?utm_source=chatgpt.com "java - Javadoc @see or {@link}?"
[6]: https://manpages.debian.org/bullseye/openjdk-11-jdk-headless/javadoc.1.en.html?utm_source=chatgpt.com "javadoc(1) — openjdk-11-jdk-headless"
[7]: https://bugs.openjdk.org/browse/JDK-8008632?utm_source=chatgpt.com "Additional JavaDoc tags @apiNote, @implSpec and @implNote"
