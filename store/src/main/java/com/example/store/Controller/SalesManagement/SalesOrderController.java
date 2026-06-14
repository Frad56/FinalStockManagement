package com.example.store.controller.salesManagement;


import com.example.store.dto.salesManagement.SalesOrderDTO;
import com.example.store.model.salesManagement.SalesOrder;
import com.example.store.service.salesManagement.interfaces.SalesOrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/salesOrder")
public class SalesOrderController {


    private final SalesOrderService salesOrderService;

    public SalesOrderController(SalesOrderService salesOrderService){
        this.salesOrderService=salesOrderService;
    }




    @PostMapping("/addSalesOrder")
    public ResponseEntity<SalesOrderDTO> addSalesOrder(@Valid @RequestBody SalesOrderDTO salesOrderDTO){
        return ResponseEntity.ok(salesOrderService.saveSaleOrder(salesOrderDTO));
    }


    @GetMapping("/listSalesOrder")
    public ResponseEntity<List<SalesOrderDTO>> fetchSalesOrderList(){
        return ResponseEntity.ok(salesOrderService.fetchSalesOrderList());
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<SalesOrderDTO> findSalesOrderById(@PathVariable("id") Long salesOrderId){
        return ResponseEntity.ok(salesOrderService.findSalesOrderByIdDTO(salesOrderId));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<SalesOrderDTO> updateSalesOrder(
            @Valid @RequestBody SalesOrderDTO salesOrderDTO,
            @PathVariable("id") Long salesOrderId
    ) {
        return ResponseEntity.ok(salesOrderService.updateSalesOrder(salesOrderDTO, salesOrderId));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteSalesOrderById(@PathVariable("id") Long salesOrderId) {
        salesOrderService.deleteSalesOrderById(salesOrderId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }
}
