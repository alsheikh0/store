package com.codewithmosh.store.services;

import com.codewithmosh.store.dtos.CartDto;
import com.codewithmosh.store.dtos.CartItemDto;
import com.codewithmosh.store.dtos.addItemToCartRequest;
import com.codewithmosh.store.entities.Cart;
import com.codewithmosh.store.exceptions.CartNotFoundException;
import com.codewithmosh.store.exceptions.ProductNotFoundException;
import com.codewithmosh.store.mappers.CartMapper;
import com.codewithmosh.store.repositories.CartRepository;
import com.codewithmosh.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@AllArgsConstructor
public class CartService {
    public final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;
    //private final static Logger logger = LoggerFactory.getLogger(CartService.class);

    public CartDto createCart(){
        var cart = new Cart();
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    public CartItemDto addItem(UUID cartId, addItemToCartRequest request) {
        var cart = cartRepository.getCartWithItems(cartId).
                    orElseThrow(CartNotFoundException::new);


        var product = productRepository.findById(request.getProductId()).
                    orElseThrow(ProductNotFoundException::new);


        var cartItem = cart.addItem(product);
        cartRepository.save(cart);


        return cartMapper.toDto(cartItem);

    }

    public CartDto getCart(UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId).
                    orElseThrow(CartNotFoundException::new);
        return cartMapper.toDto(cart);
    }


    public CartItemDto updateCartItem(UUID cartId, Long productId, int quantity) {
        var cart = cartRepository.findById(cartId).
                    orElseThrow(CartNotFoundException::new);
        var cartItem = cart.getItems().stream().filter( item -> item.getProduct().getId().equals(productId)).
                        findFirst().orElseThrow(ProductNotFoundException::new);
        cartItem.setQuantity(quantity);
        cartRepository.save(cart);
        return cartMapper.toDto(cartItem);
    }

    public ResponseEntity<Void> removeCartItem(UUID cartId, Long productId) {
        var cart = cartRepository.getCartWithItems(cartId).orElseThrow(CartNotFoundException::new);
        cart.removeItem(productId);
        cartRepository.save(cart);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> clearCart(UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId).orElseThrow(CartNotFoundException::new);
        cart.clearCart();
        cartRepository.save(cart);
        return ResponseEntity.noContent().build();
    }
}
