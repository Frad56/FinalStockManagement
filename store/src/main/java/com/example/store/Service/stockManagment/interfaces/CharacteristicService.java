package com.example.store.service.stockManagment.interfaces;

import com.example.store.dto.stockManagment.CharacteristicDTO;
import com.example.store.model.stockManagement.Characteristic;

import java.util.List;

public interface CharacteristicService {

    Characteristic saveCharacteristic(CharacteristicDTO characteristicDTO);

    Characteristic findCharacteristicById(Long characteristicId);

    List<Characteristic> fetchCharacteristicList();

    Characteristic updateCharacteristic(CharacteristicDTO characteristicDTO, Long characteristicId);

    void deleteCharacteristicById(Long characteristicId);
}
