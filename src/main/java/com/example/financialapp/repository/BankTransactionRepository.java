package com.example.financialapp.repository;
import com.example.financialapp.domain.entity.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction,Long> {
    @Query("SELECT tr FROM BankTransaction tr WHERE (tr.datePerformed BETWEEN :s AND :e)" +
            " and (tr.source.accountNo = :a or tr.destination.accountNo = :a)")
    List<BankTransaction> findByTimeBetween(@Param("a") String a ,@Param("s") Timestamp startDate,@Param("e") Timestamp endDate);

    @Query("SELECT tr FROM BankTransaction tr WHERE (tr.source.accountNo = :a or tr.destination.accountNo = :a)")
    List<BankTransaction> findByAccountNo(@Param("a") String a);
}
