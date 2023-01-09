package com.example.ticketselling.controller;

import com.example.ticketselling.constants.BoughtTicketConstants;
import com.example.ticketselling.dto.BoughtTicketDto;
import com.example.ticketselling.service.BoughtTicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boughtTicket")
public class BoughtTicketController {

    private final BoughtTicketService boughtTicketService;

    public BoughtTicketController(BoughtTicketService boughtTicketService) {
        this.boughtTicketService = boughtTicketService;
    }

    @GetMapping
    public ResponseEntity<List<BoughtTicketDto>> retrieveBoughtTickets() {
        return ResponseEntity.ok().body(boughtTicketService.retrieveBoughtTickets());
    }

    @PostMapping("/add")
    public ResponseEntity<BoughtTicketDto> addBoughtTicket(@RequestBody BoughtTicketDto boughtTicketDto) {
        return ResponseEntity.ok().body(boughtTicketService.saveBoughtTicket(boughtTicketDto));
    }

    @PutMapping("/edit/{boughtTicketId}")
    public ResponseEntity<BoughtTicketDto> editBoughtTicket(@PathVariable int boughtTicketId, @RequestBody BoughtTicketDto editedBoughtTicketDto) {
        return ResponseEntity.ok().body(boughtTicketService.updateBoughtTicket(boughtTicketId, editedBoughtTicketDto));
    }

    @DeleteMapping("/delete/{boughtTicketId}")
    public ResponseEntity<String> deleteBoughtTicket(@PathVariable int boughtTicketId) {
        boughtTicketService.deleteBoughtTicketById(boughtTicketId);
        return ResponseEntity.ok().body(BoughtTicketConstants.DELETE_OK_MESSAGE);
    }
}
