package com.example.store.dto.quotationManagement;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
public class QuotationDTO {

    private Long quotationId;
    private Date quotationDate;
    private BigDecimal totalAmount;


    private Long ClientId;
    private String clientFirstName;
    private String clientLastName;
    private String clientNumber;


    private List<QuotationLineDTO> quotationLineListDTO;
}
