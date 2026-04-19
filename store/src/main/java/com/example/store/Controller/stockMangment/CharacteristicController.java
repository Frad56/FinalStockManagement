package com.example.store.Controller.stockMangment;


import com.example.store.DTO.stockManagment.CharacteristicDTO;
import com.example.store.Model.StockMangement.Characteristic;
import com.example.store.Service.stockManagment.interfaces.CharacteristicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/characteristic")
public class CharacteristicController {

    private final CharacteristicService characteristicService;

    public CharacteristicController(CharacteristicService characteristicService) {
        this.characteristicService = characteristicService;
    }

    @PostMapping("/addCharacteristic")
    public ResponseEntity<Characteristic> saveCharacteristic(@RequestBody CharacteristicDTO characteristicDTO) {
        return ResponseEntity.ok(characteristicService.saveCharacteristic(characteristicDTO));
    }

    @GetMapping("/ListCharacteristics")
    public ResponseEntity<List<Characteristic>> fetchCharacteristicList() {
        return ResponseEntity.ok(characteristicService.fetchCharacteristicList());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Characteristic> findCharacteristicById( @PathVariable("id") Long characteristicId) {
        return ResponseEntity.ok(characteristicService.findCharacteristicById(characteristicId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Characteristic> updateCharacteristic(@RequestBody  CharacteristicDTO characteristicDTO,
                                                               @PathVariable("id") Long characteristicId)
    {
        return ResponseEntity.ok(characteristicService.updateCharacteristic(characteristicDTO, characteristicId));
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteCharacteristicById( @PathVariable("id") Long characteristicId) {
        characteristicService.deleteCharacteristicById(characteristicId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }

}
