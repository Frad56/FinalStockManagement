package com.example.store.Service.salesManagement.implementation;

import com.example.store.DTO.salesManagement.SalesOrderDTO;
import com.example.store.DTO.salesManagement.SalesOrderLineDTO;
import com.example.store.Exception.ArgumentNotValidException;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.StockMangement.ProductVariant;
import com.example.store.Model.salesManagement.SalesOrder;
import com.example.store.Model.salesManagement.SalesOrderLine;
import com.example.store.Repository.salesManagement.SalesOrderLineRepository;
import com.example.store.Service.salesManagement.interfaces.SalesOrderLineService;
import com.example.store.Service.salesManagement.interfaces.SalesOrderService;
import com.example.store.Service.stockManagment.interfaces.ProductVariantService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class SalesOrderLineServiceImpl  implements SalesOrderLineService  {

    private final SalesOrderLineRepository salesOrderLineRepository;
    private final ProductVariantService productVariantService;
    private final SalesOrderService salesOrderService;
    public SalesOrderLineServiceImpl(SalesOrderLineRepository salesOrderLineRepository,ProductVariantService productVariantService,
                                     SalesOrderService salesOrderService){
        this.salesOrderLineRepository=salesOrderLineRepository;
        this.productVariantService=productVariantService;
        this.salesOrderService=salesOrderService;
    }


    private void mapDTOToSalesOrderLine(SalesOrderLineDTO salesOrderLineDTO,SalesOrderLine salesOrderLine){
        if(salesOrderLineDTO.getSalesOrderId() != null){
            SalesOrder salesOrder = salesOrderService.findSalesOrderById(salesOrderLineDTO.getSalesOrderId());
            salesOrderLine.setSalesOrder(salesOrder);
        }else {
            throw new ArgumentNotValidException("Sale Order Id should not be null");
        }

        if(salesOrderLineDTO.getProductVariantId() != null){
            ProductVariant pv = productVariantService.findProductVariantById(salesOrderLineDTO.getProductVariantId());
            salesOrderLine.setProductVariant(pv);
            BigDecimal unitPrice = pv.getSpecificPrice();

            if(salesOrderLineDTO.getQuantity() != null){
                BigDecimal quantity = salesOrderLine.getQuantity();
                salesOrderLine.setQuantity(quantity);

                BigDecimal total = unitPrice.multiply(quantity);
                salesOrderLine.setTotal(total);
                if(salesOrderLineDTO.getDiscount() != null && salesOrderLineDTO.getDiscount().compareTo(BigDecimal.ZERO) > 0){
                    salesOrderLine.setDiscount(salesOrderLineDTO.getDiscount());
                    salesOrderLine.setTotalAfterDiscount(total.subtract(salesOrderLine.getDiscount()));

                }else {
                    salesOrderLine.setTotalAfterDiscount(total);
                }
            }else {
               throw new ArgumentNotValidException("Quantity should not be null");
            }


        }else {
            throw new ArgumentNotValidException("Product Variant Id should not be null");
        }
    }

    @Override
    public SalesOrderLine saveSaleOrderLine(SalesOrderLineDTO salesOrderLineDTO){
        SalesOrderLine salesOrderLine =new SalesOrderLine();
        mapDTOToSalesOrderLine(salesOrderLineDTO,salesOrderLine);
        return salesOrderLineRepository.save(salesOrderLine);
    }
    @Override
    public List<SalesOrderLine> fetchSalesOrderLineList(){
        return salesOrderLineRepository.findAll();
    }

    @Override
    public  List<SalesOrderLine> fetchSalesOrderLineListBySalesOrderId(Long saleOrderId){
        return salesOrderLineRepository.findBySalesOrder_SalesOrderId(saleOrderId);
    }


    @Override
    public SalesOrderLine findSalesOrderLineById(Long salesOrderLineId){
        return salesOrderLineRepository.findById(salesOrderLineId).orElseThrow(()->
                new ElementNotFoundException(salesOrderLineId));
    }

    @Override
    public SalesOrderLine updateSalesOrderLine(SalesOrderLineDTO salesOrderLineDTO, Long salesOrderLineId){
        SalesOrderLine salesOrderLine =findSalesOrderLineById(salesOrderLineId);



        BigDecimal oldOrderLineTotal = salesOrderLine.getTotalAfterDiscount();
        System.out.println("Old salesOrderLineTotal :"+oldOrderLineTotal);

        mapDTOToSalesOrderLine(salesOrderLineDTO,salesOrderLine);


        SalesOrder salesOrder = salesOrderService.findSalesOrderById(salesOrderLineDTO.getSalesOrderId());
        BigDecimal oldSalesOrderTotal= salesOrder.getTotalAmount();

        BigDecimal subtractOldSaleOrderLine  = oldSalesOrderTotal.subtract(oldOrderLineTotal);

        BigDecimal newSaleOrderTotal = subtractOldSaleOrderLine.add(salesOrderLine.getTotalAfterDiscount());
        System.out.println("Old salesOrderLine :"+newSaleOrderTotal);
        salesOrder.setTotalAmount(newSaleOrderTotal);


        return salesOrderLineRepository.save(salesOrderLine);
    }

    @Override
    public void deleteSalesOrderLineById(Long salesOrderLineId){
        SalesOrderLine salesOrderLine =findSalesOrderLineById(salesOrderLineId);
        SalesOrder saleOrder = salesOrderLine.getSalesOrder();

        BigDecimal totalAmount = saleOrder.getTotalAmount();
        BigDecimal totalSalesOrderLine = salesOrderLine.getTotalAfterDiscount();

        if(totalAmount != null || totalSalesOrderLine != null){

            System.out.println("total avant le delete "+totalAmount);
            BigDecimal newTotalAmount = totalAmount.subtract(totalSalesOrderLine);
            saleOrder.setTotalAmount(newTotalAmount);
            System.out.println("Total apres le delete "+saleOrder.getTotalAmount());
        }

        salesOrderLineRepository.deleteById(salesOrderLineId);
    }

    @Override
    public void saveListSalesOrderLine(List<SalesOrderLineDTO> salesOrderLineList){
        for(SalesOrderLineDTO salesOrderLineDTO:salesOrderLineList){
            ProductVariant pv = productVariantService.findProductVariantById(salesOrderLineDTO.getProductVariantId());
            SalesOrder salesOrder =salesOrderService.findSalesOrderById(salesOrderLineDTO.getSalesOrderId());

            SalesOrderLine salesOrderLine = new SalesOrderLine();
            mapDTOToSalesOrderLine(salesOrderLineDTO,salesOrderLine);
            salesOrderLineRepository.save(salesOrderLine);
        }

    }




}
