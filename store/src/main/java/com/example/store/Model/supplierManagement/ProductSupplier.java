package com.example.store.Model.supplierManagement;

import com.example.store.Model.StockMangement.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product_supplier")
public class ProductSupplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductSupplier;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    @JsonBackReference
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id",nullable = false)
    private Supplier supplier;

    @Column(name="purchase_price",nullable = false)
    private  Double purchasePrice;
}
