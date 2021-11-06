package com.example.financialapp.services;

import com.example.financialapp.domain.BankTransaction;
import com.example.financialapp.repository.BankTransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BankTransactionService {
    @Autowired
    private ModelMapper modelMapper;
    private BankTransactionRepository transactionRepository;

    public BankTransactionService(BankTransactionRepository bankTransactionRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        setJpaRepository(bankTransactionRepository);
    }

    public void setJpaRepository(BankTransactionRepository bankTransactionRepository) {
        this.transactionRepository = bankTransactionRepository;
    }

    public List<BankTransaction> getTransactionsWithinInterval(List<BankTransaction> transactions, Timestamp start, Timestamp end) {
        List<BankTransaction> filteredTransactions = transactionRepository.findByTimeBetween(transactions,start, end);

        return filteredTransactions;
    }
}
