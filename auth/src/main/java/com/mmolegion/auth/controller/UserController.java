package com.mmolegion.auth.controller;

import com.mmolegion.core.config.Email;
import com.mmolegion.core.constants.TokenType;
import com.mmolegion.core.exception.DatabaseExceptionHandler;
import com.mmolegion.core.exception.ExceptionHandler;
import com.mmolegion.core.exception.UserExceptionHandler;
import com.mmolegion.core.exception.database.UnsuccessfulDeleteException;
import com.mmolegion.core.exception.database.UnsuccessfulUpdateException;
import com.mmolegion.core.exception.user.UserExistsException;
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

            if (username == null || email == null || password == null) {
                throw new IllegalArgumentException("Username, email, and/or password are missing.");
            }

            if (userService.userExists(username) || userService.emailExists(email)) {
                throw new UserExistsException("Username and/or email already exist.");
            }

            User user = userService.createUser(username, email, password);

            if(user != null) {
                logger.debug("User " + username + " has been created successfully. Generating token.");
                return Token.generateTokenAndReturnResponse(user, response);
            }
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            return UserExceptionHandler.handleJWTException(e);
        } catch (IllegalArgumentException e) {
            return ExceptionHandler.handleIllegalArgumentException(e);
        } catch (UserExistsException e) {
            return UserExceptionHandler.handleUserExistsException(e);
        }

        response.put("message", "There was an error in creating your account, please try again later.");
        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.SERVICE_UNAVAILABLE), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/api/v1/user")
    public ResponseEntity<?> readUser(HttpServletRequest request) {

        Map<String, String> response = new HashMap<>();

        try {
            if (Request.isAuthorizationValid(request)) {

                User user = Token.getUserFromToken(request, userService);

                if (user != null) {
                    logger.debug("User found. Setting response and returning OK 200 status.");
                    response.put("username", user.getUsername());
                    response.put("email", user.getEmail());
                    return new ResponseEntity<>(Response.createResponse(response, HttpStatus.OK), HttpStatus.OK);
                } else {
                    throw new UserNotExistsException("Username is incorrect or user does not exist.");
                }
            }
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            return UserExceptionHandler.handleJWTException(e);
        } catch (UserNotExistsException e) {
            return UserExceptionHandler.handleUserNotExistsException(e);
        }

        response.put("message", "The token provided is not valid.");
        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/v1/user")
    public ResponseEntity<?> updateUser(HttpServletRequest request, @RequestBody User rUser) {

        Map<String, String> response = new HashMap<>();

        try {
            if (Request.isAuthorizationValid(request)) {

                User user = Token.getUserFromToken(request, userService);

                if (user != null) {
                    logger.debug("User found. Assigning new values.");

                    rUser.setUsername(null);

                    if (rUser.getEmail() != null && userService.emailExists(rUser.getEmail())) {
                        rUser.setEmail(null);
                    }

                    if (rUser.getPassword() != null) {
                        Map<String, String> hashed = Crypto.generateHashedPassword(rUser.getPassword());
                        rUser.setPasswordSalt(hashed.get("salt"));
                        rUser.setPasswordHash(hashed.get("hash"));
                    }

                    Request.copyProperties(rUser, user);
                    return updateUser(user, response);
                } else {
                    throw new UserNotExistsException("Username is incorrect or user does not exist.");
                }
            }
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            return UserExceptionHandler.handleJWTException(e);
        } catch (UserNotExistsException e) {
            return UserExceptionHandler.handleUserNotExistsException(e);
        } catch (UnsuccessfulUpdateException e) {
            return DatabaseExceptionHandler.handleUnsuccessfulUpdateException(e);
        }

        response.put("message", "The token provided is not valid.");
        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/api/v1/user")
    public ResponseEntity<?> deleteUser(HttpServletRequest request) {

        Map<String, String> response = new HashMap<>();

        try {
            if (Request.isAuthorizationValid(request)) {

                User user = Token.getUserFromToken(request, userService);

                if (user != null) {
                    if (userService.deleteUser(user) > 0) {
                        logger.debug("Delete was successful. Returning OK 200 status.");
                        response.put("task", "successful");
                        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.OK), HttpStatus.OK);
                    } else {
                        throw new UnsuccessfulDeleteException("Delete query was not successful for user " + user.getUsername());
                    }
                } else {
                    throw new UserNotExistsException("Username is incorrect or user does not exist.");
                }
            }
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            return UserExceptionHandler.handleJWTException(e);
        } catch (UserNotExistsException e) {
            return UserExceptionHandler.handleUserNotExistsException(e);
        } catch (UnsuccessfulDeleteException e) {
            return DatabaseExceptionHandler.handleUnsuccessfulDeleteException(e);
        }

        response.put("message", "The token provided is not valid.");
        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/v1/user/send-password-email")
    public ResponseEntity<?> sendPasswordResetEmail(HttpServletRequest request) {

        Map<String, String> params = Request.getParams(request);
        Map<String, String> response = new HashMap<>();

        try {
            String username = params.get("username");
            String email = params.get("email");

            if (username == null && email == null) {
                throw new IllegalArgumentException("Missing username or email parameters.");
            }

            User user;

            if (username != null && userService.userExists(username)) {
                user = userService.findUser(username);
            } else if (email != null && userService.emailExists(email)) {
                user = userService.findEmail(email);
            } else {
                throw new UserNotExistsException("Username or email provided do not exist.");
            }

            if (user != null) {
                logger.debug("Temporary password created successfully. Sending email and returning 200 OK status.");
                Map<String, String> emailMap = new HashMap<>();
                emailMap.put("to", user.getEmail());
                emailMap.put("subject", "Your MMOLegion password has been reset.");

                String url = "https://" + request.getServerName() + "/reset-password/" + Token.createPasswordResetToken(user) + "/";
                logger.debug("Password reset URL: " + url);

                String message = "Hi " + user.getUsername() + ",<br><br>";
                message += "A password reset has been requested for your account.<br><br>";
                message += "Please click on the following link to change your password.<br>";
                message += "<a href=\"" + url + "\">Click here to reset your password.</a><br><br>";
                message += "Please note that the link will expire after 24 hours.";

                emailMap.put("message", message);
                Email.sendMessage(emailMap);

                response.put("message", "A password request email was sent to the email address on file.");
                response.put("valid", "true");
                return new ResponseEntity<>(Response.createResponse(response, HttpStatus.OK), HttpStatus.OK);
            } else {
                throw new UserNotExistsException("Username is incorrect or user does not exist.");
            }

        } catch (IllegalArgumentException e) {
            return ExceptionHandler.handleIllegalArgumentException(e);
        } catch (UserNotExistsException e) {
            return UserExceptionHandler.handleUserNotExistsException(e);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            return UserExceptionHandler.handleJWTException(e);
        }
    }

    @PostMapping("/api/v1/user/reset-password")
    public ResponseEntity<?> resetPassword(HttpServletRequest request) {

        Map<String, String> params = Request.getParams(request);
        Map<String, String> response = new HashMap<>();

        try {
            if (Request.isAuthorizationValid(request)) {

                if (params.get("password") == null || params.get("password").isEmpty()) {
                    throw new IllegalArgumentException("Password parameter is missing.");
                }

                if (Token.getTokenType(request) != TokenType.PASSWORDRESET) {
                    throw new InvalidKeySpecException();
                }

                User user = Token.getUserFromToken(request, userService);

                if (user != null) {
                    Map<String, String> hashed = Crypto.generateHashedPassword(params.get("password"));
                    user.setPasswordSalt(hashed.get("salt"));
                    user.setPasswordHash(hashed.get("hash"));

                    return updateUser(user, response);
                } else {
                    throw new UserNotExistsException("Username is incorrect or user does not exist.");
                }
            }
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            return UserExceptionHandler.handleJWTException(e);
        } catch (UserNotExistsException e) {
            return UserExceptionHandler.handleUserNotExistsException(e);
        } catch (IllegalArgumentException e) {
            return ExceptionHandler.handleIllegalArgumentException(e);
        } catch (UnsuccessfulUpdateException e) {
            return DatabaseExceptionHandler.handleUnsuccessfulUpdateException(e);
        }

        response.put("message", "The token provided is not valid.");
        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> updateUser(User user, Map<String, String> response) throws UnsuccessfulUpdateException {
        if (userService.updateUser(user) > 0) {
            logger.debug("Update was successful. Returning updated token and OK 200 status.");
            return Token.generateTokenAndReturnResponse(user, response);
        } else {
            throw new UnsuccessfulUpdateException("Update query was not successful for user: " + user.getUsername());
        }
    }

}
