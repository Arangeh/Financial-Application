package com.example.financialapp.domain.dto;

import lombok.*;
import java.sql.Timestamp;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankTransactionDto {
    private Long id;
    private Integer transferAmount;
    private Timestamp datePerformed;
    private String sourceAccountNo;
    private String destinationAccountNo;
}
