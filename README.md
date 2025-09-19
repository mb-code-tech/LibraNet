# 📚 LibraNet – Library Management System

LibraNet is a simple yet extensible library management system that handles **Books**, **Audiobooks**, and **E-Magazines**.  
It demonstrates clean OOP design, reusability, and error handling in Java.

---

## 🚀 Features
- Borrow, return, and check availability of items.
- Fine calculation based on borrowing duration.
- Specialized behaviors:
  - **Book** → page count
  - **Audiobook** → playable (implements `Playable` interface)
  - **E-Magazine** → issue archiving
- Audit logging for borrow/return operations.
- Unit testing support with JUnit.

---

## 🛠️ Technologies
- **Java 17+**
- **JUnit 5** for testing
- (Optional) **Maven** for build and dependency management
- IDE: VS Code (or any Java IDE)

---


## ▶️ How to Run

---
### Option 1: With **Maven**
1. Compile:
   ```bash
   mvn clean compile

2.Run main program:
   ```bash
   mvn exec:java -Dexec.mainClass="lib.Main"


Run tests:
    ```bash
    mvn test

### Option 1: Without **Maven**
1. Compile:
   ```bash
javac -d bin -cp "lib/*" src/lib/*.java test/lib/*.java

2. Run main program:
 ```bash
java -cp "bin;lib/*" lib.Main

3.Run tests (JUnit):
```bash
java -cp "bin;lib/*" org.junit.runner.JUnitCore lib.UtilsTest

