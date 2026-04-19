package com.example.store.Controller.PurchaseManagement;


import com.example.store.DTO.PurchaseManagement.PurchaseOrderLineDTO;
import com.example.store.DTO.PurchaseManagement.request.PurchaseOrderLineRequest;
import com.example.store.Model.PurchaseManagement.PurchaseOrderLine;
import com.example.store.Service.PurchaseManagement.interfaces.PurchaseOrderLineService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/purchaseOrderLine")
public class PurchaseOrderLineController {
    private final PurchaseOrderLineService purchaseOrderLineService;

    public PurchaseOrderLineController(PurchaseOrderLineService purchaseOrderLineService){
        this.purchaseOrderLineService=purchaseOrderLineService;
    }

    @PostMapping("/addPurchaseOrderLine")
    public ResponseEntity<PurchaseOrderLine> addPurchaseOrderLine(@Valid @RequestBody PurchaseOrderLineDTO purchaseOrderLineDTO){
        return ResponseEntity.ok(purchaseOrderLineService.savePurchaseOrderLine(purchaseOrderLineDTO));
    }

    @GetMapping("/listPurchaseOrderLine")
    public ResponseEntity<List<PurchaseOrderLine>> fetchPurchaseOrderLineList(){
        return ResponseEntity.ok(purchaseOrderLineService.fetchPurchaseOrderLineList());
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<PurchaseOrderLine> findPurchaseOrderLineById(@PathVariable("id") Long purchaseOrderLineId){
        return ResponseEntity.ok(purchaseOrderLineService.findPurchaseOrderLineById(purchaseOrderLineId));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<PurchaseOrderLine> updatePurchaseOrderLine(
            @Valid @RequestBody PurchaseOrderLineDTO purchaseOrderLineDTO,
            @PathVariable("id") Long purchaseOrderLineId
            ) {

        return ResponseEntity.ok(purchaseOrderLineService.updatePurchaseOrderLine(purchaseOrderLineDTO, purchaseOrderLineId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deletePurchaseOrderLineById(@PathVariable("id") Long purchaseOrderLineId) {
        purchaseOrderLineService.deletePurchaseOrderLineById(purchaseOrderLineId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }


    @PostMapping("/addPurchaseOrderLineList")
    public ResponseEntity<Map<String,String>> addPurchaseOrderLineList(@Valid @RequestBody List<PurchaseOrderLineRequest>  purchaseOrderLineCreateRequest){
        purchaseOrderLineService.saveListOfPurchaseOrder(purchaseOrderLineCreateRequest);
        return ResponseEntity.ok(Map.of("message","List Of PurchaseOrder added Successfully"));
    }

}
