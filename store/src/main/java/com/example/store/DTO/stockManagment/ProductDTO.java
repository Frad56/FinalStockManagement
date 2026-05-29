package com.example.store.dto.stockManagment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private Long productId;
    private String reference;
    private String designation;
    private String brand;
    private String description;
    private BigDecimal basePrice;
    private Long categoryId;
    private Long aisleId;




}
