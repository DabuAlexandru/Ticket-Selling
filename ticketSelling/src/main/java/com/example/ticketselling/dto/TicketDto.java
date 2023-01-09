package com.example.ticketselling.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private int id;

    @NotEmpty(message = "A ticket should have a seat")
    private SeatDto seat;

    @NotEmpty(message = "A ticket should be for an event")
    private EventPlanningDto eventPlanning;

    @NotEmpty(message = "A ticket should have a type")
    private TicketTypeDto ticketType;
}
