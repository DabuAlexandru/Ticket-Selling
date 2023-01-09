package com.example.ticketselling.controller;

import com.example.ticketselling.constants.TicketTypeConstants;
import com.example.ticketselling.dto.TicketTypeDto;
import com.example.ticketselling.service.TicketTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticketType")
public class TicketTypeController {

    private final TicketTypeService ticketTypeService;

    public TicketTypeController(TicketTypeService ticketTypeService) {
        this.ticketTypeService = ticketTypeService;
    }

    @GetMapping
    public ResponseEntity<List<TicketTypeDto>> retrieveTicketTypes() {
        return ResponseEntity.ok().body(ticketTypeService.retrieveTicketTypes());
    }

    @PostMapping("/add")
    public ResponseEntity<TicketTypeDto> addTicketType(@RequestBody TicketTypeDto ticketTypeDto) {
        return ResponseEntity.ok().body(ticketTypeService.saveTicketType(ticketTypeDto));
    }

    @PutMapping("/edit/{ticketTypeId}")
    public ResponseEntity<TicketTypeDto> editTicketType(@PathVariable int ticketTypeId, @RequestBody TicketTypeDto editedTicketTypeDto) {
        return ResponseEntity.ok().body(ticketTypeService.updateTicketType(ticketTypeId, editedTicketTypeDto));
    }

    @DeleteMapping("/delete/{ticketTypeId}")
    public ResponseEntity<String> deleteTicketType(@PathVariable int ticketTypeId) {
        ticketTypeService.deleteTicketTypeById(ticketTypeId);
        return ResponseEntity.ok().body(TicketTypeConstants.DELETE_OK_MESSAGE);
    }
}
