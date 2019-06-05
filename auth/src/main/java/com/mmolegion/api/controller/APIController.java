package com.mmolegion.api.controller;

import com.mmolegion.api.config.InitializeAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class APIController {

    private static final Logger logger = LogManager.getLogger(APIController.class);

    @PostMapping("/api/v1/properties")
    public ResponseEntity<?> reloadAPIProperties(HttpServletRequest request) {
        InitializeAPI initializer = new InitializeAPI();
        initializer.reloadProperties();

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
