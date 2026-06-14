package com.example.store.service.salesManagement.interfaces;

import com.example.store.dto.salesManagement.SalesOrderDTO;
import com.example.store.model.salesManagement.SalesOrder;
import com.example.store.model.salesManagement.SalesOrderLine;

import java.math.BigDecimal;
import java.util.List;

public interface SalesOrderService {

    SalesOrderDTO saveSaleOrder(SalesOrderDTO salesOrderDTO);
    List<SalesOrderDTO> fetchSalesOrderList();
    BigDecimal calculateSalesOrderLineTotal(SalesOrderLine salesOrderLine);
    SalesOrderDTO findSalesOrderByIdDTO(Long salesOrderId);
    SalesOrder findSalesOrderById(Long salesOrderId);
    SalesOrderDTO updateSalesOrder(SalesOrderDTO salesOrderDTO, Long salesOrderId);
    void deleteSalesOrderById(Long salesOrderId);

}
