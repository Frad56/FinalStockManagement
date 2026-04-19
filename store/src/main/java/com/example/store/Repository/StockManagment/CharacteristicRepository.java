package com.example.store.Repository.StockManagment;


import com.example.store.Model.StockMangement.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {

Optional<Characteristic> findByName(String name);
}
