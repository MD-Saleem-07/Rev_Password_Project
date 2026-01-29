🔐 Rev Password Manager

Rev Password Manager is a Java-based console application designed to securely store, manage, and retrieve user credentials.
The application focuses on backend development best practices, including secure password handling, database integration, logging, exception handling, and modular architecture.

⸻

📌 Project Objective

The goal of this project is to build a secure password management system that:
	•	Allows users to register and authenticate securely
	•	Encrypts passwords before storing them
	•	Validates user inputs safely
	•	Handles runtime exceptions gracefully
	•	Maintains logs for debugging and auditing
	•	Follows clean and modular coding standards

⸻

🧱 Project Structure

The application follows a layered architecture, ensuring clean separation of concerns.

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
    └── Logging Configuration


⸻

⚙️ Technologies Used

Technology	Purpose
Java	Core programming
JDBC	Database connectivity
Oracle Database	Persistent storage
JUnit	Unit testing
SHA-256	Password hashing
Logger	Logging system
Exception Handling	Input validation & error handling


⸻

🔁 Application Flow

▶ Step 1: Application Start

User is shown:

1. Register
2. Login
3. Exit


⸻

▶ Step 2: Registration

User provides:
	•	User ID
	•	Name
	•	Email
	•	Password
	•	Security Question
	•	Security Answer

✔ Password is encrypted
✔ User details are stored securely
✔ Log entry is created

⸻

▶ Step 3: Login

User enters:
	•	Email
	•	Password

✔ Password is hashed
✔ Compared with stored value
✔ Login allowed only on match

⸻

▶ Step 4: After Login Options

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

Feature	Description
Password Hashing	SHA-256 encryption
Master Password	Required to view saved passwords
Verification Code	Used for password recovery
Logging	Tracks all operations
Input Validation	Prevents invalid inputs


⸻

🧠 Exception Handling

Custom Exception

InvalidInputException

Handled Exceptions

Exception	Purpose
InputMismatchException	Invalid input type
SQLException	Database errors
NullPointerException	DB connection failures
Custom Exception	Menu validation


⸻

🗃️ Database Design

USERS Table

Column	Description
user_id	Primary Key
name	User name
email	Login email
password	Hashed password
security_question	Recovery question
security_answer	Recovery answer


⸻

PASSWORDS Table

Column	Description
password_id	Primary Key
user_id	Foreign Key
account_name	Account name
username	Account username
password	Stored password


⸻

🗂️ ER Diagram (Conceptual)

+------------------+        +-------------------+
|      USERS       |        |    PASSWORDS      |
+------------------+        +-------------------+
| user_id (PK)     |<------>| password_id (PK)  |
| name             |        | user_id (FK)      |
| email            |        | account_name      |
| password         |        | username          |
| sec_question     |        | password          |
| sec_answer       |        +-------------------+
+------------------+

Relationship:
USERS 1 -------- * PASSWORDS


⸻

🧪 Testing

JUnit test cases are implemented for:
	•	User authentication
	•	Password operations
	•	Database interaction

Sample Test Case

@Test
public void testLogin() {
    assertNotNull(dao.login("user@gmail.com", "123"));
}

✔ DAO level testing
✔ No UI dependency
✔ Safe execution

⸻

📜 Logging

All logs are written to:

/logs/app.log

Example Logs:

INFO: Login attempt for user
INFO: Password updated successfully
SEVERE: Database connection failed


⸻

⚠️ Common Error Explanation

ORA-12519 Error

This is not a coding error.

✔ Cause:
	•	Oracle service not running
	•	Too many open DB connections
	•	Listener issue

✔ Solution:

1. Open SQL Plus
2. Run: lsnrctl start
3. Restart Oracle services


⸻

✅ Features Implemented

✔ User Registration
✔ Secure Login
✔ Password Encryption
✔ Password Management
✔ Master Password Validation
✔ Password Recovery
✔ Logging
✔ Exception Handling
✔ JDBC Connectivity
✔ Modular Architecture

⸻

📌 How to Run
	1.	Import project into Eclipse / STS
	2.	Add Oracle JDBC driver
	3.	Configure DB in DBUtil.java
	4.	Run MainApp.java
	5.	Use console menu

⸻

🎯 Learning Outcomes
	•	JDBC and SQL integration
	•	Secure password handling
	•	Exception-driven programming
	•	Logging & debugging
	•	DAO architecture
	•	Real-world backend development

⸻

✅ Final Summary

Rev Password Manager is a fully functional backend application that demonstrates:

✔ Clean architecture
✔ Secure coding practices
✔ Real-world database handling
✔ Strong exception management
✔ Professional project structure
