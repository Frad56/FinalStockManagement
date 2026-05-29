package com.example.store.repository.stockManagement;


import com.example.store.model.stockManagement.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {

Optional<Characteristic> findByName(String name);
}
