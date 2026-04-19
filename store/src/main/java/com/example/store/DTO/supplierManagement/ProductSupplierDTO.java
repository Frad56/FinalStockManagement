package com.example.store.DTO.supplierManagement;


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
