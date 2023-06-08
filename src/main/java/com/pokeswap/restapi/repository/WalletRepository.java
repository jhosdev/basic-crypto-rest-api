package com.pokeswap.restapi.repository;

import com.pokeswap.restapi.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
        boolean existsByUserId(Long userId);

        Wallet findByUserId(Long userId);
}
