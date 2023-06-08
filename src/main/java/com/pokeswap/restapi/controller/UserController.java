package com.pokeswap.restapi.controller;

import com.pokeswap.restapi.exception.ValidationException;
import com.pokeswap.restapi.model.User;
import com.pokeswap.restapi.model.Transaction;
import com.pokeswap.restapi.service.UserService;
import com.pokeswap.restapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokeswap/v1")
public class UserController {
    private final UserService userService;

    private final TransactionService transactionService;

    private void validateUser(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new ValidationException("Name is required");
        }
        if (user.getName().length()>30) {
            throw new ValidationException("Name must not exceed 30 characters");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        if (user.getEmail().length()>30) {
            throw new ValidationException("Email must not exceed 30 characters");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (user.getPassword().length()>30) {
            throw new ValidationException("Password must not exceed 30 characters");
        }
    }

    private void validateUserExistence(User user) {
        if (userService.existsByEmail(user.getEmail())) {
            throw new ValidationException("User already exists");
        }
    }

    @Autowired
    public UserController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    //URL: http://localhost:8080/api/pokeswap/v1/users
    //Method: POST
    @Transactional
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        validateUser(user);
        validateUserExistence(user);
        User userCreated = userService.createUser(user);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/users
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/users/1
    //Method: GET
    @Transactional
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/users/1
    //Method: PUT
    @Transactional
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @RequestBody User user) {
        validateUser(user);
        User userUpdated = userService.updateUser(id, user);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/users/1
    //Method: DELETE
    @Transactional
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long id) {
        User userDeleted = userService.deleteUser(id);
        return new ResponseEntity<>(userDeleted, HttpStatus.OK);
    }
}