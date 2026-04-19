package com.example.store.DTO.stockManagment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductVariantDTO {

    private String code;
    private BigDecimal specificPrice;
    private Integer quantityInStock;
    private Long productId;

}
