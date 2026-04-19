package com.example.store.Service.stockManagment.implementation;


import com.example.store.DTO.stockManagment.CharacteristicDTO;
import com.example.store.Exception.ElementAlreadyExistException;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Exception.ResourceInUseException;
import com.example.store.Model.StockMangement.Characteristic;
import com.example.store.Repository.StockManagment.CharacteristicRepository;
import com.example.store.Repository.StockManagment.CharacteristicValueRepository;
import com.example.store.Service.stockManagment.interfaces.CharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {


    private final CharacteristicRepository characteristicRepository;
    private  final CharacteristicValueRepository characteristicValueRepository;


    public CharacteristicServiceImpl(CharacteristicRepository characteristicRepository,
                                     CharacteristicValueRepository characteristicValueRepository){
        this.characteristicRepository=characteristicRepository;
        this.characteristicValueRepository=characteristicValueRepository;
    }


    private void mapDTOToCharacteristic(CharacteristicDTO characteristicDTO,Characteristic characteristic){
        String characteristicName = characteristicDTO.getName().trim().toLowerCase();
        characteristic.setName(characteristicName);
        characteristic.setType(characteristicDTO.getType());
    }

    @Override
    public Characteristic saveCharacteristic(CharacteristicDTO characteristicDTO){
        String characteristicName = characteristicDTO.getName().trim().toLowerCase();
        if(characteristicRepository.findByName(characteristicName).isPresent()){
            throw new ElementAlreadyExistException("the Characteristic ",characteristicName);
        }
        Characteristic characteristic = new Characteristic();
        mapDTOToCharacteristic(characteristicDTO,characteristic);
        return characteristicRepository.save(characteristic);
    }

    @Override
    public Characteristic findCharacteristicById(Long characteristicId){
        return characteristicRepository.findById(characteristicId).orElseThrow(()->
                new ElementNotFoundException(characteristicId));
    }

    @Override
    public List<Characteristic> fetchCharacteristicList(){
        return characteristicRepository.findAll();
    }

    @Override
    public Characteristic updateCharacteristic(CharacteristicDTO characteristicDTO, Long characteristicId){
        Characteristic characteristicDB = findCharacteristicById(characteristicId);
        mapDTOToCharacteristic(characteristicDTO,characteristicDB);
        return characteristicRepository.save(characteristicDB);
    }


    @Override
    public void deleteCharacteristicById(Long characteristicId){
        if(!characteristicRepository.existsById(characteristicId)){
            throw new ElementNotFoundException(characteristicId);
        }
        boolean isCharacteristicUsed = characteristicValueRepository.existsByCharacteristic_CharacteristicId(characteristicId);

    if(isCharacteristicUsed) {
        throw new ResourceInUseException("This characteristic is already used and cannot be deleted");
    }
        characteristicRepository.deleteById(characteristicId);
    }



}
