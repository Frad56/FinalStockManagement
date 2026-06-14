package com.example.store.service.quotationServiceManagement;



import com.example.store.dto.quotationManagement.QuotationDTO;
import com.example.store.dto.quotationManagement.QuotationLineDTO;
import com.example.store.model.businessPartnerManagement.clientManagment.Client;
import com.example.store.model.quotationManagement.Quotation;
import com.example.store.model.quotationManagement.QuotationLine;
import com.example.store.service.BusinessPartnerManagement.clientManagement.ClientService;
import com.example.store.service.quotationService.implementation.MapDTOToQuotation;
import com.example.store.service.quotationService.implementation.MappedQuotationToDTO;
import com.example.store.service.quotationService.interfaces.QuotationLineService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;


@ExtendWith(MockitoExtension.class)
public class QuotationMapperTest {

    @Mock
    private ClientService clientService;


    @Mock
    private QuotationLineService quotationLineService;


    @InjectMocks
    private MappedQuotationToDTO mappedQuotationToDTO;

    @InjectMocks
    private MapDTOToQuotation mapDTOToQuotation;

    @Test
    void shouldMapClientToQuotation() {

        Client client = new Client();
        client.setClientId(1L);

        when(clientService.findClientById(1L))
                .thenReturn(client);

        QuotationLineDTO lineDTO = new QuotationLineDTO();
        lineDTO.setQuotationLineTotal(BigDecimal.valueOf(100));

        when(quotationLineService.mapDTOToQuotationLine(
                eq(lineDTO), any(QuotationLine.class)))
                .thenReturn(lineDTO);

        QuotationDTO dto = new QuotationDTO();
        dto.setClientId(1L);
        dto.setQuotationLineListDTO(List.of(lineDTO));

        Quotation quotation = new Quotation();

        mapDTOToQuotation.mapDTOToQuotation(dto, quotation);

        assertThat(quotation.getClient()).isEqualTo(client);
        assertThat(quotation.getClient().getClientId()).isEqualTo(1L);
    }

    @Test
    void shouldCalculateTotalFromLines() {

        QuotationLineDTO lineDTO1 = new QuotationLineDTO();
        lineDTO1.setQuotationLineTotal(BigDecimal.valueOf(100));

        QuotationLineDTO lineDTO2 = new QuotationLineDTO();
        lineDTO2.setQuotationLineTotal(BigDecimal.valueOf(300));

        when(quotationLineService.mapDTOToQuotationLine(
                eq(lineDTO1), any(QuotationLine.class)))
                .thenReturn(lineDTO1);

        when(quotationLineService.mapDTOToQuotationLine(
                eq(lineDTO2), any(QuotationLine.class)))
                .thenReturn(lineDTO2);

        QuotationDTO dto = new QuotationDTO();
        dto.setClientId(null);
        dto.setQuotationLineListDTO(List.of(lineDTO1, lineDTO2));

        Quotation quotation = new Quotation();

        mapDTOToQuotation.mapDTOToQuotation(dto, quotation);

        assertThat(quotation.getTotalAmount())
                .isEqualByComparingTo(BigDecimal.valueOf(400));
    }

    @Test
    void shouldMapQuotationAndTotalToDTO() {

        Quotation quotation = new Quotation();
        quotation.setQuotationId(10L);
        quotation.setTotalAmount(BigDecimal.valueOf(400));

        QuotationDTO result = mappedQuotationToDTO.quotationToDTO(quotation);

        assertThat(result.getQuotationId()).isEqualTo(10L);
        assertThat(result.getTotalAmount())
                .isEqualByComparingTo(BigDecimal.valueOf(400));
    }

    @Test
    void shouldMapClientFieldsToDTOWhenClientExists() {

        Client client = new Client();
        client.setClientId(1L);
        client.setFirstName("ali");
        client.setLastName("ali");
        client.setPhoneNumber("5495958");

        Quotation quotation = new Quotation();
        quotation.setQuotationId(10L);
        quotation.setTotalAmount(BigDecimal.valueOf(400));
        quotation.setClient(client);

        QuotationDTO result = mappedQuotationToDTO.quotationToDTO(quotation);

        assertThat(result.getClientId()).isEqualTo(1L);
        assertThat(result.getClientFirstName()).isEqualTo("ali");
        assertThat(result.getClientLastName()).isEqualTo("ali");
        assertThat(result.getClientNumber()).isEqualTo("5495958");
    }
}
