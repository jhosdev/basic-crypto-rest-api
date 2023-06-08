package com.pokeswap.restapi.service.impl;

import com.pokeswap.restapi.model.Transaction;
import com.pokeswap.restapi.repository.TransactionRepository;
import com.pokeswap.restapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public Transaction updateTransaction(Long id, Transaction transaction) {
        Transaction transactionToUpdate = transactionRepository.findById(id).orElse(null);
        if (transactionToUpdate != null) {
            transactionToUpdate.setTransactionDate(transaction.getTransactionDate());
            transactionToUpdate.setTransactionType(transaction.getTransactionType());
            transactionToUpdate.setBalance(transaction.getBalance());
            transactionToUpdate.setUser(transaction.getUser());
            return transactionRepository.save(transactionToUpdate);
        }
        return null;
    }

    @Override
    public Transaction deleteTransaction(Long id) {
        Transaction transactionToDelete = transactionRepository.findById(id).orElse(null);
        if (transactionToDelete != null) {
            transactionRepository.delete(transactionToDelete);
            return transactionToDelete;
        }
        return null;
    }

    @Override
    public List<Transaction> findAllByTransactionDateAfter(LocalDateTime transactionDate) {
        return transactionRepository.findAllByTransactionDateAfter(transactionDate);
    }

    @Override
    public List<Transaction> findAllByUser_Id(Long id) {
        return transactionRepository.findAllByUser_Id(id);
    }

    @Override
    public Transaction findLastTransactionByUser_Id(Long id) {
        return transactionRepository.findLastTransactionByUser_Id(id);
    }


}