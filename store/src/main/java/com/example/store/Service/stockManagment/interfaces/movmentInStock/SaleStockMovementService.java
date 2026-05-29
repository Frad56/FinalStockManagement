package com.example.store.service.stockManagment.interfaces.movmentInStock;

import com.example.store.model.stockManagement.MovementInStock.SaleStockMovement;
import com.example.store.model.salesManagement.SalesOrderLine;

public interface SaleStockMovementService {
    SaleStockMovement createSaleOrderMovement(SalesOrderLine salesOrderLine) ;

}
