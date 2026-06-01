package com.example.store.controller.salesManagement;


import com.example.store.dto.salesManagement.InstallmentDTO;
import com.example.store.model.salesManagement.Installment;
import com.example.store.service.salesManagement.interfaces.InstallmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/installment")
public class InstallmentController {


    private final InstallmentService installmentService;

    public InstallmentController(InstallmentService installmentService){
        this.installmentService=installmentService;
    }


    @PostMapping("/addInstallment")
    public ResponseEntity<Installment> addInstallment(@Valid @RequestBody InstallmentDTO installmentDTO){
        return ResponseEntity.ok(installmentService.saveInstallment(installmentDTO));
    }


    @GetMapping("/listInstallment")
    public ResponseEntity<List<Installment>> fetchInstallmentList(){
        return ResponseEntity.ok(installmentService.fetchInstallmentList());
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Installment> findInstallmentById(@PathVariable("id") Long installmentId){
        return ResponseEntity.ok(installmentService.findInstallmentById(installmentId));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Installment> updateInstallment(
            @Valid @RequestBody InstallmentDTO installmentDTO,
            @PathVariable("id") Long installmentId
    ) {
        return ResponseEntity.ok(installmentService.updateInstallment(installmentDTO, installmentId));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteInstallmentById(@PathVariable("id") Long installmentId) {
        installmentService.deleteInstallmentById(installmentId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }
}
