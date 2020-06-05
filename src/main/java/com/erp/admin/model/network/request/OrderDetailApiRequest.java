package com.erp.admin.model.network.request;

import com.erp.admin.model.status.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailApiRequest {

    private Long id;

    private OrderStatus status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    private Long orderGroupId;

    private Long itemId;
}
