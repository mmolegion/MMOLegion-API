package com.mmolegion.core.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Crypto {

    private static SecureRandom random = new SecureRandom();

    public static Map<String, String> generateHashedPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = new byte[32];
        random.nextBytes(salt);
        String base64Salt = Base64.encodeBase64String(salt);
        System.out.println(base64Salt);

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();
        String base64Hash = Base64.encodeBase64String(hash);

        Map<String,String> hashedPasswordMap = new HashMap<>();
        hashedPasswordMap.put("salt", base64Salt);
        hashedPasswordMap.put("hash", base64Hash);

        return hashedPasswordMap;
    }

    public static boolean verifyPassword(String password, String salt, String hashedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), Base64.decodeBase64(salt), 65536, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        return Arrays.equals(factory.generateSecret(spec).getEncoded(), Base64.decodeBase64(hashedPassword));
    }
}
