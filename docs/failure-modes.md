# 💥 Failure Modes

Inferno provides multiple failure modes that you can inject into your Spring Boot application to simulate different types of system stress and failure scenarios.

Use the `mode` attribute in the `@InjectInferno` annotation to choose the desired failure type.

---

## 🔧 Available Failure Modes

| Mode            | Description                                                  |
|------------------|--------------------------------------------------------------|
| `OFF`            | Disables chaos injection for this method.                   |
| `LATENCY`        | Adds artificial delay (`latencyMs` required).               |
| `EXCEPTION`      | Throws a runtime exception.                                 |
| `TIMEOUT`        | Simulates an indefinitely hanging thread.                   |
| `CPU_SPIKE`      | Causes high CPU usage for a short time.                     |
| `OUT_OF_MEMORY`  | Simulates memory pressure by allocating large objects.      |
| `AUTO`           | Randomly selects one of the above failure modes at runtime. |

---

## 🎯 Example

```java
    @InjectInferno(
        mode = FailureMode.LATENCY,
        rate = 0.3,
        latencyMs = 1500
    )
    public String fetchData() {
        return "Delayed result";
    }
```