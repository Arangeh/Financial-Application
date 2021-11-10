package com.example.financialapp.controller;

import com.example.financialapp.domain.dto.AccountDto;
import com.example.financialapp.domain.dto.UserDto;
import com.example.financialapp.domain.entity.Account;
import com.example.financialapp.domain.entity.User;
import com.example.financialapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto usr) {
        UserDto result = userService.create(usr);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

    @PostMapping("/users/{userId}/accounts")
    public ResponseEntity<UserDto> addAccount(@PathVariable Long userId, @RequestBody AccountDto acc) {
        UserDto result = userService.addAccount(userId, acc);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }
}
