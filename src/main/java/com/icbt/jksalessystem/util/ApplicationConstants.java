package com.icbt.jksalessystem.util;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
public class ApplicationConstants {

    public static final class NotFound {
        public static final String USER_NOT_FOUND = "User not found!";
        public static final String BRANCH_NOT_FOUND = "Branch not found!";
        public static final String PRODUCT_NOT_FOUND = "Product not found!";
        public static final String STOCK_NOT_FOUND = "Stock not found!";
        public static final String FROM_BRANCH_NOT_FOUND = "From branch not found!";
        public static final String TO_BRANCH_NOT_FOUND = "To branch not found!";
        public static final String CUSTOMER_NOT_FOUND = "Customer not found!";
    }

    public static final class Invalid {
        public static final String INVALID_USERNAME_PASSWORD = "Invalid username or password";
    }

    public static final class AlreadyExists {
        public static final String BRANCH_EXISTS_WITH_EMAIL = "This email already in use";
        public static final String PRODUCT_EXISTS_WITH_NAME = "Product already exists with this name";
    }

}
