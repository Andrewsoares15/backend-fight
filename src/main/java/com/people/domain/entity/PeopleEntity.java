package com.people.domain.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PEOPLE")
public class PeopleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nick_name")
    private String nickname;

    @Column(name = "people_name")
    private String name;
    @Column(name = "dat_birth")
    private LocalDate dateNascimento;

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "people_id")
    private List<StackEntity> stacks;

    public PeopleEntity() {
    }
    public PeopleEntity(String nickname, String name, LocalDate dateNascimento, List<StackEntity> stacks) {
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

    public List<StackEntity> getStacks() {
        return stacks;
    }

    public void setStacks(List<StackEntity> stacks) {
        this.stacks = stacks;
    }
}
