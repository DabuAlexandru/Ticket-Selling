package com.example.ticketselling.dto;

import com.example.ticketselling.constants.EventPlanningConstants;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventPlanningDto {

    private int id;

    @NotEmpty(message = EventPlanningConstants.LOCATION_NOT_EMPTY_CONSTRAINT_MESSAGE)
    private LocationDto location;

    @NotEmpty(message = EventPlanningConstants.EVENT_NOT_EMPTY_CONSTRAINT_MESSAGE)
    private EventDto event;

    @NotNull(message = EventPlanningConstants.START_DATE_NOT_NULL_CONSTRAINT_MESSAGE)
    private Date startDate;
}
