package com.example.store.repository.stockManagement.MovmentInStock;


import com.example.store.exception.ElementNotFoundException;
import com.example.store.model.stockManagement.MovementInStock.PurchaseStockMovement;
import com.example.store.model.stockManagement.MovementInStock.SaleStockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseStockMovementRepository extends JpaRepository<PurchaseStockMovement,Long> {

    Optional<PurchaseStockMovement> findByPurchaseOrderLine_PurchaseOrderLineId(Long purchaseOrderLineId);

}
