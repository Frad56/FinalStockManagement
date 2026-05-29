package com.example.store.service.salesManagement.interfaces;

import com.example.store.dto.salesManagement.SalesOrderLineDTO;
import com.example.store.model.salesManagement.SalesOrderLine;

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
