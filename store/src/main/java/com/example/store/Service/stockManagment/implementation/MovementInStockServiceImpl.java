package com.example.store.service.stockManagment.implementation;


import com.example.store.model.stockManagement.MovementInStock.MovementInStock;
import com.example.store.repository.stockManagement.MovmentInStock.MovementInStockRepository;
import com.example.store.service.stockManagment.interfaces.movmentInStock.MovementInStockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementInStockServiceImpl implements MovementInStockService {
//
   private final MovementInStockRepository movementInStockRepository;
//
//    private final ProductVariantService productVariantService;
//
//    private final UnitService unitService;
    public MovementInStockServiceImpl(MovementInStockRepository movementInStockRepository){
        this.movementInStockRepository = movementInStockRepository;

    }

//    private void mapDTOToMovementInStock(MovementInStockDTO movementInStockDTO, MovementInStock movementInStock) {
//        movementInStock.setDate(movementInStockDTO.getDate());
//        movementInStock.setMovementInStockType(movementInStockDTO.getMovementInStockType());
//        movementInStock.setQuantityInStock(movementInStockDTO.getQuantityInStock());
//        movementInStock.setProductVariant(productVariantService.findProductVariantById(movementInStockDTO.getProductVariantId()));
//        if(movementInStock.getUnit() != null){
//            movementInStock.setUnit(unitService.findUnitById(movementInStockDTO.getUnitId()));
//        }
//    }
//
//    @Override
//    public MovementInStock saveMovementInStock(MovementInStockDTO movementInStock) {
//        MovementInStock movementInStockDB = new MovementInStock();
//        mapDTOToMovementInStock(movementInStock, movementInStockDB);
//        return movementInStockRepository.save(movementInStockDB);
//    }
//
//
//    @Override
//    public MovementInStock createFromPurchaseOrderLine( PurchaseOrderLine purchaseOrderLine) {
//        PurchaseStockMovement movementInStockDB = new PurchaseStockMovement();
//        movementInStockDB.setDate(new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
//        movementInStockDB.setMovementInStockType(ENTRY);
//        movementInStockDB.setQuantityInStock(purchaseOrderLine.getQuantity());
//
//        ProductVariant pv =purchaseOrderLine.getProductVariant();
//        int currentStock = pv.getQuantityInStock() != null ? pv.getQuantityInStock() : 0;
//
//        int qtyToAdd = purchaseOrderLine.getQuantity().intValue();
//        pv.setQuantityInStock(currentStock + qtyToAdd);
//
//
//        movementInStockDB.setProductVariant(purchaseOrderLine.getProductVariant());
//
//        if(purchaseOrderLine.getUnit() != null){
//            movementInStockDB.setUnit(purchaseOrderLine.getUnit());
//        }
//
//        movementInStockDB.setPurchaseOrder(purchaseOrderLine.getPurchaseOrder());
//        movementInStockDB.setPurchaseOrderLine(purchaseOrderLine);
//
//        return movementInStockRepository.save(movementInStockDB);
//    }
//
//    @Override
//    public MovementInStock createFromSaleOrderLine(SalesOrderLine salesOrderLine){
//        MovementInStock movementInStock = new MovementInStock();
//        movementInStock.setDate(new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
//        movementInStock.setMovementInStockType(EXIT);
//        movementInStock.setQuantityInStock(salesOrderLine.getQuantity());
//
//        ProductVariant pv = salesOrderLine.getProductVariant();
//        int currentStock = pv.getQuantityInStock() != null ? pv.getQuantityInStock() : 0;
//
//        int qtyToSale = salesOrderLine.getQuantity().intValue();
//        pv.setQuantityInStock(currentStock - qtyToSale);
//
//        movementInStock.setProductVariant(salesOrderLine.getProductVariant());
//
//        if(salesOrderLine.getUnit() != null){
//            movementInStock.setUnit(salesOrderLine.getUnit());
//        }
//        movementInStock.setPurchaseOrder(purchaseOrderLine.getPurchaseOrder());
//        movementInStockDB.setPurchaseOrderLine(purchaseOrderLine);
//
//
//    }
//
//    @Override
//    public MovementInStock findMovementInStockByPurchaseOrderLine(Long purchaseOrderLineId) {
//        return movementInStockRepository.findByPurchaseOrderLine_purchaseOrderLineId(purchaseOrderLineId).orElseThrow(() ->
//                new RuntimeException("MovementInStock not found with purchaseOrderLineId: " + purchaseOrderLineId));
//    }
//
//    @Override
//    public MovementInStock findMovementInStockById(Long movementInStockId) {
//        return movementInStockRepository.findById(movementInStockId).orElseThrow(() ->
//                new RuntimeException("MovementInStock not found with id: " + movementInStockId));
//    }
//
//
    @Override
    public List<MovementInStock> fetchMovementInStockList() {
        return movementInStockRepository.findAll();
    }
//
//    @Override
//    public void updateFromPurchaseOrderLine( PurchaseOrderLine purchaseOrderLine, Long purchaseOrderLineId) {
//        MovementInStock movementInStockDB = findMovementInStockByPurchaseOrderLine(purchaseOrderLineId);
//        movementInStockDB.setDate(new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
//        movementInStockDB.setMovementInStockType(ENTRY);
//        movementInStockDB.setQuantityInStock(purchaseOrderLine.getQuantity());
//        movementInStockDB.setProductVariant(purchaseOrderLine.getProductVariant());
//        if (purchaseOrderLine.getUnit() != null) {
//
//            movementInStockDB.setUnit(purchaseOrderLine.getUnit());
//        }
//
//        movementInStockRepository.save(movementInStockDB);
//
//    }
//    @Override
//    public MovementInStock updateMovementInStock(MovementInStockDTO movementInStock, Long movementInStockId) {
//        MovementInStock existingMovementInStock = findMovementInStockById(movementInStockId);
//        mapDTOToMovementInStock(movementInStock, existingMovementInStock);
//        return movementInStockRepository.save(existingMovementInStock);
//    }
//
//    @Override
//    public void deleteMovementInStockById(Long movementInStockId) {
//        if (!movementInStockRepository.existsById(movementInStockId)) {
//            throw new RuntimeException("MovementInStock not found with id: " + movementInStockId);
//        }
//        movementInStockRepository.deleteById(movementInStockId);
//    }
}
