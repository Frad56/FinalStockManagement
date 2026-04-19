package com.example.store.Model.StockMangement;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name= "product_variant")
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productVariantId;


    @Column(name ="code")
    private String code;

    @Column(name ="specific_price",precision = 10, scale = 2)
    private BigDecimal specificPrice;

    @Column(name ="quantity_in_stock")
    private Integer quantityInStock;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;


}
