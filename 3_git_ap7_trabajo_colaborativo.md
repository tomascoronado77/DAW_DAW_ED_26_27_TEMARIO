
# 7. Trabajo colaborativo con Git + GitHub

El trabajo en equipo con Git no consiste solo en “subir y bajar código”.
Para colaborar correctamente, es imprescindible entender primero **cómo funciona Git internamente** y cómo se relaciona con GitHub.

Por eso, antes de ver flujos de colaboración, repasamos los conceptos esenciales implicados.

---

## 7.1 Conceptos clave antes de colaborar

Antes de trabajar en equipo es imprescindible entender **cómo Git organiza internamente la información**.
Todo lo que hace Git ocurre dentro de la carpeta oculta `.git/`.

Esa carpeta *es literalmente el repositorio Git*.
El resto de carpetas de tu proyecto son simplemente los archivos de trabajo.

Aclaramos, a continuación, cómo funciona.

### 7.1.1 ¿Qué contiene realmente la carpeta `.git/`?

Esta carpeta almacena *todo lo necesario* para que Git funcione:

```
.git/
 ├── objects/         ← Guardan el contenido de los commits, blobs y árboles
 ├── refs/
 │    ├── heads/      ← Ramas locales (ej. main, feature-login…)
 │    └── remotes/
 │          └── origin/   ← Ramas remotas (origin/main, origin/feature-login…)
 ├── HEAD             ← Apunta a la rama en la que estás trabajando
 ├── config           ← Configuración del repositorio
 └── index            ← Área de preparación (staging area)
```

#### Lo puedes imaginar de la siguiente manera:

| Carpeta dentro de `.git`   | Qué representa                                  |
| -------------------------- | ----------------------------------------------- |
| `refs/heads/main`          | **La rama local `main` (tu copia)**             |
| `refs/remotes/origin/main` | **La versión de `main` que está en GitHub**     |
| `index`                    | Lo que has añadido con `git add`                |
| `HEAD`                     | Un puntero indicando en qué rama o commit estás |

### 7.1.2 ¿Por qué Git tiene *dos versiones* de cada rama?

Cuando sincronizas un repositorio con Github, Git crea en la carpeta local:

* **Una rama local**, por ejemplo:

```
main
```

* **Una referencia remota** que apunta a lo que hay en GitHub:

```
origin/main
```

**NO son lo mismo.**

#### Representación visual:

```
Local:
.git/refs/heads/main           → tu versión en tu PC

Remoto:
.git/refs/remotes/origin/main  → la última versión conocida de GitHub
```

Esto permite que Git compare:

* qué ha cambiado en GitHub (origin/main)
* qué has cambiado tú (main)

antes de fusionar o subir nada.

### 7.1.3 ¿Cuándo se actualizan main y origin/main?

#### ✔ **main (local)**

Se actualiza cuando tú haces:

```
git commit
git merge
git rebase
git pull (mezcla remota con local)
```

#### ✔ **origin/main (copia remota local)**

Se actualiza cuando haces:

```
git fetch
git pull   (que internamente hace fetch + merge)
```

Es muy importante:
**origin/main NO es GitHub.**
Es *tu copia local* de lo que había en GitHub la última vez que lo consultaste.

### 7.1.4 ¿Por qué existen ambas?

Porque Git está diseñado para trabajar **sin conexión**.

Puedes trabajar horas sin internet:

* hacer commits
* crear ramas
* fusionar
* reescribir historial

Y solamente cuando decidas:

```
git push
```

GitHub recibirá las versiones nuevas.

### 7.1.5 Un ejemplo perfecto para entenderlo

Supón esta situación:

#### En tu PC (local):

```
main: A — B — C
```

#### GitHub (origin/main):

```
A — B
```

Esto significa:

* Tú **tienes C**, pero **GitHub todavía no**.
* GitHub está “por detrás”.

Cuando hagas:

```
git push
```

GitHub quedará así:

```
A — B — C
```

Ahora **origin/main** (tu copia remota local) y **main** vuelven a coincidir.

### 7.1.6 ¿Y si el que va adelantado es el remoto?

GitHub:

```
A — B — C — D
```

Tu PC:

```
A — B — C
```

Al hacer:

```
git fetch
```

Git actualiza *solo* la referencia remota:

```
origin/main: A — B — C — D
main:        A — B — C
```

Tu rama local aún no tiene D.
Para actualizarla con:

```
git merge origin/main
```

o también haciendo

```
git pull
```

La diferencia es que git pull automatiza dos pasos (fetch y pull, es decir, volvería a intentar bajar la última versión de GitHub y después fusionarla con la rama local dónde estás situado):

```
git fetch   +   git merge origin/main
```

Después de actualizar la rama local, ahora sí contiene el mismo historial de versiones que GitHub:

```
main: A — B — C — D
```

### 7.1.7 Resumen visual del funcionamiento

```
┌─────────────────────────────────────────────────────────┐
│                    Carpeta .git                         │
├─────────────────────────────────────────────────────────┤
│ refs/heads/main              → rama local               │
│ refs/remotes/origin/main     → copia de GitHub          │
│ index                        → staging area             │
│ objects/                     → commits, blobs, árboles   │
└─────────────────────────────────────────────────────────┘

                   ↓ Sincronización ↓

Local main  ←→  origin/main  ←→  GitHub/main
```

### 7.1.8 Resumen fetch vs pull

| Comando                        | Qué hace                                                           | Cuándo se usa                                            |
| ------------------------------ | ------------------------------------------------------------------ | -------------------------------------------------------- |
| **`git fetch`**                | Trae información nueva del remoto **sin mezclarla con tu trabajo** | Cuando quieres revisar antes de mezclar                  |
| **`git pull` = fetch + merge** | Descarga **y fusiona directamente** los cambios del remoto         | Cuando quieres actualizar tu rama y continuar trabajando |

Con el comando `git fetch origin` actualizas todas las ramas remotas en tu carpeta local, es decir:

```
origin/main
origin/rama-trabajo1
origin/rama-trabajo2
...
```

### 7.1.9 Idea clave antes de colaborar

> En Git siempre trabajas con **dos realidades paralelas**: tu repositorio local y la copia remota.
> Entender *main vs origin/main* es esencial para trabajar en equipo sin pisarse ni perder código.

---

## 7.2 Formas de trabajar en equipo

Existen dos flujos principales:

### 7.2.1 Flujo A: Colaboración con *Merge* directo (sin Pull Request)

*(Recomendado si el coordinador trabaja en local y no se usan PR en GitHub)*

#### ROLES

* **Coordinador/a**
  Responsable de fusionar ramas en `main` y mantener la versión estable del proyecto.

* **Programadores/as**
  Cada uno trabaja en su propia rama y se encarga de mantenerla actualizada respecto a `main`.

#### FLUJO DEL PROGRAMADOR

Cada programador sigue este proceso:

**1. Clonar el repositorio**

```
git clone <url-del-repo>
cd <carpeta-proyecto>
```

**2. Crear su rama de trabajo**

```
git switch -c nombre-rama
```

**3. Trabajar normalmente**

* Edita código
* Hace commits:

```
git add .
git commit -m "Mensaje descriptivo"
```

**4. Subir su rama al remoto**

```
git push -u origin nombre-rama
```

Repetirá este proceso con varias versiones hasta acabar la funcionalidad objetivo de su rama *(el resto de subidas será con `git push` simplemente)*

**5. Cuando la funcionalidad está terminada → NO se fusiona directamente**

Antes de pedir al coordinador que fusione su rama, **DEBE ACTUALIZAR SU RAMA** con los últimos cambios de `main`.

**Paso 5.1: Actualizar `main` local**

```
git switch main
git pull
```

**Paso 5.2: Integrar `main` en su rama**

```
git switch nombre-rama
git merge main
```

* Si hay conflictos → los resuelve.
* Confirma si es necesario:

```
git add .
git commit
```

**Paso 5.3: Subir su rama actualizada**

```
git push
```

**6. Ahora SÍ puede avisar al coordinador**

> “Mi rama `nombre-rama` está actualizada contra `main` y lista para fusionarse”.

#### FLUJO DEL COORDINADOR

El coordinador SOLO fusiona ramas que ya han sido actualizadas por sus autores.

**1. Descargar la información del remoto**

```
git fetch
```

**2. Comprobar estado del repositorio**

```
git log --oneline --graph --all
```

**3. Fusionar la rama pedida**

```
git switch main
git merge nombre-rama
```

* Si no hay conflictos → merge limpio.
* Si aparecen conflictos → el coordinador los resuelve porque ahora se trata de integrar el resultado final (aunque deberían ser pocos, o más bien ninguno, si el programador actualizó su rama correctamente).

**4. Subir el `main` actualizado**

```
git push
```

**5. Eliminar ramas ya fusionadas (opcional)**

```
git push origin --delete nombre-rama
```

#### ⚠️ **Punto crítico (muy importante)**

❌ El coordinador **NO debe hacer `git pull` directamente para traer ramas de los programadores.**

Porque `git pull`:

* trae cambios **y además fusiona automáticamente**, aunque tú no quieras.

Por eso **siempre debe usar** *(desde la rama principal `main`)*:

```
git fetch            # descarga sin tocar nada
git merge rama       # fusión controlada
```

Esta combinación evita errores y permite ver primero qué ha cambiado.

#### **Resumen visual**

```text
PROGRAMADOR                                    COORDINADOR
-------------------                            -----------------------
git switch main                                git fetch
git pull                                        git log --graph --all
git switch rama                                 git switch main
git merge main ← actualizar rama                git merge rama-programador
resolver conflictos                             git push
git push                                        (opcional) borrar rama
AVISA: “rama lista”
```


### 7.2.2 Flujo B: Trabajo colaborativo con Pull Requests (PR)

*(Recomendado cuando se usa GitHub activamente, especialmente en grupos medianos o grandes)*

El uso de Pull Requests permite que el coordinador controle qué entra en la rama principal y cuándo.
Además, GitHub facilita la revisión del código sin necesidad de que el coordinador descargue las ramas de los programadores en local.

#### Ventajas del uso de Pull Requests

* ✔ Permite revisar código antes de fusionarlo.
* ✔ Evita que los programadores modifiquen `main` directamente.
* ✔ Facilita comentarios, revisiones y sugerencias entre compañeros.
* ✔ Permite integrar **pruebas automáticas** (pipelines).
* ✔ Mantiene una historia clara y controlada.
* ✔ **No requiere que el coordinador tenga las ramas de los programadores en local**.

#### FLUJO DEL PROGRAMADOR

Cada programador trabaja **en su propia rama**, por ejemplo:

```
feature-funcion-factorial-recursivo
```

**1. Actualizar su entorno antes de abrir un PR**

> **Motivación:** si el coordinador ha fusionado otras ramas, tu trabajo podría estar basado en un `main` desactualizado.
> Para evitar conflictos sorpresa y demostrar control profesional del flujo, **actualiza tu rama antes de enviar el PR**.

```bash
git switch main
git pull                         # Actualiza main con la última versión remota
git switch feature-funcion-factorial-recursivo
git merge main                   # Trae los últimos cambios de main a tu rama
```

* `git merge main` → seguro y recomendado.
* `git rebase main` → opcional (solo si nadie más trabaja en tu rama).

**2. Trabajar en la rama propia**

```bash
git add .
git commit -m "Implementada funcionalidad X"
git push -u origin feature-funcion-factorial-recursivo   # Solo la primera vez
```

**3. Crear el Pull Request**

En GitHub:

1. **Pull Requests**
2. **New Pull Request**
3. Base branch → `main`
4. Compare branch → tu rama
5. Crear PR para revisión.

#### FLUJO DEL COORDINADOR

El coordinador trabaja **principalmente desde GitHub**, revisando el PR.

**1. Revisar PRs en GitHub**

* Ver los commits.
* Revisar código.
* Pedir cambios si algo no encaja.

**2. Fusionar el PR**

Cuando esté todo correcto → pulsar:

```
Merge Pull Request → Confirm merge
```

**3. Actualizar su copia local**

```bash
git switch main
git pull
```

> El coordinador **no necesita tener las ramas de los programadores en local**.

#### 💬 Nota importante sobre rebase en PRs

El programador puede usar `rebase` **solo en su propia rama**, y únicamente cuando:

* nadie más trabaja en ella,
* quiere actualizarla respecto al main **sin generar un commit de merge**.

Ejemplo:

```bash
git switch feature-funcion-factorial-recursivo
git fetch
git rebase origin/main
git push --force-with-lease        # Solo si ya existía un push anterior
```

⚠️ No se debe hacer rebase sobre `main`.
⚠️ No se debe reescribir historia de ramas compartidas.

#### Resumen del flujo con PR

```text
PROGRAMADOR:
  - Actualiza main
  - Actualiza su rama respecto a main
  - Trabaja → commit → push
  - Crea PR

COORDINADOR:
  - Revisa PR
  - Fusiona en main
  - Actualiza su repositorio local

CONFICTOS:
  - Siempre se resuelven en la rama del programador ANTES de fusionar.
```

---

### 7.3 Conclusión

> **En Git, colaborar no es solo “subir y bajar código”, sino mantener un historial coherente entre varias personas que trabajan en paralelo.**

Para ello es esencial:

* conocer `fetch`, `pull`, `merge` y `rebase`
* saber cuándo y cómo fusionar
* entender por qué una rama debe estar basada en la versión más reciente
* organizar roles y comunicación en el equipo

Cuando estos conceptos se entienden, el trabajo en grupo fluye sin bloqueos ni pérdidas de código.

---
