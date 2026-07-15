package com.reddy.smartatm.service.impl;

import java.math.BigDecimal;
import com.reddy.smartatm.entity.enums.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.reddy.smartatm.entity.User;

import com.reddy.smartatm.entity.Account;
import com.reddy.smartatm.exception.AccountNotFoundException;
import com.reddy.smartatm.exception.InsufficientBalanceException;
import com.reddy.smartatm.repository.AccountRepository;
import com.reddy.smartatm.service.AccountService;
import com.reddy.smartatm.service.TransactionService;
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

private final TransactionService transactionService;


public AccountServiceImpl(
        AccountRepository accountRepository,
        TransactionService transactionService) {

    this.accountRepository = accountRepository;
    this.transactionService = transactionService;
}
@Override
public Account save(Account account) {

    return accountRepository.save(account);

}
@Override
public Account findByAccountNumber(String accountNumber) {

    return accountRepository
            .findByAccountNumber(accountNumber)
            .orElseThrow(() ->
                    new AccountNotFoundException(
                            "Account not found"));

}
@Override
public void deposit(String accountNumber, BigDecimal amount) {

    Account account = findByAccountNumber(accountNumber);

    account.setBalance(account.getBalance().add(amount));

    accountRepository.save(account);

    transactionService.recordTransaction(
            account,
            com.reddy.smartatm.entity.enums.TransactionType.DEPOSIT,
            amount,
            account.getBalance(),
            "Amount Deposited");
}
@Override
public void withdraw(String accountNumber, BigDecimal amount) {

    Account account = findByAccountNumber(accountNumber);

    if (account.getBalance().compareTo(amount) < 0) {

        throw new InsufficientBalanceException(
                "Insufficient Balance");
    }

    account.setBalance(account.getBalance().subtract(amount));

    accountRepository.save(account);

    transactionService.recordTransaction(
            account,
            com.reddy.smartatm.entity.enums.TransactionType.WITHDRAW,
            amount,
            account.getBalance(),
            "Amount Withdrawn");
}
@Override
public void transfer(String fromAccount,
                     String toAccount,
                     BigDecimal amount) {

    Account sender = findByAccountNumber(fromAccount);

    Account receiver = findByAccountNumber(toAccount);

    if (sender.getBalance().compareTo(amount) < 0) {

        throw new InsufficientBalanceException(
                "Insufficient Balance");
    }

    sender.setBalance(sender.getBalance().subtract(amount));

    receiver.setBalance(receiver.getBalance().add(amount));

    accountRepository.save(sender);

    accountRepository.save(receiver);

    transactionService.recordTransaction(
            sender,
TransactionType.TRANSFER,
            amount,
            sender.getBalance(),
            "Transferred to " + receiver.getAccountNumber());

    transactionService.recordTransaction(
            receiver,
            TransactionType.DEPOSIT,
            amount,
            receiver.getBalance(),
            "Received from " + sender.getAccountNumber());
}
@Override
public Account findByUser(User user) {

    return accountRepository.findByUserId(user.getId())
            .orElseThrow(() ->
                    new AccountNotFoundException("Account not found"));

}
}


