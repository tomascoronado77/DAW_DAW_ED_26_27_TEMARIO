
## ¿El comando `git rebase` siempre se hace sobre el último commit de `main`?

✅ **No.**

Aunque normalmente usamos:

```bash
git rebase main
```

…esto solo significa:

> "Reaplica mis commits encima del commit donde está ahora `main` (su HEAD)."

Pero Git también permite elegir **exactamente sobre qué commit quieres hacer el rebase**.

---

## ✅ Rebase sobre un commit concreto

Puedes indicar un commit específico:

```bash
git rebase <id_commit>
```

Ejemplo:

```bash
git rebase 3f2a7c1
```

👉 Esto le dice a Git:

> "Coge mis commits y ponlos justo después del commit `3f2a7c1`."

---

## ✅ ¿Para qué sirve?

Esto es útil cuando:

* quieres ignorar commits posteriores de `main`
* quieres reubicar tu trabajo sobre una versión anterior del proyecto
* estás reorganizando la historia

---

## ✅ Comparación rápida

| Comando                  | ¿Qué hace?                                           |
| ------------------------ | ---------------------------------------------------- |
| `git rebase main`        | Reaplica tus commits en el **HEAD de main**          |
| `git rebase <id_commit>` | Reaplica tus commits **después del commit indicado** |

---

## 🧠 Idea clave

> `git rebase` NO tiene por qué usar siempre la última versión de `main` *(aunque es su uso más común)*.
> Puedes elegir sobre qué commit se construirá tu trabajo.

---
