package com.example.ticketselling.dto;

import com.example.ticketselling.constants.ClientConstants;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private int id;

    @NotBlank(message = ClientConstants.FIRST_NAME_NOT_BLANK_CONSTRAINT_MESSAGE)
    private String firstName;

    @NotBlank(message = ClientConstants.LAST_NAME_NOT_BLANK_CONSTRAINT_MESSAGE)
    private String lastName;

    @Email(message = ClientConstants.VALID_EMAIL_CONSTRAINT_MESSAGE)
    private String email;

    @NotBlank(message = ClientConstants.PHONE_NUMBER_NOT_BLANK_CONSTRAINT_MESSAGE)
    private String phoneNumber;

    @NotNull(message = ClientConstants.DATE_OF_BIRTH_NOT_NULL_CONSTRAINT_MESSAGE)
    private Date dateOfBirth;
}
