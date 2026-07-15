package com.reddy.onlinereservation.controller;

import com.reddy.onlinereservation.entity.User;
import com.reddy.onlinereservation.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute User updatedUser,
                                HttpSession session) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/";
        }

        user.setFullName(updatedUser.getFullName());
        user.setEmail(updatedUser.getEmail());
        user.setMobile(updatedUser.getMobile());

        userService.updateUser(user);

        session.setAttribute("loggedUser", user);

        return "redirect:/profile";
    }
    @GetMapping("/change-password")
public String changePasswordPage(HttpSession session) {

    User user = (User) session.getAttribute("loggedUser");

    if (user == null) {
        return "redirect:/";
    }

    return "change-password";
}

@PostMapping("/change-password")
public String changePassword(@RequestParam String oldPassword,
                             @RequestParam String newPassword,
                             @RequestParam String confirmPassword,
                             HttpSession session,
                             Model model) {

    User user = (User) session.getAttribute("loggedUser");

    if (user == null) {
        return "redirect:/";
    }

    if (!newPassword.equals(confirmPassword)) {
        model.addAttribute("error", "New Password and Confirm Password do not match.");
        return "change-password";
    }

    boolean updated = userService.changePassword(user, oldPassword, newPassword);

    if (!updated) {
        model.addAttribute("error", "Old Password is incorrect.");
        return "change-password";
    }

    model.addAttribute("success", "Password changed successfully.");

    return "change-password";
}
}