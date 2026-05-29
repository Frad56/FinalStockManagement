package com.example.store.repository.stockManagement;


import com.example.store.model.stockManagement.ProductCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductCharacteristicRepository  extends JpaRepository<ProductCharacteristic,Long> {


    List<ProductCharacteristic> findByProduct_ProductId(Long productId);
    void deleteByProduct_ProductId(Long productId);
}
