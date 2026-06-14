package com.example.store.service.PurchaseManagement.interfaces;


import com.example.store.dto.purchaseManagement.PurchaseOrderLineDTO;
import com.example.store.model.purchaseManagement.PurchaseOrderLine;

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


    List<PurchaseOrderLine> findByPurchaseOrderLineByPurchaseOrderId(Long purchaseOrderId);

}
