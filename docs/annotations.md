# 🧩 Annotation Usage Guide

Inferno uses a single, powerful annotation: `@InjectInferno`, which you can apply to any `@Controller`, `@Service`, or `@Repository` method in your Spring Boot application.

This annotation allows you to simulate real-world system failures with minimal effort.

---

## 📌 `@InjectInferno` Overview

```java
@InjectInferno(
    mode = FailureMode.LATENCY,
    latencyMs = 1000,
    rate = 0.2
)
public String getData() {
    return "This will be delayed";
}
```

## 🔧 Annotation Attributes

| Attribute   | Type     | Required | Description                                                      |
|-------------|----------|----------|------------------------------------------------------------------|
| `mode`      | `FailureMode` | ✅ Yes   | The type of chaos to inject (e.g., `LATENCY`, `EXCEPTION`, etc.) |
| `latencyMs` | `long`   | ❌ No     | Delay duration in milliseconds (only for `LATENCY` mode)         |
| `rate`      | `double` | ❌ No     | Probability of failure injection. Default is `0.1` (always)      |


## 📋 Supported Modes
See all available values for FailureMode in [Failure Modes](failure-modes.md).

## 💡 Tips
- You can apply this annotation to any Spring-managed method (controller, service, or repo).
- Combine with unit tests or Postman to observe behavior.
- Ideal for testing resilience, fallback logic, and observability tools.

