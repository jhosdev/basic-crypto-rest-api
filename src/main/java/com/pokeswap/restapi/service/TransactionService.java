package com.pokeswap.restapi.service;

import com.pokeswap.restapi.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();

    Transaction getTransactionById(Long id);

    Transaction updateTransaction(Long id, Transaction transaction);

    Transaction deleteTransaction(Long id);

    List<Transaction> findAllByTransactionDateAfter(LocalDateTime transactionDate);
    List<Transaction> findAllByUser_Id(Long id);
    Transaction findLastTransactionByUser_Id(Long id);
}