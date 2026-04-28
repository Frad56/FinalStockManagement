package com.example.store.Controller.PurchaseManagement;


import com.example.store.DTO.PurchaseManagement.PurchaseOrderLineDTO;
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

    @PostMapping("/addPurchaseOrderLineWithoutPercentage")
    public ResponseEntity<PurchaseOrderLine> addPurchaseOrderLineWithoutPercentage(@Valid @RequestBody PurchaseOrderLineDTO purchaseOrderLineDTO){
        return ResponseEntity.ok(purchaseOrderLineService.savePurchaseOrderLineWithoutPercentage(purchaseOrderLineDTO));
    }

    @PostMapping("/addPurchaseOrderLineWithPercentage")
    public ResponseEntity<PurchaseOrderLine> addPurchaseOrderLineWithPercentage(@Valid @RequestBody PurchaseOrderLineDTO purchaseOrderLineDTO){
        return ResponseEntity.ok(purchaseOrderLineService.savePurchaseOrderLineWithPercentage(purchaseOrderLineDTO));
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


    @PostMapping("/addPurchaseOrderLineListWithoutPercentage")
    public ResponseEntity<Map<String,String>> addPurchaseOrderLineListWithoutPercentage(@Valid @RequestBody List<PurchaseOrderLineDTO>  purchaseOrderLineCreateRequest){
        purchaseOrderLineService.saveListOfPurchaseOrderIfDiscountWithoutPercentage(purchaseOrderLineCreateRequest);
        return ResponseEntity.ok(Map.of("message","List Of PurchaseOrder added Successfully"));
    }

    @PostMapping("/addPurchaseOrderLineListWithPercentage")
    public ResponseEntity<Map<String,String>> addPurchaseOrderLineListWithPercentage(@Valid @RequestBody List<PurchaseOrderLineDTO>  purchaseOrderLineCreateRequest){
        purchaseOrderLineService.saveListOfPurchaseOrderIfDiscountWithPercentage(purchaseOrderLineCreateRequest);
        return ResponseEntity.ok(Map.of("message","List Of PurchaseOrder added Successfully"));
    }

    @PostMapping("/totalAmountOfPurchaseOrder/{id}")
    public ResponseEntity<Map<String,String>> totalOfPurchaseOrder(@PathVariable("id") Long purchaseOrderId){
        purchaseOrderLineService.totalAmountOfPurchaseOrder(purchaseOrderId);
        return ResponseEntity.ok(Map.of("message","Total Price Updated Successfully"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteByPurchaseOrder/{id}")
    public ResponseEntity<Map<String,String>> deleteByPurchaseOrder(@PathVariable("id") Long purchaseOrderId) {
        purchaseOrderLineService.deleteByPurchaseOrder(purchaseOrderId);
        return ResponseEntity.ok(Map.of("message", "Deleted Successfully"));
    }

    @GetMapping("/findByPurchaseOrder/{purchaseOrderId}")
    public ResponseEntity<List<PurchaseOrderLine>> findListByPurchaseOrderDate(@PathVariable("purchaseOrderId") Long purchaseOrderId) {
        return ResponseEntity.ok(purchaseOrderLineService.findByPurchaseOrderLineByPurchaseOrderId(purchaseOrderId));
    }
}
