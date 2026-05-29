package com.example.store.repository.businessPartner.supplierManagement;

import com.example.store.model.businessPartnerManagement.supplierManagement.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
