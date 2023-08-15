package com.people.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record PeopleRequest (
    @NotBlank(message = "Nickname is required")
     String apelido,
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be equal or greater than 100 characters")
     String nome,

    @NotNull(message = "Date of birth is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
     LocalDate nascimento,
     @Size(min = 1, message = "Stacks must be at least 1")
     List<@NotBlank @Size(max = 32) String> stack
)
{
}
