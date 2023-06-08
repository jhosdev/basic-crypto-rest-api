package com.pokeswap.restapi.controller;

import com.pokeswap.restapi.exception.ValidationException;
import com.pokeswap.restapi.model.Token;
import com.pokeswap.restapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokeswap/v1")
public class TokenController {
    private final TokenService tokenService;

    private void validateToken(Token token) {
        if (token.getName() == null || token.getName().isEmpty()) {
            throw new ValidationException("Name is required");
        }
        if (token.getSymbol() == null || token.getSymbol().isEmpty()) {
            throw new ValidationException("Symbol is required");
        }
        if (token.getBalance() == null) {
            throw new ValidationException("Balance is required");
        }
        if (token.getExchangeRate() == null) {
            throw new ValidationException("Exchange Rate is required");
        }
    }

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    //URL: http://localhost:8080/api/pokeswap/v1/tokens
    //Method: POST
    @Transactional
    @PostMapping("/tokens")
    public ResponseEntity<Token> createCurrency(@RequestBody Token token) {
        validateToken(token);
        Token currencyCreated = tokenService.createToken(token);
        return new ResponseEntity<>(currencyCreated, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/tokens
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/tokens")
    public ResponseEntity<List<Token>> getAllCurrencies() {
        List<Token> currencies = tokenService.getAllTokens();
        return new ResponseEntity<>(currencies, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/tokens/{id}
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/tokens/{id}")
    public ResponseEntity<Token> getTokenById(@PathVariable Long id) {
        Token token = tokenService.getTokenById(id);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/tokens/{id}
    //Method: PUT
    @Transactional
    @PutMapping("/tokens/{id}")
    public ResponseEntity<Token> updateTokenById(@PathVariable Long id, @RequestBody Token token) {
        validateToken(token);
        Token tokenUpdated = tokenService.updateToken(id, token);
        return new ResponseEntity<>(tokenUpdated, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/tokens/{id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/tokens/{id}")
    public ResponseEntity<Token> deleteTokenById(@PathVariable Long id) {
        tokenService.deleteToken(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}