# 🔥 Inferno – Chaos Injection for Spring Boot

> Bring controlled chaos into your Spring Boot application with just one annotation.

**Inferno** is a lightweight Java library that allows developers to simulate real-world failures like latency, exceptions, timeouts, CPU spikes, and memory issues directly in their Spring Boot applications — without external config or agents.

Perfect for resilience testing, retry/circuit breaker validation, fallback logic verification, and chaos experimentation during development.

---

## 🚀 Features

✅ Simple `@InjectInferno` annotation  
✅ Supports multiple failure modes (latency, exception, timeout, etc.)  
✅ No YAML/config files – 100% Java-based  
✅ Modular architecture with clean separation of core and simulation  
✅ Developer-friendly: toggle chaos per endpoint or service method  
✅ Compatible with any Spring Boot application

---

## 📦 Module Structure

| Module              | Description                                                                 |
|---------------------|-----------------------------------------------------------------------------|
| `inferno-core`      | Core library with annotation, AOP, and failure strategies                   |
| `inferno-simulation`| Spring Boot demo app showcasing how to use Inferno                          |

---

## ⚙️ Installation

Use JitPack to add Inferno to your project:

<details>
<summary><strong>Gradle</strong></summary>

```groovy
    repositories {
        maven { url 'https://jitpack.io' }
    }
    
    dependencies {
        implementation 'com.github.k99sharma.inferno:inferno-core:1.0.0'
    }
```
</details>

<details>
    <summary><strong>Maven</strong></summary>
    
```xml
        <repositories>
            <repository>
                <id>jitpack.io</id>
                <url>https://jitpack.io</url>
            </repository>
        </repositories>
        
        <dependencies>
            <dependency>
                <groupId>com.github.k99sharma.inferno</groupId>
                <artifactId>inferno-core</artifactId>
                <version>1.0.0</version>
            </dependency>
        </dependencies>
```
</details>


## ✨ Usage

Apply `@InjectInferno` on any method in your controller, service, or repository:

```java
    @RestController
    public class OrderController {
    
        @InjectInferno(mode = FailureMode.LATENCY, latencyMs = 1000)
        @GetMapping("/orders")
        public List<String> getOrders() {
            return List.of("Order1", "Order2");
        }
    }
```

Make sure to enable inferno using `@EnableInferno` on your main application class.

## 🔥 Available Failure Modes

Inferno supports multiple failure strategies to help developers test the resilience and robustness of their applications.

| Mode           | Description                                      |
|----------------|--------------------------------------------------|
| `OFF`          | Disables chaos injection for the annotated method |
| `LATENCY`      | Introduces an artificial delay (`latencyMs`) before method execution |
| `EXCEPTION`    | Throws a runtime exception to simulate failure   |
| `TIMEOUT`      | Simulates an indefinitely hanging thread (long sleep or loop) |
| `CPU_SPIKE`    | Runs a CPU-intensive loop to consume processor cycles |
| `OUT_OF_MEMORY`| Attempts to allocate excessive memory to simulate memory pressure |
| `AUTO`         | Randomly selects one of the active failure modes (except OFF) |

> You can control these modes using the `@InjectInferno` annotation directly on any method in your application.

## 🧪 Try It Locally with Simulation

You can try out Inferno in action using the included demo Spring Boot app located in the `inferno-simulation` module.

### 🛠️ Steps to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/k99sharma/inferno.git
   cd inferno

2. Navigate to the simulation app:
   ```bash
    cd inferno-simulation

3. Start the Spring Boot application:
   ```bash
    mvn spring-boot:run

4. Hit one of the endpoints. For ex:
   ```bash
   curl http://localhost:8080/inferno/test/auto


## 📄 License
This project is licensed under the MIT License.

## 👨‍💻 Author
Made with 💻 + ☕ by Kalash Sharma

## ⭐ Like This Project?
Give it a ⭐ on GitHub and share it with your Spring Boot friends.
It really helps spread the word and keeps the chaos going! 😄