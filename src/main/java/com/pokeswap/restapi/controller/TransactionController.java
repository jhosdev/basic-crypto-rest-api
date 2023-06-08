package com.pokeswap.restapi.controller;

import com.pokeswap.restapi.exception.ValidationException;
import com.pokeswap.restapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pokeswap.restapi.model.Transaction;

import java.util.List;

@RestController
@RequestMapping("/api/pokeswap/v1")
public class TransactionController {
    private final TransactionService transactionService;

    private void validateTransaction(Transaction transaction) {
        if (transaction.getBalance() == null) {
            throw new ValidationException("Balance is required");
        }
        if (transaction.getTransactionType() == null) {
            throw new ValidationException("Transaction type is required");
        }
        if (transaction.getRecordType() == null) {
            throw new ValidationException("Record type is required");
        }
    }

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    //URL: http://localhost:8080/api/pokeswap/v1/transactions
    //Method: POST
    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        validateTransaction(transaction);
        Transaction transactionCreated = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(transactionCreated, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/transactions
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/transactions/{id}
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/transactions/{id}
    //Method: PUT
    @Transactional
    @PutMapping("/transactions/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        validateTransaction(transaction);
        Transaction transactionUpdated = transactionService.updateTransaction(id, transaction);
        return new ResponseEntity<>(transactionUpdated, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/transactions/{id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}