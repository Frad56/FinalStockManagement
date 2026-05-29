package com.example.store.repository.salesManagement;

import com.example.store.model.salesManagement.SalesOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesOrderLineRepository extends JpaRepository<SalesOrderLine,Long> {


    List<SalesOrderLine> findBySalesOrder_SalesOrderId(Long saleOrderId);


}
