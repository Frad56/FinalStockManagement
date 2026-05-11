package com.example.store.DTO.salesManagement;

import com.example.store.Model.BusinessPartnerManagement.clientManagment.Client;
import com.example.store.Model.salesManagement.PaymentType;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderLineDTO {


    private Long salesOrderId;
    private Long productVariantId;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal discount;



}
