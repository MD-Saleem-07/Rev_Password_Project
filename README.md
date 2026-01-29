```md
# 🔐 Rev Password Manager

Rev Password Manager is a **Java-based console application** designed to securely store, manage, and retrieve user credentials.  
The application focuses on **backend development**, emphasizing secure data handling, database interaction, logging, and structured exception management using **Java, JDBC, and Oracle Database**.

---

## 📌 Project Objective

The objective of this project is to develop a secure password management system that:

- Allows users to register and authenticate securely  
- Stores sensitive data in an encrypted format  
- Validates user input and handles runtime errors gracefully  
- Logs application activity for monitoring and debugging  
- Follows a clean, modular, and maintainable architecture  

---

## 🧱 Project Structure

The application is organized using a **layered architecture**, ensuring clear separation of concerns.

```

com.rev
│
├── main
│   └── MainApp.java
│
├── dao
│   ├── UserDao.java
│   └── PasswordDao.java
│
├── model
│   ├── User.java
│   └── PasswordEntry.java
│
├── util
│   ├── DBUtil.java
│   ├── HashUtil.java
│   ├── PasswordUtil.java
│   └── VerificationUtil.java
│
├── exception
│   └── InvalidInputException.java
│
├── test
│   ├── UserDaoTest.java
│   └── PasswordDaoTest.java
│
└── resources
└── Logging configuration

````

---

## ⚙️ Technologies Used

| Technology | Purpose |
|----------|---------|
| Java | Core application logic |
| JDBC | Database connectivity |
| Oracle Database | Persistent data storage |
| JUnit | Unit testing |
| SHA-256 | Password hashing |
| Logger | Application logging |
| Exception Handling | Input validation and error control |

---

## 🔁 Application Flow

### Application Start
The user is presented with the following options:
1. Register  
2. Login  
3. Exit  

### Registration
The user provides:
- Name  
- Email  
- Password  
- Security Question  
- Security Answer  

The password is encrypted using SHA-256 and stored securely in the database.

### Login
The user enters:
- Email  
- Password  

The hashed password is compared with the stored value to authenticate the user.

### Post-Login Operations
After successful login, the user can:
- Add new passwords  
- View stored passwords (with master verification)  
- Update or delete passwords  
- Recover forgotten passwords  
- Generate strong passwords  
- Update profile details  

---

## 🔐 Security Features

- **Password Hashing:** All passwords are stored in encrypted format  
- **Master Password Verification:** Required before accessing sensitive data  
- **Verification Code:** Used during password recovery  
- **Logging:** All critical actions are logged  

---

## 🧠 Exception Handling

A custom exception, **InvalidInputException**, is used to handle invalid menu choices and incorrect input formats.

Handled exceptions include:
- InputMismatchException  
- SQLException  
- NullPointerException  
- Custom validation exceptions  

---

## 🗃️ Database Design

### USERS Table

| Column | Description |
|------|-------------|
| user_id | Primary key |
| name | User name |
| email | Login email |
| password | Hashed password |
| security_question | Recovery question |
| security_answer | Recovery answer |

### PASSWORDS Table

| Column | Description |
|------|-------------|
| password_id | Primary key |
| user_id | Foreign key |
| account_name | Account name |
| username | Account username |
| password | Stored password |

---

## 🗂️ Entity Relationship (ER) Diagram

```mermaid
erDiagram
    USERS {
        int user_id PK
        string name
        string email
        string password
        string security_question
        string security_answer
    }

    PASSWORDS {
        int password_id PK
        int user_id FK
        string account_name
        string username
        string password
    }

    USERS ||--o{ PASSWORDS : stores
````

---

## 🧪 Testing

JUnit tests are implemented for:

* User authentication
* Password management operations

Example:

```java
@Test
public void testLogin() {
    assertNotNull(dao.login("user@gmail.com", "123"));
}
```

---

## 📜 Logging

All application events are recorded in:

```
/logs/app.log
```

Example log entries include login attempts, successful operations, and database errors.

---

## ⚠️ Common Error Explanation

**ORA-12519** is a database-related issue caused by listener or connection limitations and is not related to application logic.

---

## ✅ Features Implemented

* User Registration
* Secure Login
* Password Encryption
* Password Management
* Master Password Validation
* Password Recovery
* Logging
* Exception Handling
* Database Connectivity
* Modular Code Design

---

## 📌 How to Run

1. Import the project into STS or Eclipse
2. Add the Oracle JDBC driver
3. Configure database details in `DBUtil.java`
4. Run `MainApp.java`
5. Use the console menu to interact

---

## 🧠 Learning Outcomes

* JDBC connection management
* Secure password storage
* DAO-based architecture
* Exception-driven validation
* Logging and debugging
* Real-world backend application design

---

## 📌 Final Note

Rev Password Manager is a **secure, structured, and backend-focused Java application** that demonstrates practical implementation of industry-standard development practices.

```
::contentReference[oaicite:0]{index=0}
```
