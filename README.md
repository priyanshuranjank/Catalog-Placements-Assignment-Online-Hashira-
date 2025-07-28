
# 🔐 Shamir Secret Sharing in Java

This project implements **Shamir's Secret Sharing** algorithm in Java and uses **Gson** for JSON serialization and deserialization.

## ✅ Requirements
- **Java 8** or higher
- [**Gson Library (v2.10.1)**](https://github.com/google/gson)

## 📂 Project Structure
```
hashira/
├── ShamirSecretSharing.java
├── Point.java
└── lib/
    └── gson-2.10.1.jar
```

## ⚙️ Compilation
Run the following command to compile the Java files:

```bash
javac -cp lib/gson-2.10.1.jar ShamirSecretSharing.java Point.java
```

## ▶️ Execution
Run the program with the correct classpath:

### Windows
```bash
java -cp .;lib/gson-2.10.1.jar ShamirSecretSharing
```

### Linux / macOS
```bash
java -cp .:lib/gson-2.10.1.jar ShamirSecretSharing
```

## 📝 Notes
- Ensure that `ShamirSecretSharing.java` and `Point.java` are in the same directory.
- The `lib/` directory must contain the `gson-2.10.1.jar` file.
- If your Java files use a package, you must run with the full package name:
  ```bash
  java -cp .;lib/gson-2.10.1.jar packageName.ShamirSecretSharing
  ```

## ✅ Example Usage
If the program takes input from JSON or outputs JSON using Gson, an example could look like:

