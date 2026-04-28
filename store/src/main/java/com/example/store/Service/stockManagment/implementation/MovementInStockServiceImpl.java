package com.example.store.Service.stockManagment.implementation;


import com.example.store.DTO.PurchaseManagement.PurchaseOrderLineDTO;
import com.example.store.DTO.stockManagment.MovementInStockDTO;
import com.example.store.Model.PurchaseManagement.PurchaseOrderLine;
import com.example.store.Model.StockMangement.MovementInStock;
import com.example.store.Model.StockMangement.ProductVariant;
import com.example.store.Model.StockMangement.Unit;
import com.example.store.Repository.StockManagment.MovementInStockRepository;
import com.example.store.Service.stockManagment.interfaces.MovementInStockService;
import com.example.store.Service.stockManagment.interfaces.ProductVariantService;
import com.example.store.Service.stockManagment.interfaces.UnitService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.example.store.Model.StockMangement.MovementInStockType.ENTRY;

@Service
public class MovementInStockServiceImpl implements MovementInStockService {

    private final MovementInStockRepository movementInStockRepository;

    private final ProductVariantService productVariantService;

    private final UnitService unitService;
    public MovementInStockServiceImpl(MovementInStockRepository movementInStockRepository,
                                      ProductVariantService productVariantService
                                        ,UnitService unitService) {

        this.movementInStockRepository = movementInStockRepository;
        this.productVariantService=productVariantService;
        this.unitService=unitService;
    }

    private void mapDTOToMovementInStock(MovementInStockDTO movementInStockDTO, MovementInStock movementInStock) {
        movementInStock.setDate(movementInStockDTO.getDate());
        movementInStock.setMovementInStockType(movementInStockDTO.getMovementInStockType());
        movementInStock.setQuantityInStock(movementInStockDTO.getQuantityInStock());
        movementInStock.setProductVariant(productVariantService.findProductVariantById(movementInStockDTO.getProductVariantId()));
        if(movementInStock.getUnit() != null){
            movementInStock.setUnit(unitService.findUnitById(movementInStockDTO.getUnitId()));
        }
    }

    @Override
    public MovementInStock saveMovementInStock(MovementInStockDTO movementInStock) {
        MovementInStock movementInStockDB = new MovementInStock();
        mapDTOToMovementInStock(movementInStock, movementInStockDB);
        return movementInStockRepository.save(movementInStockDB);
    }


    @Override
    public MovementInStock saveMovementInStockForPurchaseOrderLine(MovementInStock movementInStock, PurchaseOrderLineDTO purchaseOrderLineDTO) {
        MovementInStock movementInStockDB = new MovementInStock();
        movementInStock.setDate(new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());

        if(purchaseOrderLineDTO.getUnitId() != null){
            Unit unit =unitService.findUnitById(purchaseOrderLineDTO.getUnitId());
            movementInStock.setUnit(unit);
        }
        ProductVariant pv =productVariantService.findProductVariantById(purchaseOrderLineDTO.getProductVariantId());
        movementInStock.setProductVariant(pv);
        movementInStock.setMovementInStockType(ENTRY);
        movementInStock.setQuantityInStock(purchaseOrderLineDTO.getQuantity());

        return movementInStockRepository.save(movementInStockDB);
    }

    @Override
    public MovementInStock findMovementInStockById(Long movementInStockId) {
        return movementInStockRepository.findById(movementInStockId).orElseThrow(() ->
                new RuntimeException("MovementInStock not found with id: " + movementInStockId));
    }

    @Override
    public List<MovementInStock> fetchMovementInStockList() {
        return movementInStockRepository.findAll();
    }

    @Override
    public MovementInStock updateMovementInStock(MovementInStockDTO movementInStock, Long movementInStockId) {
        MovementInStock existingMovementInStock = findMovementInStockById(movementInStockId);
        mapDTOToMovementInStock(movementInStock, existingMovementInStock);
        return movementInStockRepository.save(existingMovementInStock);
    }

    @Override
    public void deleteMovementInStockById(Long movementInStockId) {
        if (!movementInStockRepository.existsById(movementInStockId)) {
            throw new RuntimeException("MovementInStock not found with id: " + movementInStockId);
        }
        movementInStockRepository.deleteById(movementInStockId);
    }
}
