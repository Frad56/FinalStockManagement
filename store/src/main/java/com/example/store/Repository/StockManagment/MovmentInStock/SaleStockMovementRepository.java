package com.example.store.Repository.StockManagment.MovmentInStock;


import com.example.store.Model.StockMangement.MovementInStock.SaleStockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleStockMovementRepository extends JpaRepository<SaleStockMovement,Long> {
}
