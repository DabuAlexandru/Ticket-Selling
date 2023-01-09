package com.example.ticketselling.dto;

import com.example.ticketselling.constants.EventConstants;
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

    @NotBlank(message = EventConstants.NAME_NOT_BLANK_CONSTRAINT_MESSAGE)
    private String name;

    @Min(value = EventConstants.DURATION_MIN_CONSTRAINT_VALUE, message = EventConstants.DURATION_MIN_CONSTRAINT_MESSAGE)
    private int duration;
}
