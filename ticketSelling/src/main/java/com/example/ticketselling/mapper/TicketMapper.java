package com.example.ticketselling.mapper;

import com.example.ticketselling.dto.TicketDto;
import com.example.ticketselling.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class TicketMapper {

    private final TicketTypeMapper ticketTypeMapper;
    private final EventPlanningMapper eventPlanningMapper;
    private final SeatMapper seatMapper;

    @Autowired
    public TicketMapper(TicketTypeMapper ticketTypeMapper, EventPlanningMapper eventPlanningMapper, SeatMapper seatMapper) {
        this.ticketTypeMapper = ticketTypeMapper;
        this.eventPlanningMapper = eventPlanningMapper;
        this.seatMapper = seatMapper;
    }

    public Ticket convertFromDto(TicketDto ticketDto) {
        if (isNull(ticketDto)) {
            return null;
        }

        return new Ticket(
                ticketDto.getId(),
                seatMapper.convertFromDto(ticketDto.getSeat()),
                eventPlanningMapper.convertFromDto(ticketDto.getEventPlanning()),
                ticketTypeMapper.convertFromDto(ticketDto.getTicketType())
        );
    }

    public TicketDto convertToDto(Ticket ticket) {
        if (isNull(ticket)) {
            return null;
        }

        return new TicketDto(
                ticket.getId(),
                seatMapper.convertToDto(ticket.getSeat()),
                eventPlanningMapper.convertToDto(ticket.getEventPlanning()),
                ticketTypeMapper.convertToDto(ticket.getTicketType())
        );
    }
}
