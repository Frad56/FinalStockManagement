package com.example.store.service.quotationService.interfaces;

import com.example.store.dto.quotationManagement.QuotationLineDTO;
import com.example.store.model.quotationManagement.QuotationLine;

import java.math.BigDecimal;
import java.util.List;

public interface QuotationLineService {

    QuotationLine findQuotationLineById(Long quotationLineId);
    QuotationLineDTO mapDTOToQuotationLine(QuotationLineDTO dtoLine , QuotationLine line);
    List<QuotationLineDTO> fetchQuotationLineByQuotationId(Long quotationId);
    QuotationLineDTO updateQuotationLine(QuotationLineDTO dto,Long id);
    BigDecimal calculateQuotationLineTotal(QuotationLine quotationLine);
    void deleteQuotationLine(Long quotationLineId);


}
