package com.example.store.repository.salesManagement;

import com.example.store.model.salesManagement.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder,Long> {

}
