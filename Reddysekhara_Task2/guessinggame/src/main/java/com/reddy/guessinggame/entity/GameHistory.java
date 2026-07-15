package com.reddy.guessinggame.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class GameHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playerName;
    private String difficulty;
    private int secretNumber;
    private int totalGuesses;
    private String result;
    private LocalDateTime playedAt;

    public GameHistory() {
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getPlayerName() { return playerName; }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getDifficulty() { return difficulty; }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getSecretNumber() { return secretNumber; }

    public void setSecretNumber(int secretNumber) {
        this.secretNumber = secretNumber;
    }

    public int getTotalGuesses() { return totalGuesses; }

    public void setTotalGuesses(int totalGuesses) {
        this.totalGuesses = totalGuesses;
    }

    public String getResult() { return result; }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getPlayedAt() { return playedAt; }

    public void setPlayedAt(LocalDateTime playedAt) {
        this.playedAt = playedAt;
    }
}