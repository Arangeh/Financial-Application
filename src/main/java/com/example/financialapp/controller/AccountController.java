package com.example.financialapp.controller;

import com.example.financialapp.domain.dto.AccountDto;
import com.example.financialapp.domain.entity.Account;
import com.example.financialapp.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/accountinfo/{accountNo}")
    public ResponseEntity<AccountDto> getAccountInfo(@PathVariable String accountNo) {
        AccountDto result = accountService.getAccountByAccountNo(accountNo);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
