package com.people.controller;

import com.people.domain.dto.PeopleAssembler;
import com.people.domain.dto.PeopleRequest;
import com.people.domain.dto.PeopleResponse;
import com.people.repository.PeopleRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class PeopleController {

    private PeopleRepository peopleRepository;

    private PeopleAssembler peopleAssembler;

    public PeopleController(PeopleRepository peopleRepository, PeopleAssembler peopleAssembler) {
        this.peopleRepository = peopleRepository;
        this.peopleAssembler = peopleAssembler;
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<PeopleResponse> getPeople(@PathVariable UUID id) {
        return peopleRepository.findById(id)
                .map(peopleAssembler::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> postPeople(@RequestBody @Valid PeopleRequest peopleRequest) {
        var savedPeople = peopleRepository.save(peopleAssembler.toEntity(peopleRequest));
        return ResponseEntity.created(URI.create("/people/" + savedPeople.getId())).build();
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
       return ResponseEntity.ok(peopleRepository.findAll().size());
    }
}
