package com.mmolegion.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mmolegion.core.config.AppProperties;

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public class Token {

    private static String publicKeyStr = AppProperties.getProperty("jwt.publicKey");
    private static String privateKeyStr = AppProperties.getProperty("jwt.privateKey");

    public static String create(JWTCreator.Builder builder) throws InvalidKeySpecException, NoSuchAlgorithmException, JWTCreationException {
        RSAPublicKey publicKey = RSA.getPublicKey(publicKeyStr);
        RSAPrivateKey privateKey = RSA.getPrivateKey(privateKeyStr);

        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);

        return builder.sign(algorithm);
    }

    public static boolean verify(String token, String pub) throws InvalidKeySpecException, NoSuchAlgorithmException {
        RSAPublicKey publicKey = RSA.getPublicKey(pub);
        RSAPrivateKey privateKey = RSA.getPrivateKey(privateKeyStr);

        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        try {
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }

        return true;
    }


}
