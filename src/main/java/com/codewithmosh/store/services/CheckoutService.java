package com.codewithmosh.store.services;

import com.codewithmosh.store.dtos.CheckoutResponse;
import com.codewithmosh.store.dtos.ErrorDto;
import com.codewithmosh.store.dtos.OrderDto;
import com.codewithmosh.store.entities.Order;
import com.codewithmosh.store.entities.OrderItem;
import com.codewithmosh.store.entities.OrderStatus;
import com.codewithmosh.store.exceptions.CartNotFoundException;
import com.codewithmosh.store.mappers.OrderMapper;
import com.codewithmosh.store.repositories.CartRepository;
import com.codewithmosh.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CheckoutService {
    private final CartRepository cartRepository;
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;



    public ResponseEntity<CheckoutResponse> checkout(UUID cartId){
        var cart = cartRepository.getCartWithItems(cartId).orElseThrow(CartNotFoundException::new);
        var user = authService.getUser();

        var order = new Order();
        var orderItems = new LinkedHashSet<OrderItem>();
        order.setCustomer(user);
        order.setOrderStatus(OrderStatus.PENDING);
        cart.getItems().forEach( item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setProduct(item.getProduct());
            orderItem.setUnitPrice(item.getProduct().getPrice());
            orderItem.setTotalPrice(item.getTotalPrice());
            orderItems.add(orderItem);
        });

        order.setTotalPrice(cart.getTotalPrice());
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        cart.clearCart();

        return ResponseEntity.ok(new CheckoutResponse(String.valueOf(order.getId())));

    }



    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUnReadableMessage() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("something"));
    }
}
