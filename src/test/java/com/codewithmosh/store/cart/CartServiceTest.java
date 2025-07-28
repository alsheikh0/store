package com.codewithmosh.store.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {
    @Mock
    CartItem cartItem;
    @Mock
    CartRepository cartRepository;

    @InjectMocks
    CartService cartService;

    @Test
    void createCart() {
    }

    @Test
    void addItemShouldAddItemToCartSuccessfully() {

    }

    @Test
    void getCart() {
    }

    @Test
    void updateCartItem() {
    }

    @Test
    void removeCartItem() {
    }
}