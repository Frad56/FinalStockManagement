package com.example.store.service.quotationServiceManagement;

import com.example.store.dto.quotationManagement.QuotationLineDTO;
import com.example.store.exception.ElementNotFoundException;
import com.example.store.model.quotationManagement.Quotation;
import com.example.store.model.quotationManagement.QuotationLine;
import com.example.store.model.stockManagement.ProductUnitSale;
import com.example.store.model.stockManagement.ProductVariant;
import com.example.store.repository.quotationManagement.QuotationLineRepository;
import com.example.store.repository.quotationManagement.QuotationRepository;
import com.example.store.service.quotationService.implementation.QuotationLineServiceImpl;
import com.example.store.service.salesManagement.interfaces.ProductUnitSaleService;
import com.example.store.service.stockManagment.interfaces.ProductVariantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuotationLineServiceTest {
    @Mock
    private QuotationLineRepository quotationLineRepository;

    @Mock
    private QuotationRepository quotationRepository;

    @Mock
    private ProductUnitSaleService productUnitSaleService;

    @Mock
    private ProductVariantService productVariantService;

    @InjectMocks
    private QuotationLineServiceImpl quotationLineService;


    @Test
    void shouldCalculateLineTotalWithoutDiscount() {

        QuotationLine line = new QuotationLine();
        line.setQuantity(BigDecimal.valueOf(2));
        line.setUnitPrice(BigDecimal.valueOf(50));

        BigDecimal total =
                quotationLineService.calculateQuotationLineTotal(line);

        assertThat(total)
                .isEqualByComparingTo(BigDecimal.valueOf(100));

        assertThat(line.getQuotationLineTotal())
                .isEqualByComparingTo(BigDecimal.valueOf(100));
    }


    @Test
    void shouldCalculateLineTotalWithDiscount() {

        QuotationLine line = new QuotationLine();
        line.setQuantity(BigDecimal.valueOf(2));
        line.setUnitPrice(BigDecimal.valueOf(50));
        line.setDiscount(BigDecimal.valueOf(10));

        BigDecimal total =
                quotationLineService.calculateQuotationLineTotal(line);

        assertThat(total)
                .isEqualByComparingTo(BigDecimal.valueOf(90));

        assertThat(line.getQuotationLineTotal())
                .isEqualByComparingTo(BigDecimal.valueOf(90));
    }


    @Test
    void shouldFindQuotationLineById() {

        QuotationLine line = new QuotationLine();
        line.setQuotationLineId(1L);

        Quotation quotation= new Quotation();
        quotation.setQuotationId(1L);
        when(quotationLineRepository.findById(1L))
                .thenReturn(Optional.of(line));

        QuotationLine result =
                quotationLineService.findQuotationLineById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getQuotationLineId()).isEqualTo(1L);
    }

    @Test
    void shouldThrowExceptionWhenLineNotFound() {

        when(quotationLineRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ElementNotFoundException.class,
                () -> quotationLineService.findQuotationLineById(1L)
        );
    }

    @Test
    void shouldFetchQuotationLinesByQuotationId() {

        QuotationLine line1 = new QuotationLine();
        QuotationLine line2 = new QuotationLine();

        Quotation quotation = new Quotation();
        quotation.setQuotationId(1L);
        line1.setQuotation(quotation);
        line2.setQuotation(quotation);
        when(quotationLineRepository
                .findByQuotation_QuotationId(1L))
                .thenReturn(List.of(line1, line2));

        List<QuotationLineDTO> result =
                quotationLineService
                        .fetchQuotationLineByQuotationId(1L);

        assertThat(result).hasSize(2);
    }

    @Test
    void shouldDeleteQuotationLineAndUpdateQuotationTotal() {

        Quotation quotation = new Quotation();
        quotation.setTotalAmount(BigDecimal.valueOf(500));

        QuotationLine line = new QuotationLine();
        line.setQuotation(quotation);
        line.setQuotationLineTotal(BigDecimal.valueOf(100));

        when(quotationLineRepository.findById(1L))
                .thenReturn(Optional.of(line));

        quotationLineService.deleteQuotationLine(1L);

        assertThat(quotation.getTotalAmount())
                .isEqualByComparingTo(BigDecimal.valueOf(400));

        verify(quotationLineRepository)
                .deleteById(1L);
    }

    @Test
    void shouldMapDtoToQuotationLine() {

        ProductVariant pv = new ProductVariant();
        pv.setProductVariantId(1L);

        ProductUnitSale pus = new ProductUnitSale();

        Quotation quotation = new Quotation();
        quotation.setQuotationId(1L);
        when(quotationRepository.findById(1L))
                .thenReturn(Optional.of(quotation));

        when(productVariantService.findProductVariantById(1L))
                .thenReturn(pv);

        when(productUnitSaleService.findProductUnitSaleById(2L))
                .thenReturn(pus);

        QuotationLineDTO dto = new QuotationLineDTO();
        dto.setProductVariantId(1L);



        dto.setQuotationId(1L);
        dto.setProductUnitSaleId(2L);
        dto.setQuantity(BigDecimal.valueOf(2));
        dto.setUnitPrice(BigDecimal.valueOf(50));
        dto.setDiscount(BigDecimal.valueOf(10));

        QuotationLine line = new QuotationLine();

        QuotationLineDTO result =
                quotationLineService.mapDTOToQuotationLine(dto, line);

        assertThat(line.getProductVariant()).isEqualTo(pv);
        assertThat(line.getProductUnitSale()).isEqualTo(pus);

        assertThat(line.getQuotationLineTotal())
                .isEqualByComparingTo(BigDecimal.valueOf(90));

        assertThat(result).isNotNull();
    }
}
