package com.example.ticketselling.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventPlanningDto {

    private int id;

    @NotNull(message = "An event planning should have a valid location")
    private LocationDto location;

    @NotNull(message = "An event planning should have a valid event")
    private EventDto event;

    @NotNull(message = "An event planning should have a valid start date")
    private Date startDate;
}
