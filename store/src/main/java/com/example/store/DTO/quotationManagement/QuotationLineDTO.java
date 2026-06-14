package com.example.store.dto.quotationManagement;

import com.example.store.model.quotationManagement.Quotation;
import com.example.store.model.stockManagement.ProductUnitPurchase;
import com.example.store.model.stockManagement.ProductVariant;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class QuotationLineDTO {

    private Long quotationLineId;
    private Long quotationId;

    private Long productVariantId;
    private BigDecimal quantity;
    private Long productUnitSaleId;

    private BigDecimal unitPrice;
    private BigDecimal discount;
    private BigDecimal quotationLineTotal;

}
