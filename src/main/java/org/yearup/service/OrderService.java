package org.yearup.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.yearup.exceptions.EmptyCartException;
import org.yearup.models.Order;
import org.yearup.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProfileService profileService;
    private final ShoppingCartService shoppingCartService;

    @Transactional
    public Order createOrder(Integer userId) {
        var cart = shoppingCartService.getByUserId(userId);

        if(cart.getItems().isEmpty()) {
            throw new EmptyCartException();
        }

        var profile = profileService.getByUserId(userId);

        var order = Order.builder()
                .userId(userId)
                .date(LocalDateTime.now())
                .address(profile.getAddress())
                .city(profile.getCity())
                .state(profile.getState())
                .zip(profile.getZip())
                .shippingAmount(BigDecimal.ZERO)
                .build();


        var savedOrder = orderRepository.save(order);

        shoppingCartService.clearCart(userId);

        return savedOrder;
    }
}