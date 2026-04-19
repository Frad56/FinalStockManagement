package com.example.store.Repository.StockManagment;


import com.example.store.Model.StockMangement.CharacteristicValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacteristicValueRepository extends JpaRepository<CharacteristicValue, Long> {

        List<CharacteristicValue> findByProductVariant_ProductVariantId(Long productVariantId);

        @Query("SELECT cv.characteristicValueId FROM CharacteristicValue cv WHERE cv.productVariant.productVariantId = :productVariantId")
        Optional<Long> findCharacteristicValueIdByProductVariantId(Long productVariantId);


         boolean existsByProductVariant_ProductVariantId(Long productVariantId);

        boolean existsByCharacteristic_CharacteristicId(Long characteristicId);
}
