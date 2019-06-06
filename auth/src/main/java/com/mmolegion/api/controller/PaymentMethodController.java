package com.mmolegion.api.controller;

import com.mmolegion.core.model.PaymentMethod;
import com.mmolegion.core.model.Purchase;
import com.mmolegion.core.service.PaymentMethodService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PaymentMethodController {

    private static final Logger logger = LogManager.getLogger(PaymentMethodController.class);
    private final PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping("/api/v1/payment")
    public ResponseEntity<?> findAllPaymentMethods(HttpServletRequest request) {


        return null;
    }

    @PostMapping("/api/v1/payment")
    public ResponseEntity<?> createPaymentMethod(HttpServletRequest request) {


        return null;
    }

    @PutMapping("/api/v1/payment")
    public ResponseEntity<?> updateAllPaymentMethods(HttpServletRequest request, @RequestBody PaymentMethod requestBody) {


        return null;
    }

    @DeleteMapping("/api/v1/payment")
    public ResponseEntity<?> deleteAllPaymentMethods(HttpServletRequest request) {


        return null;
    }

    @GetMapping("/api/v1/payment/{paymentId}")
    public ResponseEntity<?> findPaymentMethodById(HttpServletRequest request, @PathVariable int paymentId) {


        return null;
    }

    @PutMapping("/api/v1/payment/{paymentId}")
    public ResponseEntity<?> updatePaymentMethodById(HttpServletRequest request, @PathVariable int paymentId, @RequestBody PaymentMethod requestBody) {


        return null;
    }

    @DeleteMapping("/api/v1/payment/{paymentId}")
    public ResponseEntity<?> deletePaymentMethodById(HttpServletRequest request, @PathVariable int paymentId) {


        return null;
    }

    @GetMapping("/api/v1/payment/{paymentId}/purchases")
    public ResponseEntity<?> findAllPurchasesMadeUsingPaymentMethod(HttpServletRequest request, @PathVariable int paymentId) {


        return null;
    }

    @PutMapping("/api/v1/payment/{paymentId}/purchases")
    public ResponseEntity<?> updateAllPurchasesMadeUsingPaymentMethod(HttpServletRequest request, @PathVariable int paymentId, @RequestBody Purchase requestBody) {


        return null;
    }

}
