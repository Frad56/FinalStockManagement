package com.example.store.service.stockManagment.interfaces.movmentInStock;


import com.example.store.model.purchaseManagement.PurchaseOrderLine;
import com.example.store.model.stockManagement.MovementInStock.PurchaseStockMovement;

public interface PurchaseStockMovementService {

     PurchaseStockMovement createFromPurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) ;

    }
