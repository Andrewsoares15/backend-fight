package com.people.domain.dto;

import com.people.domain.entity.PeopleEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PeopleAssembler {

    public PeopleEntity toEntity(PeopleRequest peopleRequest) {
        return new PeopleEntity(UUID.randomUUID(), peopleRequest.apelido(), peopleRequest.nome(), peopleRequest.nascimento(), peopleRequest.stack());
    }

}
