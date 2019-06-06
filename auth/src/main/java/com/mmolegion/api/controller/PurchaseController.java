package com.mmolegion.api.controller;

import com.mmolegion.core.model.ItemPurchase;
import com.mmolegion.core.model.Purchase;
import com.mmolegion.core.service.PurchaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PurchaseController {

    private static final Logger logger = LogManager.getLogger(PurchaseController.class);
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/api/v1/purchases")
    public ResponseEntity<?> findAllPurchases(HttpServletRequest request) {


        return null;
    }

    @PostMapping("/api/v1/purchases")
    public ResponseEntity<?> createPurchase(HttpServletRequest request) {


        return null;
    }

    @PutMapping("/api/v1/purchases")
    public ResponseEntity<?> updateAllPurchases(HttpServletRequest request, @RequestBody Purchase requestBody) {


        return null;
    }

    @GetMapping("/api/v1/purchases/{purchaseId}")
    public ResponseEntity<?> findPurchaseById(HttpServletRequest request, @PathVariable int purchaseId) {


        return null;
    }

    @PutMapping("/api/v1/purchases/{purchaseId}")
    public ResponseEntity<?> updatePurchaseById(HttpServletRequest request, @PathVariable int purchaseId, @RequestBody Purchase requestBody) {


        return null;
    }

    @GetMapping("/api/v1/purchases/{purchaseId}/item")
    public ResponseEntity<?> findAllItemsFromPurchase(HttpServletRequest request, @PathVariable int purchaseId) {


        return null;
    }

    @PutMapping("/api/v1/purchases/{purchaseId}/item")
    public ResponseEntity<?> updateAllItemsForPurchase(HttpServletRequest request, @PathVariable int purchaseId, @RequestBody ItemPurchase requestBody) {


        return null;
    }

}
