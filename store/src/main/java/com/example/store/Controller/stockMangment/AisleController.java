package com.example.store.Controller.stockMangment;


import com.example.store.DTO.stockManagment.AisleDTO;
import com.example.store.Model.StockMangement.Aisle;
import com.example.store.Service.stockManagment.interfaces.AisleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/aisle")
public class AisleController {

    private final AisleService aisleService;

    public AisleController(AisleService aisleService) {
        this.aisleService = aisleService;
    }

    @PostMapping("/addAisle")
    public ResponseEntity<Aisle> addAisle(
            @Valid @RequestBody AisleDTO aisle
    ) {
        return ResponseEntity.ok(aisleService.saveAisle(aisle));
    }

    @GetMapping("/ListAisles")
    public ResponseEntity<List<Aisle>> fetchAisleList() {
        return ResponseEntity.ok(aisleService.fetchAisleList());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Aisle> findAisleById(@PathVariable("id") Long aisleId) {
        return ResponseEntity.ok(aisleService.findAisleById(aisleId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Aisle> updateAisle(
            @Valid @RequestBody AisleDTO aisleDTO,
            @PathVariable("id") Long aisleId
    ) {
        return ResponseEntity.ok(aisleService.updateAisle(aisleDTO, aisleId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteAisleById(@PathVariable("id") Long aisleId) {
        aisleService.deleteAisleById(aisleId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }

}
