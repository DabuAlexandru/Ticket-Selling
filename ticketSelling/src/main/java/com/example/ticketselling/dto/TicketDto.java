package com.example.ticketselling.dto;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private int id;

    private SeatDto seat;

    private EventPlanningDto eventPlanning;

    private TicketTypeDto ticketType;
}
