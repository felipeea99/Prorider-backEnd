package com.prorider.interfaces.store;

import com.prorider.DTOs.request.store.CartRequest;
import com.prorider.DTOs.response.store.CartResponse;
import com.prorider.DTOs.update.store.CartUpdate;
import com.prorider.entities.store.Cart;


import java.util.List;
import java.util.UUID;

public interface ICart {

    Cart findCartById(int cartId);
    CartResponse addCart(CartRequest cartRequest);
    CartResponse editCart(CartUpdate cartUpdate);
    Boolean deleteCart(int cartID);
    List<CartResponse> findAllItemsInCartByCustomerId(UUID userId);
    Void operationsInCart(int cartID, boolean isIncrement);
    Void cartProcessCompleted(UUID customerId);
    Cart ToCartObj(CartRequest cartRequest);
    CartResponse ToCartResponse(Cart cart);
}
