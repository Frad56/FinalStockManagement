package com.example.store.service.stockManagment.implementation;

import com.example.store.dto.stockManagment.ProductUnitPurchaseDTO;
import com.example.store.dto.stockManagment.ProductUnitSaleDTO;
import com.example.store.model.stockManagement.ProductUnitPurchase;
import com.example.store.model.stockManagement.ProductUnitSale;
import com.example.store.repository.stockManagement.ProductUnitPurchaseRepository;
import com.example.store.repository.stockManagement.ProductUnitSaleRepository;
import com.example.store.service.stockManagment.interfaces.ProductUnitPurchaseService;
import com.example.store.service.stockManagment.interfaces.ProductVariantService;
import com.example.store.service.stockManagment.interfaces.UnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUnitPurchaseServiceImpl implements ProductUnitPurchaseService {



    private final ProductUnitPurchaseRepository productUnitPurchaseRepository;
    private final ProductVariantService productVariantService;
    private final UnitService unitService;
    public ProductUnitPurchaseServiceImpl(ProductUnitPurchaseRepository productUnitPurchaseRepository,
                                      ProductVariantService productVariantService,
                                      UnitService unitService) {
        this.productUnitPurchaseRepository = productUnitPurchaseRepository;
        this.productVariantService = productVariantService;
        this.unitService = unitService;
    }


    private void mapDTOToProductUnitSale(ProductUnitPurchaseDTO productUnitPurchaseDTO , ProductUnitPurchase productUnitPurchase){
        productUnitPurchase.setProductVariant(productVariantService.findProductVariantById(productUnitPurchaseDTO.getProductVariantId()));
        productUnitPurchase.setUnit(unitService.findUnitById(productUnitPurchaseDTO.getUnitId()));
        productUnitPurchase.setConversionFactor(productUnitPurchaseDTO.getConversionFactor());
        productUnitPurchase.setUnitPrice(productUnitPurchaseDTO.getUnitPrice());
    }


    @Override
    public ProductUnitPurchase saveProductUnitSale(ProductUnitPurchaseDTO productUnitPurchaseDTO) {
        ProductUnitPurchase productUnitPurchaseEntity = new ProductUnitPurchase();
        mapDTOToProductUnitSale(productUnitPurchaseDTO,productUnitPurchaseEntity);
        return productUnitPurchaseRepository.save(productUnitPurchaseEntity);
    }

    @Override
    public ProductUnitPurchase findProductUnitPurchaseById(Long productUnitPurchaseId) {

        return productUnitPurchaseRepository.findById(productUnitPurchaseId).orElseThrow(()->
                new RuntimeException("ProductUnitPurchase not found with id: " + productUnitPurchaseId));
    }

    @Override
    public ProductUnitPurchase  updateProductUnitSale(ProductUnitPurchaseDTO productUnitPurchase,Long productUnitPurchaseId){
        ProductUnitPurchase productUnitPurchaseDB = findProductUnitPurchaseById(productUnitPurchaseId);
        mapDTOToProductUnitSale(productUnitPurchase,productUnitPurchaseDB);
        return productUnitPurchaseRepository.save(productUnitPurchaseDB);
    }

    @Override
    public void deleteProductUnitPurchaseById(Long productUnitPurchaseId) {
        if(!productUnitPurchaseRepository.existsById(productUnitPurchaseId)){
            throw new RuntimeException("ProductUnitSale not found with id: " + productUnitPurchaseId);
        }
        productUnitPurchaseRepository.deleteById(productUnitPurchaseId);
    }

    @Override
    public List<ProductUnitPurchase> fetchProductUnitPurchaseList() {
        return productUnitPurchaseRepository.findAll();
    }

    @Override
    public List<ProductUnitPurchase> findAllByProductVariantId(Long productVariantId) {
        return productUnitPurchaseRepository.findAllByProductVariant_ProductVariantId(productVariantId);
    }

}