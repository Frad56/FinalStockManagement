package com.example.store.Repository.BusinessPartner.supplierManagement;

import com.example.store.Model.BusinessPartnerManagement.supplierManagement.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
