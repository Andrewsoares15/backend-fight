package com.people.domain.dto;


import com.people.domain.entity.PeopleEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Data
public class PeopleRequest {

    @NotBlank(message = "Nickname is required")
    private String nickname;
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be equal or greater than 100 characters")
    private String name;

    @NotNull(message = "Date of birth is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNascimento;

    private List<String> stacks;
}

