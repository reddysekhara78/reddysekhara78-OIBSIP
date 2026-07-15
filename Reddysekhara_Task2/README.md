# 🎮 Number Guessing Game

A modern **Number Guessing Game** web application developed using **Java, Spring Boot, Thymeleaf, MySQL, HTML, CSS, and Bootstrap**. The application allows users to play a number guessing game with different difficulty levels while tracking game statistics and history through an interactive dashboard.

---

## 📌 Project Overview

The Number Guessing Game is a web-based application where the player selects a difficulty level and tries to guess a randomly generated number within a limited number of attempts.

The application stores every completed game in a MySQL database and provides a dashboard to view game statistics, history, and performance.

---

## ✨ Features

- 🎮 Start a New Game
- 👤 Player Name Entry
- 🎯 Three Difficulty Levels
  - Easy
  - Medium
  - Hard
- 🔢 Random Number Generation
- 💡 High/Low Hint System
- ⏳ Limited Attempts
- 🏆 Win & Lose Detection
- 💾 Store Game History in MySQL
- 📊 Dashboard with Statistics
- 📈 Performance Chart
- 🥇 Best Player Display
- 🎯 Average Guess Calculation
- 📋 Recent Games History
- 🗑 Reset Game History
- 📱 Responsive User Interface
- ℹ About Page
- 📞 Contact Page

---

## 🛠 Technologies Used

### Backend
- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA

### Frontend
- HTML5
- CSS3
- Bootstrap 5
- Thymeleaf
- Chart.js

### Database
- MySQL

### Build Tool
- Maven

---

## 📂 Project Structure

```
NumberGuessingGame
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.reddy.guessinggame
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── entity
│   │   │       ├── repository
│   │   │       ├── service
│   │   │       └── GuessingGameApplication.java
│   │   │
│   │   ├── resources
│   │   │   ├── static
│   │   │   │   ├── css
│   │   │   │   ├── images
│   │   │   │   └── js
│   │   │   │
│   │   │   ├── templates
│   │   │   │   ├── index.html
│   │   │   │   ├── start-game.html
│   │   │   │   ├── game.html
│   │   │   │   ├── dashboard.html
│   │   │   │   ├── about.html
│   │   │   │   └── contact.html
│   │   │   │
│   │   │   └── application.properties
│
├── pom.xml
└── README.md
```

---

## 📊 Dashboard Features

The dashboard provides:

- Total Games Played
- Total Wins
- Total Losses
- Win Percentage
- Best Player
- Average Number of Guesses
- Recent Games
- Game History Table
- Performance Chart
- Reset History Option

---

## 🎯 Game Flow

1. Open the application.
2. Enter player name.
3. Select difficulty level.
4. Start the game.
5. Guess the number.
6. Receive hints:
   - Too High
   - Too Low
7. Win or Lose.
8. Game history is stored automatically.
9. View statistics on the dashboard.

---

## 🗄 Database

The application uses **MySQL** to store completed game history.

Example table:

```
GameHistory
------------
id
player_name
difficulty
secret_number
total_guesses
result
played_at
```

---

## 🚀 How to Run

### 1. Clone Repository

```bash
git clone https://github.com/yourusername/NumberGuessingGame.git
```

### 2. Open Project

Import as **Existing Maven Project** in Eclipse or Spring Tool Suite.

### 3. Configure Database

Update `application.properties`.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/guessing_game
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
```

### 4. Run Project

Run

```
GuessingGameApplication.java
```

### 5. Open Browser

```
http://localhost:8080
```

---

## 📷 Screenshots

Add screenshots here after uploading them.

### 🏠 Home Page

(Add Screenshot)

### 🎮 Start Game

(Add Screenshot)

### 🎯 Game Page

(Add Screenshot)

### 📊 Dashboard

(Add Screenshot)

### ℹ About Page

(Add Screenshot)

### 📞 Contact Page

(Add Screenshot)

---

## 📈 Future Enhancements

- Login & Registration
- Player Authentication
- Difficulty Customization
- Sound Effects
- Dark Mode
- Online Multiplayer
- Global Leaderboard
- Player Rankings

---

## 👨‍💻 Developer

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

---

## 📄 License

This project is developed for learning and internship purposes.

---

# ⭐ If you like this project, don't forget to give it a Star!