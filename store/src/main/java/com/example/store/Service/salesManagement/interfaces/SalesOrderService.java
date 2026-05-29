package com.example.store.service.salesManagement.interfaces;

import com.example.store.dto.salesManagement.SalesOrderDTO;
import com.example.store.model.salesManagement.SalesOrder;
import com.example.store.model.salesManagement.SalesOrderLine;

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
