package com.example.store.service.salesManagement.implementation;

import com.example.store.dto.salesManagement.SalesOrderDTO;
import com.example.store.dto.salesManagement.SalesOrderLineDTO;
import com.example.store.exception.ArgumentNotValidException;
import com.example.store.exception.ElementNotFoundException;
import com.example.store.model.stockManagement.ProductUnitSale;
import com.example.store.model.stockManagement.ProductVariant;
import com.example.store.model.salesManagement.SalesOrder;
import com.example.store.model.salesManagement.SalesOrderLine;
import com.example.store.repository.salesManagement.SalesOrderLineRepository;
import com.example.store.repository.salesManagement.SalesOrderRepository;
import com.example.store.service.salesManagement.interfaces.ProductUnitSaleService;
import com.example.store.service.salesManagement.interfaces.SalesOrderLineService;
import com.example.store.service.salesManagement.interfaces.SalesOrderService;
import com.example.store.service.stockManagment.interfaces.ProductVariantService;
import com.example.store.service.stockManagment.interfaces.movmentInStock.SaleStockMovementService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class SalesOrderLineServiceImpl  implements SalesOrderLineService  {

    private final SalesOrderLineRepository salesOrderLineRepository;
    private final ProductVariantService productVariantService;
    private final SalesOrderRepository salesOrderRepository;
    private final SalesOrderMapper salesOrderMapper;
    private final SaleStockMovementService saleStockMovementService;
    private final ProductUnitSaleService productUnitSaleService;

    public SalesOrderLineServiceImpl(SalesOrderLineRepository salesOrderLineRepository,ProductVariantService productVariantService,
                                     SalesOrderRepository salesOrderRepository,
                                     SalesOrderMapper salesOrderMapper,
                                     SaleStockMovementService saleStockMovementService,
                                     ProductUnitSaleService productUnitSaleService){
        this.salesOrderLineRepository=salesOrderLineRepository;
        this.productVariantService=productVariantService;
        this.salesOrderRepository=salesOrderRepository;
        this.salesOrderMapper=salesOrderMapper;
        this.saleStockMovementService=saleStockMovementService;
        this.productUnitSaleService=productUnitSaleService;
    }


    private void mapDTOToSalesOrderLine(SalesOrderLineDTO salesOrderLineDTO,SalesOrderLine salesOrderLine){
        if(salesOrderLineDTO.getSalesOrderId() != null){
            SalesOrder salesOrder= salesOrderRepository.findById(salesOrderLineDTO.getSalesOrderId()).orElseThrow(()->
                    new ElementNotFoundException(salesOrderLineDTO.getSalesOrderId()));
            salesOrderLine.setSalesOrder(salesOrder);
        }else {
            throw new ArgumentNotValidException("Sale Order Id should not be null");
        }

        if(salesOrderLineDTO.getProductVariantId() != null){
            ProductVariant pv = productVariantService.findProductVariantById(salesOrderLineDTO.getProductVariantId());
            salesOrderLine.setProductVariant(pv);

            BigDecimal unitPrice = salesOrderLineDTO.getUnitPrice() != null   && salesOrderLineDTO.getUnitPrice().compareTo(BigDecimal.ZERO) > 0
                    ? salesOrderLineDTO.getUnitPrice()
                    : pv.getSpecificPrice();

            if(salesOrderLineDTO.getQuantity() != null){
                BigDecimal quantity = salesOrderLineDTO.getQuantity();
                salesOrderLine.setQuantity(quantity);

                BigDecimal total = unitPrice.multiply(quantity);
                salesOrderLine.setTotal(total);
                salesOrderLine.setUnitPrice(unitPrice);
                if(salesOrderLineDTO.getDiscount() != null && salesOrderLineDTO.getDiscount().compareTo(BigDecimal.ZERO) > 0){
                    salesOrderLine.setDiscount(salesOrderLineDTO.getDiscount());
                    salesOrderLine.setTotalAfterDiscount(total.subtract(salesOrderLine.getDiscount()));

                }else {
                    salesOrderLine.setTotalAfterDiscount(total);
                }
            }else {
               throw new ArgumentNotValidException("Quantity should not be null");
            }
                if(salesOrderLineDTO.getProductUnitSaleId() != null){
                    ProductUnitSale pus =  productUnitSaleService.findProductUnitSaleById(salesOrderLineDTO.getProductUnitSaleId());
                    salesOrderLine.setProductUnitSale(pus);
                }


        }else {
            throw new ArgumentNotValidException("Product Variant Id should not be null");
        }
    }


    @Override
    public  SalesOrder findSalesOrder(Long salesOrderId){
        return salesOrderRepository.findById(salesOrderId).orElseThrow(()->
                new ElementNotFoundException(salesOrderId));

    }
    @Override
    public SalesOrderLineDTO saveSaleOrderLine(SalesOrderLineDTO salesOrderLineDTO){
        SalesOrderLine salesOrderLine =new SalesOrderLine();
        mapDTOToSalesOrderLine(salesOrderLineDTO,salesOrderLine);
        salesOrderLineRepository.save(salesOrderLine);
        return salesOrderMapper.salesOrderLineToDTO(salesOrderLine);
    }
    @Override
    public List<SalesOrderLineDTO> fetchSalesOrderLineList(){
        return salesOrderLineRepository.findAll()
                .stream()
                .map(salesOrderMapper::salesOrderLineToDTO)
                .toList();
    }

    @Override
    public  List<SalesOrderLineDTO> fetchSalesOrderLineListBySalesOrderId(Long saleOrderId){
        return salesOrderLineRepository
                .findBySalesOrder_SalesOrderId(saleOrderId)
                .stream()
                .map(salesOrderMapper::salesOrderLineToDTO)
                .toList();
    }


    @Override
    public SalesOrderLineDTO findSalesOrderLineById(Long salesOrderLineId){
        SalesOrderLine line =
                salesOrderLineRepository.findById(salesOrderLineId)
                        .orElseThrow(() -> new ElementNotFoundException(salesOrderLineId));

        return salesOrderMapper.salesOrderLineToDTO(line);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public SalesOrderLineDTO updateSalesOrderLine(SalesOrderLineDTO salesOrderLineDTO, Long salesOrderLineId){
        SalesOrderLine salesOrderLine =salesOrderLineRepository.findById(salesOrderLineId)
                .orElseThrow(() -> new ElementNotFoundException(salesOrderLineId));

        BigDecimal oldOrderLineTotal = salesOrderLine.getTotalAfterDiscount();
        System.out.println("Old salesOrderLineTotal :"+oldOrderLineTotal);

        mapDTOToSalesOrderLine(salesOrderLineDTO,salesOrderLine);


        SalesOrder salesOrder=findSalesOrder(salesOrderLineDTO.getSalesOrderId());

        BigDecimal oldSalesOrderTotal= salesOrder.getTotalAmount();

        BigDecimal subtractOldSaleOrderLine  = oldSalesOrderTotal.subtract(oldOrderLineTotal);

        BigDecimal newSaleOrderTotal = subtractOldSaleOrderLine.add(salesOrderLine.getTotalAfterDiscount());
        System.out.println("Old salesOrderLine :"+newSaleOrderTotal);
        salesOrder.setTotalAmount(newSaleOrderTotal);

        SalesOrderLine saved = salesOrderLineRepository.save(salesOrderLine);

        return salesOrderMapper.salesOrderLineToDTO(saved);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteSalesOrderLineById(Long salesOrderLineId){
        SalesOrderLine line =
                salesOrderLineRepository.findById(salesOrderLineId)
                        .orElseThrow(() -> new ElementNotFoundException(salesOrderLineId));

        SalesOrder saleOrder = line.getSalesOrder();

        BigDecimal totalAmount = saleOrder.getTotalAmount();
        BigDecimal totalSalesOrderLine = line.getTotalAfterDiscount();

        if(totalAmount != null &&  totalSalesOrderLine != null){

            System.out.println("total avant le delete "+totalAmount);
            BigDecimal newTotalAmount = totalAmount.subtract(totalSalesOrderLine);
            saleOrder.setTotalAmount(newTotalAmount);
            System.out.println("Total apres le delete "+saleOrder.getTotalAmount());
        }
        saleStockMovementService.deleteSaleOrderMovement(salesOrderLineId);
        salesOrderLineRepository.deleteById(salesOrderLineId);
    }

    @Override
    public void saveListSalesOrderLine(List<SalesOrderLineDTO> salesOrderLineList){
        for(SalesOrderLineDTO salesOrderLineDTO:salesOrderLineList){
            ProductVariant pv = productVariantService.findProductVariantById(salesOrderLineDTO.getProductVariantId());
            SalesOrder salesOrder= findSalesOrder(salesOrderLineDTO.getSalesOrderId());

            SalesOrderLine salesOrderLine = new SalesOrderLine();
            mapDTOToSalesOrderLine(salesOrderLineDTO,salesOrderLine);
            salesOrderLineRepository.save(salesOrderLine);
        }

    }




}
