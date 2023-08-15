package com.people.domain.dto;

import com.people.domain.entity.PeopleEntity;
import com.people.domain.entity.StackEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class PeopleAssembler {

    public PeopleEntity toEntity(PeopleRequest peopleRequest) {

        return new PeopleEntity(peopleRequest.apelido(), peopleRequest.nome(), peopleRequest.nascimento(), peopleRequest.stack() != null ? peopleRequest.stack().stream().map(StackEntity::new).toList() : Collections.emptyList());
    }

}
