package com.reddy.smartatm.controller;

import com.reddy.smartatm.entity.User;
import com.reddy.smartatm.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EditProfileController {

    private final UserService userService;

    public EditProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit-profile")
    public String editProfilePage(HttpSession session,
                                  Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);

        return "edit-profile";
    }

    @PostMapping("/edit-profile")
    public String updateProfile(
            @RequestParam String email,
            @RequestParam String phone,
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        // Validate phone number
        if (!phone.matches("\\d{10}")) {

            model.addAttribute("error",
                    "Phone number must contain exactly 10 digits.");

            model.addAttribute("user", user);

            return "edit-profile";
        }

        userService.updateProfile(
                user,
                email,
                phone);

        session.setAttribute("loggedInUser", user);

        return "redirect:/profile";
    }
}