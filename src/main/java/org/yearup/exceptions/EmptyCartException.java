package org.yearup.exceptions;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException() {
        super("Cannot create order from an empty cart");
    }
}
