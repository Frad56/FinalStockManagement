package com.example.store.service.salesManagement.implementation;


import com.example.store.dto.salesManagement.SalesOrderDTO;
import com.example.store.dto.salesManagement.SalesOrderLineDTO;
import com.example.store.model.salesManagement.SalesOrder;
import com.example.store.model.salesManagement.SalesOrderLine;
import org.springframework.stereotype.Service;

@Service
public class SalesOrderMapper {

    public SalesOrderDTO salesOrderToDTO(SalesOrder order) {

        SalesOrderDTO dto = new SalesOrderDTO();

        dto.setSalesOrderId(order.getSalesOrderId());
        dto.setSalesOrderDate(order.getSalesOrderDate());

        if(  order.getClient() != null){
             dto.setClientId(order.getClient().getClientId());
            dto.setClientFirstName(order.getClient().getFirstName());
            dto.setClientLastName(order.getClient().getLastName());
        }

        dto.setTotalAmount(order.getTotalAmount());
        dto.setPaymentType(order.getPaymentType());

        dto.setSalesOrderLineListDTO(
                order.getOrderLines()
                        .stream()
                        .map(this::salesOrderLineToDTO)
                        .toList()
        );

        return dto;
    }


    public SalesOrderLineDTO salesOrderLineToDTO(SalesOrderLine line) {

        SalesOrderLineDTO dto = new SalesOrderLineDTO();

        dto.setSalesOrderLineId(line.getSalesOrderLineId());

        dto.setSalesOrderId(
                line.getSalesOrder() != null ? line.getSalesOrder().getSalesOrderId() : null
        );

        dto.setProductUnitSaleName(
                line.getProductUnitSale() != null && line.getProductUnitSale().getUnit() != null
                        ? line.getProductUnitSale().getUnit().getName()
                        : null
        );
        dto.setProductVariantId(
                line.getProductVariant() != null ? line.getProductVariant().getProductVariantId() : null
        );

        dto.setProductUnitSaleId(
                line.getProductUnitSale() != null ? line.getProductUnitSale().getProductUnitSaleId() : null
        );

        dto.setProductVariantCode(line.getProductVariant().getCode());
        dto.setProductReference(line.getProductVariant().getProduct().getReference());
        dto.setProductDesignation(line.getProductVariant().getProduct().getDesignation());
        dto.setProductBrand(line.getProductVariant().getProduct().getBrand());


        dto.setQuantity(line.getQuantity());
        dto.setUnitPrice(line.getUnitPrice());
        dto.setTotal(line.getTotal());
        dto.setDiscount(line.getDiscount());
        dto.setTotalAfterDiscount(line.getTotalAfterDiscount());

        return dto;
    }
}
