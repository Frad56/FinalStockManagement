package com.example.store.DTO.salesManagement;

import com.example.store.Model.salesManagement.PaymentType;

import java.math.BigDecimal;
import java.util.List;


import com.example.store.Model.salesManagement.SalesOrderLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderDTO {

    private Long clientId;
    private PaymentType paymentType;
    private List<SalesOrderLineDTO> salesOrderLineListDTO;
}
