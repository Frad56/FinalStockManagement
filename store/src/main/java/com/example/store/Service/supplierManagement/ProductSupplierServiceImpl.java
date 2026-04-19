package com.example.store.Service.supplierManagement;


import com.example.store.DTO.supplierManagement.ProductSupplierDTO;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.StockMangement.Product;
import com.example.store.Model.supplierManagement.ProductSupplier;
import com.example.store.Model.supplierManagement.Supplier;
import com.example.store.Repository.supplierManagement.ProductSupplierRepository;
import com.example.store.Service.stockManagment.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSupplierServiceImpl implements ProductSupplierService {
    private ProductSupplierRepository productSupplierRepository;
    private ProductService productService;
    private SupplierService supplierService;


    @Autowired
    public ProductSupplierServiceImpl(ProductSupplierRepository productSupplierRepository, ProductService productService,
                                      SupplierService supplierService) {
        this.productSupplierRepository = productSupplierRepository;
        this.productService = productService;
        this.supplierService = supplierService;
    }

    @Override
    public ProductSupplier saveProductSupplier(ProductSupplierDTO productSupplier) {
        ProductSupplier saveProductSupplier = new ProductSupplier();

        Long product_id = productSupplier.getProductId();
        Product findProduct = productService.findProductById(product_id);
        if (findProduct != null) {
            saveProductSupplier.setProduct(findProduct);
        }

        Supplier supplier = supplierService.findSupplierById(productSupplier.getSupplierId());
        saveProductSupplier.setSupplier(supplier);
        saveProductSupplier.setPurchasePrice(productSupplier.getPurchasePrice());
        productSupplierRepository.save(saveProductSupplier);
        return saveProductSupplier;
    }


    @Override
    public ProductSupplier findProductSupplierById(Long productSupplierId) {
        return productSupplierRepository.findById(productSupplierId)
                .orElseThrow(() -> new ElementNotFoundException(productSupplierId));
    }

    @Override
    public List<ProductSupplier> fetchProductSupplierList() {
        return productSupplierRepository.findAll();
    }

    @Override
    public void deleteProductSupplierById(Long productSupplierId) {
        if (!productSupplierRepository.existsById(productSupplierId)) {
            throw new ElementNotFoundException(productSupplierId);
        }
        productSupplierRepository.deleteById(productSupplierId);
    }


}
