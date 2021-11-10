package com.example.financialapp.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String accountNo;
    private Integer credit;

    public void deposit(Integer d) {
        credit += d;
    }

    public void withdraw(Integer w) {
        credit -= w;
    }
}
