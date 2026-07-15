package com.reddy.smartatm.controller;

import com.reddy.smartatm.entity.User;
import com.reddy.smartatm.exception.UserNotFoundException;
import com.reddy.smartatm.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ForgotPasswordController {

    private final UserService userService;

    public ForgotPasswordController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {

        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String resetPassword(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            Model model) {

        try {

            if (!newPassword.equals(confirmPassword)) {

                model.addAttribute(
                        "error",
                        "New Password and Confirm Password do not match.");

                return "forgot-password";
            }

            User user = userService.findByUsernameAndEmail(
                    username,
                    email);

            userService.resetPassword(
                    user,
                    newPassword);

            model.addAttribute(
                    "success",
                    "Password changed successfully. Please login.");

            return "login";

        } catch (UserNotFoundException ex) {

            model.addAttribute(
                    "error",
                    ex.getMessage());

            return "forgot-password";
        }
    }

}