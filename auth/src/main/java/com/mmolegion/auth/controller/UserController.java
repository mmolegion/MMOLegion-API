package com.mmolegion.auth.controller;

import com.mmolegion.core.model.*;
import com.mmolegion.core.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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

        if(users.size() > 0) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/api/v1/users")
    public ResponseEntity<?> createUser(HttpServletRequest request) {

        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setCreatedByUser(user);
        user.setModifiedByUser(user);

        Password password = new Password();
        password.setPasswordHash(request.getParameter("password"));
        password.setPasswordSalt(request.getParameter("password"));
        password.setPasswordQuestion(request.getParameter("passwordQuestion"));
        password.setPasswordAnswer(request.getParameter("passwordAnswer"));
        password.setUser(user);
        password.setPasswordUserCreatedByUser(user);
        password.setPasswordUserModifiedByUser(user);

        UserPrefix prefix = new UserPrefix();
        prefix.setPrefix(UUID.randomUUID().toString().substring(0, 8));
        prefix.setUser(user);
        prefix.setPrefixUserCreatedByUser(user);
        prefix.setPrefixUserModifiedByUser(user);

        user.setPassword(password);
        user.setUserPrefix(prefix);
        userService.createUser(user);

        return null;
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
