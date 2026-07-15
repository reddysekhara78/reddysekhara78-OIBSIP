
package com.reddy.smartatm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reddy.smartatm.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findByUserId(Long userId);

}