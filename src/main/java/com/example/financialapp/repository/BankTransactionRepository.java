package com.example.financialapp.repository;
import com.example.financialapp.domain.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction,Long> {

    @Query("SELECT tr FROM BankTransaction tr WHERE tr in :l AND (tr.datePerformed BETWEEN :s AND :e)")
    List<BankTransaction> findByTimeBetween(@Param("l") List<BankTransaction> l ,@Param("s") Timestamp startDate,@Param("e") Timestamp endDate);
}
