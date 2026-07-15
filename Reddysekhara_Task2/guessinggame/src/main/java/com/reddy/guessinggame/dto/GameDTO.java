package com.reddy.guessinggame.dto;

public class GameDTO {

    private String playerName;
    private String difficulty;

    public GameDTO() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}