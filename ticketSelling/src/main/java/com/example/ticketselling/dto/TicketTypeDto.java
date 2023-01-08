package com.example.ticketselling.dto;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketTypeDto {

    private int id;

    private String title;

    private float price;

    private EventDto event;
}
