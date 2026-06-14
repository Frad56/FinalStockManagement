package com.example.store.repository.stockManagement.MovmentInStock;


import com.example.store.model.stockManagement.MovementInStock.SaleStockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaleStockMovementRepository extends JpaRepository<SaleStockMovement,Long> {
    Optional<SaleStockMovement> findBySalesOrderLine_SalesOrderLineId(Long salesOrderLineId);
}
