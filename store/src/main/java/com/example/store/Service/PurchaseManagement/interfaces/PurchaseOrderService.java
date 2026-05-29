package com.example.store.service.PurchaseManagement.interfaces;

import com.example.store.dto.PurchaseManagement.PurchaseOrderDTO;
import com.example.store.model.purchaseManagement.PurchaseOrder;

import java.math.BigDecimal;
import java.util.List;

public interface PurchaseOrderService {

    PurchaseOrder savePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO);

    PurchaseOrder findPurchaseOrderById(Long purchaseOrderId);

    public PurchaseOrder setTotalAmountOrderById(Long purchaseOrderId, BigDecimal totalAmount);

    List<PurchaseOrder> findPurchaseOrderListNotDelivered();

    List<PurchaseOrder> fetchPurchaseOrderList();

    public PurchaseOrder updatePurchaseOrderTotalAmount(PurchaseOrder purchaseOrder);

    PurchaseOrder updatePurchaseOrder(PurchaseOrderDTO purchaseOrder, Long purchaseOrderId);

    void deletePurchaseOrderById(Long purchaseOrderId);





}
