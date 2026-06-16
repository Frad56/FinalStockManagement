package com.example.store.controller.quotationController;



import com.example.store.dto.quotationManagement.QuotationLineDTO;

import com.example.store.service.quotationService.interfaces.QuotationLineService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/findByQuotationId/{id}")
    public ResponseEntity<List<QuotationLineDTO>> findQuotationLineList(@PathVariable("id") Long quotationLineId){
        return ResponseEntity.ok(quotationLineService.fetchQuotationLineByQuotationId(quotationLineId));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<QuotationLineDTO> findQuotationLineById(@PathVariable("id") Long quotationLineId){
        return ResponseEntity.ok(quotationLineService.findQuotationLineByIdAndReturnDTO(quotationLineId));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<QuotationLineDTO> updateQuotationLine(
            @Valid @RequestBody QuotationLineDTO quotationLineDTO,
            @PathVariable("id") Long quotationLineId
    ) {
        return ResponseEntity.ok(quotationLineService.updateQuotationLine(quotationLineDTO, quotationLineId));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteQuotationLineById(@PathVariable("id") Long quotationLineId) {
        quotationLineService.deleteQuotationLine(quotationLineId);
        return ResponseEntity.ok(Map.of("message", "Deleted Successfully"));
    }

    @PostMapping("/quotationLine")
    public ResponseEntity<QuotationLineDTO> createQuotationLineByQuotationId(@Valid @RequestBody
                               QuotationLineDTO quotationLineDTO) {
        return ResponseEntity.ok(quotationLineService.createQuotationLineFromQuotationId(quotationLineDTO));
    }


}
