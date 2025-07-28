package com.codewithmosh.store.checkout;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
@AllArgsConstructor
public class CheckoutController {
    private CheckoutService checkoutService;


    @GetMapping
    public ResponseEntity<CheckoutResponse> checkout(
            @Valid @RequestParam CheckoutRequest request
    ){
        return checkoutService.checkout(request.getCartId());
    }
}