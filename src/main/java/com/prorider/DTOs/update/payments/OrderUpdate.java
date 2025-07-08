package com.prorider.DTOs.update.payments;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdate {
    private UUID orderId;
    private LocalDateTime paymentDate;
    private BigDecimal totalAmount;
    private String stripePaymentIntentId;
    private String status;
    private UUID userId;
    private int addressId;
}
