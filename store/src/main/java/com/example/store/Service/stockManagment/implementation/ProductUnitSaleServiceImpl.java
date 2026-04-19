package com.example.store.Service.stockManagment.implementation;

import com.example.store.DTO.stockManagment.ProductUnitSaleDTO;
import com.example.store.Model.StockMangement.ProductUnitSale;
import com.example.store.Repository.StockManagment.ProductUnitSaleRepository;
import com.example.store.Service.stockManagment.interfaces.ProductService;
import com.example.store.Service.stockManagment.interfaces.ProductUnitSaleService;
import com.example.store.Service.stockManagment.interfaces.UnitService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductUnitSaleServiceImpl implements ProductUnitSaleService {



    private final ProductUnitSaleRepository productUnitSaleRepository;
    private final ProductService productService;
    private final UnitService unitService;
    public ProductUnitSaleServiceImpl(ProductUnitSaleRepository productUnitSaleRepository,
                                      ProductService productService,
                                      UnitService unitServic) {
        this.productUnitSaleRepository = productUnitSaleRepository;
        this.productService = productService;
        this.unitService = unitServic;
    }


    private void mapDTOToProductUnitSale(ProductUnitSaleDTO productUnitSaleDTO, ProductUnitSale productUnitSale){
        productUnitSale.setProduct(productService.findProductById(productUnitSaleDTO.getProductId()));
        productUnitSale.setUnit(unitService.findUnitById(productUnitSaleDTO.getUnitId()));
        productUnitSale.setConversionFactor(productUnitSaleDTO.getConversionFactor());
        productUnitSale.setUnitPrice(productUnitSaleDTO.getUnitPrice());
    }


    @Override
    public ProductUnitSale saveProductUnitSale(ProductUnitSaleDTO productUnitSaleDTO) {
        ProductUnitSale productUnitSaleEntity = new ProductUnitSale();
        mapDTOToProductUnitSale(productUnitSaleDTO,productUnitSaleEntity);
        return productUnitSaleRepository.save(productUnitSaleEntity);
    }

    @Override
    public ProductUnitSale findProductUnitSaleById(Long productUnitSaleId) {

        return productUnitSaleRepository.findById(productUnitSaleId).orElseThrow(()->
                new RuntimeException("ProductUnitSale not found with id: " + productUnitSaleId));
    }

    @Override
    public ProductUnitSale updateProductUnitSale(ProductUnitSaleDTO productUnitSaleDTO, Long productUnitSaleId) {
        ProductUnitSale productUnitSaleDB = findProductUnitSaleById(productUnitSaleId);
        mapDTOToProductUnitSale(productUnitSaleDTO,productUnitSaleDB);
        return productUnitSaleRepository.save(productUnitSaleDB);
    }

    @Override
    public void deleteProductUnitSaleById(Long productUnitSaleId) {
        if(!productUnitSaleRepository.existsById(productUnitSaleId)){
            throw new RuntimeException("ProductUnitSale not found with id: " + productUnitSaleId);
        }
        productUnitSaleRepository.deleteById(productUnitSaleId);
    }

    @Override
    public List<ProductUnitSale> fetchProductUnitSaleList() {
        return productUnitSaleRepository.findAll();
    }


}