package com.example.ticketselling.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoughtTicketDto {

    private int id;

    @NotEmpty(message = "The ticket should not be null for a bought ticket record")
    private TicketDto ticket;

    @NotEmpty(message = "The client should not be null for a bought ticket record")
    private ClientDto client;

    @NotNull(message = "The boughtAt should not be null for a bought ticket record")
    private Date boughtAt;
}