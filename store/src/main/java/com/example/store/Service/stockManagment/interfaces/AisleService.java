package com.example.store.service.stockManagment.interfaces;

import com.example.store.dto.stockManagment.AisleDTO;
import com.example.store.model.stockManagement.Aisle;

import java.util.List;

public interface AisleService {

    Aisle saveAisle(AisleDTO aisleDTO);
    List<Aisle> fetchAisleList();
    Aisle findAisleById(Long idAisle);
    Aisle updateAisle(AisleDTO aisleDTO, Long aisleId);
    void deleteAisleById(Long aisleId);
    void clearAisle(Long aisleId);

}
