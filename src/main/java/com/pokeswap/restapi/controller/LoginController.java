package com.pokeswap.restapi.controller;

import com.pokeswap.restapi.dto.UserCredentialsDTO;
import com.pokeswap.restapi.exception.ValidationException;
import com.pokeswap.restapi.model.User;
import com.pokeswap.restapi.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pokeswap/v1")
public class LoginController {
    private final UserService userService;

    private void validateUser (UserCredentialsDTO user, User userFound) {
        if (userFound == null) {
            throw new ValidationException("El usuario no existe");
        }
        if (!userFound.getPassword().equals(user.getPassword())) {
            throw new ValidationException("La contraseña es incorrecta");
        }
    }

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    //URL: http://localhost:8080/api/pokeswap/v1/auth/login
    //Method: POST
    @Transactional
    @PostMapping("/auth/login")
    public ResponseEntity<User> createUser(@RequestBody UserCredentialsDTO user) {
        User userFound = userService.findByEmail(user.getEmail());
        validateUser(user,userFound);
        return new ResponseEntity<>(userFound, HttpStatus.CREATED);
    }

}
