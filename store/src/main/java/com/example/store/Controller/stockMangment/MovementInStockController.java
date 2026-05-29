package com.example.store.controller.stockMangment;


import com.example.store.model.stockManagement.MovementInStock.MovementInStock;
import com.example.store.service.stockManagment.interfaces.movmentInStock.MovementInStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movementInStock")
public class MovementInStockController {

    private final MovementInStockService movementInStockService;

    public MovementInStockController(MovementInStockService movementInStockService) {
        this.movementInStockService = movementInStockService;
    }
//
//    @PostMapping("/addMovementInStock")
//    public ResponseEntity<MovementInStock> saveMovementInStock( @RequestBody MovementInStockDTO movementInStockDTO) {
//        return ResponseEntity.ok(movementInStockService.saveMovementInStock(movementInStockDTO));
//    }
//
//    @GetMapping("/find/{id}")
//    public ResponseEntity<MovementInStock> findMovementInStockById( @PathVariable("id")  Long movementInStockId) {
//        return ResponseEntity.ok(movementInStockService.findMovementInStockById(movementInStockId));
//    }
//
//
    @GetMapping("/ListMovementInStock")
    public ResponseEntity<List<MovementInStock>> fetchMovementInStockList() {
        return ResponseEntity.ok(movementInStockService.fetchMovementInStockList());
    }
//
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<MovementInStock> updateMovementInStock(@RequestBody MovementInStockDTO movementInStockDTO,
//                                                                 @PathVariable("id") Long movementInStockId)
//    {        return ResponseEntity.ok(movementInStockService.updateMovementInStock(movementInStockDTO, movementInStockId));
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Map<String,String>> deleteMovementInStockById(@PathVariable("id")  Long movementInStockId) {
//        movementInStockService.deleteMovementInStockById(movementInStockId);
//        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
//    }

}
