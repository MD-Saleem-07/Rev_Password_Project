🔐 Rev Password Manager

A Java Console-Based Password Management System that allows users to securely store, manage, and retrieve passwords using JDBC, Oracle DB, hashing, logging, and exception handling.

This project demonstrates real-world backend development concepts such as:
	•	DAO Architecture
	•	Exception Handling
	•	Secure Password Storage
	•	Logging
	•	Input Validation
	•	JDBC Connectivity
	•	Modular Code Design

⸻

📌 Project Objective

The goal of this project is to build a secure password manager that:
	•	Allows users to register and login
	•	Stores passwords safely
	•	Uses hashing for security
	•	Handles invalid inputs gracefully
	•	Uses logging for monitoring
	•	Implements database connectivity properly
	•	Follows clean architecture

⸻

🧱 Project Structure

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


⸻

⚙️ Technologies Used

Technology	Purpose
Java	Core programming
JDBC	Database connectivity
Oracle DB	Data storage
JUnit	Testing
SHA-256	Password hashing
Logger	Logging user actions
Exception Handling	Input validation


⸻

🔁 Application Flow

Step 1: Application Start

User sees:

1. Register
2. Login
3. Exit


⸻

Step 2: Registration Flow

User enters:
	•	User ID
	•	Name
	•	Email
	•	Password
	•	Security Question
	•	Security Answer

✔ Password is hashed
✔ Data is stored in DB
✔ Logs are created

⸻

Step 3: Login Flow

User enters:
	•	Email
	•	Password

✔ Password is hashed
✔ Compared with DB
✔ Login allowed only if matched

⸻

Step 4: After Login Menu

1. Add Password
2. View Passwords
3. View Password (Master Check)
4. Update Password
5. Update Profile
6. Forgot Password
7. Generate Password
8. Delete Password
9. Logout


⸻

🔐 Security Features

✅ Password Hashing

All passwords are encrypted using SHA-256 before storing.

✅ Master Password Verification

Before viewing passwords, user must re-enter password.

✅ Verification Code

Used during password reset.

✅ Logging

All operations are logged using java.util.logging.

⸻

🧠 Exception Handling

Custom Exception:

InvalidInputException

Used when:
	•	User enters characters instead of numbers
	•	Invalid menu options
	•	Wrong input format

Handled Exceptions:

Exception	Reason
InputMismatchException	Non-numeric input
SQLException	DB issues
NullPointerException	Connection issues
Custom Exception	Invalid user input


⸻

🗃️ Database Design

USERS TABLE

Column	Description
user_id	Primary key
name	User name
email	Login email
password	Hashed password
security_question	Recovery question
security_answer	Recovery answer

PASSWORDS TABLE

Column	Description
password_id	Primary key
user_id	Foreign key
account_name	Gmail, Facebook
username	Account username
password	Stored password


⸻

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
⸻

🧪 Testing

JUnit tests are written for:
	•	UserDao
	•	PasswordDao

Example:

@Test
public void testLogin() {
    assertNotNull(dao.login("user@gmail.com", "123"));
}

✔ No UI dependency
✔ Safe database tests
✔ Proper assertions

⸻

📜 Logging

Logs are stored in:

/logs/app.log

Example:

INFO: Login attempt for email
INFO: Login successful
SEVERE: Database connection failed


⸻

⚠️ Common Error Explained

ORA-12519 Error

❌ This is not a code error
✔ It is an Oracle DB issue

Reason:
	•	Oracle listener is down
	•	Too many connections
	•	DB service not started

Fix:

1. Open SQL Plus
2. Run: lsnrctl start
3. Restart Oracle service


⸻

✅ Features Implemented

✔ User Registration
✔ Login Authentication
✔ Password Encryption
✔ Password Management
✔ Master Password Validation
✔ Forgot Password
✔ Logging
✔ Exception Handling
✔ Database Connectivity
✔ Input Validation
✔ Modular Code

⸻

📌 How to Run
	1.	Import project into STS / Eclipse
	2.	Add Oracle JDBC driver
	3.	Configure DB in DBUtil.java
	4.	Run MainApp.java
	5.	Use console menu

⸻

🧠 Learning Outcomes

✔ JDBC connection handling
✔ Exception management
✔ Secure password storage
✔ Layered architecture
✔ Clean coding practices
✔ Logging & debugging
✔ Real-world backend logic

⸻

📌 Final Note

This project is:
	•	Fully functional
	•	Well-structured
	•	Secure
	•	Industry-aligned
	•	Interview ready

⸻
