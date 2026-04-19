package com.example.store.Repository.StockManagment;

import com.example.store.Model.StockMangement.Aisle;
import com.example.store.Model.StockMangement.Category;
import com.example.store.Model.StockMangement.Product;
import com.example.store.Model.StockMangement.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByAisle(Aisle aisle);

    Optional<Product> findByReference(String reference);

    boolean existsByReference(String reference);

    boolean existsByCategory_Name(String name);

    boolean existsByDesignation(String designation);

    List<Product> findByReferenceStartingWithIgnoreCase(String keyword);

    List<Product> findByDesignationStartingWithIgnoreCase(String keyword);

    List<Product> findByCategoryNameStartingWithIgnoreCase(String keyword);



}
