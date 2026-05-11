package com.example.store.DTO.salesManagement;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstallmentDTO {

    private Long salesOrderId;
    private BigDecimal amount;
    private boolean paid;
}
