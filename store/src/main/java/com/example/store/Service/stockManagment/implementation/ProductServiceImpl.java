package com.example.store.Service.stockManagment.implementation;


import com.example.store.DTO.stockManagment.ProductDTO;
import com.example.store.DTO.stockManagment.request.CategoryRequest;
import com.example.store.DTO.stockManagment.request.DesignationRequest;
import com.example.store.DTO.stockManagment.request.ReferenceRequest;
import com.example.store.Exception.ElementAlreadyExistException;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Exception.ResourceInUseException;
import com.example.store.Model.StockMangement.Aisle;
import com.example.store.Model.StockMangement.Category;
import com.example.store.Model.StockMangement.Product;
import com.example.store.Model.StockMangement.ProductVariant;
import com.example.store.Repository.StockManagment.ProductCharacteristicRepository;
import com.example.store.Repository.StockManagment.ProductRepository;
import com.example.store.Repository.StockManagment.ProductUnitSaleRepository;
import com.example.store.Service.stockManagment.interfaces.AisleService;
import com.example.store.Service.stockManagment.interfaces.CategoryService;
import com.example.store.Service.stockManagment.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;
    private  final AisleService aisleService;
    private final ProductUnitSaleRepository productUnitSaleRepository;
    private final ProductCharacteristicRepository productCharacteristicRepository;


    public ProductServiceImpl(ProductRepository productRepository,CategoryService categoryService,
                              AisleService aisleService,
                              ProductUnitSaleRepository productUnitSaleRepository,
                              ProductCharacteristicRepository productCharacteristicRepository){
        this.productRepository=productRepository;
        this.categoryService=categoryService;
        this.aisleService=aisleService;
        this.productUnitSaleRepository = productUnitSaleRepository;
        this.productCharacteristicRepository = productCharacteristicRepository;
    }

    private void mapDTOToProduct(ProductDTO dto, Product product) {
        product.setReference(dto.getReference());
        product.setDesignation(dto.getDesignation());
        product.setBrand(dto.getBrand());
        product.setDescription(dto.getDescription());
        product.setBasePrice(dto.getBasePrice());

        Category category =categoryService.findCategoryById(dto.getCategoryId());
        Aisle aisle = aisleService.findAisleById(dto.getAisleId());
        product.setCategory(category);
        product.setAisle(aisle);

    }

    @Override
    public Product saveProduct(ProductDTO productDTO){
        String newProductReference = productDTO.getReference().trim().toLowerCase();
        if(productRepository.findByReference(newProductReference).isPresent()){
            throw new ElementAlreadyExistException("the Product Reference ",productDTO.getReference());

        }
        Product product = new Product();
        mapDTOToProduct(productDTO,product);
        return productRepository.save(product);
    }


    @Override
    public List<Product> fetchProductList(){
        return  productRepository.findAll();
    }


    @Override
    @Transactional
    public void deleteProductById(Long productId){
        if(!productRepository.existsById(productId)){
            throw new ElementNotFoundException(productId);
        }
        boolean isProductInUnitSale = productUnitSaleRepository.existsByProduct_ProductId(productId);
        if(isProductInUnitSale){
            throw new ResourceInUseException("This Product is already used and cannot be deleted");
        }
        productCharacteristicRepository.deleteByProduct_ProductId(productId);
        productRepository.deleteById(productId);
    }


   @Override
    public Product updateProduct(ProductDTO product, Long productId){
        Product productDB = findProductById(productId);
       String newProductReference = productDB.getReference().trim().toLowerCase();
       if(productRepository.findByReference(newProductReference).isPresent()){
           throw new ElementAlreadyExistException("the Product Reference ",product.getReference());

       }
        mapDTOToProduct(product,productDB);

        return productRepository.save(productDB);

    }


    @Override
    public Product findProductById(Long productId){
        return productRepository.findById(productId).orElseThrow(() ->
                new ElementNotFoundException(productId));
    }

    @Override
    public boolean verifyIfProductExistsByReference(String reference){
        return productRepository.existsByReference(reference);
    }

    @Override
    public boolean verifyIfProductExistsByCategoryName(String category_name){
        return productRepository.existsByCategory_Name(category_name);
    }

    @Override
    public boolean verifyIfProductExistsByDesignation(String designation){
        return productRepository.existsByDesignation(designation);
    }

    @Override
    public List<Product> findByReferenceStartingWithIgnoreCase(ReferenceRequest keyword){
        return productRepository.findByReferenceStartingWithIgnoreCase(keyword.getProductReference());
    }

    @Override
    public  List<Product> findByDesignationStartingWithIgnoreCase(DesignationRequest keyword){
        return productRepository.findByDesignationStartingWithIgnoreCase(keyword.getProductDesignation());
    }
    @Override
    public List<Product> findByCategoryNameStartingWithIgnoreCase(CategoryRequest keyword){
        return productRepository.findByCategoryNameStartingWithIgnoreCase(keyword.getProductCategoryName());
    }
}
