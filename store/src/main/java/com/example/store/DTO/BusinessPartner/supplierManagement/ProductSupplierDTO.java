package com.example.store.dto.businessPartner.supplierManagement;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductSupplierDTO {
    private Long productId;
    private Long supplierId;
    private Double purchasePrice;
}
