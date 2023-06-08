package com.pokeswap.restapi.repository;

import com.pokeswap.restapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

        boolean existsByEmail(String email);

        User findByEmail(String email);
}
