package com.people.domain.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "STACK")
public class StackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stack_seq")
    @SequenceGenerator(name = "stack_seq", sequenceName = "stack_seq", allocationSize = 1)
    private Long id;
    @Column(name = "people_id")
    private UUID peopleId;

    @Column(name = "stack_name")
    private String name;

    public StackEntity(Long id, UUID peopleId, String name) {
        this.id = id;
        this.peopleId = peopleId;
        this.name = name;
    }
    public StackEntity() {
    }

    public StackEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(UUID peopleId) {
        this.peopleId = peopleId;
    }
}
