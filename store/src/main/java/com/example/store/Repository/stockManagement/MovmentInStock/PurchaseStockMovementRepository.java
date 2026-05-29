package com.example.store.repository.stockManagement.MovmentInStock;


import com.example.store.model.stockManagement.MovementInStock.PurchaseStockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseStockMovementRepository extends JpaRepository<PurchaseStockMovement,Long> {
}
