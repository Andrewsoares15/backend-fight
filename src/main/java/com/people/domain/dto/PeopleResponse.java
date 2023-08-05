package com.people.domain.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record PeopleResponse(
     UUID id,
     String nickname,
     String name,
     LocalDate dateNascimento,
     List<String> stacks
){

}
