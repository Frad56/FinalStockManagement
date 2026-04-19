package com.example.store.Service.stockManagment.implementation;


import com.example.store.DTO.stockManagment.CharacteristicDTO;
import com.example.store.DTO.stockManagment.ProductCharacteristicDTO;
import com.example.store.Exception.ElementAlreadyExistException;
import com.example.store.Model.StockMangement.Characteristic;
import com.example.store.Model.StockMangement.ProductCharacteristic;
import com.example.store.Repository.StockManagment.ProductCharacteristicRepository;
import com.example.store.Service.stockManagment.interfaces.CharacteristicService;
import com.example.store.Service.stockManagment.interfaces.ProductCharacteristicService;
import com.example.store.Service.stockManagment.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.sql.SQLNonTransientException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCharacteristicServiceImpl implements ProductCharacteristicService {

    private final ProductCharacteristicRepository productCharacteristicRepository;
    private final ProductService productService;
    private final CharacteristicService characteristicService;

    public ProductCharacteristicServiceImpl(ProductCharacteristicRepository productCharacteristicRepository,
                                            ProductService productService
            , CharacteristicService characteristicService) {
        this.productCharacteristicRepository = productCharacteristicRepository;
        this.productService = productService;
        this.characteristicService = characteristicService;
    }

    private void mapDTOToProductCharacteristic(ProductCharacteristicDTO productCharacteristicDTO, ProductCharacteristic productCharacteristic) {
        productCharacteristic.setProduct(productService.findProductById(productCharacteristicDTO.getProductId()));
        productCharacteristic.setCharacteristic(characteristicService.findCharacteristicById(productCharacteristicDTO.getCharacteristicId()));
    }

    @Override
    public ProductCharacteristic saveProductCharacteristic(ProductCharacteristicDTO productCharacteristic) {
        Long productId = productCharacteristic.getProductId();
        Long characteristicId = productCharacteristic.getCharacteristicId();

        List<ProductCharacteristic> existingList = productCharacteristicRepository.findByProduct_ProductId(productId);
        for(ProductCharacteristic existing : existingList){
            if(existing.getCharacteristic().getCharacteristicId().equals(characteristicId)){
                Characteristic characteristic= characteristicService.findCharacteristicById(characteristicId);
                throw new ElementAlreadyExistException( characteristic.getName()," already exists for productId: " + productId );
            }
        }
        ProductCharacteristic productCharacteristicDB = new ProductCharacteristic();
        mapDTOToProductCharacteristic(productCharacteristic, productCharacteristicDB);
        return productCharacteristicRepository.save(productCharacteristicDB);
    }

    @Override
    public ProductCharacteristic findProductCharacteristicById(Long productCharacteristicId) {
        return productCharacteristicRepository.findById(productCharacteristicId).orElseThrow(() ->
                new RuntimeException("ProductCharacteristic not found with id: " + productCharacteristicId));
    }

    @Override
    public List<ProductCharacteristic> fetchProductCharacteristicList() {
        return productCharacteristicRepository.findAll();
    }

    @Override
    public ProductCharacteristic updateProductCharacteristic(ProductCharacteristicDTO productCharacteristic, Long productCharacteristicId) {
        ProductCharacteristic existingProductCharacteristic = findProductCharacteristicById(productCharacteristicId);
        mapDTOToProductCharacteristic(productCharacteristic, existingProductCharacteristic);
        return productCharacteristicRepository.save(existingProductCharacteristic);
    }

    //SQLIntegrityConstraintViolationException
    @Override
    public void deleteProductCharacteristicById(Long productCharacteristicId) {
        if (!productCharacteristicRepository.existsById(productCharacteristicId)) {
            throw new RuntimeException("ProductCharacteristic not found with id: " + productCharacteristicId);
        }
        productCharacteristicRepository.deleteById(productCharacteristicId);
    }


    @Override
    public void deleteProductCharacteristicByProductId(Long productId) {
        productService.findProductById(productId);
        productCharacteristicRepository.deleteByProduct_ProductId(productId);
    }
    @Override
    public List<ProductCharacteristic> saveProductCharacteristicList(List<Long> characteristicList, Long productId) {
        List<ProductCharacteristic> list = new ArrayList<>();
        for (Long characteristicId : characteristicList) {

            ProductCharacteristicDTO productCharacteristicDTO = new ProductCharacteristicDTO();
            productCharacteristicDTO.setProductId(productId);
            productCharacteristicDTO.setCharacteristicId(characteristicId);

            ProductCharacteristic productCharacteristic =new ProductCharacteristic();
            mapDTOToProductCharacteristic(productCharacteristicDTO,productCharacteristic);
            saveProductCharacteristic(productCharacteristicDTO);
            list.add(productCharacteristic);
        }
        return list;
    }

    @Override
    public List<ProductCharacteristic> findProductCharacteristicByProductId(Long productId) {
        productService.findProductById(productId);
        return productCharacteristicRepository.findByProduct_ProductId(productId);
    }

}
