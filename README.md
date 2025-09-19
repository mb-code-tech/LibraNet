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

## 📂 Project Structure
LibraNet/
│── src/lib/ # Main source files
│ ├── LibraryItem.java
│ ├── Book.java
│ ├── Audiobook.java
│ ├── EMagazine.java
│ ├── Playable.java
│ ├── FinePolicy.java
│ ├── StandardFinePolicy.java
│ ├── BorrowRecord.java
│ ├── FineRecord.java
│ ├── LibraryService.java
│ ├── Utils.java
│ └── Main.java
│
│── test/lib/ # Unit tests
│ ├── UtilsTest.java
│ └── LibraryServiceTest.java
│
│── lib/ # External JARs (JUnit, if not using Maven)
│── .gitignore
│── README.md


---

## ▶️ How to Run

### Option 1: With **Maven**
1. Compile:
   ```bash
   mvn clean compile
