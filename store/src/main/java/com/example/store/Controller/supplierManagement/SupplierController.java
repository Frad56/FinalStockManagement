package com.example.store.Controller.supplierManagement;


import com.example.store.DTO.supplierManagement.SupplierDTO;
import com.example.store.Model.supplierManagement.Supplier;
import com.example.store.Service.supplierManagement.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/supplier")
public class SupplierController {

    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService){
        this.supplierService =supplierService;
    }

    @PostMapping("/addSupplier")
    public ResponseEntity<Supplier> saveSupplier(@Valid @RequestBody SupplierDTO dto){
        Supplier supplier = supplierService.saveSupplier(dto);
        return ResponseEntity.ok(supplier);
    }

    @GetMapping("/suppliers")
    public ResponseEntity<List<Supplier>> getListSuppliers(){
        return ResponseEntity.ok(supplierService.fetchSupplierList());
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Supplier> findSupplierById (@PathVariable("id") Long supplierId){
        Supplier supplier = supplierService.findSupplierById(supplierId);
        return ResponseEntity.ok(supplier);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String,String>> deleteSupplierById(@PathVariable("id") Long supplierId){
        supplierService.deleteSupplierByID(supplierId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Supplier> updateSupplierById(
            @RequestBody SupplierDTO supplierDTO,
            @PathVariable("id") Long supplierId){

        Supplier updateSupplier = supplierService.updateSupplier(supplierDTO,supplierId);
        return ResponseEntity.ok(updateSupplier);
    }
}
