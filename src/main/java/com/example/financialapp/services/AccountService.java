package com.example.financialapp.services;

import com.example.financialapp.domain.Account;
import com.example.financialapp.domain.BankTransaction;
import com.example.financialapp.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class AccountService {
    @Autowired
    private ModelMapper modelMapper;
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        setJpaRepository(accountRepository);
    }


    public void setJpaRepository(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts(){
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }

    public Account getAccountByAccountNo(String accountNo){
        Account account = accountRepository.findByAccountNo(accountNo).orElseThrow(NullPointerException::new);
        return account;
    }

    public List<BankTransaction>getTransactionsByAccountNo(String accountNo){
        Account account = getAccountByAccountNo(accountNo);
        return account.getBankTransactions();
    }

    @Transactional
    public List<Account> transfer(String source, String destination, BankTransaction tr){
        List<Account> accounts = new ArrayList<>();
        Account sourceAccount = accountRepository.findByAccountNo(source).orElseThrow(NullPointerException::new);
        Account destinationAccount = accountRepository.findByAccountNo(destination).orElseThrow(NullPointerException::new);

        Integer transferAmount = tr.getTransferAmount();
        sourceAccount.withdraw(tr, transferAmount);
        sourceAccount = accountRepository.save(sourceAccount);

        destinationAccount.deposit(tr, transferAmount);
        destinationAccount = accountRepository.save(destinationAccount);

        accounts.add(sourceAccount);
        accounts.add(destinationAccount);

        return accounts;
    }


}
