package com.example.store.Repository.StockManagment.MovmentInStock;


import com.example.store.Model.StockMangement.MovementInStock.PurchaseStockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseStockMovementRepository extends JpaRepository<PurchaseStockMovement,Long> {
}
