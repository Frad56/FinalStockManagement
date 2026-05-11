package com.example.store.Repository.salesManagement;

import com.example.store.Model.PurchaseManagement.PurchaseOrderLine;
import com.example.store.Model.salesManagement.SalesOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesOrderLineRepository extends JpaRepository<SalesOrderLine,Long> {


    List<SalesOrderLine> findBySalesOrder_SalesOrderId(Long saleOrderId);


}
