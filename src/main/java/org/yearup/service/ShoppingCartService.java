package org.yearup.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.yearup.models.ShoppingCart;
import org.yearup.repository.ShoppingCartRepository;

@Service
@AllArgsConstructor
public class ShoppingCartService
{
    // a shopping cart is built from cart rows plus a product lookup for each row
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCart getByUserId(int userId)
    {
        // load the user's cart rows, look up each product, and build the ShoppingCart
        return null;
    }

    // add additional methods here
}
