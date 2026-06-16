package com.example.store.controller.quotationController;


import com.example.store.dto.purchaseManagement.PurchaseOrderLineDTO;
import com.example.store.dto.quotationManagement.QuotationDTO;
import com.example.store.model.purchaseManagement.PurchaseOrder;
import com.example.store.model.purchaseManagement.PurchaseOrderLine;
import com.example.store.service.quotationService.interfaces.QuotationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/quotation")
public class QuotationController {


    private final QuotationService quotationService;
    public QuotationController(QuotationService quotationService){
        this.quotationService=quotationService;
    }


    @PostMapping("/quotation")
    public ResponseEntity<QuotationDTO> addQuotation(@Valid @RequestBody QuotationDTO quotationDTO){
        return ResponseEntity.ok(quotationService.saveQuotation(quotationDTO));
    }


    @GetMapping("/quotationList")
    public ResponseEntity<List<QuotationDTO>> fetchQuotationList(){
        return ResponseEntity.ok(quotationService.fetchQuotationList());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteQuotationById(@PathVariable("id") Long quotationId) {
        quotationService.deleteQuotation(quotationId);
        return ResponseEntity.ok(Map.of("message", "Deleted Successfully"));
    }

}
