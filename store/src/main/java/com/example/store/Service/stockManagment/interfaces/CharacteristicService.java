package com.example.store.Service.stockManagment.interfaces;

import com.example.store.DTO.stockManagment.CharacteristicDTO;
import com.example.store.Model.StockMangement.Characteristic;

import java.util.List;

public interface CharacteristicService {

    Characteristic saveCharacteristic(CharacteristicDTO characteristicDTO);

    Characteristic findCharacteristicById(Long characteristicId);

    List<Characteristic> fetchCharacteristicList();

    Characteristic updateCharacteristic(CharacteristicDTO characteristicDTO, Long characteristicId);

    void deleteCharacteristicById(Long characteristicId);
}
