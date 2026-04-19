package com.example.store.Controller.stockMangment;



import com.example.store.DTO.stockManagment.UnitDTO;
import com.example.store.Model.StockMangement.Unit;
import com.example.store.Service.stockManagment.interfaces.UnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/unit")
public class UnitController {
    private final UnitService unitService;
    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }
    @PostMapping("/addUnit")
    public ResponseEntity<Unit> saveUnit(@RequestBody  UnitDTO unitDTO) {
        return ResponseEntity.ok(unitService.saveUnit(unitDTO));
    }
    @GetMapping("/ListUnits")
    public ResponseEntity<List<Unit>> fetchUnitList() {
        return ResponseEntity.ok(unitService.fetchUnitList());
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Unit> findUnitById(@PathVariable("id") Long unitId) {
        return ResponseEntity.ok(unitService.findUnitById(unitId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteUnitById(@PathVariable("id") Long unitId) {
        unitService.deleteUnitById(unitId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Unit> updateUnit(@RequestBody  UnitDTO unitDTO,@PathVariable("id") Long unitId) {
        return ResponseEntity.ok(unitService.updateUnit(unitDTO, unitId));
    }
}
