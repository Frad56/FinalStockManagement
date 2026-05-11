package com.example.store.Service.stockManagment.implementation.MovmentInStock;


import com.example.store.Model.PurchaseManagement.PurchaseOrderLine;
import com.example.store.Model.StockMangement.MovementInStock.PurchaseStockMovement;
import com.example.store.Model.StockMangement.ProductVariant;
import com.example.store.Repository.StockManagment.MovmentInStock.PurchaseStockMovementRepository;
import com.example.store.Service.stockManagment.interfaces.movmentInStock.PurchaseStockMovementService;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.example.store.Model.StockMangement.MovementInStockType.ENTRY;

@Service
public class PurchaseStockMovementImpl implements PurchaseStockMovementService {
    private final PurchaseStockMovementRepository purchaseStockMovementRepository;

    public PurchaseStockMovementImpl(PurchaseStockMovementRepository purchaseStockMovementRepository){
        this.purchaseStockMovementRepository=purchaseStockMovementRepository;
    }

    @Override
    public PurchaseStockMovement createFromPurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
        PurchaseStockMovement movementInStockDB = new PurchaseStockMovement();
        movementInStockDB.setDate(new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
        movementInStockDB.setMovementInStockType(ENTRY);
        movementInStockDB.setQuantity(purchaseOrderLine.getQuantity());

        ProductVariant pv =purchaseOrderLine.getProductVariant();
        int currentStock = pv.getQuantityInStock() != null ? pv.getQuantityInStock() : 0;

        int qtyToAdd = purchaseOrderLine.getQuantity().intValue();
        pv.setQuantityInStock(currentStock + qtyToAdd);


        movementInStockDB.setProductVariant(purchaseOrderLine.getProductVariant());

        if(purchaseOrderLine.getUnit() != null){
            movementInStockDB.setUnit(purchaseOrderLine.getUnit());
        }

        movementInStockDB.setPurchaseOrder(purchaseOrderLine.getPurchaseOrder());
        movementInStockDB.setPurchaseOrderLine(purchaseOrderLine);

        return purchaseStockMovementRepository.save(movementInStockDB);
    }


}
