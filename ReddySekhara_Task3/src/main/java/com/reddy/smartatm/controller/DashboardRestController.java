package com.reddy.smartatm.controller;

import java.util.List;

import com.reddy.smartatm.dto.DashboardAnalyticsDTO;
import com.reddy.smartatm.entity.Account;
import com.reddy.smartatm.entity.User;
import com.reddy.smartatm.service.AccountService;
import com.reddy.smartatm.service.TransactionService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DashboardRestController {


    private final TransactionService transactionService;

    private final AccountService accountService;


    public DashboardRestController(
            TransactionService transactionService,
            AccountService accountService) {

        this.transactionService = transactionService;
        this.accountService = accountService;

    }


    @GetMapping("/api/dashboard/analytics")
    public DashboardAnalyticsDTO getAnalytics(
            HttpSession session) {


        User user =
                (User) session.getAttribute("loggedInUser");


        if(user == null) {

            throw new RuntimeException("User not logged in");

        }


        Account account =
                accountService.findByUser(user);


        return transactionService
                .getDashboardAnalytics(account);

    }



    @GetMapping("/api/dashboard/monthly")
    public List<Object[]> getMonthlyTransactions(
            HttpSession session) {


        User user =
                (User) session.getAttribute("loggedInUser");


        Account account =
                accountService.findByUser(user);


        return transactionService
                .getMonthlyTransactionCount(account);

    }



    @GetMapping("/api/dashboard/types")
    public List<Object[]> getTransactionTypes(
            HttpSession session) {


        User user =
                (User) session.getAttribute("loggedInUser");


        Account account =
                accountService.findByUser(user);


        return transactionService
                .getTransactionTypeCount(account);

    }

}