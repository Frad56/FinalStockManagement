package com.example.store.Service.stockManagment.interfaces;


import com.example.store.DTO.stockManagment.CharacteristicValueDTO;
import com.example.store.Model.StockMangement.CharacteristicValue;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CharacteristicValueService {

    CharacteristicValue saveCharacteristicValue(CharacteristicValueDTO characteristicValueDTO);

    CharacteristicValue findCharacteristicValueById(Long characteristicValueId);

    List<CharacteristicValue> fetchCharacteristicValueList();

    CharacteristicValue updateCharacteristicValue(CharacteristicValueDTO characteristicValueDTO,Long characteristicValueId);

    void deleteCharacteristicValueById(Long characteristicValueId);

    List<CharacteristicValue>  saveAll(List<CharacteristicValueDTO> characteristicValues);

   // Map<String, String> findCharacteristicValueByProductVariantId(Long productVariantId);

}
