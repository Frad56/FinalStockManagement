package com.example.store.Repository.PurchaseManagement;

import com.example.store.Model.PurchaseManagement.PurchaseOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PurchaseOrderLineRepository extends JpaRepository<PurchaseOrderLine,Long> {


    List<PurchaseOrderLine> findByPurchaseOrder_PurchaseOrderId(Long purchaseOrderId);

    void deleteByPurchaseOrder_PurchaseOrderId(Long purchaseOrderId);
}
