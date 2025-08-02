# 🚀 Getting Started

Welcome to **Inferno** – a chaos injection library built for Spring Boot developers. This guide will help you integrate Inferno into your project and start simulating failure modes right away.

---

## 🛠️ Installation

Inferno is published via **JitPack**, so you can easily add it to your project using Maven or Gradle.

---

### 📦 Maven

Add the **JitPack** repository and Inferno dependency:

```xml
    <repositories>
      <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
      </repository>
    </repositories>
    
    <dependencies>
      <dependency>
        <groupId>com.github.k99sharma</groupId>
        <artifactId>inferno-core</artifactId>
        <version>v1.0.0</version> <!-- Replace with latest tag -->
      </dependency>
    </dependencies>
```

## 📦 Gradle

```groovy
    repositories {
        maven { url 'https://jitpack.io' }
    }
    
    dependencies {
        implementation 'com.github.k99sharma:inferno-core:v1.0.0' // Replace with latest tag
    }
```

## 🔧 Configuration

Enable Inferno via your application.yml:

```yml
    inferno:
      enabled: true
```

Enable using annotation (recommended):

```java
    @SpringBootApplication
    @EnableInferno
    public class app() {}
```

You can also configure inferno to run with specific active profiles:

```java
    @SpringBootApplication
    @EnableInferno(profiles = {'dev', 'test'})
    public class app() {}
```

## ✅ Using @InjectInferno

Annotate any Spring component method:
```java
    @InjectInferno(
        mode = FailureMode.LATENCY,
        latencyMs = 1000,
        rate = 0.3
    )
    public String getData() {
        return "Success";
    }
```

### Parameters:

| Attribute   | Description                                         |
|-------------|-----------------------------------------------------|
| `mode`      | The type of chaos to inject (e.g., `LATENCY`)       |
| `latencyMs` | Delay duration in milliseconds (used only with `LATENCY`) |
| `rate`      | Probability of injection.   |

## 🧪 Run the Simulation App

A demo app is available under the inferno-simulation module.
To try it:

```bash
    cd inferno/inferno-simulation 
    ./mvnw spring-boot:run
```

Then hit one of the routes:

```bash
    GET http://localhost:8080/api/latency
    GET http://localhost:8080/api/exception
```