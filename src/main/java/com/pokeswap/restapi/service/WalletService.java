package com.pokeswap.restapi.service;

import com.pokeswap.restapi.model.Wallet;

import java.util.List;

public interface WalletService {

    Wallet createWallet(Wallet wallet);

    List<Wallet> getAllWallets();

    Wallet getWalletById(Long id);

    Wallet updateWallet(Long id, Wallet wallet);

    Wallet deleteWallet(Long id);

    boolean existsByUserId(Long userId);

    Wallet getWalletByUserId(Long userId);
}