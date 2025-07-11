package com.prorider.services.payments;

import com.prorider.DTOs.request.payments.OrderItemRequest;
import com.prorider.DTOs.response.payments.OrderItemResponse;
import com.prorider.entities.payments.Order;
import com.prorider.entities.payments.OrderItem;
import com.prorider.entities.store.Product;
import com.prorider.interfaces.payments.IOrderItem;
import com.prorider.services.store.ProductService;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService implements IOrderItem {
    private final ProductService productService;

    public OrderItemService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public OrderItemResponse ToOrderItemResponse(OrderItem orderItem) {
        OrderItemResponse itemResponse = new OrderItemResponse();
        itemResponse.setProductName(orderItem.getProduct().getProductName());
        itemResponse.setPrice(orderItem.getUnitPrice());
        itemResponse.setQuantity(orderItem.getQuantity());
        return itemResponse;
    }

    @Override
    public OrderItem ToOrderItem(OrderItemRequest orderItemRequest, Order order) {
        //find the product object base on the productId
        Product product = productService.findProductByIdBaseForm(orderItemRequest.getProductId());
        // orderItem initialization
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(orderItemRequest.getQuantity());
        item.setUnitPrice(product.getPrice());
        return item;
    }
}
