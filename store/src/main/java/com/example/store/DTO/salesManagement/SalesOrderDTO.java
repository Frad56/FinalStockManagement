package com.example.store.dto.salesManagement;

import com.example.store.model.salesManagement.PaymentType;

import java.util.List;


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
