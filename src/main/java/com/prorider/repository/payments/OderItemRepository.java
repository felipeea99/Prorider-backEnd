package com.prorider.repository.payments;

import com.prorider.entities.payments.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OderItemRepository extends JpaRepository<OrderItem, UUID> {
}
