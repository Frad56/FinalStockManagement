package com.example.store.Service.stockManagment.interfaces;


import com.example.store.DTO.stockManagment.MovementInStockDTO;
import com.example.store.Model.StockMangement.MovementInStock;

import java.util.List;

public interface MovementInStockService {


        MovementInStock saveMovementInStock(MovementInStockDTO movementInStock);
        MovementInStock findMovementInStockById(Long movementInStockId);
        List<MovementInStock> fetchMovementInStockList();
        MovementInStock updateMovementInStock(MovementInStockDTO movementInStock,Long movementInStockId);
        void deleteMovementInStockById(Long movementInStockId);
}
