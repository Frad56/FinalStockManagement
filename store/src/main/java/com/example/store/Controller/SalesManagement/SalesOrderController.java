package com.example.store.Controller.SalesManagement;


import com.example.store.DTO.salesManagement.SalesOrderDTO;
import com.example.store.Model.salesManagement.SalesOrder;
import com.example.store.Service.salesManagement.interfaces.SalesOrderService;
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
    public ResponseEntity<SalesOrder> addSalesOrder(@Valid @RequestBody SalesOrderDTO salesOrderDTO){
        return ResponseEntity.ok(salesOrderService.saveSaleOrder(salesOrderDTO));
    }


    @GetMapping("/listSalesOrder")
    public ResponseEntity<List<SalesOrder>> fetchSalesOrderList(){
        return ResponseEntity.ok(salesOrderService.fetchSalesOrderList());
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<SalesOrder> findSalesOrderById(@PathVariable("id") Long salesOrderId){
        return ResponseEntity.ok(salesOrderService.findSalesOrderById(salesOrderId));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<SalesOrder> updateSalesOrder(
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
