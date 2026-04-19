package com.example.store.Service.stockManagment.interfaces;

import com.example.store.DTO.stockManagment.AisleDTO;
import com.example.store.Model.StockMangement.Aisle;

import java.util.List;

public interface AisleService {

    Aisle saveAisle(AisleDTO aisleDTO);
    List<Aisle> fetchAisleList();
    Aisle findAisleById(Long idAisle);
    Aisle updateAisle(AisleDTO aisleDTO, Long aisleId);
    void deleteAisleById(Long aisleId);
    void clearAisle(Long aisleId);

}
