package com.example.store.service.quotationService.implementation;


import com.example.store.dto.quotationManagement.QuotationLineDTO;
import com.example.store.exception.ElementNotFoundException;
import com.example.store.exception.EmptyOrInvalidFieldException;
import com.example.store.model.quotationManagement.Quotation;
import com.example.store.model.quotationManagement.QuotationLine;
import com.example.store.model.stockManagement.ProductUnitSale;
import com.example.store.model.stockManagement.ProductVariant;
import com.example.store.repository.quotationManagement.QuotationLineRepository;
import com.example.store.repository.quotationManagement.QuotationRepository;
import com.example.store.service.quotationService.interfaces.QuotationLineService;
import com.example.store.service.quotationService.interfaces.QuotationService;
import com.example.store.service.salesManagement.interfaces.ProductUnitSaleService;
import com.example.store.service.stockManagment.interfaces.ProductVariantService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuotationLineServiceImpl  implements QuotationLineService {

    private final QuotationLineRepository quotationLineRepository;
    private final QuotationRepository quotationRepository;
    private final ProductUnitSaleService productUnitSaleService;
    private final ProductVariantService productVariantService;
    private final MappedQuotationToDTO mappedQuotationToDTO;

    public QuotationLineServiceImpl(QuotationLineRepository quotationLineRepository,
                                    QuotationRepository quotationRepository,
                                    ProductUnitSaleService productUnitSaleService,
                                    ProductVariantService productVariantService,
                                    MappedQuotationToDTO mappedQuotationToDTO){
        this.quotationLineRepository=quotationLineRepository;
        this.quotationRepository=quotationRepository;
        this.productUnitSaleService=productUnitSaleService;
        this.productVariantService=productVariantService;
        this.mappedQuotationToDTO=mappedQuotationToDTO;
    }




    public QuotationLineDTO quotationLineToDTO(QuotationLine line){
        QuotationLineDTO quotationLineDTO = new QuotationLineDTO();

        quotationLineDTO.setQuotationId(line.getQuotation().getQuotationId());
        quotationLineDTO.setQuotationLineId(line.getQuotationLineId());
        quotationLineDTO.setQuotationLineTotal(line.getQuotationLineTotal());
        quotationLineDTO.setDiscount(line.getDiscount());

        return quotationLineDTO;
    }


    @Override
    public QuotationLineDTO mapDTOToQuotationLine(QuotationLineDTO dtoLine , QuotationLine line){


        if(dtoLine.getQuotationId() != null){
            Quotation quotation = quotationRepository.findById(dtoLine.getQuotationId()).orElseThrow(()
                    ->new ElementNotFoundException(dtoLine.getQuotationId()));
            line.setQuotation(quotation);
        }else {
            throw new EmptyOrInvalidFieldException("you should write quotation Id");

        }
        //pv
        if(dtoLine.getProductVariantId() != null) {
            ProductVariant pv = productVariantService.findProductVariantById(dtoLine.getProductVariantId());
            line.setProductVariant(pv);
        }else {
            throw new EmptyOrInvalidFieldException("you should write product Variant Id");
        }
        //discount
        if(dtoLine.getDiscount()!= null && dtoLine.getDiscount().compareTo(BigDecimal.ZERO) > 0 ){
            line.setDiscount(dtoLine.getDiscount());
        }

        //unit Price
        if(dtoLine.getUnitPrice() != null && dtoLine.getUnitPrice().compareTo(BigDecimal.ZERO) > 0 ){
            line.setUnitPrice(dtoLine.getUnitPrice() );
        }else {
            throw  new EmptyOrInvalidFieldException("invalid unit Price");
        }
        //qte
        if(dtoLine.getQuantity() != null  &&dtoLine.getUnitPrice().compareTo(BigDecimal.ZERO) > 0 ){
            line.setQuantity(dtoLine.getQuantity());
        }else {
            throw  new EmptyOrInvalidFieldException("invalid quantity");
        }

        //qte p u s
        if(dtoLine.getProductUnitSaleId()!=null){
            ProductUnitSale pus = productUnitSaleService.findProductUnitSaleById(dtoLine.getProductUnitSaleId());
            line.setProductUnitSale(pus);
        }

        BigDecimal lineTotal =calculateQuotationLineTotal(line);

        //total
//        quotationTotal =quotationTotal.add(lineTotal);
//        System.out.println("============ Sale Order Total      ===================");
//        System.out.println(quotationTotal);

        System.out.println("============ Line Total     ===================");
        System.out.println(lineTotal);
        quotationLineRepository.save(line);
        return quotationLineToDTO(line);
    }


    @Override
    public List<QuotationLineDTO> fetchQuotationLineByQuotationId(Long quotationId){
        List<QuotationLine> quotationLineList = quotationLineRepository.findByQuotation_QuotationId(quotationId);
        List<QuotationLineDTO>   quotationLineDTOList = new ArrayList<>();
        for (QuotationLine ql :quotationLineList){
            quotationLineDTOList.add(quotationLineToDTO(ql));
        }
        return quotationLineDTOList;
    }


    @Override
    public  QuotationLine findQuotationLineById(Long quotationLineId){
       return quotationLineRepository.findById(quotationLineId).orElseThrow(()
                ->new ElementNotFoundException(quotationLineId));
    }
    @Override
    public QuotationLineDTO updateQuotationLine(QuotationLineDTO dto,Long id){
        QuotationLine quotationLine =findQuotationLineById(id);

        BigDecimal oldQuotationLineTotal = quotationLine.getQuotationLineTotal();
        System.out.println("Old Quotation Line Total "+oldQuotationLineTotal);

        mapDTOToQuotationLine(dto,quotationLine);

        Quotation quotation= quotationRepository.findById(dto.getQuotationId()).
                orElseThrow(()->new ElementNotFoundException(" quotation Not found!"));

        BigDecimal oldQuotationTotal= quotation.getTotalAmount();

        BigDecimal subtractOldQuotationLine =oldQuotationTotal.subtract(oldQuotationLineTotal);

        BigDecimal newQuotationTotal = subtractOldQuotationLine.add(quotationLine.getQuotationLineTotal());
        System.out.println("Old Quotation total :" + oldQuotationTotal);
        System.out.println("New Quotation total :" + quotationLine.getQuotationLineTotal());
        quotation.setTotalAmount(newQuotationTotal);

        quotationLineRepository.save(quotationLine);

        return  quotationLineToDTO(quotationLine);
    }


    @Override
    public BigDecimal calculateQuotationLineTotal(QuotationLine quotationLine){


        BigDecimal quantity = quotationLine.getQuantity();
        BigDecimal total= quotationLine.getUnitPrice().multiply(quantity);

        if(quotationLine.getDiscount() != null && quotationLine.getDiscount().compareTo(BigDecimal.ZERO) > 0){
            quotationLine.setDiscount(quotationLine.getDiscount());
            quotationLine.setQuotationLineTotal(total.subtract(quotationLine.getDiscount()));
        }else {
            quotationLine.setQuotationLineTotal(total);
        }

        return quotationLine.getQuotationLineTotal();
    }




    @Override
    public void deleteQuotationLine(Long quotationLineId){
        QuotationLine quotationLine =findQuotationLineById(quotationLineId);
        Quotation quotation = quotationLine.getQuotation();

        BigDecimal quotationLineTotalAmount = quotationLine.getQuotationLineTotal();
        BigDecimal totalAmount= quotation.getTotalAmount();

        if(quotationLineTotalAmount != null &&  totalAmount != null){

            System.out.println("Line total avant le delete "+quotationLineTotalAmount+ " ,total avant: "+totalAmount);
            BigDecimal newTotalAmount = totalAmount.subtract(quotationLineTotalAmount);
            quotation.setTotalAmount(newTotalAmount);
            System.out.println("Total apres le delete "+quotation.getTotalAmount());
        }
        quotationLineRepository.deleteById(quotationLineId);
    }
}
