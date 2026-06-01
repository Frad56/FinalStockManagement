package com.example.store.controller.businessPartnerManagement.ClientManagement;


import com.example.store.dto.BusinessPartner.ClientManagement.ClientDTO;

import com.example.store.model.businessPartnerManagement.clientManagment.Client;

import com.example.store.service.BusinessPartnerManagement.clientManagement.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/add")
    public ResponseEntity<Client> saveClient(@Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.saveClient(clientDTO));
    }


    @GetMapping("/ListClients")
    public ResponseEntity<List<Client>> fetchClientList() {
        return ResponseEntity.ok(clientService.fetchClientList());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable("id") Long clientId) {
        return ResponseEntity.ok(clientService.findClientById(clientId));
    }

    //

    @PostMapping("/searchClientByFirstName")
    public  ResponseEntity<List<Client>>findByCategoryName(@RequestBody String firstName){
        return ResponseEntity.ok(clientService.findByFirstNameStartingWithIgnoreCase(firstName));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateClient(
            @Valid @RequestBody ClientDTO clientDTO,
            @PathVariable("id") Long clientId
    ) {
        return ResponseEntity.ok(clientService.updateClient(clientDTO, clientId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteClientById(@PathVariable("id") Long aisleId) {
        clientService.deleteClientById(aisleId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }


}
