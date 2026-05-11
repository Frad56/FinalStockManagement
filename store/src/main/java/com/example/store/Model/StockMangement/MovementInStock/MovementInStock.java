package com.example.store.Model.StockMangement.MovementInStock;


import com.example.store.Model.PurchaseManagement.PurchaseOrder;
import com.example.store.Model.PurchaseManagement.PurchaseOrderLine;
import com.example.store.Model.StockMangement.MovementInStockType;
import com.example.store.Model.StockMangement.ProductVariant;
import com.example.store.Model.StockMangement.Unit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "movement_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class MovementInStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movementInStockId;

    @Column(name = "date")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private MovementInStockType movementInStockType;

    private BigDecimal quantity;

    @ManyToOne
    @JoinColumn(name="product_variant_id")
    private ProductVariant productVariant;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;


}
