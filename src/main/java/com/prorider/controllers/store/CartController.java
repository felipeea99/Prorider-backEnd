package com.prorider.controllers.store;

import com.prorider.DTOs.request.store.CartRequest;
import com.prorider.DTOs.response.store.CartResponse;
import com.prorider.DTOs.update.store.CartUpdate;
import com.prorider.services.store.CartService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /// This EndPoint adds a cartObject into the database
    @PostMapping("/")
    public CartResponse addCart(@Valid  @RequestBody CartRequest cartRequest){
        return this.cartService.addCart(cartRequest);
    }

    /// This EndPoint edits a cartObject by using the cartId
    @PutMapping("/}")
    public CartResponse editCart(@Valid @RequestBody CartUpdate cartUpdate){
        return this.cartService.editCart(cartUpdate);
    }

    /// This EndPoint deletes a cartObject by using the cartId
    @DeleteMapping("/{cartId}")
    public Boolean deleteCart(@PathVariable int cartId){
        return this.cartService.deleteCart(cartId);
    }

    /// This EndPoint retrieves all the cartObjects in the cart by using the UserId
    @GetMapping("/{userId}")
    public List<CartResponse> findAllItemsInCartByUserId(@PathVariable UUID userId){
        return this.cartService.findAllItemsInCartByCustomerId(userId);
    }
    ///
    @PutMapping("/operations/{cartId}/{isIncrement}")
    public void operationsInCart(@PathVariable int cartId, @PathVariable boolean isIncrement) {
        cartService.operationsInCart(cartId, isIncrement);
    }

}
