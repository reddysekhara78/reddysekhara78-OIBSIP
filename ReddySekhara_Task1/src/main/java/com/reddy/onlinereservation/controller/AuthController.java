package com.reddy.onlinereservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.reddy.onlinereservation.entity.User;
import com.reddy.onlinereservation.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {

        service.register(user);

        return "redirect:/";
    }
    @GetMapping("/logout")
public String logout(HttpSession session) {

    session.invalidate();

    return "redirect:/";
}

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {

        User user = service.login(email, password);

        if (user != null) {

            session.setAttribute("loggedUser", user);

        return "redirect:/dashboard";
        }

        return "login";
    }

}