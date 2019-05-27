package com.mmolegion.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mmolegion.core.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class Token {

    private static final Logger logger = LogManager.getLogger(Token.class);
    private static final String ISSUER = "MMOLegion";
    private static final int EXPIRE_DUR = 7;

    private static final Algorithm algorithm = Algorithm.HMAC256(System.getProperty("jwt.secret"));
    private static final JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(ISSUER)
            .build();

    public static String createToken(User user) {
        try {
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withExpiresAt(Time.addDays(EXPIRE_DUR))
                    .withIssuedAt(new Date())
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
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }
    }

}
