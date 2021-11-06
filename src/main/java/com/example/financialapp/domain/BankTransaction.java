package com.example.financialapp.domain;

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
    @Column(name = "id", nullable = false)
    private Long id;
    private String source;
    private String destination;
    private Integer transferAmount;

    @CreationTimestamp
    @Column(name = "date_performed")
    private Timestamp datePerformed;

}
