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
public class TransferController {

    private final AccountService accountService;

    public TransferController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/transfer")
    public String transferPage() {
        return "transfer";
    }

    @PostMapping("/transfer")
    public String transferMoney(
            @RequestParam("receiverAccount") String receiverAccount,
            @RequestParam("amount") BigDecimal amount,
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        Account sender = accountService.findByUser(user);

        try {

            accountService.transfer(
                    sender.getAccountNumber(),
                    receiverAccount,
                    amount);

            return "redirect:/dashboard";

        } catch (InsufficientBalanceException ex) {

            model.addAttribute("error", ex.getMessage());

            return "transfer";
        }
    }
}