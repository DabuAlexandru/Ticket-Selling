package com.example.ticketselling.controller;

import com.example.ticketselling.constants.TicketConstants;
import com.example.ticketselling.dto.TicketDto;
import com.example.ticketselling.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> retrieveTickets() {
        return ResponseEntity.ok().body(ticketService.retrieveTickets());
    }

    @GetMapping("/view/{ticketId}")
    public ResponseEntity<TicketDto> viewTicket(@PathVariable int ticketId) {
        return ResponseEntity.ok().body(ticketService.findTicketById(ticketId));
    }

    @PostMapping("/add")
    public ResponseEntity<TicketDto> addTicket(@RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok().body(ticketService.saveTicket(ticketDto));
    }

    @PutMapping("/edit/{ticketId}")
    public ResponseEntity<TicketDto> editTicket(@PathVariable int ticketId, @RequestBody TicketDto editedTicketDto) {
        return ResponseEntity.ok().body(ticketService.updateTicket(ticketId, editedTicketDto));
    }

    @DeleteMapping("/delete/{ticketId}")
    public ResponseEntity<String> deleteTicket(@PathVariable int ticketId) {
        ticketService.deleteTicketById(ticketId);
        return ResponseEntity.ok().body(TicketConstants.DELETE_OK_MESSAGE);
    }
}
