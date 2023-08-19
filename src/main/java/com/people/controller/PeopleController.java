package com.people.controller;

import com.people.domain.dto.PeopleAssembler;
import com.people.domain.dto.PeopleRequest;
import com.people.domain.entity.PeopleEntity;
import com.people.repository.PeopleRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(produces = "application/json")
@CacheConfig(cacheNames = "people")
public class PeopleController {

    private final PeopleRepository peopleRepository;

    private final PeopleAssembler peopleAssembler;

    @Autowired
    private RedisTemplate<String, PeopleEntity> redisTemplate;

    public PeopleController(PeopleRepository peopleRepository, PeopleAssembler peopleAssembler) {
        this.peopleRepository = peopleRepository;
        this.peopleAssembler = peopleAssembler;
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<PeopleEntity> getPeople(@PathVariable UUID id) {
        PeopleEntity peopleCached = redisTemplate.opsForValue().get(id.toString());
        if (peopleCached != null) {
            return ResponseEntity.ok(peopleCached);
        }
        return peopleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/pessoas")
    public ResponseEntity<?> postPeople(@RequestBody @Valid PeopleRequest peopleRequest) {
        if (redisTemplate.hasKey(peopleRequest.apelido())) {
            return ResponseEntity.unprocessableEntity().body("Apelido já cadastrado");
        }
        PeopleEntity entity = peopleAssembler.toEntity(peopleRequest);
        redisTemplate.opsForValue().set(entity.getId().toString(), entity);
        redisTemplate.opsForValue().set(entity.getNickname(), entity);
        peopleRepository.save(entity);
        return ResponseEntity.created(URI.create("/pessoas/" + entity.getId())).build();
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
