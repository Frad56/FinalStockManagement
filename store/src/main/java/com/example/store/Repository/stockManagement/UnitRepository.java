package com.example.store.repository.stockManagement;


import com.example.store.model.stockManagement.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit,Long> {


    boolean existsByName(String name);
    boolean existsBySymbol(String symbol);
}
