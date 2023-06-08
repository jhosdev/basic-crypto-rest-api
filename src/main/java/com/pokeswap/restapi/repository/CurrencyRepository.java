package com.pokeswap.restapi.repository;

import com.pokeswap.restapi.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

        boolean existsByName(String currencyName);

        Currency findByName(String currencyName);
}
