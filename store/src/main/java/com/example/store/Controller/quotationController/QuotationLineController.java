package com.example.store.controller.quotationController;



import com.example.store.dto.quotationManagement.QuotationLineDTO;

import com.example.store.service.quotationService.interfaces.QuotationLineService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/quotationLine")
public class QuotationLineController {
    private final QuotationLineService quotationLineService;
    public QuotationLineController( QuotationLineService quotationLineService){
        this.quotationLineService=quotationLineService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<List<QuotationLineDTO>> findQuotationLineList(@PathVariable("id") Long quotationLineId){
        return ResponseEntity.ok(quotationLineService.fetchQuotationLineByQuotationId(quotationLineId));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<QuotationLineDTO> updateQuotationLineList(
            @Valid @RequestBody QuotationLineDTO quotationLineDTO,
            @PathVariable("id") Long quotationLineId
    ) {
        return ResponseEntity.ok(quotationLineService.updateQuotationLine(quotationLineDTO, quotationLineId));
    }

//@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteByQuotationLine/{id}")
    public ResponseEntity<Map<String,String>> deleteQuotationLineById(@PathVariable("id") Long quotationLineId) {
        quotationLineService.deleteQuotationLine(quotationLineId);
        return ResponseEntity.ok(Map.of("message", "Deleted Successfully"));
    }


}
