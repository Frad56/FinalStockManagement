package com.example.store.model.purchaseManagement;


import com.example.store.model.stockManagement.ProductUnitPurchase;
import com.example.store.model.stockManagement.ProductUnitSale;
import com.example.store.model.stockManagement.ProductVariant;
import com.example.store.model.stockManagement.Unit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="purchase_order_line")
public class PurchaseOrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseOrderLineId;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id",nullable = false)
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "product_variant_id",nullable = false)
    private ProductVariant productVariant;

    @ManyToOne
    @JoinColumn(name = "product_unit_purchase_id",nullable = true)
    private ProductUnitPurchase productUnitPurchase;

    @Column(name = "quantity")
    private BigDecimal quantity;


    @Column(name ="discount")
    private BigDecimal discount;

    @Column(name ="unit_price_ht",precision = 10, scale = 2)
    private BigDecimal unitPriceHt;

    @Column(name ="unit_price_ttc",precision = 10, scale = 2)
    private BigDecimal unitPriceTTC;

    @Column(name ="total_ht",precision = 10, scale = 2)
    private BigDecimal totalHT;


    @Column(name ="total_ttc",precision = 10, scale = 2)
    private BigDecimal totalTTC;

    @Column(name ="tax",precision = 5, scale = 2)
    private BigDecimal tax;

    @Column(name="total",precision = 10, scale = 2)
    private BigDecimal total;


}
