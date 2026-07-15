package com.reddy.guessinggame.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.reddy.guessinggame.dto.GameDTO;
import com.reddy.guessinggame.dto.GuessDTO;
import com.reddy.guessinggame.entity.GameHistory;
import com.reddy.guessinggame.repository.GameHistoryRepository;
import com.reddy.guessinggame.service.GameService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final GameService gameService;
    private final GameHistoryRepository historyRepository;

    public HomeController(GameService gameService,
                          GameHistoryRepository historyRepository) {
        this.gameService = gameService;
        this.historyRepository = historyRepository;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/start")
    public String start(Model model) {
        model.addAttribute("game", new GameDTO());
        return "start-game";
    }

    @PostMapping("/play")
    public String play(@ModelAttribute GameDTO game,
                       HttpSession session,
                       Model model) {

        int secretNumber = gameService.generateNumber(game.getDifficulty());
        int attempts = gameService.getAttempts(game.getDifficulty());

        session.setAttribute("secretNumber", secretNumber);
        session.setAttribute("attempts", attempts);
        session.setAttribute("difficulty", game.getDifficulty());
        session.setAttribute("player", game.getPlayerName());
        session.setAttribute("guessCount", 0);

        model.addAttribute("guess", new GuessDTO());
        model.addAttribute("player", game.getPlayerName());
        model.addAttribute("difficulty", game.getDifficulty());
        model.addAttribute("attempts", attempts);

        return "game";
    }

    @PostMapping("/guess")
    public String guess(@ModelAttribute GuessDTO guessDTO,
                        HttpSession session,
                        Model model) {

        int secret = (int) session.getAttribute("secretNumber");
        int attempts = (int) session.getAttribute("attempts");
        int guessCount = (int) session.getAttribute("guessCount");

        guessCount++;
        attempts--;

        session.setAttribute("guessCount", guessCount);
        session.setAttribute("attempts", attempts);

        String message;

        if (guessDTO.getGuess() == secret) {

            message = "🎉 Congratulations! You guessed the correct number in " + guessCount + " guesses.";

            GameHistory history = new GameHistory();
            history.setPlayerName((String) session.getAttribute("player"));
            history.setDifficulty((String) session.getAttribute("difficulty"));
            history.setSecretNumber(secret);
            history.setTotalGuesses(guessCount);
            history.setResult("WIN");
            history.setPlayedAt(LocalDateTime.now());

            historyRepository.save(history);

        } else if (attempts == 0) {

            message = "❌ Game Over! The correct number was " + secret;

            GameHistory history = new GameHistory();
            history.setPlayerName((String) session.getAttribute("player"));
            history.setDifficulty((String) session.getAttribute("difficulty"));
            history.setSecretNumber(secret);
            history.setTotalGuesses(guessCount);
            history.setResult("LOSE");
            history.setPlayedAt(LocalDateTime.now());

            historyRepository.save(history);

        } else if (guessDTO.getGuess() > secret) {

            message = "📈 Too High! Try again.";

        } else {

            message = "📉 Too Low! Try again.";
        }

        model.addAttribute("guess", new GuessDTO());
        model.addAttribute("message", message);
        model.addAttribute("attempts", attempts);
        model.addAttribute("player", session.getAttribute("player"));
        model.addAttribute("difficulty", session.getAttribute("difficulty"));

        return "game";
    }
@GetMapping("/dashboard")
public String dashboard(Model model) {

    long totalGames = historyRepository.count();
    long wins = historyRepository.countByResult("WIN");
    long losses = historyRepository.countByResult("LOSE");

    double winRate = totalGames == 0 ? 0 : ((double) wins / totalGames) * 100;

    GameHistory bestPlayer = historyRepository.findTopByOrderByTotalGuessesAsc();

    Double avg = historyRepository.averageGuesses();

    model.addAttribute("games", historyRepository.findAll());

    model.addAttribute("totalGames", totalGames);
    model.addAttribute("wins", wins);
    model.addAttribute("losses", losses);
    model.addAttribute("winRate", String.format("%.1f", winRate));

    model.addAttribute("winsChart", wins);
    model.addAttribute("lossesChart", losses);

    model.addAttribute("bestPlayer", bestPlayer);
    model.addAttribute("averageGuesses", avg == null ? 0 : String.format("%.1f", avg));
model.addAttribute("recentGames",
        historyRepository.findTop5ByOrderByPlayedAtDesc());
    return "dashboard";
}
@GetMapping("/playAgain")
public String playAgain(Model model) {

    model.addAttribute("game", new GameDTO());

    return "start-game";
}
    @GetMapping("/about")
public String about() {
    return "about";
}

@GetMapping("/contact")
public String contact() {
    return "contact";
}
@GetMapping("/resetHistory")
public String resetHistory() {

    historyRepository.deleteAll();

    return "redirect:/dashboard";
}
}