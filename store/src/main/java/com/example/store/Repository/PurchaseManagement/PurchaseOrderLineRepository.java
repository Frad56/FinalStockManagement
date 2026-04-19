package com.example.store.Repository.PurchaseManagement;

import com.example.store.Model.PurchaseManagement.PurchaseOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderLineRepository extends JpaRepository<PurchaseOrderLine,Long> {
}
