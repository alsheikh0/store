package com.codewithmosh.store.orders;

import com.codewithmosh.store.product.ProductDto;
import lombok.Data;

@Data
public class OrderItemDto {
    private ProductDto product;
    private int quantity;
    private double totalPrice;
}
