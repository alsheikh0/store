package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.OrderDto;
import com.codewithmosh.store.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/all")
    public List<OrderDto> getAllOrders() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderService.getOrder(id);
    }
}
