package com.example.store.model.stockManagement;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Product_unit_purchase")
public class ProductUnitPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productUnitPurchaseId;

    @ManyToOne
    @JoinColumn(name = "product_variant_id",nullable = false)
    private ProductVariant productVariant;


    @ManyToOne
    @JoinColumn(name = "unit_id",nullable = false)
    private Unit unit;

    @Column(name = "conversion_factor")
    private Double conversionFactor;


    @Column(name="unit_price",nullable = false)
    private  Double unitPrice;

}
