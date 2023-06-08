package com.pokeswap.restapi.repository;

import com.pokeswap.restapi.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

            boolean existsBySymbol(String tokenName);

            Token findBySymbol(String tokenName);
}
