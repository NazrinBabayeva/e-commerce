package com.example.ecommerce.util;

import com.example.ecommerce.model.dto.*;
import com.example.ecommerce.model.entity.*;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class Mapper {

    // ================= USER =================
    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .birthdate(user.getBirthdate())
                .fin(user.getFin())
                .account(user.getAccount())
                .email(user.getEmail())
                .password(user.getPassword())
                .address(user.getAddress())
                .accountBalance(user.getAccountBalance())
                .build();
    }

    public static User toUserEntity(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .birthdate(dto.getBirthdate())
                .fin(dto.getFin())
                .account(dto.getAccount())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .address(dto.getAddress())
                .accountBalance(dto.getAccountBalance())
                .build();
    }

    // ================= CATEGORY =================
    public static CategoryDto toCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .status(category.isStatus())
                .build();
    }

    public static Category toCategoryEntity(CategoryDto dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .status(dto.isStatus())
                .build();
    }

    // ================= BRAND =================
    public static BrandDto toBrandDto(Brand brand) {
        return BrandDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .status(brand.isStatus())
                .build();
    }

    public static Brand toBrandEntity(BrandDto dto) {
        return Brand.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .status(dto.isStatus())
                .build();
    }

    // ================= PRODUCT =================
    public static ProductDto toProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .amount(product.getAmount())
                .remainCount(product.getRemainCount())
                .starRating(product.getStarRating())
                .comment(product.getComment())
                .productDetails(product.getProductDetails())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .brandId(product.getBrand() != null ? product.getBrand().getId() : null)
                .build();
    }

    public static Product toProductEntity(ProductDto dto, Category category, Brand brand) {
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .remainCount(dto.getRemainCount())
                .starRating(dto.getStarRating())
                .comment(dto.getComment())
                .productDetails(dto.getProductDetails())
                .category(category)
                .brand(brand)
                .status(true)
                .build();
    }

    // ================= CART =================
    public static CartDto toCartDto(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .userId(cart.getUser() != null ? cart.getUser().getId() : null)
                .productId(cart.getProduct() != null ? cart.getProduct().getId() : null)
                .count(cart.getCount())
                .totalAmount(cart.getTotalAmount())
                .build();
    }

    public static Cart toCartEntity(CartDto dto, User user, Product product) {
        return Cart.builder()
                .id(dto.getId())
                .user(user)
                .product(product)
                .count(dto.getCount())
                .totalAmount(dto.getTotalAmount())
                .build();
    }

    // ================= LIST MAPPERS =================
    public static java.util.List<ProductDto> toProductDtoList(java.util.List<Product> products) {
        return products.stream().map(Mapper::toProductDto).collect(Collectors.toList());
    }

    public static java.util.List<CategoryDto> toCategoryDtoList(java.util.List<Category> categories) {
        return categories.stream().map(Mapper::toCategoryDto).collect(Collectors.toList());
    }

    public static java.util.List<BrandDto> toBrandDtoList(java.util.List<Brand> brands) {
        return brands.stream().map(Mapper::toBrandDto).collect(Collectors.toList());
    }

    public static java.util.List<CartDto> toCartDtoList(java.util.List<Cart> carts) {
        return carts.stream().map(Mapper::toCartDto).collect(Collectors.toList());
    }

    public static java.util.List<UserDto> toUserDtoList(java.util.List<User> users) {
        return users.stream().map(Mapper::toUserDto).collect(Collectors.toList());
    }
}
