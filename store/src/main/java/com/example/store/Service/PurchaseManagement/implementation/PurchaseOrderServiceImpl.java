package com.example.store.service.PurchaseManagement.implementation;


import com.example.store.dto.PurchaseManagement.PurchaseOrderDTO;
import com.example.store.exception.ElementNotFoundException;
import com.example.store.model.purchaseManagement.PurchaseOrder;
import com.example.store.model.businessPartnerManagement.supplierManagement.Supplier;
import com.example.store.repository.purchaseManagement.PurchaseOrderRepository;
import com.example.store.service.PurchaseManagement.interfaces.PurchaseOrderService;
import com.example.store.service.BusinessPartnerManagement.supplierManagement.SupplierService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.example.store.model.purchaseManagement.Status.PENDING;


@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {


    private final SupplierService supplierService;
    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderServiceImpl(SupplierService supplierService,
                                    PurchaseOrderRepository purchaseOrderRepository){
        this.supplierService=supplierService;
        this.purchaseOrderRepository=purchaseOrderRepository;
    }

    private void mapDTOToPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO,PurchaseOrder purchaseOrder){

        Supplier supplier = supplierService.findSupplierById(purchaseOrderDTO.getSupplierId());
        purchaseOrder.setSupplier(supplier);
        purchaseOrder.setStatus(purchaseOrderDTO.getStatus());

        purchaseOrder.setTotalAmount(purchaseOrderDTO.getTotalAmount());

    }
    @Override
    public PurchaseOrder savePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO){
        PurchaseOrder purchaseOrder=new PurchaseOrder();
        mapDTOToPurchaseOrder(purchaseOrderDTO,purchaseOrder);
        purchaseOrder.setStatus(PENDING);
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder updatePurchaseOrderTotalAmount(PurchaseOrder purchaseOrder){
        return purchaseOrderRepository.save(purchaseOrder);
    }



    @Override
    public PurchaseOrder findPurchaseOrderById(Long purchaseOrderId){
        return purchaseOrderRepository.findById(purchaseOrderId).orElseThrow(()->
                new ElementNotFoundException(purchaseOrderId));
    }


    @Override
    public PurchaseOrder setTotalAmountOrderById(Long purchaseOrderId, BigDecimal totalAmount){
       PurchaseOrder purchaseOrder = findPurchaseOrderById(purchaseOrderId);
       purchaseOrder.setTotalAmount(totalAmount);
       return purchaseOrderRepository.save(purchaseOrder);

    }

    @Override
    public List<PurchaseOrder> findPurchaseOrderListNotDelivered(){
        return purchaseOrderRepository.findPurchaseOrderListNotDelivered();
    }

    @Override
    public List<PurchaseOrder> fetchPurchaseOrderList(){
        return purchaseOrderRepository.findAll();
    }

    @Override
    public PurchaseOrder updatePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO, Long purchaseOrderId){
        PurchaseOrder purchaseOrder = findPurchaseOrderById(purchaseOrderId);
        mapDTOToPurchaseOrder(purchaseOrderDTO,purchaseOrder);

        return purchaseOrderRepository.save(purchaseOrder);
    }


    @Override
    public void deletePurchaseOrderById(Long purchaseOrderId){
        findPurchaseOrderById(purchaseOrderId);
        purchaseOrderRepository.deleteById(purchaseOrderId);
    }
}
