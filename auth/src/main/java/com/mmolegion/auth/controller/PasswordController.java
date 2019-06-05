package com.mmolegion.auth.controller;

import com.mmolegion.core.model.Password;
import com.mmolegion.core.service.PasswordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PasswordController {

    private static final Logger logger = LogManager.getLogger(PasswordController.class);
    private final PasswordService passwordService;

    @Autowired
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping("/api/v1/users/password")
    public ResponseEntity<?> resetPassword(HttpServletRequest request) {

        return null;
    }

    @PutMapping("/api/v1/users/password")
    public ResponseEntity<?> changePassword(HttpServletRequest request, @RequestBody Password requestBody) {


        return null;
    }

}
