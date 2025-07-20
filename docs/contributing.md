# 🤝 Contributing

Thank you for considering contributing to **Inferno**! Your contributions can help make this tool more powerful, flexible, and developer-friendly.

---

## 🛠️ How to Contribute

We welcome all contributions — code, documentation, bug reports, feature suggestions, and more.

### ✅ Steps to Contribute

1. **Fork the repository**

   Click the **Fork** button at the top right of the [Inferno GitHub repo](https://github.com/k99sharma/inferno).

2. **Clone your fork locally**

   ```bash
   git clone https://github.com/your-username/inferno.git
   cd inferno
   ```

3. **Create a new branch**

   Use a descriptive branch name based on your change:

   ```bash
   git checkout -b feature/failure-mode-throttle
   ```

4. **Make your changes**

    - Update code or documentation.
    - Add JUnit tests if applicable.
    - Run `mvn clean install` to ensure everything builds correctly.

5. **Commit your changes**

   ```bash
   git commit -am "Add throttle failure mode"
   ```

6. **Push to your fork**

   ```bash
   git push origin feature/failure-mode-throttle
   ```

7. **Open a Pull Request**

   Go to the original repository and click **"New Pull Request"**.

---

## 📋 Contribution Guidelines

- Follow the existing coding style and structure.
- Add tests for new features or bug fixes.
- Keep commits focused and meaningful.
- Write clear, helpful commit messages.
- If adding a new failure mode or annotation feature, document it in the proper `.md` files.

---

## 📦 Development Setup

```bash
# Run simulation app
cd inferno-simulation
mvn spring-boot:run
```

- Ensure JDK 17+ and Maven are installed.
- Make sure changes in `inferno-core` are reflected by running a local install:

```bash
cd inferno-core
mvn clean install
```

---

## 📮 Issues and Suggestions

- Found a bug? [Open an issue](https://github.com/k99sharma/inferno/issues)
- Have an idea? [Start a discussion](https://github.com/k99sharma/inferno/discussions)

---

## 🙌 Thanks for Helping!

Your time and skills make this project better. We appreciate your support! ⭐
