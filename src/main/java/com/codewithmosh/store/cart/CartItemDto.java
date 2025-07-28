package com.codewithmosh.store.cart;

import com.codewithmosh.store.product.ProductDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private ProductDto product;
    private int quantity;
    private BigDecimal totalPrice;


}
