package com.mmolegion.api.controller;

import com.auth0.jwt.JWT;
import com.mmolegion.core.config.AppProperties;
import com.mmolegion.core.model.*;
import com.mmolegion.core.service.UserService;
import com.mmolegion.core.util.PasswordHasher;
import com.mmolegion.core.util.Time;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/api/v1/users")
    public ResponseEntity<?> findAllUsers(HttpServletRequest request) {
        logger.debug("Finding all users.");

        List<User> users = userService.findAllUsers();

        if (users.size() > 0) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/api/v1/users")
    public ResponseEntity<?> createUser(HttpServletRequest request) {
        logger.debug("Creating user.");

        String random = UUID.randomUUID().toString().replaceAll("\\d", "").replace("-", "").substring(0,6);
        try {
            User user = new User();
            user.setUsername(random);
            user.setEmail(random);
//            user.setUsername(request.getParameter("username"));
//            user.setEmail(request.getParameter("email"));
            user.setCreatedByUser(user);
            user.setModifiedByUser(user);

            Password password = new Password();
            password.setUser(user);
            password.setPassword(request.getParameter("password"));
            password.setPasswordUserCreatedByUser(user);
            password.setPasswordUserModifiedByUser(user);

            Map<String, String> hashed = PasswordHasher.generateHashedPassword(password.getPassword());
            password.setPasswordSalt(hashed.get("salt"));
            password.setPasswordHash(hashed.get("hash"));

            user.setPassword(password);
            user = userService.createUser(user);

            if (user.getUserId() > 0) {
                return createUserToken(user);
            }
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NullPointerException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/v1/users")
    public ResponseEntity<?> updateAllUsers(HttpServletRequest request, @RequestBody User requestBody) {
        // would I use this for anything?
        return null;
    }

    @DeleteMapping("/api/v1/users")
    public ResponseEntity<?> deleteAllUsers(HttpServletRequest request) {
        logger.debug("Deleting all users.");
        userService.deleteAllUsers();

        if(userService.findAllUsers().size() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/api/v1/users/{userId}")
    public ResponseEntity<?> findUserById(HttpServletRequest request, @PathVariable int userId) {
        User user = userService.findUser(userId);

        if(user != null) {
            try {
                return createUserToken(user);
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/v1/users/{userId}")
    public ResponseEntity<?> updateUserById(HttpServletRequest request, @PathVariable String userId, @RequestBody User requestBody) {
        // what would i update?
        return null;
    }

    @DeleteMapping("/api/v1/users/{userId}")
    public ResponseEntity<?> deleteUserById(HttpServletRequest request, @PathVariable int userId) {
        userService.deleteUser(userId);

        if(userService.findUser(userId) == null)
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<?> createUserToken(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String token = Token.create(
                JWT.create()
                        .withIssuer(AppProperties.getProperty("jwt.issuer"))
                        .withExpiresAt(Time.addDays(7))
                        .withIssuedAt(new Date())
                        .withSubject(String.valueOf(user.getUserId()))
                        .withClaim("username", user.getUsername())
                        .withClaim("email", user.getEmail())
                        .withClaim("createdDate", user.getCreatedDate())
                        .withClaim("modifiedDate", user.getModifiedDate())
                        .withClaim("active", user.isActive())
        );

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
