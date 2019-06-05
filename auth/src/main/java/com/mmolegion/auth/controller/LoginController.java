package com.mmolegion.auth.controller;

import com.mmolegion.core.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/api/v1/auth/login")
    public ResponseEntity<?> findAllLoginAttempts(HttpServletRequest request) {


        return null;
    }

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<?> login(HttpServletRequest request) {


        return null;
    }

    @GetMapping("/api/v1/auth/login{query}")
    public ResponseEntity<?> findAllLoginAttemptsWithQuery(HttpServletRequest request, @PathVariable String query) {


        return null;
    }

    @PostMapping("/api/v1/auth/verify")
    public ResponseEntity<?> verifyToken(HttpServletRequest request) {


        return null;
    }
}
