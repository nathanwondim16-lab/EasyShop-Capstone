package org.yearup.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yearup.service.OrderService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/orders")
@CrossOrigin
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class OrdersController {

    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createOrder(Principal principal) {
        var user = userService.getByUserName(principal.getName());
        var order = orderService.createOrder(user.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
