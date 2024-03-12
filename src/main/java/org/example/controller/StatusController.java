package org.example.controller;

import org.apache.commons.lang3.StringUtils;
import org.example.models.UserModel;
import org.example.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class StatusController {
    @GetMapping(value = "/api/v1/users/{login}")
    public ResponseEntity<?> getUserByLogin(@PathVariable String login) {
        UserRepository repository = new UserRepository();
        try {
            UserModel user = repository.getUserByLogin(login);
            return ResponseEntity.ok(user);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not found");
        }
    }

    @PostMapping("/api/v1/users")
    public ResponseEntity<?> createUser(@RequestBody UserModel user) {
        if (user == null || StringUtils.isBlank(user.getLogin()) ||
                StringUtils.isBlank(user.getPassword()) || StringUtils.isBlank(user.getEmail()) ||
        user.getDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data");
        }
        UserRepository repository = new UserRepository();
        try {
            int rows = repository.createUser(user);
            return ResponseEntity.ok(rows);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }
    }
}



