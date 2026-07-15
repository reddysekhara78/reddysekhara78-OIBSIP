package com.reddy.smartatm.service;

import java.math.BigDecimal;
import java.util.List;

import com.reddy.smartatm.dto.DashboardAnalyticsDTO;
import com.reddy.smartatm.entity.Account;
import com.reddy.smartatm.entity.Transaction;
import com.reddy.smartatm.entity.enums.TransactionType;

public interface TransactionService {

    Transaction save(Transaction transaction);


    List<Transaction> getTransactions(Account account);


    void recordTransaction(Account account,
                           TransactionType type,
                           BigDecimal amount,
                           BigDecimal balanceAfter,
                           String description);


    
    List<Object[]> getTransactionTypeCount(Account account);

List<Object[]> getMonthlyTransactionCount(Account account);

DashboardAnalyticsDTO getDashboardAnalytics(Account account);

}