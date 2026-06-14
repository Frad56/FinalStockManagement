package com.example.store.service.quotationService.implementation;


import com.example.store.dto.quotationManagement.QuotationDTO;
import com.example.store.model.quotationManagement.Quotation;
import org.springframework.stereotype.Service;

@Service
public class MappedQuotationToDTO {



    public QuotationDTO quotationToDTO(Quotation quotation) {
        QuotationDTO quotationDTO = new QuotationDTO();

        quotationDTO.setQuotationId(quotation.getQuotationId());
        quotationDTO.setTotalAmount(quotation.getTotalAmount());
        if(quotation.getClient() != null){
            quotationDTO.setClientId(quotation.getClient().getClientId());
            quotationDTO.setClientFirstName(quotation.getClient().getFirstName());
            quotationDTO.setClientLastName(quotation.getClient().getLastName());
            quotationDTO.setClientNumber(quotation.getClient().getPhoneNumber());

        }
        //List<QuotationLine> quotationLines =quotationLineService.fetchQuotationLineByQuotationId(quotation.getQuotationId());
        return  quotationDTO;
    }
}
