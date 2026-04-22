package com.example.store.Service.PurchaseManagement.interfaces;


import com.example.store.DTO.PurchaseManagement.PurchaseOrderLineDTO;
import com.example.store.Model.PurchaseManagement.PurchaseOrderLine;

import java.util.List;

public interface PurchaseOrderLineService {

    public PurchaseOrderLine savePurchaseOrderLineWithPercentage(PurchaseOrderLineDTO purchaseOrderDTO);
    public PurchaseOrderLine savePurchaseOrderLineWithoutPercentage(PurchaseOrderLineDTO purchaseOrderDTO);

    PurchaseOrderLine findPurchaseOrderLineById(Long purchaseOrderLineId);

    List<PurchaseOrderLine> fetchPurchaseOrderLineList();

    PurchaseOrderLine updatePurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLineDTO, Long purchaseOrderLineId);

    void deletePurchaseOrderLineById(Long purchaseOrderLineId);

    void saveListOfPurchaseOrderIfDiscountWithoutPercentage(List<PurchaseOrderLineDTO> purchaseOrderLineCreateRequests);

    void saveListOfPurchaseOrderIfDiscountWithPercentage(List<PurchaseOrderLineDTO> purchaseOrderLineCreateRequests);

    void totalAmountOfPurchaseOrder(Long purchaseOrderId);

    void deleteByPurchaseOrder(Long purchaseOrderId);
}
