package com.example.store.service.stockManagment.interfaces;


import com.example.store.dto.stockManagment.CharacteristicValueDTO;
import com.example.store.model.stockManagement.CharacteristicValue;

import java.util.List;
import java.util.Map;

public interface CharacteristicValueService {

    CharacteristicValue saveCharacteristicValue(CharacteristicValueDTO characteristicValueDTO);

    CharacteristicValue findCharacteristicValueById(Long characteristicValueId);

    List<CharacteristicValue> fetchCharacteristicValueList();

    CharacteristicValue updateCharacteristicValue(CharacteristicValueDTO characteristicValueDTO,Long characteristicValueId);

    void deleteCharacteristicValueById(Long characteristicValueId);

    List<CharacteristicValue>  saveAll(List<CharacteristicValueDTO> characteristicValues);

   Map<String, String> findCharacteristicValueByProductVariantId(Long productVariantId);

    void updateCharacteristicValues(Long productVariantId, Map<String, String> newValues);

}
