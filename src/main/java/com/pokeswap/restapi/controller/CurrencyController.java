package com.pokeswap.restapi.controller;

import com.pokeswap.restapi.exception.ValidationException;
import com.pokeswap.restapi.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pokeswap.restapi.model.Currency;

import java.util.List;

@RestController
@RequestMapping("/api/pokeswap/v1")
public class CurrencyController {
    private final CurrencyService currencyService;

    private void validateCurrency(Currency currency) {
        if (currency.getName() == null || currency.getName().isEmpty()) {
            throw new ValidationException("Name is required");
        }
        if (currency.getWaitTime() == null) {
            throw new ValidationException("Wait Time is required");
        }
        if (currency.getCommission() == null) {
            throw new ValidationException("Commission is required");
        }
        if (currency.getMinimumDeposit() == null) {
            throw new ValidationException("Minimum Deposit is required");
        }
        if (currency.getImageUrl() == null || currency.getImageUrl().isEmpty()) {
            throw new ValidationException("Image URL is required");
        }
    }

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    //URL: http://localhost:8080/api/pokeswap/v1/currencies
    //Method: POST
    @Transactional
    @PostMapping("/currencies")
    public ResponseEntity<Currency> createCurrency(@RequestBody Currency currency) {
        validateCurrency(currency);
        Currency currencyCreated = currencyService.createCurrency(currency);
        return new ResponseEntity<>(currencyCreated, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/currencies
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/currencies")
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        List<Currency> currencies = currencyService.getAllCurrencies();
        return new ResponseEntity<>(currencies, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/currencies/{id}
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/currencies/{id}")
    public ResponseEntity<Currency> getCurrencyById(@PathVariable Long id) {
        Currency currency = currencyService.getCurrencyById(id);
        return new ResponseEntity<>(currency, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/currencies/{id}
    //Method: PUT
    @Transactional
    @PutMapping("/currencies/{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable Long id, @RequestBody Currency currency) {
        Currency currencyUpdated = currencyService.updateCurrency(id, currency);
        return new ResponseEntity<>(currencyUpdated, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/currencies/{id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/currencies/{id}")
    public ResponseEntity<Currency> deleteCurrency(@PathVariable Long id) {
        Currency currencyDeleted = currencyService.deleteCurrency(id);
        return new ResponseEntity<>(currencyDeleted, HttpStatus.NO_CONTENT);
    }
}
