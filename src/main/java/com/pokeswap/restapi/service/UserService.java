package com.pokeswap.restapi.service;

import com.pokeswap.restapi.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(Long id, User user);

    User deleteUser(Long id);

    boolean existsByEmail(String email);

    User findByEmail(String email);
}