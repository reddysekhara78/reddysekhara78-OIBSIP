package com.reddy.smartatm.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.reddy.smartatm.entity.Account;
import com.reddy.smartatm.entity.User;
import com.reddy.smartatm.exception.InsufficientBalanceException;
import com.reddy.smartatm.service.AccountService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WithdrawController {

    private final AccountService accountService;

    public WithdrawController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/withdraw")
    public String withdrawPage() {
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdrawMoney(
            @RequestParam("amount") BigDecimal amount,
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        Account account = accountService.findByUser(user);

        try {

            accountService.withdraw(
                    account.getAccountNumber(),
                    amount);

            return "redirect:/dashboard";

        } catch (InsufficientBalanceException ex) {

            model.addAttribute("error", ex.getMessage());

            return "withdraw";
        }
    }
}