package com.example.store.Service.stockManagment.interfaces.movmentInStock;


import com.example.store.Model.StockMangement.MovementInStock.MovementInStock;

import java.util.List;

public interface MovementInStockService {


//        MovementInStock saveMovementInStock(MovementInStockDTO movementInStock);
//        MovementInStock createFromPurchaseOrderLine( PurchaseOrderLine purchaseOrderLine);
//
//        MovementInStock createFromSaleOrderLine( PurchaseOrderLine purchaseOrderLine);
//
//
//        MovementInStock findMovementInStockByPurchaseOrderLine(Long movementInStockId);
//        MovementInStock findMovementInStockById(Long movementInStockId);
//
        List<MovementInStock> fetchMovementInStockList();
//
//        void updateFromPurchaseOrderLine( PurchaseOrderLine purchaseOrderLine, Long purchaseOrderLineId);
//        MovementInStock updateMovementInStock(MovementInStockDTO movementInStock,Long movementInStockId);
//        void deleteMovementInStockById(Long movementInStockId);
}
