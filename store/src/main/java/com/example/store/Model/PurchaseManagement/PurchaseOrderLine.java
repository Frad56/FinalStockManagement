package com.example.store.Model.PurchaseManagement;


import com.example.store.Model.StockMangement.ProductVariant;
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

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name ="unit_price",precision = 10, scale = 2)
    private BigDecimal unitPrice;


}
