package com.example.store.model.quotationManagement;


import com.example.store.model.purchaseManagement.PurchaseOrder;
import com.example.store.model.stockManagement.ProductUnitPurchase;
import com.example.store.model.stockManagement.ProductUnitSale;
import com.example.store.model.stockManagement.ProductVariant;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name= "quotation_line")
public class QuotationLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quotationLineId;

    @ManyToOne
    @JoinColumn(name = "quotation_id",nullable = false)
    private Quotation quotation;

    @ManyToOne
    @JoinColumn(name = "product_variant_id",nullable = false)
    private ProductVariant productVariant;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @ManyToOne
    @JoinColumn(name = "product_unit_purchase_id",nullable = true)
    private ProductUnitSale ProductUnitSale;

    @Column(name ="unit_price",precision = 10, scale = 2)
    private BigDecimal unitPrice;


    @Column(name ="discount")
    private BigDecimal discount;

    @Column(name ="quotation_line_total",precision = 10, scale = 2)
    private BigDecimal quotationLineTotal;


}
