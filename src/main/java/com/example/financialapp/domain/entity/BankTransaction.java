package com.example.financialapp.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class BankTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private Integer transferAmount;
    @CreationTimestamp
    private Timestamp datePerformed;

    @ManyToOne(fetch=FetchType.LAZY)
    private Account source;
    @ManyToOne(fetch=FetchType.LAZY)
    private Account destination;
}
