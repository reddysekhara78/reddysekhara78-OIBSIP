package com.reddy.guessinggame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reddy.guessinggame.entity.GameHistory;

public interface GameHistoryRepository extends JpaRepository<GameHistory, Long> {

    long countByResult(String result);

    @Query("SELECT COUNT(g) FROM GameHistory g")
    long totalGames();

    GameHistory findTopByOrderByTotalGuessesAsc();

    @Query("SELECT AVG(g.totalGuesses) FROM GameHistory g")
    Double averageGuesses();

    List<GameHistory> findTop5ByOrderByPlayedAtDesc();

}