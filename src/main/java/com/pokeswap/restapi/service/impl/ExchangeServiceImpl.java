package com.pokeswap.restapi.service.impl;

import com.pokeswap.restapi.model.Exchange;
import com.pokeswap.restapi.repository.ExchangeRepository;
import com.pokeswap.restapi.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeRepository exchangeRepository;

    @Autowired
    public ExchangeServiceImpl (ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    @Override
    public Exchange createExchange(Exchange exchange) {
        return exchangeRepository.save(exchange);
    }

    @Override
    public List<Exchange> getAllExchanges() {
        return exchangeRepository.findAll();
    }

    @Override
    public Exchange getExchangeById(Long id) {
        return exchangeRepository.findById(id).orElse(null);
    }

    @Override
    public Exchange updateExchangeById(Long id, Exchange exchange) {
        Exchange exchangeToUpdate = exchangeRepository.findById(id).orElse(null);
        if (exchangeToUpdate != null) {
            exchangeToUpdate.setStatus(exchange.getStatus());
            return exchangeRepository.save(exchangeToUpdate);
        }
        return null;
    }

    @Override
    public Exchange deleteExchangeById(Long id) {
        Exchange exchange = exchangeRepository.findById(id).orElse(null);
        exchangeRepository.deleteById(id);
        return exchange;
    }

}