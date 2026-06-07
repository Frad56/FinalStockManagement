package com.example.store.model.stockManagement.MovementInStock;


import com.example.store.model.stockManagement.MovementInStockType;
import com.example.store.model.stockManagement.ProductVariant;
import com.example.store.model.stockManagement.Unit;
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




}
