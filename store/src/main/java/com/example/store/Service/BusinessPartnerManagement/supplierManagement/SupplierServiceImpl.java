package com.example.store.service.BusinessPartnerManagement.supplierManagement;

import com.example.store.dto.BusinessPartner.supplierManagement.SupplierDTO;
import com.example.store.exception.ElementNotFoundException;
import com.example.store.model.businessPartnerManagement.supplierManagement.Supplier;
import com.example.store.repository.businessPartner.supplierManagement.SupplierRepository;
import com.example.store.service.stockManagment.interfaces.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SupplierServiceImpl  implements SupplierService{

    private SupplierRepository supplierRepository;
    private ProductService productService;
    public SupplierServiceImpl(SupplierRepository supplierRepository,
                               ProductService productService){
        this.supplierRepository =supplierRepository;
        this.productService = productService;

    }

    private void mapDTOToSupplier(SupplierDTO supplierDTO,Supplier supplier){
        supplier.setCompanyName(supplierDTO.getCompanyName());
        supplier.setContactName(supplierDTO.getContactName());
        supplier.setPhoneNumber(supplierDTO.getPhoneNumber());
        supplier.setFax(supplierDTO.getFax());
        supplier.setEmail(supplierDTO.getEmail());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setCity(supplierDTO.getCity());
        supplier.setPostalCode(supplierDTO.getPostalCode());
        supplier.setCountry(supplierDTO.getCountry());
        supplier.setTaxIdentificationNumber(supplierDTO.getTaxIdentificationNumber());
    }

    @Override
    public Supplier saveSupplier(SupplierDTO supplierDTO){

        Supplier supplier = new Supplier();
        mapDTOToSupplier(supplierDTO,supplier);
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier findSupplierById(Long supplierId){
       return supplierRepository.findById(supplierId)
               .orElseThrow(() -> new ElementNotFoundException(supplierId));
    }

    @Override
    public List<Supplier> fetchSupplierList(){
        return supplierRepository.findAll();
    }


    @Override
    public void deleteSupplierByID(Long supplierId){
        if(!supplierRepository.existsById(supplierId)){
            throw  new ElementNotFoundException(supplierId);
        }
        supplierRepository.deleteById(supplierId);
    }

    @Override
    public Supplier updateSupplier(SupplierDTO supplierDTO,Long supplierId){
        Supplier supplierDB = findSupplierById(supplierId);
        mapDTOToSupplier(supplierDTO,supplierDB);
        return supplierRepository.save(supplierDB);
    }
}

