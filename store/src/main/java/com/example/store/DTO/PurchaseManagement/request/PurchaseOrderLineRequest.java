package com.example.store.dto.purchaseManagement.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseOrderLineRequest {
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private Long productVariantId;
    private Long purchaseOrderId;
    //    private BigDecimal discount;

}
