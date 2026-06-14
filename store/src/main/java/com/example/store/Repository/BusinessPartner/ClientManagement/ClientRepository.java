package com.example.store.repository.businessPartner.ClientManagement;


import com.example.store.model.businessPartnerManagement.clientManagment.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    List<Client> findByFirstNameContainingIgnoreCase(String keyword);

    //    List<ProductVariant> findByCodeContainingIgnoreCase(String keyword);

}
