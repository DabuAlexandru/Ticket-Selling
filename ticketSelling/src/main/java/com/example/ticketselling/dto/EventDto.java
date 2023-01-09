package com.example.ticketselling.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private int id;

    private String description;

    @NotBlank(message="A valid event should have a name")
    private String name;

    @Min(value=30, message="A valid event should have a duration of at least 30 minutes!")
    private int duration;
}
