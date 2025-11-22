package com.example.ecommerce.constants;

public class Constants {

    // ===========================
    // JWT CONSTANTS
    // ===========================
    public static final String JWT_SECRET = "MY_SUPER_SECRET_KEY_12345";
    public static final long JWT_EXPIRATION = 1000 * 60 * 60 * 10; // 10 saat

    // Token header & prefix
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    // ===========================
    // USER ROLES
    // ===========================
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    // ===========================
    // GENERAL ERROR MESSAGES
    // ===========================
    public static final String USER_NOT_FOUND = "User not found";
    public static final String CATEGORY_NOT_FOUND = "Category not found";
    public static final String PRODUCT_NOT_FOUND = "Product not found";
    public static final String BRAND_NOT_FOUND = "Brand not found";
    public static final String CART_NOT_FOUND = "Cart not found";
    public static final String ACCESS_DENIED = "You don't have permission for this action";

    // ===========================
    // SUCCESS MESSAGES
    // ===========================
    public static final String REGISTER_SUCCESS = "User successfully registered";
    public static final String LOGIN_SUCCESS = "Login successful";

    // ===========================
    // VALIDATION MESSAGES
    // ===========================
    public static final String EMAIL_EXISTS = "This email is already used";
    public static final String USERNAME_EXISTS = "This username is already taken";
}

