package com.example.store.Service.PurchaseManagement.interfaces;


import com.example.store.DTO.PurchaseManagement.PurchaseOrderLineDTO;
import com.example.store.DTO.PurchaseManagement.request.PurchaseOrderLineListRequest;
import com.example.store.DTO.PurchaseManagement.request.PurchaseOrderLineRequest;
import com.example.store.Model.PurchaseManagement.PurchaseOrderLine;

import java.util.List;

public interface PurchaseOrderLineService {

    PurchaseOrderLine savePurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLineDTO);

    PurchaseOrderLine findPurchaseOrderLineById(Long purchaseOrderLineId);

    List<PurchaseOrderLine> fetchPurchaseOrderLineList();

    PurchaseOrderLine updatePurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLineDTO, Long purchaseOrderLineId);

    void deletePurchaseOrderLineById(Long purchaseOrderLineId);

    void saveListOfPurchaseOrder(List<PurchaseOrderLineRequest> purchaseOrderLineCreateRequests);
}
