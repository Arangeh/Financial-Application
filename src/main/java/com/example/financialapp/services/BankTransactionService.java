package com.example.financialapp.services;

import com.example.financialapp.domain.dto.BankTransactionDto;
import com.example.financialapp.domain.entity.Account;
import com.example.financialapp.domain.entity.BankTransaction;
import com.example.financialapp.repository.BankTransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankTransactionService {
    @Autowired
    private ModelMapper modelMapper;
    private BankTransactionRepository transactionRepository;
    @Autowired
    private final AccountService accountService;

    public BankTransactionService(BankTransactionRepository bankTransactionRepository, ModelMapper modelMapper, AccountService accountService) {
        this.modelMapper = modelMapper;
        this.accountService = accountService;
        setJpaRepository(bankTransactionRepository);
    }

    public void setJpaRepository(BankTransactionRepository bankTransactionRepository) {
        this.transactionRepository = bankTransactionRepository;
    }

    public List<BankTransactionDto> getTransactionsWithinInterval(String accountNo, Timestamp start, Timestamp end) {
        List<BankTransaction> l = transactionRepository.findByTimeBetween(accountNo, start, end);
        return l.stream().map(this::createBankTransactionDto).collect(Collectors.toList());
    }

    public List<BankTransactionDto> getTransactionsByAccountNo(String accountNo) {
        List<BankTransaction> l = transactionRepository.findByAccountNo(accountNo);
        return l.stream().map(this::createBankTransactionDto).collect(Collectors.toList());
    }

    private BankTransactionDto createBankTransactionDto(BankTransaction tr) {
        BankTransactionDto trDto = modelMapper.map(tr, BankTransactionDto.class);
        trDto.setSourceAccountNo(tr.getSource().getAccountNo());
        trDto.setDestinationAccountNo(tr.getDestination().getAccountNo());
        return trDto;
    }

    private BankTransaction createBankTransction(BankTransactionDto trDto) {
        BankTransaction tr = modelMapper.map(trDto, BankTransaction.class);

        Account source = modelMapper.map(accountService.getAccountByAccountNo(trDto.getSourceAccountNo()),Account.class);
        Account destination = modelMapper.map(accountService.getAccountByAccountNo(trDto.getDestinationAccountNo()),Account.class);
        tr.setSource(source);
        tr.setDestination(destination);
        return tr;
    }

    @Transactional
    public BankTransactionDto transfer(BankTransactionDto trDto) {
        BankTransaction tr = createBankTransction(trDto);

        Integer transferAmount = tr.getTransferAmount();

        tr.getSource().withdraw(transferAmount);
        tr.getDestination().deposit(transferAmount);

        transactionRepository.save(tr);

        accountService.updateAccount(tr.getSource());
        accountService.updateAccount(tr.getDestination());
        return createBankTransactionDto(tr);
    }


}
