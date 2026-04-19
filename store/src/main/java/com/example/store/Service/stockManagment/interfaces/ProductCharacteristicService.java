package com.example.store.Service.stockManagment.interfaces;


import com.example.store.DTO.stockManagment.ProductCharacteristicDTO;
import com.example.store.Model.StockMangement.Characteristic;
import com.example.store.Model.StockMangement.ProductCharacteristic;

import java.util.List;

public interface ProductCharacteristicService {

    ProductCharacteristic saveProductCharacteristic(ProductCharacteristicDTO productCharacteristic);
    ProductCharacteristic findProductCharacteristicById(Long productCharacteristicId);
    List<ProductCharacteristic> fetchProductCharacteristicList();
    ProductCharacteristic updateProductCharacteristic(ProductCharacteristicDTO productCharacteristic,Long productCharacteristicId);

    void deleteProductCharacteristicByProductId(Long productId);
    void deleteProductCharacteristicById(Long productCharacteristicId);

    List<ProductCharacteristic> saveProductCharacteristicList(List<Long> characteristicList, Long productId);
    List<ProductCharacteristic> findProductCharacteristicByProductId(Long productId);

}
