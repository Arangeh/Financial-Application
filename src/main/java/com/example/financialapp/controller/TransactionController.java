package com.example.financialapp.controller;

import com.example.financialapp.domain.dto.BankTransactionDto;
import com.example.financialapp.services.BankTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final BankTransactionService bankTransactionService;
    @PostMapping("/transactions/transfer")
    public ResponseEntity<BankTransactionDto> performTransaction(@RequestBody BankTransactionDto trDto) {
        BankTransactionDto result = bankTransactionService.transfer(trDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/accounts/{accountNo}")
    public ResponseEntity<List<BankTransactionDto>> getAllTransactionsForAccount(@PathVariable String accountNo) {
        List<BankTransactionDto> result = bankTransactionService.getTransactionsByAccountNo(accountNo);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/transactions/{accountNo}")
    public ResponseEntity<List<BankTransactionDto>> getBankTransactionsWithinInterval(@PathVariable String accountNo, @RequestParam String startDate, @RequestParam String endDate) {
        List<BankTransactionDto> result = bankTransactionService.getTransactionsWithinInterval(accountNo, Timestamp.valueOf(startDate), Timestamp.valueOf(endDate));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
