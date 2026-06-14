package com.example.store.controller.salesManagement;


import com.example.store.dto.salesManagement.SalesOrderLineDTO;
import com.example.store.model.salesManagement.SalesOrderLine;
import com.example.store.service.salesManagement.interfaces.SalesOrderLineService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/salesOrderLine")
public class SalesOrderLineController {
    private final SalesOrderLineService salesOrderLineService;

    public SalesOrderLineController(SalesOrderLineService salesOrderLineService){
        this.salesOrderLineService=salesOrderLineService;
    }


    @PostMapping("/addSalesOrderLine")
    public ResponseEntity<SalesOrderLineDTO> addSalesOrderLine(@Valid @RequestBody SalesOrderLineDTO salesOrderLineDTO){
        return ResponseEntity.ok(salesOrderLineService.saveSaleOrderLine(salesOrderLineDTO));
    }


    @GetMapping("/listSalesOrderLine")
    public ResponseEntity<List<SalesOrderLineDTO>> fetchSalesOrderLineList(){
        return ResponseEntity.ok(salesOrderLineService.fetchSalesOrderLineList());
    }

    @GetMapping("/listSalesOrderLine/{salesOrderId}")
    public  ResponseEntity<List<SalesOrderLineDTO>> fetchSalesOrderLineListBySalesOrderId(@PathVariable("salesOrderId") Long saleOrderId){
        return ResponseEntity.ok(salesOrderLineService.fetchSalesOrderLineListBySalesOrderId(saleOrderId));

    }


    @GetMapping("/find/{id}")
    public ResponseEntity<SalesOrderLineDTO> findSalesOrderLineById(@PathVariable("id") Long salesOrderLineId){
        return ResponseEntity.ok(salesOrderLineService.findSalesOrderLineById(salesOrderLineId));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<SalesOrderLineDTO> updateSalesOrderLine(
            @Valid @RequestBody SalesOrderLineDTO salesOrderLineDTO,
            @PathVariable("id") Long salesOrderLineId
    ) {
        return ResponseEntity.ok(salesOrderLineService.updateSalesOrderLine(salesOrderLineDTO, salesOrderLineId));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteSalesOrderLineById(@PathVariable("id") Long salesOrderLineId) {
        salesOrderLineService.deleteSalesOrderLineById(salesOrderLineId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }
}
