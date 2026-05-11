package com.example.store.Service.salesManagement.implementation;


import com.example.store.DTO.salesManagement.SalesOrderDTO;
import com.example.store.DTO.salesManagement.SalesOrderLineDTO;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.BusinessPartnerManagement.clientManagment.Client;
import com.example.store.Model.StockMangement.MovementInStock.SaleStockMovement;
import com.example.store.Model.StockMangement.ProductVariant;
import com.example.store.Model.salesManagement.SalesOrder;
import com.example.store.Model.salesManagement.SalesOrderLine;
import com.example.store.Repository.salesManagement.SalesOrderRepository;
import com.example.store.Service.BusinessPartnerManagement.clientManagement.ClientService;
import com.example.store.Service.salesManagement.interfaces.SalesOrderService;
import com.example.store.Service.stockManagment.interfaces.ProductVariantService;
import com.example.store.Service.stockManagment.interfaces.movmentInStock.SaleStockMovementService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {



    private  final SalesOrderRepository salesOrderRepository;
    private final ClientService clientService;
    private final ProductVariantService productVariantService;
    private final SaleStockMovementService saleStockMovementService;

    public SalesOrderServiceImpl(SalesOrderRepository salesOrderRepository,
                                 ClientService clientService,
                                 ProductVariantService productVariantService,
                                 SaleStockMovementService saleStockMovementService){
        this.salesOrderRepository =salesOrderRepository;
        this.clientService=clientService;
        this.productVariantService=productVariantService;
        this.saleStockMovementService=saleStockMovementService;

    }

    @Override
    public BigDecimal calculateSalesOrderLineTotal(SalesOrderLine salesOrderLine){

        BigDecimal quantity = salesOrderLine.getQuantity();
        BigDecimal total= salesOrderLine.getUnitPrice().multiply(quantity);

        if(salesOrderLine.getDiscount() != null && salesOrderLine.getDiscount().compareTo(BigDecimal.ZERO) > 0){
            salesOrderLine.setDiscount(salesOrderLine.getDiscount());
            salesOrderLine.setTotalAfterDiscount(total.subtract(salesOrderLine.getDiscount()));
        }else {
            salesOrderLine.setTotalAfterDiscount(total);
        }

        salesOrderLine.setTotal(total);
        saleStockMovementService.createSaleOrderMovement(salesOrderLine);
        return salesOrderLine.getTotalAfterDiscount();
    }

    private void mapDTOToSalesOrder(SalesOrderDTO salesOrderDTO,SalesOrder salesOrder){
        if(salesOrderDTO.getClientId()!= null){
            Client client = clientService.findClientById(salesOrderDTO.getClientId());
            salesOrder.setClient(client);
        }

        salesOrder.setPaymentType(salesOrderDTO.getPaymentType());

        BigDecimal saleOrderTotal=BigDecimal.ZERO;
        List<SalesOrderLine> lines = new ArrayList<>();

        for(SalesOrderLineDTO l : salesOrderDTO.getSalesOrderLineListDTO()){
            SalesOrderLine line = new SalesOrderLine();
            ProductVariant pv = productVariantService.findProductVariantById(l.getProductVariantId());

            line.setProductVariant(pv);
            line.setQuantity(l.getQuantity());
            line.setUnitPrice(l.getUnitPrice());

            if(l.getDiscount() != null && l.getDiscount().compareTo(BigDecimal.ZERO) > 0){
                line.setDiscount(l.getDiscount());
            }


            BigDecimal lineTotal=calculateSalesOrderLineTotal(line);

            saleOrderTotal =saleOrderTotal.add(lineTotal);
            System.out.println(saleOrderTotal);

            System.out.println("Line total "+lineTotal);
            line.setSalesOrder(salesOrder);

            System.out.println(saleOrderTotal);
            lines.add(line);

        }
        salesOrder.setTotalAmount(saleOrderTotal);
        salesOrder.setOrderLines(lines);

    }

    @Override
    public SalesOrder saveSaleOrder(SalesOrderDTO salesOrderDTO){
        SalesOrder salesOrder = new SalesOrder();
        mapDTOToSalesOrder(salesOrderDTO,salesOrder);

        return salesOrderRepository.save(salesOrder);
    }

    @Override
    public SalesOrder updateSalesOrder(SalesOrderDTO salesOrderDTO, Long salesOrderId){
        SalesOrder salesOrder =findSalesOrderById(salesOrderId);
        mapDTOToSalesOrder(salesOrderDTO,salesOrder);
        return salesOrderRepository.save(salesOrder);

    }


    @Override
    public List<SalesOrder> fetchSalesOrderList(){
      return   salesOrderRepository.findAll();

    }

    @Override
    public SalesOrder findSalesOrderById(Long salesOrderId){
        return salesOrderRepository.findById(salesOrderId).orElseThrow(()->
                new ElementNotFoundException(salesOrderId));
    }


    @Override
    public void deleteSalesOrderById(Long salesOrderId){
     SalesOrder salesOrder = findSalesOrderById(salesOrderId);
     salesOrderRepository.deleteById(salesOrder.getSalesOrderId());
    }


}
