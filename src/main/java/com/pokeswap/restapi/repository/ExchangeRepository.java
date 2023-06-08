package com.pokeswap.restapi.repository;

import com.pokeswap.restapi.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

}
