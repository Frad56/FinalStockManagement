package com.example.store.dto.purchaseManagement;

import com.example.store.model.purchaseManagement.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PurchaseOrderDTO {
    private Long supplierId;
    private Status status;
    private BigDecimal totalAmount;
}
