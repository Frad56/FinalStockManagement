package com.example.store.Service.stockManagment.interfaces.movmentInStock;

import com.example.store.Model.PurchaseManagement.PurchaseOrderLine;
import com.example.store.Model.StockMangement.MovementInStock.PurchaseStockMovement;
import com.example.store.Model.StockMangement.MovementInStock.SaleStockMovement;
import com.example.store.Model.salesManagement.SalesOrder;
import com.example.store.Model.salesManagement.SalesOrderLine;

public interface SaleStockMovementService {
    SaleStockMovement createSaleOrderMovement(SalesOrderLine salesOrderLine) ;

}
