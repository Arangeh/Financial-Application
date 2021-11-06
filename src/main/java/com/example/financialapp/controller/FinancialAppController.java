package com.example.financialapp.controller;

import com.example.financialapp.domain.Account;
import com.example.financialapp.domain.BankTransaction;
import com.example.financialapp.domain.User;
import com.example.financialapp.services.AccountService;
import com.example.financialapp.services.BankTransactionService;
import com.example.financialapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FinancialAppController {

    private final UserService userService;
    private final AccountService accountService;
    private final BankTransactionService bankTransactionService;


    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User usr) {
        User result = userService.update(usr);

        ResponseEntity<User> res = ResponseEntity.status(HttpStatus.CREATED)
                .body(result);

        return res;
    }

    @PostMapping("/users/{userId}/accounts")
    public ResponseEntity<User> addAccount(@PathVariable Long userId, @RequestBody Account acc) {


        User result = userService.addAccount(userId, acc);
        ResponseEntity<User> res = ResponseEntity.status(HttpStatus.CREATED)
                .body(result);

        return res;
    }

    @PostMapping("/users/transfer")
    public ResponseEntity<List<Account>> performTransaction(@RequestBody BankTransaction tr) {
        String sourceAccountNo = tr.getSource();
        String destinationAccountNo = tr.getDestination();


        List<Account> result = accountService.transfer(sourceAccountNo, destinationAccountNo, tr);

        ResponseEntity<List<Account>> res = ResponseEntity.status(HttpStatus.CREATED).body(result);
        return res;
    }

    @GetMapping("/transactions/{accountNo}")
    public ResponseEntity<List<BankTransaction>> getBankTransactionsWithinInterval(@PathVariable String accountNo, @RequestParam String startDate, @RequestParam String endDate) {
        Account a = accountService.getAccountByAccountNo(accountNo);
        List<BankTransaction> result = bankTransactionService.getTransactionsWithinInterval(a.getBankTransactions(), Timestamp.valueOf(startDate), Timestamp.valueOf(endDate));

        ResponseEntity<List<BankTransaction>> res = ResponseEntity.status(HttpStatus.OK).body(result);
        return res;
    }

    @GetMapping("/accounts/{accountNo}")
    public ResponseEntity<List<BankTransaction>> getAccount(@PathVariable String accountNo) {
        List<BankTransaction> result = accountService.getTransactionsByAccountNo(accountNo);
        ResponseEntity<List<BankTransaction>> res = ResponseEntity.status(HttpStatus.OK).body(result);

        return res;
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> result = accountService.getAccounts();
        ResponseEntity<List<Account>> res = ResponseEntity.status(HttpStatus.OK).body(result);
        return res;
    }


}
