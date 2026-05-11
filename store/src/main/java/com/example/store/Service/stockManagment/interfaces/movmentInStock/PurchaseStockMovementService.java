package com.example.store.Service.stockManagment.interfaces.movmentInStock;


import com.example.store.Model.PurchaseManagement.PurchaseOrderLine;
import com.example.store.Model.StockMangement.MovementInStock.PurchaseStockMovement;

public interface PurchaseStockMovementService {

     PurchaseStockMovement createFromPurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) ;

    }
