
package com.example.ecommerce.service;

import com.example.ecommerce.model.dto.CartDto;
import com.example.ecommerce.model.dto.ProductDto;
import com.example.ecommerce.model.entity.Cart;
import com.example.ecommerce.model.entity.Product;
import com.example.ecommerce.model.entity.User;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // Add product to cart
    public CartDto addProductToCart(String userEmail, Long productId, Integer count) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getRemainCount() < count)
            throw new RuntimeException("Not enough product in stock");

        product.setRemainCount(product.getRemainCount() - count);
        productRepository.save(product);

        Cart cart = Cart.builder()
                .user(user)
                .product(product)
                .count(count)
                .totalAmount(product.getAmount() * count)
                .build();

        return Mapper.toCartDto(cartRepository.save(cart));
    }

    // Remove product from cart
    public void removeProductFromCart(String userEmail, Long cartId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        if (!cart.getUser().getId().equals(user.getId()))
            throw new RuntimeException("Unauthorized");

        Product product = cart.getProduct();
        product.setRemainCount(product.getRemainCount() + cart.getCount());
        productRepository.save(product);

        cartRepository.delete(cart);
    }

    // Get user cart
    public List<CartDto> getUserCart(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findByUserId(user.getId())
                .stream()
                .map(Mapper::toCartDto)
                .collect(Collectors.toList());
    }

    // Buy products in cart
    public void buyProductsInCart(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Cart> carts = cartRepository.findByUserId(user.getId());

        double total = carts.stream().mapToDouble(Cart::getTotalAmount).sum();
        if (user.getAccountBalance() < total)
            throw new RuntimeException("Insufficient balance");

        user.setAccountBalance(user.getAccountBalance() - total);
        userRepository.save(user);

        cartRepository.deleteAll(carts);
    }
}
