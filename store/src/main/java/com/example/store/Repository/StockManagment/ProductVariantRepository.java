package com.example.store.Repository.StockManagment;



import com.example.store.Model.StockMangement.Product;
import com.example.store.Model.StockMangement.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    Optional<ProductVariant> findByCode(String code);

    boolean existsByProduct_ProductId(Long productId);

    boolean existsByProduct_Reference(String reference);
    boolean existsByProduct_Designation(String designation);
    boolean existsByProduct_Category_Name(String categoryName);

    List<ProductVariant> findByProduct_ProductId(Long productId);

    List<ProductVariant> findByProduct_reference(String reference);

    List<ProductVariant> findByProduct_designation(String designation);


    List<ProductVariant> findByCodeStartingWithIgnoreCase(String keyword);


    @Query("""
    SELECT pv 
    FROM ProductVariant pv 
    WHERE pv.product.category.name = :category_name """)
    List<ProductVariant> findByCategoryName(@Param("category_name") String category_name);



    @Query("SELECT pv.product FROM ProductVariant pv  WHERE LOWER(pv.product.reference) LIKE LOWER (CONCAT(:keyword, '%'))")
    List<Product> findByProductReference(String keyword);

    @Query("SELECT pv.product FROM ProductVariant pv  WHERE LOWER(pv.product.designation) LIKE LOWER (CONCAT(:keyword, '%'))")
    List<Product> findByProductDesignation(String keyword);

    @Query("SELECT pv.product FROM ProductVariant pv  WHERE LOWER(pv.product.category.name) LIKE LOWER (CONCAT(:keyword, '%'))")
    List<Product> findByProductCategoryName(String keyword);


}