package com.example.store.dto.salesManagement;

import java.math.BigDecimal;

import com.example.store.model.stockManagement.ProductUnitSale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderLineDTO {

    private Long salesOrderLineId;
    private Long salesOrderId;
    private Long productVariantId;

    private String productVariantCode;
    private String productReference;
    private String productDesignation;
    private String productBrand;


    private String  productUnitSaleName;

    private Long productUnitSaleId;

    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal total;
    private BigDecimal discount;
    private BigDecimal totalAfterDiscount;



}
