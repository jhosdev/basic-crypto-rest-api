package com.pokeswap.restapi.controller;

import com.pokeswap.restapi.exception.ValidationException;
import com.pokeswap.restapi.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pokeswap.restapi.model.Wallet;

import java.util.List;

@RestController
@RequestMapping("/api/pokeswap/v1")
public class WalletController {
    private final WalletService walletService;

    private void validateWallet(Wallet wallet) {
        if (wallet.getUser() == null) {
            throw new ValidationException("User is required");
        }
        if (wallet.getBalance() == null) {
            throw new ValidationException("Balance is required");
        }
    }

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    //URL: http://localhost:8080/api/pokeswap/v1/wallets
    //Method: POST
    @Transactional
    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet) {
        validateWallet(wallet);
        Wallet walletCreated = walletService.createWallet(wallet);
        return new ResponseEntity<>(walletCreated, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/wallets
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/wallets")
    public ResponseEntity<List<Wallet>> getAllWallets() {
        List<Wallet> wallets = walletService.getAllWallets();
        return new ResponseEntity<>(wallets, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/wallets/{id}
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/wallets/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable Long id) {
        Wallet wallet = walletService.getWalletById(id);
        return new ResponseEntity<>(wallet, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/wallets/{id}
    //Method: PUT
    @Transactional
    @PutMapping("/wallets/{id}")
    public ResponseEntity<Wallet> updateWallet(@PathVariable Long id, @RequestBody Wallet wallet) {
        validateWallet(wallet);
        Wallet walletUpdated = walletService.updateWallet(id, wallet);
        return new ResponseEntity<>(walletUpdated, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/wallets/{id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/wallets/{id}")
    public ResponseEntity<Wallet> deleteWallet(@PathVariable Long id) {
        walletService.deleteWallet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}