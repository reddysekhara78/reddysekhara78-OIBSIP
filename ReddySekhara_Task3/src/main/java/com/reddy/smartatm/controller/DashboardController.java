package com.reddy.smartatm.controller;

import com.reddy.smartatm.dto.DashboardAnalyticsDTO;
import com.reddy.smartatm.entity.Account;
import com.reddy.smartatm.entity.User;
import com.reddy.smartatm.service.AccountService;
import com.reddy.smartatm.service.TransactionService;
import com.reddy.smartatm.service.DashboardAnalyticsService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardController {


    private final TransactionService transactionService;

    private final AccountService accountService;

    private final DashboardAnalyticsService dashboardAnalyticsService;



    public DashboardController(
            TransactionService transactionService,
            AccountService accountService,
            DashboardAnalyticsService dashboardAnalyticsService) {


        this.transactionService = transactionService;

        this.accountService = accountService;

        this.dashboardAnalyticsService = dashboardAnalyticsService;

    }



    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {


        User user = (User) session.getAttribute("loggedInUser");


        if(user == null) {

            return "redirect:/login";

        }


        Account account = accountService.findByUser(user);



        DashboardAnalyticsDTO analytics =
                dashboardAnalyticsService.getAnalytics(account);



        model.addAttribute("user", user);

        model.addAttribute("account", account);


        model.addAttribute(
            "analytics",
            analytics
        );


        model.addAttribute(
            "transactions",
            transactionService.getTransactions(account)
                    .stream()
                    .limit(5)
                    .toList()
        );



        model.addAttribute(
            "monthlyTransactions",
            transactionService.getMonthlyTransactionCount(account)
        );

    model.addAttribute(
         "transactionTypes",
         transactionService.getTransactionTypeCount(account)
);
        return "dashboard";

    }

}