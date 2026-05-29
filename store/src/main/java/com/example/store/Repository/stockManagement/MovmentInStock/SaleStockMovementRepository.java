package com.example.store.repository.stockManagement.MovmentInStock;


import com.example.store.model.stockManagement.MovementInStock.SaleStockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleStockMovementRepository extends JpaRepository<SaleStockMovement,Long> {
}
