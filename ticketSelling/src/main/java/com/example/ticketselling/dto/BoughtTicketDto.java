package com.example.ticketselling.dto;

import lombok.*;

import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoughtTicketDto {

    private int id;

    private TicketDto ticket;

    private ClientDto client;

    private Date boughtAt;
}