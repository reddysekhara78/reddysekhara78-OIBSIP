package com.reddy.smartatm.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFound(
            UserNotFoundException ex,
            Model model) {

        model.addAttribute("error", ex.getMessage());

        return "login";
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public String handleInvalidCredentials(
            InvalidCredentialsException ex,
            Model model) {

        model.addAttribute("error", ex.getMessage());

        return "login";
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public String handleAccountNotFound(
            AccountNotFoundException ex,
            Model model) {

        model.addAttribute("error", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public String handleInsufficientBalance(
            InsufficientBalanceException ex,
            Model model) {

        model.addAttribute("error", ex.getMessage());

        return "withdraw";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(
            Exception ex,
            Model model) {

        model.addAttribute(
                "error",
                "Something went wrong. Please try again.");

        return "error";
    }
}