package com.pokeswap.restapi.service;

import com.pokeswap.restapi.model.Token;

import java.util.List;

public interface TokenService {
    Token createToken(Token token);
    List<Token> getAllTokens();

    Token getTokenById(Long id);

    Token updateToken(Long id, Token token);

    Token deleteToken(Long id);

    boolean existsBySymbol(String symbol);

    Token getTokenBySymbol(String symbol);
}