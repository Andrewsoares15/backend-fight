package com.people.domain.dto;

import com.people.domain.entity.PeopleEntity;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class PeopleAssembler {

    public PeopleEntity toEntity(PeopleRequest peopleRequest) {
        var people = new PeopleEntity();
        people.setName(peopleRequest.getName());
        people.setNickname(peopleRequest.getNickname());
        people.setDateNascimento(peopleRequest.getDateNascimento());
        people.setStacks(peopleRequest.getStacks());
        return people;
    }
    public PeopleResponse toResponse(PeopleEntity peopleEntity) {
        return PeopleResponse.builder()
                .id(peopleEntity.getId())
                .nickname(peopleEntity.getNickname())
                .name(peopleEntity.getName())
                .dateNascimento(peopleEntity.getDateNascimento())
                .stacks(peopleEntity.getStacks())
                .build();
    }
}
