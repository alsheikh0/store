package com.codewithmosh.store.checkout;

import com.codewithmosh.store.auth.AuthService;
import com.codewithmosh.store.common.ErrorDto;
import com.codewithmosh.store.orders.Order;
import com.codewithmosh.store.orders.OrderItem;
import com.codewithmosh.store.orders.OrderStatus;
import com.codewithmosh.store.cart.CartNotFoundException;
import com.codewithmosh.store.orders.OrderMapper;
import com.codewithmosh.store.cart.CartRepository;
import com.codewithmosh.store.orders.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashSet;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CheckoutService {
    private final CartRepository cartRepository;
    private final AuthService authService;
    private final OrderRepository orderRepository;



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
