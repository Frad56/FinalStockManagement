package com.example.store.service.quotationService.implementation;

import com.example.store.dto.quotationManagement.QuotationDTO;
import com.example.store.dto.quotationManagement.QuotationLineDTO;
import com.example.store.model.businessPartnerManagement.clientManagment.Client;
import com.example.store.model.quotationManagement.Quotation;
import com.example.store.model.quotationManagement.QuotationLine;
import com.example.store.service.BusinessPartnerManagement.clientManagement.ClientService;

import com.example.store.service.quotationService.interfaces.QuotationLineService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;



@Service
public class MapDTOToQuotation {

    private final ClientService clientService;
    private final QuotationLineService quotationLineService;


    public MapDTOToQuotation(ClientService clientService, QuotationLineService quotationLineService){
        this.clientService=clientService;
        this.quotationLineService=quotationLineService;


    }


    public void mapDTOToQuotation(QuotationDTO quotationDTO, Quotation quotation){
        if(quotationDTO.getClientId() != null ){
            Client client= clientService.findClientById(quotationDTO.getClientId());
            quotation.setClient(client);
        }
        if (quotationDTO.getQuotationLineListDTO() == null ||
                quotationDTO.getQuotationLineListDTO().isEmpty()) {
            throw new IllegalArgumentException("Quotation must have at least one line");
        }
        BigDecimal quotationTotal=BigDecimal.ZERO;
        for(QuotationLineDTO dtoLine : quotationDTO.getQuotationLineListDTO()){
                    QuotationLine line = new QuotationLine();
                    line.setQuotation(quotation);
                    QuotationLineDTO Saved= quotationLineService.mapDTOToQuotationLine(dtoLine,line);
                    quotationTotal =quotationTotal.add(Saved.getQuotationLineTotal());

        }

        quotation.setTotalAmount(quotationTotal);

    }




}
