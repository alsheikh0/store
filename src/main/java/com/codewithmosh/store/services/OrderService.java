package com.codewithmosh.store.services;

import com.codewithmosh.store.dtos.OrderDto;
import com.codewithmosh.store.entities.Cart;
import com.codewithmosh.store.entities.Order;
import com.codewithmosh.store.mappers.OrderMapper;
import com.codewithmosh.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderMapper orderMapper;
    private OrderRepository orderRepository;
    private AuthService authService;

    public List<OrderDto> getAll(){
        var user = authService.getUser();
        var orders = orderRepository.getAllByUserId(user);
        return orders.stream().map(orderMapper::toDto).toList();
    }

    public OrderDto getOrder(Long id) {
        var order = orderRepository.findById(id).orElseThrow();
        return orderMapper.toDto(order);
    }
}
