package com.people.controller;

import com.people.entity.PeopleEntity;
import com.people.repository.PeopleRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping(produces = "application/json")
@CacheConfig(cacheNames = "people")
public class PeopleController {

    private final PeopleRepository peopleRepository;

    private final RedisTemplate<String, PeopleEntity> redisTemplate;

    private final ExecutorService executorService;

    public PeopleController(PeopleRepository peopleRepository, RedisTemplate<String, PeopleEntity> redisTemplate) {
        this.peopleRepository = peopleRepository;
        this.redisTemplate = redisTemplate;
        this.executorService = Executors.newFixedThreadPool(10);
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
    public ResponseEntity<?> postPeople(@RequestBody @Valid PeopleEntity peopleEntity) {
        if (redisTemplate.opsForValue().get(peopleEntity.getApelido()) != null) {
            return ResponseEntity.unprocessableEntity().body("Apelido já cadastrado");
        }
        peopleEntity.setId(UUID.randomUUID());
        redisTemplate.opsForValue().set(peopleEntity.getId().toString(), peopleEntity);
        redisTemplate.opsForValue().set(peopleEntity.getApelido(), peopleEntity);
        savePeopleAsync(peopleEntity);
        return ResponseEntity.created(URI.create("/pessoas/" + peopleEntity.getId())).build();
    }

    @GetMapping("/contagem-pessoas")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(peopleRepository.count());
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<PeopleEntity>> searchPessoas(@RequestParam("t") @NotNull @NotEmpty String t) {
        return ResponseEntity.ok(peopleRepository.findAllBySearchTerm(t));
    }

    @Async
    public CompletableFuture<Void> savePeopleAsync(PeopleEntity peopleEntity) {
        return CompletableFuture.runAsync(() -> peopleRepository.save(peopleEntity), executorService);
    }
}
