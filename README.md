Banking Management System

Technologies: Java, JSP, Servlets, MySQL, JDBC, HTML, CSS

📄 Project Description

This is a Banking Management System developed using Java web technologies.
The application allows users to log in, view account balance, and transfer funds securely between accounts.
The project follows MVC architecture and uses DAO pattern for database operations.
The main focus of this project is to understand backend development, database connectivity, and clean project structure.

🚀 Features
User login authentication
View account balance
Fund transfer between users
Secure database access using PreparedStatement
Transaction handling for data consistency

🛠️ Technologies Used

Java, JSP & Servlets, MySQL, JDBC, HTML & CSS
Apache Tomcat

🧱 Project Architecture
Model: Represents user data
View: JSP pages for UI
Controller: Servlets handle requests
DAO: Handles database queries

BankingSystem/
├── src/
│   └── com/banking/
│       ├── config/
│       │   └── DBConnection.java
│       ├── model/
│       │   └── User.java
│       ├── dao/
│       │   └── UserDAO.java
│       └── controller/
│           ├── LoginServlet.java
│           └── TransferServlet.java
├── WebContent/
│   ├── index.jsp
│   └── dashboard.jsp
└── database.sql
