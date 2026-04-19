package com.example.store.DTO.PurchaseManagement;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PurchaseOrderLineDTO {

    private Long purchaseOrderId;
    private Long productVariantId;
   // private Long unitId;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
  //  private BigDecimal discount;
}
