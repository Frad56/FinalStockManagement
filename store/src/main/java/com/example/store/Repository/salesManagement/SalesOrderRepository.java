package com.example.store.Repository.salesManagement;

import com.example.store.Model.salesManagement.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder,Long> {

}
