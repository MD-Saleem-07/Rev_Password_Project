# 🔐 Rev Password Manager

Rev Password Manager is a **Java-based console application** designed to securely store, manage, and retrieve user credentials.  
It demonstrates real-world backend development concepts such as **JDBC**, **database security**, **logging**, **exception handling**, and **layered architecture**.

---

## 🎯 Project Objective

The goal of this project is to:

- Allow users to securely register and log in
- Encrypt passwords before storing them
- Store and manage multiple account passwords
- Handle invalid input safely
- Log system activity for debugging
- Follow clean coding and layered architecture principles

---

## 🏗️ Project Structure

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

## ⚙️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Core programming |
| JDBC | Database connectivity |
| Oracle DB | Data storage |
| JUnit | Unit testing |
| SHA-256 | Password encryption |
| Logger | Activity logging |
| Exception Handling | Input validation & safety |

---

## 🔁 Application Flow

### Step 1: Application Starts
User is shown:

	1.	Register
	2.	Login
	3.	Exit

---

### Step 2: Registration
User enters:
- User ID
- Name
- Email
- Password
- Security Question
- Security Answer

✔ Password is encrypted  
✔ Data is stored in database  
✔ Log is generated  

---

### Step 3: Login
User enters:
- Email
- Password

✔ Password is hashed  
✔ Compared with DB  
✔ On success → Dashboard opens  

---

### Step 4: After Login Menu

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

- Password hashing using SHA-256
- Master password verification
- Secure password recovery
- Input validation
- Logging for auditing

---

## 🧠 Exception Handling

Handled using:
- `InvalidInputException`
- `InputMismatchException`
- `SQLException`
- `NullPointerException`

✔ Prevents application crash  
✔ Ensures smooth execution  

---

## 🗃️ Database Design

### USERS Table

| Column | Description |
|------|-------------|
| user_id | Primary Key |
| name | User name |
| email | Login email |
| password | Encrypted password |
| security_question | Recovery question |
| security_answer | Recovery answer |

---

### PASSWORDS Table

| Column | Description |
|------|-------------|
| password_id | Primary Key |
| user_id | Foreign Key |
| account_name | Account name |
| username | Account username |
| password | Stored password |

---

## 📊 ER Diagram (Conceptual)

+——————+
|      USERS       |
+——————+
| user_id (PK)     |
| name             |
| email            |
| password         |
| security_question|
| security_answer  |
+——————+
|
| 1
|
| *
+——————+
|   PASSWORDS      |
+——————+
| password_id (PK) |
| user_id (FK)     |
| account_name     |
| username         |
| password         |
+——————+

---

## 🧪 Testing

JUnit is used to test:

- Login validation
- Password operations
- Database connectivity

Example:

```java
@Test
public void testLogin() {
    assertNotNull(dao.login("user@gmail.com", "123"));
}

✔ DAO-level testing
✔ No UI dependency

⸻

📜 Logging

All logs are written to:

/logs/app.log

Examples:
	•	Login attempts
	•	Errors
	•	Successful operations

⸻

⚠️ Common Error Explained

ORA-12519

This error means:

Oracle database has reached maximum connections.

✔ Not a code issue
✔ Restart DB or increase sessions

⸻

✅ Features Implemented

✔ Secure registration
✔ Login authentication
✔ Password encryption
✔ Password management
✔ Exception handling
✔ Logging
✔ Database integration
✔ Modular architecture

⸻

▶️ How to Run
	1.	Import project into Eclipse / STS
	2.	Add Oracle JDBC driver
	3.	Configure DB in DBUtil.java
	4.	Run MainApp.java
	5.	Use console menu

⸻

🎯 Learning Outcome
	•	JDBC & Database handling
	•	Secure password storage
	•	Exception-driven design
	•	Layered architecture
	•	Logging and debugging
	•	Real-world backend project structure

⸻

🏁 Final Note

This project demonstrates real backend development practices and is suitable for:

✔ Academic submission
✔ Java backend interviews
✔ Portfolio project

⸻

