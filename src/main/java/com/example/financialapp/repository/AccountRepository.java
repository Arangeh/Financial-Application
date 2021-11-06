package com.example.financialapp.repository;
import com.example.financialapp.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByAccountNo(String accountNo);
}
