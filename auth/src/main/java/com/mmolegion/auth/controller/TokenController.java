package com.mmolegion.auth.controller;

import com.mmolegion.core.exception.UserExceptionHandler;
import com.mmolegion.core.exception.user.AccountLockedException;
import com.mmolegion.core.exception.user.UserNotExistsException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class TokenController {

    private static final Logger logger = LogManager.getLogger(TokenController.class);
    private final UserService userService;

    @Autowired
    public TokenController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/token/generate-token")
    public ResponseEntity<?> generateToken(HttpServletRequest request) {
        logger.debug("Attempting to generate a token.");

        Map<String, String> params = Request.getParams(request);
        Map<String, String> response = new HashMap<>();

        if (params.get("username") != null && params.get("password") != null) {
            logger.debug("Username and password parameters are not null, checking if valid.");

            try {
                User user = userService.findUser(params.get("username"));

                if (user != null) {
                    if (checkLockoutStatus(user)) {
                        logger.debug("User is locked out until " + user.getLockoutUntil() + ". Returning Unauthorized 400 status.");
                        throw new AccountLockedException("User is locked out until " + new Date(user.getLockoutUntil()).toString(), user);
                    } else if (Crypto.verifyPassword(params.get("password"),
                            user.getPasswordSalt(),
                            user.getPasswordHash())) {
                        userService.clearUserLockout(user);
                        logger.debug("Username and password are valid. Generating token.");
                        return Token.generateTokenAndReturnResponse(user, response);
                    } else {
                        logger.debug("Username and/or password were not valid. Returning Unauthorized 401 status.");
                        userService.incrementFailedAttempts(user);

                        response.put("message", "The password provided is incorrect. Please try again.");
                        response.put("failedAttempts", String.valueOf(Objects.requireNonNull(user).getFailedAttempts()));
                        response.put("valid", "false");
                        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
                    }
                } else {
                    throw new UserNotExistsException("Username is incorrect or user does not exist.");
                }
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                return UserExceptionHandler.handleJWTException(e);
            } catch (AccountLockedException e) {
                return UserExceptionHandler.handleAccountLockedException(e);
            } catch (UserNotExistsException e) {
                return UserExceptionHandler.handleUserNotExistsException(e);
            }
        }

        response.put("message", "The username and/or password were not provided. Please try again.");
        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/v1/token/verify-token")
    public ResponseEntity<?> verifyToken(HttpServletRequest request) {
        logger.debug("Attempting to verify a token.");

        Map<String, String> headers = Request.getHeaders(request);
        Map<String, String> response = new HashMap<>();

        if (headers.get("Authorization") != null && !headers.get("Authorization").isEmpty()) {
            logger.debug("Authorization header is present. Verifying token: " + headers.get("Authorization"));

            try {
                if (Token.verifyToken(headers.get("Authorization"))) {
                    logger.debug("Token is valid. Returning OK 200 status.");
                    response.put("valid", "true");
                    return new ResponseEntity<>(Response.createResponse(response, HttpStatus.OK), HttpStatus.OK);
                } else {
                    logger.debug("Token is not valid. Returning Unauthorized 401 status.");
                    response.put("message", "The token provided is not valid.");
                    response.put("valid", "false");
                    return new ResponseEntity<>(Response.createResponse(response, HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
                }
            } catch (UserNotExistsException e) {
                UserExceptionHandler.handleUserNotExistsException(e);
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                UserExceptionHandler.handleJWTException(e);
            }
        }

        logger.debug("Authorization header not provided. Returning Bad Request 400 status.");
        response.put("message", "The authorization header was not provided. Please try again.");
        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    private boolean checkLockoutStatus(User user) {
        if (user.getLockoutUntil() > 0 && new Date().before(new Date(user.getLockoutUntil()))) {
            return true;
        } else if (user.getLockoutUntil() > 0) {
            userService.clearUserLockout(user);
        }
        return false;
    }

}
