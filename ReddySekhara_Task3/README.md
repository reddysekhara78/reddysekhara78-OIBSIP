<div align="center">

# 🏦 Smart ATM Management System

### A Secure Banking Web Application Built Using Spring Boot

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8-blue?style=for-the-badge&logo=mysql)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5-purple?style=for-the-badge&logo=bootstrap)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Template-success?style=for-the-badge)
![License](https://img.shields.io/badge/License-Educational-red?style=for-the-badge)

Developed as a **Java Full Stack Banking Application** using **Spring Boot, Spring MVC, Spring Data JPA, Thymeleaf, Bootstrap and MySQL**.

</div>

---

# 📖 Table of Contents

- Project Overview
- Features
- Technologies Used
- Project Architecture
- Project Modules
- Database Design
- Screenshots
- Installation
- Running the Project
- REST APIs
- Exception Handling
- Future Enhancements
- Learning Outcomes
- Developer

---

# 🏦 Project Overview

The **Smart ATM Management System** is a web-based banking application that simulates the functionality of a real ATM.

Users can securely register, log in, deposit money, withdraw money, transfer funds, view transaction history, download PDF statements, update profile information, and manage passwords.

The project follows the **MVC (Model-View-Controller)** architecture using Spring Boot and stores banking information in a MySQL database.

---

# ✨ Features

| Feature | Status |
|----------|--------|
| User Registration | ✅ |
| Secure Login | ✅ |
| Logout | ✅ |
| Forgot Password | ✅ |
| Change Password | ✅ |
| Deposit Money | ✅ |
| Withdraw Money | ✅ |
| Fund Transfer | ✅ |
| Mini Statement | ✅ |
| PDF Statement | ✅ |
| Dashboard Analytics | ✅ |
| Monthly Transaction Chart | ✅ |
| Transaction Type Chart | ✅ |
| Recent Transactions | ✅ |
| My Profile | ✅ |
| Edit Profile | ✅ |
| Session Management | ✅ |
| Exception Handling | ✅ |
| Responsive UI | ✅ |

---

# 🛠 Technologies Used

## Backend

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate

## Frontend

- HTML5
- CSS3
- Bootstrap 5
- Thymeleaf
- JavaScript
- Chart.js

## Database

- MySQL

## Build Tool

- Maven

## IDE

- Eclipse IDE

---

# 🏗 Project Architecture

```
                Client
                   │
                   ▼
            Spring MVC Controller
                   │
                   ▼
              Service Layer
                   │
                   ▼
             Repository Layer
                   │
                   ▼
                 MySQL
```

---

# 📂 Project Structure

```
SmartATM
│
├── src
│   ├── main
│   │
│   ├── java
│   │     └── com.reddy.smartatm
│   │
│   │          ├── controller
│   │          ├── service
│   │          ├── service.impl
│   │          ├── repository
│   │          ├── entity
│   │          ├── dto
│   │          ├── exception
│   │
│   └── resources
│         ├── templates
│         ├── static
│         │      ├── css
│         │      └── js
│         └── application.properties
│
├── pom.xml
└── README.md
```

---

# 📚 Project Modules

## 🔐 Authentication Module

- User Registration
- Login
- Logout
- Forgot Password
- Change Password

---

## 👤 Profile Module

- View Profile
- Edit Profile
- Email Update
- Phone Number Update

---

## 💰 Banking Module

### Deposit

- Deposit money
- Balance updated
- Transaction saved

---

### Withdraw

- Withdraw money
- Balance validation
- Insufficient balance handling

---

### Transfer

- Fund Transfer
- Receiver validation
- Balance validation
- Dual transaction recording

---

## 📄 Statement Module

Displays

- Transaction ID
- Transaction Type
- Amount
- Description
- Status
- Date & Time

Supports PDF Download.

---

## 📊 Dashboard Module

Displays

- Current Balance
- Total Deposits
- Total Withdrawals
- Total Transfers

Charts

- Monthly Transactions
- Transaction Types

Recent Transactions

- Last 5 transactions

---

# 🗄 Database Tables

## users

Stores

- Username
- Password
- Email
- Phone
- Active Status

---

## accounts

Stores

- Account Number
- Account Type
- Balance
- Branch
- IFSC Code

---

## transactions

Stores

- Transaction Type
- Amount
- Balance
- Description
- Status
- Transaction Date

---

# 📷 Screenshots

> Add screenshots after uploading to GitHub.

### 🔐 Login

```
screenshots/login.png
```

---

### 📝 Register

```
screenshots/register.png
```

---

### 📊 Dashboard

```
screenshots/dashboard.png
```

---

### 💰 Deposit

```
screenshots/deposit.png
```

---

### 💸 Withdraw

```
screenshots/withdraw.png
```

---

### 🔄 Transfer

```
screenshots/transfer.png
```

---

### 👤 Profile

```
screenshots/profile.png
```

---

### 📄 Mini Statement

```
screenshots/statement.png
```

---

# ⚙ Installation

Clone the repository

```bash
git clone https://github.com/yourusername/SmartATM.git
```

Go to project folder

```bash
cd SmartATM
```

Import as Maven Project in Eclipse.

---

# 🛢 Configure Database

Create database

```sql
CREATE DATABASE smartatm;
```

Update

```
application.properties
```

with

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/smartatm

spring.datasource.username=root

spring.datasource.password=yourpassword
```

Run the application.

---

# ▶ Running

Start Spring Boot application.

Open

```
http://localhost:8080/login
```

---

# 📡 REST APIs

| API | Description |
|------|-------------|
| /api/dashboard/analytics | Dashboard Totals |
| /api/dashboard/monthly | Monthly Transactions |
| /api/dashboard/types | Transaction Types |

---

# ⚠ Exception Handling

Handled Exceptions

- User Not Found
- Invalid Password
- Invalid Account
- Insufficient Balance
- General Exception

---

# 🔒 Security

- Session Authentication
- Login Validation
- Protected Dashboard
- Password Verification
- Exception Handling

---

# 🚀 Future Enhancements

- Spring Security
- BCrypt Password Encryption
- OTP Verification
- Email Notification
- SMS Alerts
- Admin Dashboard
- Pagination
- Transaction Filters
- Search Transactions

---

# 📖 Learning Outcomes

This project helped me understand

- Spring Boot MVC
- Spring Data JPA
- Hibernate
- Thymeleaf
- Bootstrap
- REST APIs
- Session Management
- Exception Handling
- PDF Generation
- Chart.js
- MySQL Integration

---

# 👨‍💻 Developer

**Reddy Sekhara**

Final Year B.Tech Student

### Skills

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- MySQL
- HTML
- CSS
- Bootstrap
- Thymeleaf

---

<div align="center">

⭐ If you like this project, don't forget to give it a star on GitHub!

</div>
