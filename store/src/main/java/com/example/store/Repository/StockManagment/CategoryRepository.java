package com.example.store.Repository.StockManagment;


import com.example.store.Model.StockMangement.Category;
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


}
