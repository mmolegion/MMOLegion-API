package com.mmolegion.auth.controller;

import com.mmolegion.core.model.User;
import com.mmolegion.core.service.UserService;
import com.mmolegion.core.util.Crypto;
import com.mmolegion.core.util.Request;
import com.mmolegion.core.util.Response;
import com.mmolegion.core.util.Token;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/user")
    public ResponseEntity<?> createUser(HttpServletRequest request) {
        logger.debug("Attempting to create a user.");

        Map<String, String> params = Request.getParams(request);
        Map<String, String> response = new HashMap<>();

        try {
            String username = params.get("username");
            String email = params.get("email");
            String password = params.get("password");

            User user = userService.createUser(username, email, password);

            logger.debug("User " + username + " has been created successfully. Generating token.");
            return Token.generateTokenAndReturnResponse(user, response);
        } catch (NullPointerException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.debug("There was a missing parameter. Returning Bad Request 400 status.");
        }

        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/v1/user")
    public ResponseEntity<?> readUser(HttpServletRequest request) {

        Map<String, String> response = new HashMap<>();

        if (Request.isAuthorizationValid(request)) {
            try {
                User user = userService.findUser(Token.getSubject(Request.getToken(request)));

                if (user != null) {
                    logger.debug("User found. Setting response and returning OK 200 status.");
                    response.put("username", user.getUsername());
                    response.put("email", user.getEmail());
                    return new ResponseEntity<>(Response.createResponse(response, HttpStatus.OK), HttpStatus.OK);
                } else {
                    logger.debug("Could not find user, returning Bad Request 400 status.");
                    response.put("valid", "false");
                    return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
                }
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/v1/user")
    public ResponseEntity<?> updateUser(HttpServletRequest request, @RequestBody User rUser) {

        Map<String, String> response = new HashMap<>();

        if (Request.isAuthorizationValid(request)) {
            try {
                User user = userService.findUser(Token.getSubject(Request.getToken(request)));

                if (user != null) {
                    logger.debug("User found. Assigning new values.");

                    rUser.setUsername(null);

                    if(rUser.getPassword() != null) {
                        Map<String, String> hashed = Crypto.generateHashedPassword(rUser.getPassword());
                        rUser.setPasswordSalt(hashed.get("salt"));
                        rUser.setPasswordHash(hashed.get("hash"));
                    }

                    Request.copyProperties(rUser, user);
                    if(userService.updateUser(user) > 0) {
                        logger.debug("Update was successful. Returning updated token and OK 200 status.");
                        return Token.generateTokenAndReturnResponse(user, response);
                    }

                    logger.debug("Update was not successful. Returning Bad Request 400 status.");
                } else {
                    logger.debug("Could not find user, returning Bad Request 400 status.");
                    response.put("valid", "false");
                    return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
                }
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/api/v1/user")
    public ResponseEntity<?> deleteUser(HttpServletRequest request) {

        Map<String, String> response = new HashMap<>();

        if (Request.isAuthorizationValid(request)) {
            String username = Token.getSubject(Request.getToken(request));

            try {
                User user = userService.findUser(username);

                if(user != null) {
                    if(userService.deleteUser(user) > 0) {
                        logger.debug("Delete was successful. Returning OK 200 status.");
                        response.put("task", "successful");
                        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.OK), HttpStatus.OK);
                    }
                } else {
                    logger.debug("Could not find user, returning Bad Request 400 status.");
                    response.put("valid", "false");
                    return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
                }
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

}
