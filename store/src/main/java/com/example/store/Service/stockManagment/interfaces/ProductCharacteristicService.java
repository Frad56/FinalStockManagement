package com.example.store.service.stockManagment.interfaces;


import com.example.store.dto.stockManagment.ProductCharacteristicDTO;
import com.example.store.model.stockManagement.ProductCharacteristic;

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
