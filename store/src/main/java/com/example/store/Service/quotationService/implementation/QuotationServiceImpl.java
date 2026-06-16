package com.example.store.service.quotationService.implementation;


import com.example.store.dto.quotationManagement.QuotationDTO;
import com.example.store.exception.ElementNotFoundException;
import com.example.store.model.quotationManagement.Quotation;


import com.example.store.repository.quotationManagement.QuotationLineRepository;
import com.example.store.repository.quotationManagement.QuotationRepository;
import com.example.store.service.quotationService.interfaces.QuotationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
public class QuotationServiceImpl implements QuotationService {

    private final QuotationRepository quotationRepository;
    private final MapDTOToQuotation quotationMapper;
    private final MappedQuotationToDTO mappedQuotationToDTO;
    private final QuotationLineRepository quotationLineRepository;

    public QuotationServiceImpl(QuotationRepository quotationRepository,
                                MapDTOToQuotation quotationMapper,
                                MappedQuotationToDTO mappedQuotationToDTO,
                                QuotationLineRepository quotationLineRepository){
        this.quotationMapper=quotationMapper;
        this.quotationRepository=quotationRepository;
        this.mappedQuotationToDTO=mappedQuotationToDTO;
        this.quotationLineRepository=quotationLineRepository;


    }




    @Override
    public QuotationDTO saveQuotation( QuotationDTO  quotationDTO){
        Quotation quotation = new Quotation();
        quotationRepository.save(quotation);
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



    @Override
    @Transactional
    public  void deleteQuotation(Long quotationId){
        quotationLineRepository.deleteByQuotation_QuotationId(quotationId);
        quotationRepository.deleteById(quotationId);
    }


    }
