package com.example.store.controller.purchaseManagement;

import com.example.store.dto.PurchaseManagement.PurchaseOrderDTO;
import com.example.store.model.purchaseManagement.PurchaseOrder;
import com.example.store.service.PurchaseManagement.interfaces.PurchaseOrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/purchaseOrder")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService){
        this.purchaseOrderService=purchaseOrderService;
    }

    @PostMapping("/addPurchaseOrder")
    public ResponseEntity<PurchaseOrder> addPurchaseOrder(@Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO){
        return ResponseEntity.ok(purchaseOrderService.savePurchaseOrder(purchaseOrderDTO));
    }


    @GetMapping("/listPurchaseOrder")
    public ResponseEntity<List<PurchaseOrder>> fetchPurchaseOrderList(){
        return ResponseEntity.ok(purchaseOrderService.fetchPurchaseOrderList());
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<PurchaseOrder> findPurchaseOrderById(@PathVariable("id") Long purchaseOrderId){
        return ResponseEntity.ok(purchaseOrderService.findPurchaseOrderById(purchaseOrderId));
    }

    @GetMapping("/listNotDelivered")
    public ResponseEntity<List<PurchaseOrder>> findPurchaseOrderListNotDelivered(){
        return ResponseEntity.ok(purchaseOrderService.findPurchaseOrderListNotDelivered());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(
            @Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO,
            @PathVariable("id") Long purchaseOrderId
    ) {
        return ResponseEntity.ok(purchaseOrderService.updatePurchaseOrder(purchaseOrderDTO, purchaseOrderId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deletePurchaseOrderById(@PathVariable("id") Long purchaseOrderId) {
        purchaseOrderService.deletePurchaseOrderById(purchaseOrderId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }
}
