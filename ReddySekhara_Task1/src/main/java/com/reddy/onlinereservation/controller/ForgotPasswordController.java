package com.reddy.onlinereservation.controller;

import com.reddy.onlinereservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ForgotPasswordController {

    @Autowired
    private UserService userService;

    @GetMapping("/forgot-password")
    public String forgotPassword(){

        return "forgot-password";

    }

    @PostMapping("/forgot-password")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String confirmPassword,
                                Model model){

        if(!password.equals(confirmPassword)){

            model.addAttribute("error",
                    "Passwords do not match.");

            return "forgot-password";

        }

        boolean updated=userService.resetPassword(email,password);

        if(!updated){

            model.addAttribute("error",
                    "Email not found.");

            return "forgot-password";

        }

        model.addAttribute("success",
                "Password changed successfully.");

        return "forgot-password";

    }

}