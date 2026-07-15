package com.reddy.smartatm.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.reddy.smartatm.dto.DashboardAnalyticsDTO;
import com.reddy.smartatm.service.DashboardAnalyticsService;
import org.springframework.stereotype.Service;

import com.reddy.smartatm.entity.Account;
import com.reddy.smartatm.entity.Transaction;
import com.reddy.smartatm.entity.enums.TransactionStatus;
import com.reddy.smartatm.entity.enums.TransactionType;
import com.reddy.smartatm.repository.TransactionRepository;
import com.reddy.smartatm.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {


    private final TransactionRepository transactionRepository;
    private final DashboardAnalyticsService dashboardAnalyticsService;


    public TransactionServiceImpl(
        TransactionRepository transactionRepository,
        DashboardAnalyticsService dashboardAnalyticsService) {

    this.transactionRepository = transactionRepository;
    this.dashboardAnalyticsService = dashboardAnalyticsService;

}

    @Override
    public Transaction save(Transaction transaction) {

        return transactionRepository.save(transaction);

    }


    @Override
    public List<Transaction> getTransactions(Account account) {

        return transactionRepository
                .findByAccountOrderByTransactionDateDesc(account);

    }


    @Override
    public void recordTransaction(Account account,
                                  TransactionType type,
                                  BigDecimal amount,
                                  BigDecimal balanceAfter,
                                  String description) {


        Transaction transaction = new Transaction();


        transaction.setAccount(account);

        transaction.setTransactionType(type);

        transaction.setAmount(amount);

        transaction.setBalanceAfterTransaction(balanceAfter);

        transaction.setDescription(description);

        transaction.setStatus(TransactionStatus.SUCCESS);

        transaction.setTransactionDate(LocalDateTime.now());


        save(transaction);

    }


    @Override
    public List<Object[]> getMonthlyTransactionCount(Account account) {


        return transactionRepository
                .getMonthlyTransactionCount(account);

    }
    @Override
public List<Object[]> getTransactionTypeCount(Account account){

    return transactionRepository
            .getTransactionTypeCount(account);

}
@Override
public DashboardAnalyticsDTO getDashboardAnalytics(Account account) {

    return dashboardAnalyticsService.getAnalytics(account);

}
}