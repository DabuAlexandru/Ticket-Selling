package com.example.ticketselling.service;

import com.example.ticketselling.constants.EventPlanningConstants;
import com.example.ticketselling.constants.SeatConstants;
import com.example.ticketselling.constants.TicketConstants;
import com.example.ticketselling.constants.TicketTypeConstants;
import com.example.ticketselling.dto.TicketDto;
import com.example.ticketselling.mapper.TicketMapper;
import com.example.ticketselling.model.EventPlanning;
import com.example.ticketselling.model.TicketType;
import com.example.ticketselling.model.Seat;
import com.example.ticketselling.model.Ticket;
import com.example.ticketselling.repository.EventPlanningRepository;
import com.example.ticketselling.repository.TicketTypeRepository;
import com.example.ticketselling.repository.SeatRepository;
import com.example.ticketselling.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final SeatRepository seatRepository;
    private final EventPlanningRepository eventPlanningRepository;


    private final TicketMapper ticketMapper;

    public TicketService(TicketRepository ticketRepository,
                         TicketTypeRepository ticketTypeRepository,
                         SeatRepository seatRepository,
                         EventPlanningRepository eventPlanningRepository,
                         TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketTypeRepository = ticketTypeRepository;
        this.seatRepository = seatRepository;
        this.eventPlanningRepository = eventPlanningRepository;
        this.ticketMapper = ticketMapper;
    }

    public List<TicketDto> retrieveTickets() {
        return ticketRepository.findAll().stream()
                .map(ticketMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public TicketDto findTicketById(int TicketId) {
        Ticket ticket = ticketRepository.findById(TicketId).orElseThrow(() -> new RuntimeException(TicketConstants.TICKET_NOT_FOUND_MESSAGE));
        return ticketMapper.convertToDto(ticket);
    }

    public TicketDto updateTicket(int TicketId, TicketDto updatedTicketDto) {
        Ticket ticket = ticketRepository.findById(TicketId).orElseThrow(() -> new RuntimeException(TicketConstants.TICKET_NOT_FOUND_MESSAGE));
        return getTicketConverted(updatedTicketDto, ticket);
    }

    private TicketDto getTicketConverted(TicketDto ticketDto, Ticket ticket) {
        if (!isNull(ticketDto.getTicketType())) {
            TicketType ticketType = ticketTypeRepository.findById(ticketDto.getTicketType().getId()).orElseThrow(() -> new RuntimeException(TicketTypeConstants.TICKET_TYPE_NOT_FOUND_MESSAGE));
            ticket.setTicketType(ticketType);
        }
        if (!isNull(ticketDto.getSeat())) {
            Seat seat = seatRepository.findById(ticketDto.getSeat().getId()).orElseThrow(() -> new RuntimeException(SeatConstants.SEAT_NOT_FOUND_MESSAGE));
            ticket.setSeat(seat);
        }
        if (!isNull(ticketDto.getEventPlanning())) {
            EventPlanning eventPlanning = eventPlanningRepository.findById(ticketDto.getEventPlanning().getId()).orElseThrow(() -> new RuntimeException(EventPlanningConstants.EVENT_PLANNING_NOT_FOUND_MESSAGE));
            ticket.setEventPlanning(eventPlanning);
        }
        return ticketMapper.convertToDto(ticketRepository.save(ticket));
    }

    public TicketDto saveTicket(TicketDto ticketDto) {
        Ticket ticket = ticketMapper.convertFromDto(ticketDto);
        return getTicketConverted(ticketDto, ticket);
    }

    public void deleteTicketById(Integer ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
