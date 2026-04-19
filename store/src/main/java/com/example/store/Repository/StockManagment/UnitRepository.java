package com.example.store.Repository.StockManagment;


import com.example.store.Model.StockMangement.Product;
import com.example.store.Model.StockMangement.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit,Long> {


    boolean existsByName(String name);
    boolean existsBySymbol(String symbol);
}
