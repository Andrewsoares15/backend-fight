package com.people.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PEOPLE")
public class PeopleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nick_name", nullable = false, unique = true, length = 32)
    private String nickname;

    @Column(name = "people_name", nullable = false, length = 100)
    private String name;
    @Column(name = "dat_birth", nullable = false)
    private LocalDate dateNascimento;

    @Convert(converter = StringListConverter.class)
    @Column(name = "stacks")
    private List<String> stacks;

    public PeopleEntity() {
    }
    public PeopleEntity(String nickname, String name, LocalDate dateNascimento, List<String> stacks) {
        this.nickname = nickname;
        this.name = name;
        this.dateNascimento = dateNascimento;
        this.stacks = stacks;
    }

    public UUID getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateNascimento() {
        return dateNascimento;
    }

    public void setDateNascimento(LocalDate dateNascimento) {
        this.dateNascimento = dateNascimento;
    }

    public List<String> getStacks() {
        return stacks;
    }

    public void setStacks(List<String> stacks) {
        this.stacks = stacks;
    }
}
