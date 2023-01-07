package com.example.ticketselling.service;

import com.example.ticketselling.model.Event;
import com.example.ticketselling.model.Location;
import com.example.ticketselling.model.TicketType;
import com.example.ticketselling.repository.EventRepository;
import com.example.ticketselling.repository.LocationRepository;
import com.example.ticketselling.repository.TicketTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Service
public class TicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;
    private final EventRepository eventRepository;

    public TicketTypeService(TicketTypeRepository ticketTypeRepository,
                             EventRepository eventRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
        this.eventRepository = eventRepository;
    }

    public List<TicketType> retrieveTicketTypes() {
        return ticketTypeRepository.findAll();
    }

    public TicketType findTicketTypeById(int TicketTypeId) {
        return ticketTypeRepository.findById(TicketTypeId).orElseThrow(() -> new RuntimeException("TicketType not found!"));
    }

    public TicketType updateTicketType(int TicketTypeId, TicketType updatedTicketType) {
        TicketType ticketType = ticketTypeRepository.findById(TicketTypeId).orElseThrow(() -> new RuntimeException("TicketType not found!"));
        if (!isNull(updatedTicketType.getTitle())) {
            ticketType.setTitle(updatedTicketType.getTitle());
        }
        if (updatedTicketType.getPrice() != 0) {
            ticketType.setPrice(updatedTicketType.getPrice());
        }
        if (!isNull(updatedTicketType.getEvent())) {
            Event event = eventRepository.findById(updatedTicketType.getEvent().getId()).orElseThrow(() -> new RuntimeException("Event not found!"));
            ticketType.setEvent(event);
        }
        return ticketTypeRepository.save(ticketType);
    }

    public TicketType saveTicketType(TicketType ticketType) {
        Event event = eventRepository.findById(ticketType.getEvent().getId()).orElseThrow(() -> new RuntimeException("Event not found!"));
        ticketType.setEvent(event);

        return ticketTypeRepository.save(ticketType);
    }

    public void deleteTicketTypeById(Integer ticketTypeId) {
        ticketTypeRepository.deleteById(ticketTypeId);
    }
}
