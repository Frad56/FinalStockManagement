package com.example.store.Controller.stockMangment;


import com.example.store.DTO.stockManagment.CharacteristicValueDTO;
import com.example.store.Model.StockMangement.CharacteristicValue;
import com.example.store.Model.StockMangement.ProductCharacteristic;
import com.example.store.Service.stockManagment.interfaces.CharacteristicValueService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/characteristicValue")
public class CharacteristicValueController {

    private final CharacteristicValueService characteristicValueService;

    public CharacteristicValueController(CharacteristicValueService characteristicValueService) {
        this.characteristicValueService = characteristicValueService;
    }

    @PostMapping("/addCharacteristicValue")
    public ResponseEntity<CharacteristicValue> saveCharacteristicValue( @RequestBody CharacteristicValueDTO characteristicValue) {
        return ResponseEntity.ok(characteristicValueService.saveCharacteristicValue(characteristicValue));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CharacteristicValue> findCharacteristicValueById(@PathVariable("id") Long characteristicValueId) {
        return ResponseEntity.ok(characteristicValueService.findCharacteristicValueById(characteristicValueId));
    }
    @GetMapping("/ListCharacteristicValues")
    public ResponseEntity<List<CharacteristicValue>> fetchCharacteristicValueList() {
        return ResponseEntity.ok(characteristicValueService.fetchCharacteristicValueList());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CharacteristicValue> updateCharacteristicValue(@RequestBody CharacteristicValueDTO characteristicValue,
                                                                         @PathVariable("id") Long characteristicValueId) {
        return ResponseEntity.ok(characteristicValueService.updateCharacteristicValue(characteristicValue, characteristicValueId));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteCharacteristicValueById(@PathVariable("id") Long characteristicValueId) {
        characteristicValueService.deleteCharacteristicValueById(characteristicValueId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }

    @PostMapping("/save-all")
    public ResponseEntity<List<CharacteristicValue>> saveAllCharacteristicValues(@RequestBody List<CharacteristicValueDTO> characteristicValues) {
        return ResponseEntity.ok(characteristicValueService.saveAll(characteristicValues));
    }


//    @GetMapping("/allCharacteristicValuesByProductVariantId/{productVariantId}")
//    public ResponseEntity<Map<String, String> > findCharacteristicValueByProductVariantId(@PathVariable("productVariantId")  Long productVariantId) {
//        return ResponseEntity.ok(characteristicValueService.findCharacteristicValueByProductVariantId(productVariantId));
//    }

}
