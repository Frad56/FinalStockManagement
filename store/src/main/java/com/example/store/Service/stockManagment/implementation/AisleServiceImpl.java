package com.example.store.Service.stockManagment.implementation;

import com.example.store.DTO.stockManagment.AisleDTO;
import com.example.store.Exception.ElementAlreadyExistException;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.StockMangement.Aisle;
import com.example.store.Model.StockMangement.Product;
import com.example.store.Repository.StockManagment.AisleRepository;
import com.example.store.Repository.StockManagment.ProductRepository;
import com.example.store.Service.stockManagment.interfaces.AisleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AisleServiceImpl implements AisleService {

    private final AisleRepository aisleRepository;
    private final ProductRepository productRepository;

        public AisleServiceImpl(AisleRepository aisleRepository,
                                ProductRepository productRepository){
            this.aisleRepository = aisleRepository;
            this.productRepository=productRepository;
        }


    private void mapDTOToAisle(AisleDTO aisleDTO, Aisle aisle) {
        String changeToLower = aisleDTO.getName().trim().toUpperCase();
        aisle.setName(changeToLower);
    }


    @Override
    public Aisle saveAisle(AisleDTO aisleDTO) {

        String newName = aisleDTO.getName().trim().toLowerCase();
        if(aisleRepository.findByName(newName).isPresent()){
            throw new ElementAlreadyExistException("The aisle",aisleDTO.getName());
        }
        Aisle aisle = new Aisle();
        mapDTOToAisle(aisleDTO, aisle);
        return aisleRepository.save(aisle);
    }

    @Override
    public List<Aisle> fetchAisleList() {
        return aisleRepository.findAll();
    }

    @Override
    public Aisle findAisleById(Long idAisle) {
        return aisleRepository.findById(idAisle).orElseThrow(()->
                new RuntimeException("Aisle not found with id: " + idAisle));
    }

    @Override
    public Aisle updateAisle(AisleDTO aisleDTO, Long aisleId) {
            Aisle aisle = findAisleById(aisleId);
        mapDTOToAisle(aisleDTO,aisle);
        return aisleRepository.save(aisle);
    }

    @Override
    public void deleteAisleById(Long aisleId) {
        if(!aisleRepository.existsById(aisleId)){
            throw new RuntimeException("Aisle not found with id: " + aisleId);
        }
        aisleRepository.deleteById(aisleId);
    }


   @Override
   //transaction sig si il ya un error pour variant N3 ne fait rien
   @Transactional
    public void clearAisle(Long aisleId){
        Aisle aisle = findAisleById(aisleId);
        List<Product> products = productRepository.findByAisle(aisle);
        for(Product variant:products){
            variant.setAisle(null);
            productRepository.save(variant);
        }

   }
}
