package com.example.ticketselling.dto;

import com.example.ticketselling.constants.TicketConstants;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private int id;

    @NotEmpty(message = TicketConstants.SEAT_NOT_EMPTY_CONSTRAINT_MESSAGE)
    private SeatDto seat;

    @NotEmpty(message = TicketConstants.EVENT_PLANNING_NOT_EMPTY_CONSTRAINT_MESSAGE)
    private EventPlanningDto eventPlanning;

    @NotEmpty(message = TicketConstants.TICKET_TYPE_NOT_EMPTY_CONSTRAINT_MESSAGE)
    private TicketTypeDto ticketType;
}
