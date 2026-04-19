package com.example.store.Model.StockMangement;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="movement_in_stock")
public class MovementInStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movementInStockId;

    @Column(name = "date")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private MovementInStockType movementInStockType;

    @Column(name = "quantity_in_stock")
    private BigDecimal quantityInStock;

    @ManyToOne
    @JoinColumn(name="product_variant_id")
    private ProductVariant productVariant;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;
}
