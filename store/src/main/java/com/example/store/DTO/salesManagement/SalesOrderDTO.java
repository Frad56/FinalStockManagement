package com.example.store.dto.salesManagement;

import com.example.store.model.salesManagement.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderDTO {

    private Long salesOrderId;
    private LocalDateTime salesOrderDate;

    private Long clientId;
    private String clientFirstName;
    private String clientLastName;


    private BigDecimal totalAmount;
    private PaymentType paymentType;
    private List<SalesOrderLineDTO> salesOrderLineListDTO;
}
