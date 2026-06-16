package com.example.store.service.quotationService.interfaces;

import com.example.store.dto.quotationManagement.QuotationDTO;
import com.example.store.dto.salesManagement.SalesOrderDTO;
import com.example.store.model.quotationManagement.Quotation;
import com.example.store.model.quotationManagement.QuotationLine;
import com.example.store.model.salesManagement.SalesOrderLine;

import java.math.BigDecimal;
import java.util.List;

public interface QuotationService {
     QuotationDTO saveQuotation( QuotationDTO  quotationDTO);
     List<QuotationDTO> fetchQuotationList();
     Quotation findQuotationById(Long quotationId);
     void deleteQuotation(Long quotationId);

}
