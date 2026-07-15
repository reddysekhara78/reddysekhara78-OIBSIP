package com.reddy.smartatm.controller;
import com.reddy.smartatm.entity.Account;
import com.reddy.smartatm.repository.AccountRepository;
import com.reddy.smartatm.entity.User;
import com.reddy.smartatm.exception.InvalidCredentialsException;
import com.reddy.smartatm.exception.UserNotFoundException;
import com.reddy.smartatm.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UserService userService;
    private final AccountRepository accountRepository;

    public LoginController(UserService userService,
                       AccountRepository accountRepository) {

    this.userService = userService;
    this.accountRepository = accountRepository;
}

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
public String loginPage(
        @RequestParam(required = false) String registered,
        Model model) {

    if (registered != null) {

        model.addAttribute(
                "success",
                "Registration successful! Please login using your username and password.");

    }

    return "login";
}
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        try {

            User user = userService.login(username, password);

            session.setAttribute("loggedInUser", user);

            return "redirect:/dashboard";

        } catch (UserNotFoundException | InvalidCredentialsException ex) {

            model.addAttribute("error", ex.getMessage());

            return "login";
        }
    }
    @PostMapping("/register")
public String register(

        @RequestParam String fullName,
        @RequestParam String username,
        @RequestParam String email,
        @RequestParam String phone,
        @RequestParam String password,
        Model model) {

    try {

        User user = new User();

        user.setFullName(fullName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);

        user = userService.save(user);

        Account account = new Account();

        account.setUser(user);
        account.setAccountType("Savings");
        account.setBranch("Smart ATM Branch");
        account.setIfscCode("SMART0001");

        accountRepository.save(account);

        model.addAttribute(
                "success",
                "Registration successful! Please login.");

    } catch (Exception e) {

        model.addAttribute(
                "error",
                "Username or Email already exists.");

    }

     return "login";
}

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
    @GetMapping("/register")
public String registerPage() {
    return "register";
}

}