package com.example.ticketselling.dto;

import com.example.ticketselling.constants.TicketTypeConstants;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketTypeDto {

    private int id;

    @NotBlank(message = TicketTypeConstants.TITLE_NOT_BLANK_CONSTRAINT_MESSAGE)
    private String title;

    @Min(value = 0, message = TicketTypeConstants.PRICE_MIN_CONSTRAINT_MESSAGE)
    private float price;

    @NotEmpty(message = TicketTypeConstants.EVENT_NOT_EMPTY_CONSTRAINT_MESSAGE)
    private EventDto event;
}
