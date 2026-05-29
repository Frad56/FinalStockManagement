package com.example.store.dto.stockManagment;


import com.example.store.model.stockManagement.MovementInStockType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementInStockDTO {

    private LocalDateTime date;
    private MovementInStockType movementInStockType;
    private BigDecimal quantityInStock;
    private Long productVariantId;
    private Long unitId;

}
