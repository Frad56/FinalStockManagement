package com.example.store.service.stockManagment.implementation.MovmentInStock;


import com.example.store.model.stockManagement.MovementInStock.SaleStockMovement;
import com.example.store.model.stockManagement.MovementInStockType;
import com.example.store.model.stockManagement.ProductVariant;
import com.example.store.model.salesManagement.SalesOrderLine;
import com.example.store.repository.stockManagement.MovmentInStock.SaleStockMovementRepository;
import com.example.store.service.stockManagment.interfaces.movmentInStock.SaleStockMovementService;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.time.ZoneId.systemDefault;

@Service
public class SaleStockMovementImpl implements SaleStockMovementService {

    private final SaleStockMovementRepository saleStockMovementRepository;
    public SaleStockMovementImpl(SaleStockMovementRepository saleStockMovementRepository){
        this.saleStockMovementRepository=saleStockMovementRepository;
    }

    @Override
    public SaleStockMovement createSaleOrderMovement(SalesOrderLine salesOrderLine){
        SaleStockMovement saleStockMovementDB= new SaleStockMovement();
        saleStockMovementDB.setDate(new Date().toInstant().atZone(systemDefault()).toLocalDateTime());
        saleStockMovementDB.setMovementInStockType(MovementInStockType.EXIT);

        saleStockMovementDB.setQuantity(salesOrderLine.getQuantity());

        ProductVariant pv =salesOrderLine.getProductVariant();
        int currentStock = pv.getQuantityInStock() != null ? pv.getQuantityInStock() : 0;
        int qtyToSubtraction = salesOrderLine.getQuantity().intValue();
        pv.setQuantityInStock(currentStock - qtyToSubtraction);

        saleStockMovementDB.setProductVariant(salesOrderLine.getProductVariant());

        if(salesOrderLine.getProductUnitSale() != null){
            saleStockMovementDB.setProductUnitSale(salesOrderLine.getProductUnitSale());
        }
        saleStockMovementDB.setSalesOrder(salesOrderLine.getSalesOrder());
        saleStockMovementDB.setSalesOrderLine(salesOrderLine);

        return saleStockMovementRepository.save(saleStockMovementDB);

    }


}
