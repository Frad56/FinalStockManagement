package com.example.store.repository.stockManagement;

import com.example.store.model.stockManagement.ProductUnitPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductUnitPurchaseRepository extends JpaRepository<ProductUnitPurchase,Long> {

    boolean existsByProductVariant_ProductVariantId(Long productVariantId);
    boolean existsByUnit_UnitId(Long unitId);
    List<ProductUnitPurchase> findAllByProductVariant_ProductVariantId(Long productVariantId);

}
