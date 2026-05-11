package com.example.store.Repository.BusinessPartner.ClientManagement;


import com.example.store.DTO.stockManagment.request.CodeRequest;
import com.example.store.Model.BusinessPartnerManagement.clientManagment.Client;
import com.example.store.Model.StockMangement.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    List<Client> findByFirstNameStartingWithIgnoreCase(String keyword);

}
