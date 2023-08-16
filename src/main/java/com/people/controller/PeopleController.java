package com.people.controller;

import com.people.domain.dto.PeopleAssembler;
import com.people.domain.dto.PeopleRequest;
import com.people.domain.entity.PeopleEntity;
import com.people.repository.PeopleRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(produces = "application/json")
public class PeopleController {

    private final PeopleRepository peopleRepository;

    private final PeopleAssembler peopleAssembler;

    public PeopleController(PeopleRepository peopleRepository, PeopleAssembler peopleAssembler) {
        this.peopleRepository = peopleRepository;
        this.peopleAssembler = peopleAssembler;
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<PeopleEntity> getPeople(@PathVariable UUID id) {
        return peopleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/pessoas")
    public ResponseEntity<?> postPeople(@RequestBody @Valid PeopleRequest peopleRequest) {
        try {
            PeopleEntity saved = peopleRepository.save(peopleAssembler.toEntity(peopleRequest));
            return ResponseEntity.created(URI.create("/pessoas/" + saved.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body("Erro ao salvar pessoa");
        }

    }

    @GetMapping("/contagem-pessoas")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(peopleRepository.findAll().size());
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<PeopleEntity>> searchPessoas(@RequestParam("t") @NotNull @NotEmpty String t) {
        return ResponseEntity.ok(peopleRepository.findAllBySearchTerm(t));
    }

}
