package com.example.store.service.stockManagment.implementation;


import com.example.store.dto.stockManagment.ProductVariantDTO;
import com.example.store.dto.stockManagment.request.CategoryRequest;
import com.example.store.dto.stockManagment.request.CodeRequest;
import com.example.store.dto.stockManagment.request.DesignationRequest;
import com.example.store.dto.stockManagment.request.ReferenceRequest;
import com.example.store.exception.ElementNotFoundException;
import com.example.store.exception.ProductHasNoVariantsException;
import com.example.store.model.stockManagement.Product;
import com.example.store.model.stockManagement.ProductVariant;
import com.example.store.repository.stockManagement.CharacteristicValueRepository;
import com.example.store.repository.stockManagement.ProductVariantRepository;
import com.example.store.service.stockManagment.interfaces.ProductService;
import com.example.store.service.stockManagment.interfaces.ProductVariantService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductService productService;
    private final CharacteristicValueRepository characteristicValueRepository;




     public ProductVariantServiceImpl(ProductVariantRepository productVariantRepository,
                                      ProductService productService,
                                      CharacteristicValueRepository characteristicValueRepository){
         this.productVariantRepository = productVariantRepository;
         this.productService = productService;
         this.characteristicValueRepository = characteristicValueRepository;
     }



    private void mapDTOToVariant(ProductVariantDTO dto, ProductVariant variant) {
        variant.setCode(dto.getCode());
        variant.setSpecificPrice(dto.getSpecificPrice());
        variant.setQuantityInStock(dto.getQuantityInStock());
        variant.setProduct(productService.findProductById(dto.getProductId()));

    }
    public String generateVariantCode(Product product, String color, String size) {

        String base = product.getReference();

        return base + "-"
                + color.substring(0, 3).toUpperCase()
                + "-" + size;
    }
    @Override
    public ProductVariant saveProductVariant(ProductVariantDTO productVariantDTO) {

        Product product =productService.findProductById(productVariantDTO.getProductId());





        if((productVariantDTO.getSpecificPrice() == null)){
            productVariantDTO.setSpecificPrice(product.getBasePrice());
        }
        ProductVariant productVariant = new ProductVariant();
        mapDTOToVariant(productVariantDTO,productVariant);

        return productVariantRepository.save(productVariant);

    }


    @Override
    public List<ProductVariant> fetchProductVariantList() {
        return  productVariantRepository.findAll();
    }


    @Override
    public ProductVariant findProductVariantById(Long idProductVariant) {
        return productVariantRepository.findById(idProductVariant).orElseThrow(()->
                new ElementNotFoundException(idProductVariant));
    }


    @Override
    public ProductVariant updateProductVariantDTO(ProductVariantDTO productVariantDTO, Long productVariantId) {
        ProductVariant productVariantToUpdate = findProductVariantById(productVariantId);
        mapDTOToVariant(productVariantDTO, productVariantToUpdate);
        return productVariantRepository.save(productVariantToUpdate);

    }
    @Override
    public ProductVariant updateProductVariant(ProductVariant productVariant, Long productVariantId){
        ProductVariant productVariantToUpdate = findProductVariantById(productVariantId);
        return productVariantRepository.save(productVariantToUpdate);
    }


    @Override
    public void deleteProductVariantById(Long productVariantId) {
         if(!productVariantRepository.existsById(productVariantId)){
             throw new ElementNotFoundException(productVariantId);
         }
         Optional<Long> characteristicValueId = characteristicValueRepository.findCharacteristicValueIdByProductVariantId(productVariantId);

         if(characteristicValueId.isPresent()){
             characteristicValueRepository.deleteById(characteristicValueId.get());
             //throw new ResourceInUseException("This Product Variant is already used and cannot be deleted");
        }
         productVariantRepository.deleteById(productVariantId);

    }

    @Override
    public boolean hasVariantsByProductId(Long productId){
        return  productVariantRepository.existsByProduct_ProductId(productId);
    }


    @Override
    public boolean hasVariantsByProductReference(ReferenceRequest request){
        return  productVariantRepository.existsByProduct_Reference(request.getProductReference());
    }

    @Override
    public boolean hasVariantsByProductDesignation(DesignationRequest request){
        return  productVariantRepository.existsByProduct_Designation(request.getProductDesignation());
    }

    @Override
    public boolean hasVariantsByProductCategoryName(CategoryRequest request){
        return  productVariantRepository.existsByProduct_Category_Name(request.getProductCategoryName());
    }

    @Override
    public List<ProductVariant> findByProduct_ProductId(Long productId){
        if(!productVariantRepository.existsByProduct_ProductId(productId)){
            throw new ElementNotFoundException("No variants found for product id: " + productId );
        }
        return productVariantRepository.findByProduct_ProductId(productId);
    }


    @Override
    public List<ProductVariant> findProductVariantListByProductReference(String product_reference){

         if(!productService.verifyIfProductExistsByReference(product_reference)){
             throw new ElementNotFoundException(product_reference);
         }
        List<ProductVariant> pvList= productVariantRepository.findByProduct_reference(product_reference);

        if (pvList.isEmpty()) {
            throw new ProductHasNoVariantsException("No Product Variants found for this product");
        }
         return productVariantRepository.findByProduct_reference(product_reference);
    }

    @Override
    public List<ProductVariant> findProductVariantListByProductDesignation(String product_designation){

         if(!productService.verifyIfProductExistsByDesignation(product_designation)){
             throw new ElementNotFoundException(product_designation);
         }
        List<ProductVariant> pvList= productVariantRepository.findByProduct_designation(product_designation);

        if (pvList.isEmpty()) {
            throw new ProductHasNoVariantsException("No Product Variants found for this product");
        }

         return productVariantRepository.findByProduct_designation(product_designation);
    }

    @Override
    public  List<ProductVariant> findProductVariantListByCategoryName(String category_name){

        if(!productService.verifyIfProductExistsByCategoryName(category_name)){
            throw new ElementNotFoundException(category_name);
        }
        List<ProductVariant> pvList= productVariantRepository.findByCategoryName(category_name);

        if (pvList.isEmpty()) {
            throw new ProductHasNoVariantsException("No Product Variants found for this product");
        }
         return productVariantRepository.findByCategoryName(category_name);
    }

    @Override
    public List<Product> findProductByReferenceStartingWithIgnoreCase(ReferenceRequest keyword){
        return productVariantRepository.findByProductReference(keyword.getProductReference());
    }

    @Override
    public  List<Product> findProductByDesignationStartingWithIgnoreCase(DesignationRequest keyword){
        return productVariantRepository.findByProductDesignation(keyword.getProductDesignation());
    }
    @Override
    public List<Product> findProductByCategoryNameStartingWithIgnoreCase(CategoryRequest keyword){
        return productVariantRepository.findByProductCategoryName(keyword.getProductCategoryName());
    }


    @Override
    public List<ProductVariant> findByCodeStartingWithIgnoreCase(CodeRequest keyword){
        return productVariantRepository.findByCodeStartingWithIgnoreCase(keyword.getCode());
    }
}
