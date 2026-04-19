package com.example.store.Repository.StockManagment;

import com.example.store.Model.StockMangement.MovementInStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovementInStockRepository extends JpaRepository<MovementInStock,Long> {

}
