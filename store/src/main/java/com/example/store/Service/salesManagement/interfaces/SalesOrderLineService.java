package com.example.store.Service.salesManagement.interfaces;

import com.example.store.DTO.salesManagement.SalesOrderDTO;
import com.example.store.DTO.salesManagement.SalesOrderLineDTO;
import com.example.store.Model.salesManagement.SalesOrder;
import com.example.store.Model.salesManagement.SalesOrderLine;

import java.util.List;

public interface SalesOrderLineService {

    SalesOrderLine saveSaleOrderLine(SalesOrderLineDTO salesOrderLineDTO);
    List<SalesOrderLine> fetchSalesOrderLineList();
    List<SalesOrderLine> fetchSalesOrderLineListBySalesOrderId(Long saleOrderId);

    SalesOrderLine findSalesOrderLineById(Long salesOrderLineId);
    SalesOrderLine updateSalesOrderLine(SalesOrderLineDTO salesOrderLineDTO, Long salesOrderId);
    void deleteSalesOrderLineById(Long salesOrderLineId);
    void saveListSalesOrderLine(List<SalesOrderLineDTO> salesOrderLineList);
}
