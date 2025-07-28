package com.codewithmosh.store.cart;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class addItemToCartRequest {
    @NotNull(message = "productId must be provided")
    private Long productId;
}
