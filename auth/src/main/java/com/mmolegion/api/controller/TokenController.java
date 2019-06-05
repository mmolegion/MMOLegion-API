package com.mmolegion.api.controller;

import com.mmolegion.core.util.Token;
import com.mmolegion.core.util.RSA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TokenController {

    private static final Logger logger = LogManager.getLogger(TokenController.class);

    @PostMapping("/api/v1/token/create")
    public ResponseEntity<?> createToken(HttpServletRequest request) {


        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/api/v1/token/verify")
    public ResponseEntity<?> verifyToken(HttpServletRequest request) {
        try {
            String[] auth = request.getHeader("Authorization").split(" ");
            String token = auth[0];
            String publicKey = auth[1];

            if(token != null && publicKey != null && Token.verify(token, publicKey)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/v1/token/keypair")
    public ResponseEntity<?> generateKeypair(HttpServletRequest request) {

        KeyPair keyPair = null;
        try {
            keyPair = RSA.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if(keyPair != null) {
            Map<String, byte[]> map = new HashMap<>();
            map.put("prv", keyPair.getPrivate().getEncoded());
            map.put("pub", keyPair.getPublic().getEncoded());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
