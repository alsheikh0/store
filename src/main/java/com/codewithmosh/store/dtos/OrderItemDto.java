package com.codewithmosh.store.dtos;

import lombok.Data;

@Data
public class OrderItemDto {
    private ProductDto product;
    private int quantity;
    private double totalPrice;
}
