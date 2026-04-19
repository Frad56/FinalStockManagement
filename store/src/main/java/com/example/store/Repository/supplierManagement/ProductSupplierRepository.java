package com.example.store.Repository.supplierManagement;

import com.example.store.Model.supplierManagement.ProductSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductSupplierRepository extends JpaRepository<ProductSupplier,Long> {
}
