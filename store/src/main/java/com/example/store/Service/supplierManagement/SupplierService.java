package com.example.store.Service.supplierManagement;

import com.example.store.DTO.supplierManagement.SupplierDTO;
import com.example.store.Model.supplierManagement.Supplier;

import java.util.List;

public interface SupplierService {

    Supplier saveSupplier(SupplierDTO supplier);

    Supplier findSupplierById(Long supplierId);

    List<Supplier> fetchSupplierList();

    void deleteSupplierByID(Long supplierId);

     Supplier updateSupplier(SupplierDTO supplierDTO , Long supplierId);
}
