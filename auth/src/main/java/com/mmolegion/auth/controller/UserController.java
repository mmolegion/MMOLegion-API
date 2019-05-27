package com.mmolegion.auth.controller;

import com.mmolegion.core.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/user")
    public ResponseEntity<String> createUser(HttpServletRequest request) {

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user")
    public ResponseEntity<String> readUser(HttpServletRequest request) {

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/user")
    public ResponseEntity<String> updateUser(HttpServletRequest request) {

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(HttpServletRequest request) {

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
