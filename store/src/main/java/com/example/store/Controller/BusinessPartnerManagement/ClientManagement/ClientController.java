package com.example.store.Controller.BusinessPartnerManagement.ClientManagement;


import com.example.store.DTO.BusinessPartner.ClientManagement.ClientDTO;

import com.example.store.DTO.stockManagment.request.CodeRequest;
import com.example.store.Model.BusinessPartnerManagement.clientManagment.Client;

import com.example.store.Model.StockMangement.ProductVariant;
import com.example.store.Service.BusinessPartnerManagement.clientManagement.ClientService;
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
