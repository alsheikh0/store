package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.CheckoutRequest;
import com.codewithmosh.store.dtos.CheckoutResponse;
import com.codewithmosh.store.dtos.OrderDto;
import com.codewithmosh.store.services.CheckoutService;
import com.codewithmosh.store.services.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkout")
@AllArgsConstructor
public class CheckoutController {
    private CheckoutService checkoutService;


    @GetMapping
    public ResponseEntity<CheckoutResponse> checkout(
           @Valid @RequestBody CheckoutRequest request
    ){
        return checkoutService.checkout(request.getCartId());
    }
}
