package com.pokeswap.restapi.controller;

import com.pokeswap.restapi.exception.ValidationException;
import com.pokeswap.restapi.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pokeswap.restapi.model.Exchange;

import java.util.List;

@RestController
@RequestMapping("/api/pokeswap/v1")
public class ExchangeController {

    private final ExchangeService exchangeService;

    private void validateExchange(Exchange exchange) {
        if (exchange.getUser() == null) {
            throw new ValidationException("User is required");
        }
        if (exchange.getToUserId() == null) {
            throw new ValidationException("To User ID is required");
        }
        if (exchange.getAmount() == null) {
            throw new ValidationException("Amount is required");
        }
        if (exchange.getFromTokenId() == null) {
            throw new ValidationException("From Token ID is required");
        }
        if (exchange.getFromTokenQuantity() == null) {
            throw new ValidationException("From Token Quantity is required");
        }
        if (exchange.getToTokenId() == null) {
            throw new ValidationException("To Token ID is required");
        }
        if (exchange.getToTokenQuantity() == null) {
            throw new ValidationException("To Token Quantity is required");
        }
        if (exchange.getStatus() == null) {
            throw new ValidationException("Status is required");
        }
    }

    @Autowired
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    //URL: http://localhost:8080/api/pokeswap/v1/exchanges
    //Method: POST
    @Transactional
    @PostMapping("/exchanges")
    public ResponseEntity<Exchange> createExchange(@RequestBody Exchange exchange) {
        validateExchange(exchange);
        Exchange exchangeCreated = exchangeService.createExchange(exchange);
        return new ResponseEntity<>(exchangeCreated, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/exchanges
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/exchanges")
    public ResponseEntity<List<Exchange>> getAllExchanges() {
        List<Exchange> exchanges = exchangeService.getAllExchanges();
        return new ResponseEntity<>(exchanges, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/exchanges/{id}
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/exchanges/{id}")
    public ResponseEntity<Exchange> getExchangeById(@PathVariable Long id) {
        Exchange exchange = exchangeService.getExchangeById(id);
        return new ResponseEntity<>(exchange, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/exchanges/{id}
    //Method: PUT
    @Transactional
    @PutMapping("/exchanges/{id}")
    public ResponseEntity<Exchange> updateExchangeById(@PathVariable Long id, @RequestBody Exchange exchange) {
        validateExchange(exchange);
        Exchange exchangeUpdated = exchangeService.updateExchangeById(id, exchange);
        return new ResponseEntity<>(exchangeUpdated, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/exchanges/{id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/exchanges/{id}")
    public ResponseEntity<Exchange> deleteExchangeById(@PathVariable Long id) {
        Exchange exchangeDeleted = exchangeService.deleteExchangeById(id);
        return new ResponseEntity<>(exchangeDeleted, HttpStatus.CREATED);
    }
}
