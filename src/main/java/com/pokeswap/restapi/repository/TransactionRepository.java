package com.pokeswap.restapi.repository;

import com.pokeswap.restapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

        List<Transaction> findAllByTransactionDateAfter(LocalDateTime transactionDate);
        List<Transaction> findAllByUser_Id(Long id);

        Transaction findLastTransactionByUser_Id(Long id);
}
