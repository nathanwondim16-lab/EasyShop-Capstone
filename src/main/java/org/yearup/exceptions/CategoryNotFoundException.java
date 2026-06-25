package org.yearup.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Integer categoryId) {
        super("Category with id " + categoryId + " was not found");
    }
}
