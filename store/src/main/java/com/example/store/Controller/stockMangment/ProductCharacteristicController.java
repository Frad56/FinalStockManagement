package com.example.store.Controller.stockMangment;


import com.example.store.DTO.stockManagment.ProductCharacteristicDTO;
import com.example.store.Model.StockMangement.Characteristic;
import com.example.store.Model.StockMangement.ProductCharacteristic;
import com.example.store.Service.stockManagment.interfaces.ProductCharacteristicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/productCharacteristic")
public class ProductCharacteristicController {
    private final ProductCharacteristicService productCharacteristicService;

    public ProductCharacteristicController(ProductCharacteristicService productCharacteristicService) {
        this.productCharacteristicService = productCharacteristicService;
    }

    @PostMapping("/addProductCharacteristic")
    public ResponseEntity<ProductCharacteristic> saveProductCharacteristic( @RequestBody ProductCharacteristicDTO productCharacteristicDTO) {
        return ResponseEntity.ok(productCharacteristicService.saveProductCharacteristic(productCharacteristicDTO));
    }


    @GetMapping("/ListProductCharacteristics")
    public ResponseEntity<List<ProductCharacteristic>> fetchProductCharacteristicList() {
        return ResponseEntity.ok(productCharacteristicService.fetchProductCharacteristicList());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProductCharacteristic> findProductCharacteristicById(@PathVariable("id") Long productCharacteristicId) {
        return ResponseEntity.ok(productCharacteristicService.findProductCharacteristicById(productCharacteristicId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductCharacteristic> updateProductCharacteristic( @RequestBody ProductCharacteristicDTO productCharacteristicDTO,
                                                                              @PathVariable("id") Long productCharacteristicId)
    {         return ResponseEntity.ok(productCharacteristicService.updateProductCharacteristic(productCharacteristicDTO, productCharacteristicId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteProductCharacteristicById(@PathVariable("id") Long productCharacteristicId) {
        productCharacteristicService.deleteProductCharacteristicById(productCharacteristicId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }

    @PostMapping("/addProductCharacteristicList/{productId}")
    public ResponseEntity<List<ProductCharacteristic>> saveAll( @RequestBody List<Long> characteristicList,@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productCharacteristicService.saveProductCharacteristicList(characteristicList,productId));
    }

    @GetMapping("/allProductCharacteristicsByProductId/{productId}")
    public ResponseEntity<List<ProductCharacteristic>> findProductCharacteristicByProductId(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productCharacteristicService.findProductCharacteristicByProductId(productId));
    }
}
