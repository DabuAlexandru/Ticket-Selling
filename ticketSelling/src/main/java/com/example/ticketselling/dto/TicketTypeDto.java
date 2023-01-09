package com.example.ticketselling.dto;

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

    @NotBlank(message = "A ticket type should have a title")
    private String title;

    @Min(value = 0, message = "The price should be a positive real number")
    private float price;

    @NotEmpty(message = "A ticket type is defined by the event")
    private EventDto event;
}
