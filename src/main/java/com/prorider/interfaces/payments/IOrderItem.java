package com.prorider.interfaces.payments;

import com.prorider.DTOs.request.payments.OrderItemRequest;
import com.prorider.DTOs.response.payments.OrderItemResponse;
import com.prorider.entities.payments.Order;
import com.prorider.entities.payments.OrderItem;

public interface IOrderItem {
    OrderItemResponse ToOrderItemResponse (OrderItem orderItem);
    OrderItem ToOrderItem (OrderItemRequest orderItemRequest, Order order);
}
