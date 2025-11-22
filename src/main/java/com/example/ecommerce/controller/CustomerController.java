package com.example.ecommerce.controller;

import com.example.ecommerce.model.dto.CartDto;
import com.example.ecommerce.model.dto.ProductDto;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final ProductService productService;
    private final CartService cartService;
    private final UserService userService;

    // GET ALL ACTIVE PRODUCTS
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllActiveProducts());
    }

    // ADD PRODUCT TO CART
    @PostMapping("/cart/add/{productId}")
    public ResponseEntity<CartDto> addProductToCart(
            Authentication authentication,
            @PathVariable Long productId,
            @RequestParam Integer count
    ) {
        String email = authentication.getName(); // Spring Security-dən email alırıq
        return ResponseEntity.ok(cartService.addProductToCart(email, productId, count));
    }

    // REMOVE PRODUCT FROM CART
    @DeleteMapping("/cart/remove/{cartId}")
    public ResponseEntity<Void> removeProductFromCart(
            Authentication authentication,
            @PathVariable Long cartId
    ) {
        String email = authentication.getName();
        cartService.removeProductFromCart(email, cartId);
        return ResponseEntity.ok().build();
    }

    // GET USER CART
    @GetMapping("/cart")
    public ResponseEntity<List<CartDto>> getUserCart(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(cartService.getUserCart(email));
    }

    // BUY PRODUCTS IN CART
    @PostMapping("/cart/buy")
    public ResponseEntity<Void> buyProductsInCart(Authentication authentication) {
        String email = authentication.getName();
        cartService.buyProductsInCart(email);
        return ResponseEntity.ok().build();
    }
}
