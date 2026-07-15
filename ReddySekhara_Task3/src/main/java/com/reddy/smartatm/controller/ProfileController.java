package com.reddy.smartatm.controller;

import com.reddy.smartatm.entity.Account;
import com.reddy.smartatm.entity.User;
import com.reddy.smartatm.service.AccountService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private final AccountService accountService;

    public ProfileController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        Account account = accountService.findByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("account", account);

        return "profile";
    }
}