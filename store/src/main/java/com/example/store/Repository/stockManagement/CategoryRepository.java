package com.example.store.repository.stockManagement;


import com.example.store.model.stockManagement.Category;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query("""
    SELECT c FROM Category c
    WHERE c.categoryId NOT IN (
    SELECT c2.parent.categoryId
    FROM Category c2
    WHERE c2.parent IS NOT NULL
    )
    """)
    List<Category> findLeafCategories();

    @Query("SELECT c FROM Category c LEFT JOIN Product p ON p.category = c" +
            " WHERE p.productId IS NULL")
    List<Category> findCategoriesWithoutProducts();

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.children")
    List<Category> findAllWithChildren();


    List<Category> findByParentIsNull();
    @Query("SELECT c FROM Category c WHERE c.parent IS NULL")
    List<Category> findRootCategories();


    @Query("SELECT COUNT(p) > 0 FROM Product p WHERE p.category.categoryId = :categoryId")
    boolean existsProductByCategoryId(@Param("categoryId") Long categoryId);

}
