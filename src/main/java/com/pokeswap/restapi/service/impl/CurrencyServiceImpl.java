package com.pokeswap.restapi.service.impl;

import com.pokeswap.restapi.model.Currency;
import com.pokeswap.restapi.repository.CurrencyRepository;
import com.pokeswap.restapi.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl (CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency createCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency getCurrencyById(Long id) {
        return currencyRepository.findById(id).orElse(null);
    }

    @Override
    public Currency updateCurrency(Long id, Currency currency) {
        Currency currencyToUpdate = currencyRepository.findById(id).orElse(null);
        if (currencyToUpdate != null) {
            currencyToUpdate.setName(currency.getName());
            currencyToUpdate.setWaitTime(currency.getWaitTime());
            currencyToUpdate.setCommission(currency.getCommission());
            currencyToUpdate.setMinimumDeposit(currency.getMinimumDeposit());
            currencyToUpdate.setImageUrl(currency.getImageUrl());
            return currencyRepository.save(currencyToUpdate);
        }
        return null;
    }

    @Override
    public Currency deleteCurrency(Long id) {
        Currency currency = currencyRepository.findById(id).orElse(null);
        currencyRepository.deleteById(id);
        return currency;
    }

    @Override
    public boolean existsByName(String symbol) {
        return currencyRepository.existsByName(symbol);
    }

    @Override
    public Currency getCurrencyByName(String symbol) {
        return currencyRepository.findByName(symbol);
    }

}