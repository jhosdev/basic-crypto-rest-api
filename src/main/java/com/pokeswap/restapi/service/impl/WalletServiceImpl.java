package com.pokeswap.restapi.service.impl;

import com.pokeswap.restapi.model.Wallet;
import com.pokeswap.restapi.repository.WalletRepository;
import com.pokeswap.restapi.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl (WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id).orElse(null);
    }

    @Override
    public Wallet updateWallet(Long id, Wallet wallet) {
        Wallet walletToUpdate = walletRepository.findById(id).orElse(null);
        if (walletToUpdate != null) {
            walletToUpdate.setLastUpdated(wallet.getLastUpdated());
            walletToUpdate.setLastTransactionId(wallet.getLastTransactionId());
            walletToUpdate.setBalance(wallet.getBalance());
            return walletRepository.save(walletToUpdate);
        }
        return null;
    }
    @Override
    public Wallet deleteWallet(Long id) {
        Wallet walletToDelete = walletRepository.findById(id).orElse(null);
        if (walletToDelete != null) {
            walletRepository.delete(walletToDelete);
            return walletToDelete;
        }
        return null;
    }

    @Override
    public boolean existsByUserId(Long userId) {
        return walletRepository.existsByUserId(userId);
    }

    @Override
    public Wallet getWalletByUserId(Long userId) {
        return walletRepository.findByUserId(userId);
    }

}