package com.mmolegion.web.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmolegion.core.exception.ExceptionHandler;
import com.mmolegion.core.exception.UserExceptionHandler;
import com.mmolegion.core.exception.user.UserNotExistsException;
import com.mmolegion.core.model.User;
import com.mmolegion.core.service.UserService;
import com.mmolegion.core.util.Request;
import com.mmolegion.core.util.Response;
import com.mmolegion.core.util.Token;
import com.mmolegion.core.util.URL;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ResetPasswordController {

    private static final Logger logger = LogManager.getLogger(ResetPasswordController.class);

    @GetMapping("/reset-password/{token}/")
    public ModelAndView getResetPasswordPage(@PathVariable(value = "token") String token) {
        logger.debug("Retrieving reset password page.");

        Map<String, String> params = new HashMap<>();

        try {
            if (Token.verifyToken(token)) {
                params.put("valid", "true");
                params.put("token", token);
            } else {
                params.put("valid", "false");
            }
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | UserNotExistsException e) {
            e.printStackTrace();
            return new ModelAndView("error");
        }

        return new ModelAndView("reset-password", "params", params);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(HttpServletRequest request) {
        logger.debug("Attempting to reset password.");

        Map<String, String> params = Request.getParams(request);
        Map<String, String> response = new HashMap<>();

        try {
            if (Request.isAuthorizationValid(request)) {
                if (params.get("password") != null && !params.get("password").isEmpty()) {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody formBody = new okhttp3.FormBody.Builder()
                            .add("password", params.get("password"))
                            .build();
                    okhttp3.Request req = new okhttp3.Request.Builder()
                            .url(URL.getUrlFromRequest(request, "/auth/api/v1/user/reset-password"))
                            .addHeader("Authorization", Request.getToken(request))
                            .post(formBody)
                            .build();

                    okhttp3.Response res = client.newCall(req).execute();
                    if (res.body() != null && res.code() == HttpStatus.OK.value()) {
                        JsonNode body = new ObjectMapper().readTree(res.body().string());
                        response.put("token", body.get("response").get("token").asText());
                        response.put("valid", "true");
                        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.OK), HttpStatus.OK);
                    } else {
                        throw new IOException();
                    }
                } else {
                    throw new IllegalArgumentException("Missing password parameter.");
                }
            }
        } catch (IllegalArgumentException e) {
            return ExceptionHandler.handleIllegalArgumentException(e);
        } catch (IOException e) {
            return ExceptionHandler.handleIOException(e);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            return UserExceptionHandler.handleJWTException(e);
        } catch (UserNotExistsException e) {
            return UserExceptionHandler.handleUserNotExistsException(e);
        }

        response.put("message", "The token provided is not valid.");
        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}
