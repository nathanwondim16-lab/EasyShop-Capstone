package org.yearup.exceptions;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(Integer productId) {
        super("Product " + productId + " was not found in the shopping cart");
    }
}
