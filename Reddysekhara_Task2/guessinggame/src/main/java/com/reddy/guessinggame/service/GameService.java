package com.reddy.guessinggame.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final Random random = new Random();

    public int generateNumber(String difficulty) {

        switch (difficulty) {

            case "Easy":
                return random.nextInt(50) + 1;

            case "Medium":
                return random.nextInt(100) + 1;

            case "Hard":
                return random.nextInt(200) + 1;

            default:
                return random.nextInt(100) + 1;
        }

    }

    public int getAttempts(String difficulty){

        switch (difficulty){

            case "Easy":
                return 10;

            case "Medium":
                return 7;

            case "Hard":
                return 5;

            default:
                return 7;
        }

    }

}