package com.example.store.service.quotationServiceManagement;


import com.example.store.dto.quotationManagement.QuotationDTO;
import com.example.store.model.quotationManagement.Quotation;
import com.example.store.repository.quotationManagement.QuotationRepository;
import com.example.store.service.quotationService.implementation.MapDTOToQuotation;
import com.example.store.service.quotationService.implementation.MappedQuotationToDTO;
import com.example.store.service.quotationService.implementation.QuotationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuotationServiceTest {

    @Mock
    private QuotationRepository quotationRepository;

    @Mock
    private MapDTOToQuotation mapDTOToQuotation;

    @Mock
    private MappedQuotationToDTO mappedQuotationToDTO;

    @InjectMocks
    private QuotationServiceImpl quotationService;


    @Test
    void shouldSaveQuotation() {

        QuotationDTO quotationDTO = new QuotationDTO();
        quotationDTO.setClientId(1L);
        quotationDTO.setTotalAmount(BigDecimal.valueOf(100));

        Quotation quotation = new Quotation();

        QuotationDTO returnedDTO = new QuotationDTO();
        returnedDTO.setClientId(1L);

        doNothing().when(mapDTOToQuotation)
                .mapDTOToQuotation(
                        any(QuotationDTO.class),
                        any(Quotation.class));

        when(quotationRepository.save(any(Quotation.class)))
                .thenReturn(quotation);

        when(mappedQuotationToDTO.quotationToDTO(any(Quotation.class)))
                .thenReturn(returnedDTO);




        QuotationDTO result =
                quotationService.saveQuotation(quotationDTO);


        assertNotNull(result);

        verify(mapDTOToQuotation)
                .mapDTOToQuotation(
                        eq(quotationDTO),
                        any(Quotation.class));

        verify(quotationRepository)
                .save(any(Quotation.class));

        verify(mappedQuotationToDTO)
                .quotationToDTO(any(Quotation.class));
    }
}
