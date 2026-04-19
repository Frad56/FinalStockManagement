package com.example.store.Service.PurchaseManagement.interfaces;

import com.example.store.DTO.PurchaseManagement.PurchaseOrderDTO;
import com.example.store.Model.PurchaseManagement.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {

    PurchaseOrder savePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO);

    PurchaseOrder findPurchaseOrderById(Long purchaseOrderId);

    List<PurchaseOrder> fetchPurchaseOrderList();

    PurchaseOrder updatePurchaseOrder(PurchaseOrderDTO purchaseOrder, Long purchaseOrderId);

    void deletePurchaseOrderById(Long purchaseOrderId);


}
