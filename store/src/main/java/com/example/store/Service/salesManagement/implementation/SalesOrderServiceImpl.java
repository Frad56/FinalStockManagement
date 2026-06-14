package com.example.store.service.salesManagement.implementation;


import com.example.store.dto.salesManagement.SalesOrderDTO;
import com.example.store.dto.salesManagement.SalesOrderLineDTO;
import com.example.store.exception.ElementNotFoundException;
import com.example.store.exception.EmptyOrInvalidFieldException;
import com.example.store.model.businessPartnerManagement.clientManagment.Client;
import com.example.store.model.stockManagement.ProductUnitSale;
import com.example.store.model.stockManagement.ProductVariant;
import com.example.store.model.salesManagement.SalesOrder;
import com.example.store.model.salesManagement.SalesOrderLine;
import com.example.store.repository.salesManagement.SalesOrderRepository;
import com.example.store.service.BusinessPartnerManagement.clientManagement.ClientService;
import com.example.store.service.salesManagement.interfaces.ProductUnitSaleService;
import com.example.store.service.salesManagement.interfaces.SalesOrderLineService;
import com.example.store.service.salesManagement.interfaces.SalesOrderService;
import com.example.store.service.stockManagment.interfaces.ProductVariantService;
import com.example.store.service.stockManagment.interfaces.movmentInStock.SaleStockMovementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {



    private  final SalesOrderRepository salesOrderRepository;
    private final ClientService clientService;
    private final ProductVariantService productVariantService;
    private final SaleStockMovementService saleStockMovementService;
    private final SalesOrderMapper salesOrderMapper;
    private final SalesOrderLineService salesOrderLineService;
    private final ProductUnitSaleService productUnitSaleService;

    public SalesOrderServiceImpl(SalesOrderRepository salesOrderRepository,
                                 ClientService clientService,
                                 ProductVariantService productVariantService,
                                 SaleStockMovementService saleStockMovementService,
                                 SalesOrderMapper salesOrderMapper,
                                 SalesOrderLineService salesOrderLineService,
                                 ProductUnitSaleService productUnitSaleService){
        this.salesOrderRepository =salesOrderRepository;
        this.clientService=clientService;
        this.productVariantService=productVariantService;
        this.saleStockMovementService=saleStockMovementService;
        this.salesOrderMapper=salesOrderMapper;
        this.salesOrderLineService=salesOrderLineService;
        this.productUnitSaleService=productUnitSaleService;


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
        return salesOrderLine.getTotalAfterDiscount();
    }

    private List<SalesOrderLine> mapDTOToSalesOrder(SalesOrderDTO salesOrderDTO,SalesOrder salesOrder){
        if(salesOrderDTO.getClientId()!= null){
            Client client = clientService.findClientById(salesOrderDTO.getClientId());
            salesOrder.setClient(client);
        }

        salesOrder.setPaymentType(salesOrderDTO.getPaymentType());

        BigDecimal saleOrderTotal=BigDecimal.ZERO;
        List<SalesOrderLine> lines = new ArrayList<>();

        for(SalesOrderLineDTO l : salesOrderDTO.getSalesOrderLineListDTO()){
            SalesOrderLine line = new SalesOrderLine();
            if(l.getProductVariantId() != null) {
                ProductVariant pv = productVariantService.findProductVariantById(l.getProductVariantId());
                line.setProductVariant(pv);
            }else {
                throw new EmptyOrInvalidFieldException("you should write product Variant Id");
            }

            if(l.getQuantity() != null  && l.getQuantity().compareTo(BigDecimal.ONE) > 1 ){
                line.setQuantity(l.getQuantity());
            }else {
                throw  new EmptyOrInvalidFieldException("invalid quantity");
            }


            if(l.getUnitPrice() != null  && l.getUnitPrice().compareTo(BigDecimal.ZERO) > 0 ){
                line.setUnitPrice(l.getUnitPrice());
            }else {
                throw  new EmptyOrInvalidFieldException("invalid price");
            }



            if(l.getDiscount() != null && l.getDiscount().compareTo(BigDecimal.ZERO) > 0){
                line.setDiscount(l.getDiscount());
            }
            if(l.getProductUnitSaleId() != null){
                ProductUnitSale pus =  productUnitSaleService.findProductUnitSaleById(l.getProductUnitSaleId());
                line.setProductUnitSale(pus);
            }


            BigDecimal lineTotal=calculateSalesOrderLineTotal(line);

            saleOrderTotal =saleOrderTotal.add(lineTotal);
            System.out.println(saleOrderTotal);

            System.out.println("Line total "+lineTotal);
            line.setSalesOrder(salesOrder);

            System.out.println("sale Order Total "+saleOrderTotal);
            lines.add(line);


        }
        salesOrder.setTotalAmount(saleOrderTotal);
        salesOrder.setOrderLines(lines);
        return lines;
    }

    @Override
    @Transactional
    public SalesOrderDTO saveSaleOrder(SalesOrderDTO salesOrderDTO){
        SalesOrder salesOrder = new SalesOrder();

        List<SalesOrderLine> lines= mapDTOToSalesOrder(salesOrderDTO,salesOrder);
        salesOrderRepository.save(salesOrder);
        for (SalesOrderLine l : lines){
            saleStockMovementService.createSaleOrderMovement(l);
        }
        //lines.forEach(saleStockMovementService::createSaleOrderMovement);

        return salesOrderMapper.salesOrderToDTO(salesOrder);
    }

    @Override
    public SalesOrderDTO updateSalesOrder(SalesOrderDTO salesOrderDTO, Long salesOrderId) {
        SalesOrder salesOrder = salesOrderRepository.findById(salesOrderId)
                .orElseThrow(() -> new ElementNotFoundException(salesOrderId));

        List<SalesOrderLine> lines = mapDTOToSalesOrder(salesOrderDTO, salesOrder);
        salesOrderRepository.save(salesOrder);

       // lines.forEach(saleStockMovementService::createSaleOrderMovement);

        return salesOrderMapper.salesOrderToDTO(salesOrder);
    }


    @Override
    public List<SalesOrderDTO> fetchSalesOrderList(){
            return salesOrderRepository.findAll()
                    .stream()
                    .map(salesOrderMapper::salesOrderToDTO)
                    .toList();
    }

    @Override
    public SalesOrderDTO findSalesOrderByIdDTO(Long salesOrderId){
        SalesOrder salesOrder= salesOrderRepository.findById(salesOrderId).orElseThrow(()->
                new ElementNotFoundException(salesOrderId));
        return salesOrderMapper.salesOrderToDTO(salesOrder);
    }

    @Override
    public SalesOrder findSalesOrderById(Long salesOrderId){
        return salesOrderRepository.findById(salesOrderId).orElseThrow(()->
                new ElementNotFoundException(salesOrderId));
    }

    @Override
    @Transactional
    public void deleteSalesOrderById(Long salesOrderId){
        SalesOrder salesOrder = salesOrderRepository.findById(salesOrderId)
                .orElseThrow(() -> new ElementNotFoundException(salesOrderId, "saleOrderError"));

     List<SalesOrderLine> lines = salesOrder.getOrderLines();

        for (SalesOrderLine line : lines) {
            System.out.println("====================================");
            System.out.println("sales Order line id "+line.getSalesOrderLineId());
        //    saleStockMovementService.deleteSaleOrderMovement(line.getSalesOrderLineId());
            salesOrderLineService.deleteSalesOrderLineById(line.getSalesOrderLineId());
        }
        salesOrderRepository.deleteById(salesOrder.getSalesOrderId());
    }


}
