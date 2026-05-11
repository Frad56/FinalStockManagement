package com.example.store.Repository.StockManagment.MovmentInStock;


import com.example.store.Model.StockMangement.MovementInStock.MovementInStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementInStockRepository extends JpaRepository<MovementInStock,Long> {

       // Optional<MovementInStock> findByPurchaseOrderLine_purchaseOrderLineId(Long purchaseOrderId);
}
