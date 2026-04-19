package com.example.store.Repository.StockManagment;


import com.example.store.Model.StockMangement.ProductUnitSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductUnitSaleRepository extends JpaRepository<ProductUnitSale,Long> {

    boolean existsByProduct_ProductId(Long productId);
    boolean existsByUnit_UnitId(Long unitId);

}
