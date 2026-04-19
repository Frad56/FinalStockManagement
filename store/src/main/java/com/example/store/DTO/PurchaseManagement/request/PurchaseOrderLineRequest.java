package com.example.store.DTO.PurchaseManagement.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PurchaseOrderLineRequest {
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private Long productVariantId;
    private Long purchaseOrderId;
    //    private BigDecimal discount;

}
