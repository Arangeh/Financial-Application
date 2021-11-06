package com.example.financialapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String accountNo;
    private Integer credit;


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<BankTransaction> bankTransactions;

    public void deposit(BankTransaction t, Integer d){
        credit += d;
        bankTransactions.add(t);
    }

    public void withdraw(BankTransaction t, Integer w){
        credit -= w;
        bankTransactions.add(t);
    }
}
