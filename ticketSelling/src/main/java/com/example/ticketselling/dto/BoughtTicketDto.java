package com.example.ticketselling.dto;

import com.example.ticketselling.constants.BoughtTicketConstants;
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

    @NotEmpty(message = BoughtTicketConstants.TICKET_NOT_EMPTY_CONSTRAINT_MESSAGE)
    private TicketDto ticket;

    @NotEmpty(message = BoughtTicketConstants.CLIENT_NOT_EMPTY_CONSTRAINT_MESSAGE)
    private ClientDto client;

    @NotNull(message = BoughtTicketConstants.BOUGHT_AT_NOT_EMPTY_CONSTRAINT_MESSAGE)
    private Date boughtAt;
}