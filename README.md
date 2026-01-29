# 🔐 REV PASSWORD MANAGER  
*A Secure Console-Based Password Management System*

---

## 📌 Project Overview

**Rev Password Manager** is a Java-based console application designed to securely store and manage user passwords.  
It follows **real-world backend development practices**, including:

- Layered architecture  
- JDBC-based database interaction  
- Secure password hashing  
- Logging and exception handling  
- Modular code structure  
- Input validation  
- Unit testing  

This project simulates how real password managers work internally and focuses on **security, reliability, and clean design**.

---

## 🎯 Project Objectives

✔ Allow users to register and log in securely  
✔ Store account passwords safely  
✔ Prevent unauthorized access  
✔ Provide password recovery mechanism  
✔ Handle invalid inputs gracefully  
✔ Maintain logs for auditing  
✔ Follow clean coding and design standards  

---

## 🧩 Project Structure

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
└── logging configuration

---

## 🧠 Application Flow

### 🔹 Step 1: Application Starts
User sees:

	1.	Register
	2.	Login
	3.	Exit

---

### 🔹 Step 2: Registration Flow

User enters:
- User ID
- Name
- Email
- Password
- Security Question
- Security Answer

✔ Password is hashed  
✔ Data stored in database  
✔ Log entry created  

---

### 🔹 Step 3: Login Flow

User enters:
- Email
- Password

✔ Password is hashed  
✔ Compared with DB  
✔ Login allowed only if matched  

---

### 🔹 Step 4: Password Menu

After login:

	1.	Add Password
	2.	View Passwords
	3.	View Password (Master Check)
	4.	Update Password
	5.	Update Profile
	6.	Forgot Password
	7.	Generate Password
	8.	Delete Password
	9.	Logout

---

## 🔐 Security Features

### ✔ Password Hashing
- Passwords are never stored in plain text
- Uses secure hashing

### ✔ Master Password Verification
- Required to view stored passwords
- Prevents unauthorized access

### ✔ Verification Code
- Used for password recovery
- Generated dynamically

### ✔ Logging
- Tracks login, registration, errors
- Stored in log file

### ✔ Exception Handling
- Prevents crashes
- Handles invalid input
- Graceful error messages

---

## 🗄️ Database Design

### USERS TABLE

| Column | Description |
|------|-------------|
| user_id | Primary Key |
| name | User name |
| email | Login email |
| password | Hashed password |
| security_question | Recovery question |
| security_answer | Recovery answer |

---

### PASSWORDS TABLE

| Column | Description |
|------|-------------|
| password_id | Primary Key |
| user_id | Foreign Key |
| account_name | Account name |
| username | Account username |
| password | Stored password |

---

## 🔷 ER Diagram (Conceptual)

+———––+        +––––––––+
|   USERS     |        |   PASSWORDS    |
+———––+        +––––––––+
| user_id (PK)| <––  | password_id    |
| name        |        | user_id (FK)   |
| email       |        | account_name   |
| password    |        | username       |
| sec_question|        | password       |
| sec_answer  |        +––––––––+
+———––+

---

## 🧩 Class Responsibilities

### 🔹 MainApp.java
- Entry point
- Handles user input
- Menu navigation
- Exception handling
- Calls DAO methods

---

### 🔹 UserDao.java
Handles:
- User registration
- Login authentication
- Profile update
- Forgot password
- Master password verification

---

### 🔹 PasswordDao.java
Handles:
- Add password
- View passwords
- Update password
- Delete password
- Search passwords

---

### 🔹 DBUtil.java
- Manages database connection
- Returns connection object
- Handles connection errors

---

### 🔹 HashUtil.java
- Encrypts passwords
- Prevents plain-text storage

---

### 🔹 PasswordUtil.java
- Generates random strong passwords
- Custom length support

---

### 🔹 VerificationUtil.java
- Generates verification code
- Used during password reset

---

### 🔹 InvalidInputException.java
- Custom exception
- Handles:
  - Invalid menu input
  - Wrong data type
  - Unexpected user input

---

## 🧪 Testing

### ✔ JUnit Testing
- UserDaoTest
- PasswordDaoTest

### ✔ Tested Features
- Login
- Password insert
- Password update
- Password delete
- Validation

---

## 🧾 Logging

Implemented using:

java.util.logging.Logger

Logs include:
- Login attempts
- Registration
- Errors
- Database failures

Stored in:

logs/app.log

---

## ⚠️ Common Issues Handled

| Issue | Solution |
|------|----------|
| Invalid input | Custom exception |
| Wrong menu choice | Validation |
| DB connection failure | Handled with logs |
| Wrong password | Authentication check |
| Crash due to input | try-catch blocks |

---

## 🧠 Real-Time Issues Solved

✔ InputMismatchException handled  
✔ Database connection errors logged  
✔ Invalid menu inputs handled  
✔ Login failure handled safely  
✔ Application never crashes  

---

## 🧩 Architecture Diagram (Logical)

User
↓
MainApp
↓
DAO Layer
↓
Database

Utilities support:
- Logging
- Password hashing
- Validation

---

## 🏁 Final Summary

✔ Secure password storage  
✔ Clean architecture  
✔ Logging implemented  
✔ Exception handling done  
✔ Input validation added  
✔ JUnit testing done  
✔ Database integrated  
✔ Ready for evaluation  

---

## ✅ Project Status

✔ Completed  
✔ Tested  
✔ Error-handled  
✔ Production-ready  
✔ GitHub ready  

---

📌 **This project demonstrates real-world Java backend development skills with security, database handling, and clean coding practices.**

---
