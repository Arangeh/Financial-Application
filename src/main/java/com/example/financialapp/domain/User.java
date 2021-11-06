package com.example.financialapp.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String surname;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Account> accounts;


}
