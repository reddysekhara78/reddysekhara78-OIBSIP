package com.reddy.smartatm.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reddy.smartatm.entity.Account;
import com.reddy.smartatm.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    List<Transaction> findByAccountOrderByTransactionDateDesc(Account account);



    // Total Deposit
    @Query("""
        SELECT COALESCE(SUM(t.amount),0)
        FROM Transaction t
        WHERE t.account = :account
        AND t.transactionType = 'DEPOSIT'
        AND t.status = 'SUCCESS'
    """)
    BigDecimal getTotalDeposit(Account account);



    // Total Withdrawal
    @Query("""
        SELECT COALESCE(SUM(t.amount),0)
        FROM Transaction t
        WHERE t.account = :account
        AND t.transactionType = 'WITHDRAW'
        AND t.status = 'SUCCESS'
    """)
    BigDecimal getTotalWithdrawal(Account account);



    // Total Transfer
    @Query("""
        SELECT COALESCE(SUM(t.amount),0)
        FROM Transaction t
        WHERE t.account = :account
        AND t.transactionType = 'TRANSFER'
        AND t.status = 'SUCCESS'
    """)
    BigDecimal getTotalTransfer(Account account);



    // Monthly transaction count
    @Query("""
        SELECT MONTH(t.transactionDate), COUNT(t.id)
        FROM Transaction t
        WHERE t.account = :account
        GROUP BY MONTH(t.transactionDate)
        ORDER BY MONTH(t.transactionDate)
    """)
    List<Object[]> getMonthlyTransactionCount(Account account);



    // Transaction type chart
    @Query("""
        SELECT t.transactionType, COUNT(t.id)
        FROM Transaction t
        WHERE t.account = :account
        GROUP BY t.transactionType
    """)
    List<Object[]> getTransactionTypeCount(Account account);

}