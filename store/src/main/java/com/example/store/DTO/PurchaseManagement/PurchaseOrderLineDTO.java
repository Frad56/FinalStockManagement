package com.example.store.dto.purchaseManagement;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PurchaseOrderLineDTO {

    private Long purchaseOrderId;
    private Long productVariantId;
    private Long productUnitPurchaseId;
    private BigDecimal quantity;
    private String discount;
    private BigDecimal unitPriceHt;
    private BigDecimal unitPriceTTC;
    private BigDecimal totalHT;
    private BigDecimal totalTTC;
    private BigDecimal tax;
    private BigDecimal total;
}
