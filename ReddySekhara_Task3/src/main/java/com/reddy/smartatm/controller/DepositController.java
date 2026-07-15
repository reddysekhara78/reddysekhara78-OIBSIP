package com.reddy.smartatm.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.reddy.smartatm.entity.Account;
import com.reddy.smartatm.entity.User;
import com.reddy.smartatm.service.AccountService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DepositController {

    private final AccountService accountService;


    public DepositController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/deposit")
    public String depositPage() {

        return "deposit";

    }


    @PostMapping("/deposit")
    public String depositMoney(
            @RequestParam("amount") BigDecimal amount,
            HttpSession session) {


        User user = (User) session.getAttribute("loggedInUser");


        if(user == null) {

            return "redirect:/login";

        }


        Account account = accountService.findByUser(user);


        accountService.deposit(
                account.getAccountNumber(),
                amount
        );


        return "redirect:/dashboard";

    }

}