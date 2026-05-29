package com.example.store.repository.stockManagement;

import com.example.store.model.stockManagement.Aisle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AisleRepository  extends JpaRepository<Aisle, Long> {


  Optional<Aisle> findByName(String name);


}
