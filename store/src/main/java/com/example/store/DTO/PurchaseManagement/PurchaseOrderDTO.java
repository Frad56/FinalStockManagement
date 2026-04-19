package com.example.store.DTO.PurchaseManagement;

import com.example.store.Model.PurchaseManagement.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class PurchaseOrderDTO {
    private Long supplierId;
    private Status status;
    private BigDecimal totalAmount;
}
