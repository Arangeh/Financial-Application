package com.example.financialapp.services;

import com.example.financialapp.domain.dto.AccountDto;
import com.example.financialapp.domain.entity.Account;
import com.example.financialapp.repository.AccountRepository;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class AccountService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AccountRepository accountRepository;

    public AccountService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AccountDto getAccountByAccountNo(String accountNo) {
        Account result = accountRepository.findByAccountNo(accountNo).orElseThrow(NullPointerException::new);
        return modelMapper.map(result, AccountDto.class);
    }

    public AccountDto updateAccount(Account acc){
        accountRepository.save(acc);
        return modelMapper.map(acc,AccountDto.class);
    }
}
