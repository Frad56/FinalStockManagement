package com.example.store.service.quotationService.implementation;


import com.example.store.dto.quotationManagement.QuotationDTO;
import com.example.store.exception.ElementNotFoundException;
import com.example.store.model.quotationManagement.Quotation;


import com.example.store.repository.quotationManagement.QuotationRepository;
import com.example.store.service.quotationService.interfaces.QuotationService;

import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class QuotationServiceImpl implements QuotationService {

    private final QuotationRepository quotationRepository;
    private final MapDTOToQuotation quotationMapper;
    private final MappedQuotationToDTO mappedQuotationToDTO;

    public QuotationServiceImpl(QuotationRepository quotationRepository,
                                MapDTOToQuotation quotationMapper,
                                MappedQuotationToDTO mappedQuotationToDTO){
        this.quotationMapper=quotationMapper;
        this.quotationRepository=quotationRepository;
        this.mappedQuotationToDTO=mappedQuotationToDTO;


    }




    @Override
    public QuotationDTO saveQuotation( QuotationDTO  quotationDTO){
        Quotation quotation = new Quotation();
        quotationMapper.mapDTOToQuotation(quotationDTO,quotation);

        quotationRepository.save(quotation);
        return mappedQuotationToDTO.quotationToDTO(quotation);

    }
    @Override
    public List<QuotationDTO> fetchQuotationList(){
            return quotationRepository.findAll()
                    .stream()
                    .map(mappedQuotationToDTO::quotationToDTO)
                    .toList();
        }


    @Override
    public Quotation findQuotationById(Long quotationId){
        return quotationRepository.findById(quotationId).orElseThrow(()->new ElementNotFoundException(" quotation Not found!"));
    }





    }
