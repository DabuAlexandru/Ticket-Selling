package com.example.ticketselling.service;

import com.example.ticketselling.constants.EventConstants;
import com.example.ticketselling.constants.TicketTypeConstants;
import com.example.ticketselling.dto.TicketTypeDto;
import com.example.ticketselling.mapper.TicketTypeMapper;
import com.example.ticketselling.model.Event;
import com.example.ticketselling.model.Location;
import com.example.ticketselling.model.TicketType;
import com.example.ticketselling.repository.EventRepository;
import com.example.ticketselling.repository.LocationRepository;
import com.example.ticketselling.repository.TicketTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class TicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;
    private final EventRepository eventRepository;

    private final TicketTypeMapper ticketTypeMapper;

    public TicketTypeService(TicketTypeRepository ticketTypeRepository, EventRepository eventRepository, TicketTypeMapper ticketTypeMapper) {
        this.ticketTypeRepository = ticketTypeRepository;
        this.eventRepository = eventRepository;
        this.ticketTypeMapper = ticketTypeMapper;
    }

    public List<TicketTypeDto> retrieveTicketTypes() {
        return ticketTypeRepository.findAll().stream()
                .map(ticketTypeMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public TicketType findTicketTypeById(int TicketTypeId) {
        return ticketTypeRepository.findById(TicketTypeId).orElseThrow(() -> new RuntimeException(TicketTypeConstants.TICKET_TYPE_NOT_FOUND_MESSAGE));
    }

    public TicketTypeDto updateTicketType(int TicketTypeId, TicketTypeDto updatedTicketTypeDto) {
        TicketType ticketType = ticketTypeRepository.findById(TicketTypeId).orElseThrow(() -> new RuntimeException(TicketTypeConstants.TICKET_TYPE_NOT_FOUND_MESSAGE));
        if (!isNull(updatedTicketTypeDto.getTitle())) {
            ticketType.setTitle(updatedTicketTypeDto.getTitle());
        }
        if (updatedTicketTypeDto.getPrice() != 0) {
            ticketType.setPrice(updatedTicketTypeDto.getPrice());
        }
        if (!isNull(updatedTicketTypeDto.getEvent())) {
            Event event = eventRepository.findById(updatedTicketTypeDto.getEvent().getId()).orElseThrow(() -> new RuntimeException(EventConstants.EVENT_NOT_FOUND_MESSAGE));
            ticketType.setEvent(event);
        }
        return ticketTypeMapper.convertToDto(ticketTypeRepository.save(ticketType));
    }

    public TicketTypeDto saveTicketType(TicketTypeDto ticketTypeDto) {
        TicketType ticketType = ticketTypeMapper.convertFromDto(ticketTypeDto);

        if (!isNull(ticketTypeDto.getEvent())) {
            Event event = eventRepository.findById(ticketTypeDto.getEvent().getId()).orElseThrow(() -> new RuntimeException(EventConstants.EVENT_NOT_FOUND_MESSAGE));
            ticketType.setEvent(event);
        }

        return ticketTypeMapper.convertToDto(ticketTypeRepository.save(ticketType));
    }

    public void deleteTicketTypeById(Integer ticketTypeId) {
        ticketTypeRepository.deleteById(ticketTypeId);
    }
}
