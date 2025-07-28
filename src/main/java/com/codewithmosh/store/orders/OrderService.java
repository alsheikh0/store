package com.codewithmosh.store.orders;

import com.codewithmosh.store.auth.AuthService;
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
