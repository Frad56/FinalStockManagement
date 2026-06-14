package com.example.store.service.salesManagement.interfaces;

import com.example.store.dto.salesManagement.SalesOrderLineDTO;
import com.example.store.exception.ElementNotFoundException;
import com.example.store.model.salesManagement.SalesOrder;
import com.example.store.model.salesManagement.SalesOrderLine;

import java.util.List;

public interface SalesOrderLineService {

    SalesOrderLineDTO saveSaleOrderLine(SalesOrderLineDTO salesOrderLineDTO);
    List<SalesOrderLineDTO> fetchSalesOrderLineList();
    List<SalesOrderLineDTO> fetchSalesOrderLineListBySalesOrderId(Long saleOrderId);

    SalesOrder findSalesOrder(Long salesOrderId);
    SalesOrderLineDTO findSalesOrderLineById(Long salesOrderLineId);
    SalesOrderLineDTO updateSalesOrderLine(SalesOrderLineDTO salesOrderLineDTO, Long salesOrderId);
    void deleteSalesOrderLineById(Long salesOrderLineId);
    void saveListSalesOrderLine(List<SalesOrderLineDTO> salesOrderLineList);
}
