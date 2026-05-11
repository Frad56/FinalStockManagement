package com.example.store.Service.salesManagement.interfaces;

import com.example.store.DTO.salesManagement.SalesOrderDTO;
import com.example.store.DTO.salesManagement.SalesOrderLineDTO;
import com.example.store.Model.salesManagement.SalesOrder;
import com.example.store.Model.salesManagement.SalesOrderLine;

import java.math.BigDecimal;
import java.util.List;

public interface SalesOrderService {

    SalesOrder saveSaleOrder(SalesOrderDTO salesOrderDTO);
    List<SalesOrder> fetchSalesOrderList();
    BigDecimal calculateSalesOrderLineTotal(SalesOrderLine salesOrderLine);
    SalesOrder findSalesOrderById(Long salesOrderId);
    SalesOrder updateSalesOrder(SalesOrderDTO salesOrderDTO, Long salesOrderId);
    void deleteSalesOrderById(Long salesOrderId);

}
