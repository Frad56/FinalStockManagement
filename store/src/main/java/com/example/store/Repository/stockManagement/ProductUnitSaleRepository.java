package com.example.store.repository.stockManagement;


import com.example.store.model.stockManagement.ProductUnitSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductUnitSaleRepository extends JpaRepository<ProductUnitSale,Long> {

    boolean existsByProductVariant_ProductVariantId(Long productVariantId);
    boolean existsByUnit_UnitId(Long unitId);

    List<ProductUnitSale> findAllByProductVariant_ProductVariantId(Long productVariantId);

}
