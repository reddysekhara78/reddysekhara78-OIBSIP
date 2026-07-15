package com.reddy.smartatm.service;

import java.math.BigDecimal;

import com.reddy.smartatm.entity.Account;
import com.reddy.smartatm.entity.User;

public interface AccountService {

    Account save(Account account);

    Account findByAccountNumber(String accountNumber);

    void deposit(String accountNumber, BigDecimal amount);

    void withdraw(String accountNumber, BigDecimal amount);

    void transfer(String fromAccount,
                  String toAccount,
                  BigDecimal amount);

    Account findByUser(User user);
}
