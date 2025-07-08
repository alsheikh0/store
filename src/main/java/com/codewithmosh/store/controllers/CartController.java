package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.CartDto;
import com.codewithmosh.store.dtos.CartItemDto;
import com.codewithmosh.store.dtos.addItemToCartRequest;
import com.codewithmosh.store.dtos.updateCartRequest;
import com.codewithmosh.store.exceptions.CartNotFoundException;
import com.codewithmosh.store.exceptions.ProductNotFoundException;
import com.codewithmosh.store.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @PostMapping("/new")
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriBuilder
    ) {
        var cartDto = cartService.createCart();
        var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();
        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("{cartId}/add-item")
    public ResponseEntity<CartItemDto> addItemToCart(
            @PathVariable UUID cartId,
            @RequestBody addItemToCartRequest request

            ){
        var cartItemDto = cartService.addItem(cartId, request);
        System.out.println(cartItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @GetMapping("{cartId}")
    public CartDto getCart(
            @PathVariable UUID cartId){
        return cartService.getCart(cartId);
    }

    @PutMapping("{cartId}/items/{productId}")
    public CartItemDto updateCartItem (
            @PathVariable UUID cartId,
            @PathVariable Long productId,
            @RequestBody updateCartRequest request){
        return cartService.updateCartItem(cartId, productId, request.getQuantity());
    }

    @DeleteMapping("{cartId}/items/{productId}")
    public ResponseEntity<Void> deleteCartItem(
            @PathVariable UUID cartId,
            @PathVariable Long productId
    ){
        return cartService.removeCartItem(cartId, productId);
    }
    @DeleteMapping("{cartId}/delete")
    public ResponseEntity<Void> deleteCart(
            @PathVariable UUID cartId
    ) {
       return  cartService.clearCart(cartId);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCartNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "cart not found"));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "product not found"));
    }
}
