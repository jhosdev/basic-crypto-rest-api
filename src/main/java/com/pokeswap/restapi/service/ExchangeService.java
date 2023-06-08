package com.pokeswap.restapi.service;

import com.pokeswap.restapi.model.Exchange;

import java.util.List;

public interface ExchangeService {

    Exchange createExchange(Exchange exchange);

    List<Exchange> getAllExchanges();

    Exchange getExchangeById(Long id);

    Exchange updateExchangeById(Long id, Exchange exchange);

    Exchange deleteExchangeById(Long id);
}