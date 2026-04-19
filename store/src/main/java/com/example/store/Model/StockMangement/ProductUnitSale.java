package com.example.store.Model.StockMangement;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Product_unit_sale")
public class ProductUnitSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productUnitSaleId;


    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;


    @ManyToOne
    @JoinColumn(name = "unit_id",nullable = false)
    private Unit unit;

    @Column(name = "conversion_factor")
    private Double conversionFactor;
    @Column(name="unit_price",nullable = false)
    private  Double unitPrice;

}
