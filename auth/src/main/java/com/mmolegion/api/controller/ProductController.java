package com.mmolegion.api.controller;

import com.mmolegion.core.model.Product;
import com.mmolegion.core.model.ProductCategory;
import com.mmolegion.core.model.ProductSubCategory;
import com.mmolegion.core.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProductController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/v1/products")
    public ResponseEntity<?> findAllProducts(HttpServletRequest request) {


        return null;
    }

    @PostMapping("/api/v1/products")
    public ResponseEntity<?> createProduct(HttpServletRequest request) {


        return null;
    }

    @PutMapping("/api/v1/products")
    public ResponseEntity<?> updateAllProducts(HttpServletRequest request, @RequestBody Product requestBody) {


        return null;
    }

    @DeleteMapping("/api/v1/products")
    public ResponseEntity<?> deleteAllProducts(HttpServletRequest request) {


        return null;
    }

    @GetMapping("/api/v1/products/{productId}")
    public ResponseEntity<?> findProductById(HttpServletRequest request, @PathVariable int productId) {


        return null;
    }

    @PutMapping("/api/v1/products/{productId}")
    public ResponseEntity<?> updateProductById(HttpServletRequest request, @PathVariable int productId, @RequestBody Product requestBody) {


        return null;
    }

    @DeleteMapping("/api/v1/products/{productId}")
    public ResponseEntity<?> deleteProductById(HttpServletRequest request, @PathVariable int productId) {


        return null;
    }

    @GetMapping("/api/v1/products/categories")
    public ResponseEntity<?> findAllCategories(HttpServletRequest request) {


        return null;
    }

    @PostMapping("/api/v1/products/categories")
    public ResponseEntity<?> createCategory(HttpServletRequest request) {


        return null;
    }

    @PutMapping("/api/v1/products/categories")
    public ResponseEntity<?> updateAllCategories(HttpServletRequest request, @RequestBody ProductCategory requestBody) {


        return null;
    }

    @DeleteMapping("/api/v1/products/categories")
    public ResponseEntity<?> deleteAllCategories(HttpServletRequest request) {


        return null;
    }

    @GetMapping("/api/v1/products/categories/{categoryId}")
    public ResponseEntity<?> findCategoryById(HttpServletRequest request, @PathVariable int categoryId) {


        return null;
    }

    @PutMapping("/api/v1/products/categories/{categoryId}")
    public ResponseEntity<?> updateCategoryById(HttpServletRequest request, @PathVariable int categoryId, @RequestBody ProductCategory requestBody) {


        return null;
    }

    @DeleteMapping("/api/v1/products/categories/{categoryId}")
    public ResponseEntity<?> deleteCategoryById(HttpServletRequest request, @PathVariable int categoryId) {


        return null;
    }

    @GetMapping("/api/v1/products/categories/subcategories")
    public ResponseEntity<?> findAllSubcategories(HttpServletRequest request) {


        return null;
    }

    @PutMapping("/api/v1/products/categories/subcategories")
    public ResponseEntity<?> updateAllSubcategories(HttpServletRequest request, @RequestBody ProductSubCategory requestBody) {


        return null;
    }

    @DeleteMapping("/api/v1/products/categories/subcategories")
    public ResponseEntity<?> deleteAllSubcategories(HttpServletRequest request) {


        return null;
    }

    @GetMapping("/api/v1/products/categories/{categoryId}/subcategory")
    public ResponseEntity<?> findAllSubCategoriesForId(HttpServletRequest request, @PathVariable int categoryId) {


        return null;
    }

    @PostMapping("/api/v1/products/categories/{categoryId}/subcategory")
    public ResponseEntity<?> createSubCategoryForId(HttpServletRequest request, @PathVariable int categoryId) {


        return null;
    }

    @PutMapping("/api/v1/products/categories/{categoryId}/subcategory")
    public ResponseEntity<?> updateAllSubCategoriesForId(HttpServletRequest request, @PathVariable int categoryId, @RequestBody ProductSubCategory requestBody) {


        return null;
    }

    @DeleteMapping("/api/v1/products/categories/{categoryId}/subcategory")
    public ResponseEntity<?> deleteAllSubCategoriesForId(HttpServletRequest request, @PathVariable int categoryId) {


        return null;
    }

}
