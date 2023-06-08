package com.pokeswap.restapi.service.impl;

import com.pokeswap.restapi.model.Token;
import com.pokeswap.restapi.repository.TokenRepository;
import com.pokeswap.restapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl (TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token createToken(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public List<Token> getAllTokens() {
        return tokenRepository.findAll();
    }

    @Override
    public Token getTokenById(Long id) {
        return tokenRepository.findById(id).orElse(null);
    }

    @Override
    public Token updateToken(Long id, Token token) {
        Token tokenToUpdate = tokenRepository.findById(id).orElse(null);
        if (tokenToUpdate != null) {
            tokenToUpdate.setName(token.getName());
            tokenToUpdate.setSymbol(token.getSymbol());
            tokenToUpdate.setBalance(token.getBalance());
            tokenToUpdate.setExchangeRate(token.getExchangeRate());
            return tokenRepository.save(tokenToUpdate);
        }
        return null;
    }

    @Override
    public Token deleteToken(Long id) {
        Token tokenToDelete = tokenRepository.findById(id).orElse(null);
        if (tokenToDelete != null) {
            tokenRepository.delete(tokenToDelete);
            return tokenToDelete;
        }
        return null;
    }

    @Override
    public boolean existsBySymbol(String symbol) {
        return tokenRepository.existsBySymbol(symbol);
    }

    @Override
    public Token getTokenBySymbol(String symbol) {
        return tokenRepository.findBySymbol(symbol);
    }

}