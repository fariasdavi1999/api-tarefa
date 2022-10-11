package com.backendcrudangular.backendcrudangular.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendcrudangular.backendcrudangular.model.User;
import com.backendcrudangular.backendcrudangular.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/listar-users")
    public ResponseEntity<List<User>> listarTodos() {

        return ResponseEntity.ok(userRepository.findAll());

    }

}
