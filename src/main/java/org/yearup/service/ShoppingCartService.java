package org.yearup.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.yearup.models.CartItem;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.repository.ShoppingCartRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ShoppingCartService
{
    // a shopping cart is built from cart rows plus a product lookup for each row
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCart getByUserId(Integer userId)
    {
        // load the user's cart rows, look up each product, and build the ShoppingCart
        List<CartItem> cartItems = shoppingCartRepository.findByUserId(userId);
        return createShoppingCart(cartItems);

    }

    // add additional methods here

    private ShoppingCart createShoppingCart(List<CartItem> cartItems) {
        var shoppingCart = new ShoppingCart();

        cartItems.stream()
                .map(item -> {
                    var product = productService.getById(item.getProductId());

                    var shoppingCartItem = new ShoppingCartItem();
                    shoppingCartItem.setProduct(product);
                    shoppingCartItem.setQuantity(item.getQuantity());

                    return shoppingCartItem;
                })
                .forEach(shoppingCart::add);

        return shoppingCart;
    }

    public ShoppingCart addProduct(Integer userId, Integer productId) {
        var cartItem = shoppingCartRepository.findByUserIdAndProductId(userId, productId).orElse(null);

        if(cartItem == null) {
            cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(productId);
            cartItem.setQuantity(1);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }

        shoppingCartRepository.save(cartItem);

        return getByUserId(userId);
    }

    public ShoppingCart updateQuantity(Integer userId, Integer productId, int quantity) {
        var cartItem = shoppingCartRepository.findByUserIdAndProductId(userId, productId).orElseThrow();

        cartItem.setQuantity(quantity);
        shoppingCartRepository.save(cartItem);

        return getByUserId(userId);
    }

    @Transactional
    public ShoppingCart clearCart(Integer userId) {
        shoppingCartRepository.deleteByUserId(userId);
        return getByUserId(userId);
    }
}