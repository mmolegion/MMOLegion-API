package com.mmolegion.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mmolegion.core.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class Token {

    private static final Logger logger = LogManager.getLogger(Token.class);
    private static final String ISSUER = "MMOLegion";
    private static final int EXPIRE_DUR = 7;

    private static final Algorithm algorithm = Algorithm.HMAC256(System.getProperty("jwt.secret"));
    private static final JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(ISSUER)
            .build();

    public static ResponseEntity<?> generateTokenAndReturnResponse(User user, Map<String,String> response) {
        String token = Token.createToken(user);

        if(token != null && token.length() > 0) {
            logger.debug("Token generated successfully.");
            logger.debug("Token: " + token);
            logger.debug("Returning OK 200 status.");

            response.put("token", token);
            return new ResponseEntity<>(Response.createResponse(response, HttpStatus.OK), HttpStatus.OK);
        } else {
            response.put("error", "There was an issue in generating a token.");
            return new ResponseEntity<>(Response.createResponse(response, HttpStatus.SERVICE_UNAVAILABLE), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    public static String createToken(User user) {
        try {
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withExpiresAt(Time.addDays(EXPIRE_DUR))
                    .withIssuedAt(new Date())
                    .withClaim("isAdmin", user.isAdmin())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verifyToken(String token) {
        logger.debug("Verifying JWT is valid");
        try {
            verifier.verify(token);
            logger.debug("JWT is valid.");
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            logger.debug("JWT is not valid.");
            return false;
        }
    }

    public static String getAlgorithm(String token) {
        logger.debug("Retrieving token algorithm.");
        return Objects.requireNonNull(decodeToken(token)).getAlgorithm();
    }

    public static String getType(String token) {
        logger.debug("Retrieving token type.");
        return Objects.requireNonNull(decodeToken(token)).getType();
    }

    public static String getIssuer(String token) {
        logger.debug("Retrieving token issuer.");
        return Objects.requireNonNull(decodeToken(token)).getIssuer();
    }

    public static String getSubject(String token) {
        logger.debug("Retrieving token subject.");
        return Objects.requireNonNull(decodeToken(token)).getSubject();
    }

    public static List<String> getAudience(String token) {
        logger.debug("Retrieving token audience.");
        return Objects.requireNonNull(decodeToken(token)).getAudience();
    }

    public static Date getExpiresAt(String token) {
        logger.debug("Retrieving token expires at date.");
        return Objects.requireNonNull(decodeToken(token)).getExpiresAt();
    }

    public static Date getIssuedAt(String token) {
        logger.debug("Retrieving token issued at date.");
        return Objects.requireNonNull(decodeToken(token)).getIssuedAt();
    }

    public static String getParameter(String token, String parameter) {
        Map<String, String> map = getParameter(token, new String[] {parameter});
        return map.get(parameter);
    }

    public static Map<String, String> getParameter(String token, String... parameters) {
        logger.debug("Retrieving token parameters.");
        Map<String, Claim> claims = Objects.requireNonNull(decodeToken(token)).getClaims();
        Map<String, String> params = new HashMap<>();

        for(String p : parameters) {
            try {
                params.put(p, claims.get(p).asString());
                logger.debug("Added " + p + " with value " + claims.get(p).asString());
            } catch (NullPointerException e) {
                logger.debug(p + " is not a valid parameter in this token.");
            }
        }

        return params;
    }

    private static DecodedJWT decodeToken(String token) {
        logger.debug("Decoding token.");
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return null;
        }
    }

}
