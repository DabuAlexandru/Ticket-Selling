package com.example.ticketselling.controller;

import com.example.ticketselling.constants.ClientConstants;
import com.example.ticketselling.dto.ClientDto;
import com.example.ticketselling.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> retrieveClients() {
        return ResponseEntity.ok().body(clientService.retrieveClients());
    }

    @PostMapping("/add")
    public ResponseEntity<ClientDto> addClient(@RequestBody ClientDto clientDto) {
        return ResponseEntity.ok().body(clientService.saveClient(clientDto));
    }

    @PutMapping("/edit/{clientId}")
    public ResponseEntity<ClientDto> editClient(@PathVariable int clientId, @RequestBody ClientDto editedClientDto) {
        return ResponseEntity.ok().body(clientService.updateClient(clientId, editedClientDto));
    }

    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable int clientId) {
        String message = clientService.deleteClientById(clientId);
        return ResponseEntity.ok().body(message);
    }
}
