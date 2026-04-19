package com.example.store.DTO.PurchaseManagement.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseOrderLineListRequest {

    private BigDecimal quantity;
    private BigDecimal unitPrice;
}
