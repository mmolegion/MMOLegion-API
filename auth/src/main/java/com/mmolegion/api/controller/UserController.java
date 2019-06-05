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

        try {
            User user = new User();
            user.setUsername(request.getParameter("username"));
            user.setEmail(request.getParameter("email"));
            user.setCreatedByUser(user);
            user.setModifiedByUser(user);

            Password password = new Password();
            password.setUser(user);
            password.setPassword(request.getParameter("password"));
            password.setPasswordQuestion(request.getParameter("passwordQuestion"));
            password.setPasswordAnswer(request.getParameter("passwordAnswer"));
            password.setPasswordUserCreatedByUser(user);
            password.setPasswordUserModifiedByUser(user);

            Map<String, String> hashed = PasswordHasher.generateHashedPassword(password.getPassword());
            password.setPasswordSalt(hashed.get("salt"));
            password.setPasswordHash(hashed.get("hash"));

            UserPrefix userPrefix = new UserPrefix();
            userPrefix.setPrefix(UUID.randomUUID().toString().substring(0, 12));
            userPrefix.setUser(user);
            userPrefix.setPrefixUserCreatedByUser(user);
            userPrefix.setPrefixUserModifiedByUser(user);

            user.setPassword(password);
            user.setUserPrefix(userPrefix);
            user = userService.createUser(user);

            if (user.getUserId() > 0) {
                String token = Token.create(
                        JWT.create()
                                .withIssuer(AppProperties.getProperty("jwt.issuer"))
                                .withExpiresAt(Time.addDays(7))
                                .withIssuedAt(new Date())
                                .withSubject(String.valueOf(user.getUserId()))
                                .withClaim("prefix", userPrefix.getPrefix())
                                .withClaim("notes", userPrefix.getNotes())
                                .withClaim("username", user.getUsername())
                                .withClaim("email", user.getEmail())
                                .withClaim("createdDate", user.getCreatedDate())
                                .withClaim("modifiedDate", user.getModifiedDate())
                                .withClaim("active", user.isActive())
                );

                return new ResponseEntity<>(token, HttpStatus.OK);
            }
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NullPointerException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/v1/users")
    public ResponseEntity<?> updateAllUsers(HttpServletRequest request, @RequestBody User requestBody) {


        return null;
    }

    @DeleteMapping("/api/v1/users")
    public ResponseEntity<?> deleteAllUsers(HttpServletRequest request) {


        return null;
    }

    @GetMapping("/api/v1/users{query}")
    public ResponseEntity<?> findAllUsersWithQuery(HttpServletRequest request, @PathVariable String query) {


        return null;
    }

    @PutMapping("/api/v1/users{query}")
    public ResponseEntity<?> updateAllUsersWithQuery(HttpServletRequest request, @PathVariable String query, @RequestBody User requestBody) {


        return null;
    }

    @DeleteMapping("/api/v1/users{query}")
    public ResponseEntity<?> deleteAllUsersWithQuery(HttpServletRequest request, @PathVariable String query) {


        return null;
    }

    @GetMapping("/api/v1/users/{userId}")
    public ResponseEntity<?> findUserById(HttpServletRequest request, @PathVariable String userId) {


        return null;
    }

    @PutMapping("/api/v1/users/{userId}")
    public ResponseEntity<?> updateUserById(HttpServletRequest request, @PathVariable String userId, @RequestBody User requestBody) {


        return null;
    }

    @DeleteMapping("/api/v1/users/{userId}")
    public ResponseEntity<?> deleteUserById(HttpServletRequest request, @PathVariable String userId) {


        return null;
    }

    @GetMapping("/api/v1/users/{userId}/purchases")
    public ResponseEntity<?> findAllPurchasesByUser(HttpServletRequest request, @PathVariable String userId) {


        return null;
    }

    @PutMapping("/api/v1/users/{userId}/purchases")
    public ResponseEntity<?> updateAllPurchasesByUser(HttpServletRequest request, @PathVariable String userId, @RequestBody Purchase requestBody) {


        return null;
    }

    @GetMapping("/api/v1/users/{userId}/purchases{query}")
    public ResponseEntity<?> findAllPurchasesByUserWithQuery(HttpServletRequest request, @PathVariable String userId, @PathVariable String query) {


        return null;
    }

    @PutMapping("/api/v1/users/{userId}/purchases{query}")
    public ResponseEntity<?> updateAllPurchasesByUserWithQuery(HttpServletRequest request, @PathVariable String userId, @PathVariable String query, @RequestBody Purchase requestBody) {


        return null;
    }

    @GetMapping("/api/v1/users/{userId}/purchases/items")
    public ResponseEntity<?> findAllItemsPurchasedByUser(HttpServletRequest request, @PathVariable String userId) {


        return null;
    }

    @PutMapping("/api/v1/users/{userId}/purchases/items")
    public ResponseEntity<?> updateAllItemsPurchasedByUser(HttpServletRequest request, @PathVariable String userId, @RequestBody ItemPurchase requestBody) {


        return null;
    }

    @GetMapping("/api/v1/users/{userId}/purchases/items{query}")
    public ResponseEntity<?> findAllItemsPurchasedByUserWithQuery(HttpServletRequest request, @PathVariable String userId, @PathVariable String query) {


        return null;
    }

    @PutMapping("/api/v1/users/{userId}/purchases/items{query}")
    public ResponseEntity<?> updateAllItemsPurchasedByUserWithQuery(HttpServletRequest request, @PathVariable String userId, @PathVariable String query, @RequestBody ItemPurchase requestBody) {


        return null;
    }

}
