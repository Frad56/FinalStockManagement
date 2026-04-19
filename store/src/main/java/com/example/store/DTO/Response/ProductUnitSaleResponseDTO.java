package com.example.store.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUnitSaleResponseDTO {

    private Long productUnitSaleId;
    private Long productId;
    private Long unitId;
    private Double unitPrice;
    private Double conversionFactor;
}