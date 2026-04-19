package com.example.store.DTO.stockManagment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductUnitSaleDTO {

    private Long productId;
    private Long unitId;
    private  Double unitPrice;
    private Double conversionFactor;


}
