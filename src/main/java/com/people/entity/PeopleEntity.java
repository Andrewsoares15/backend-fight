package com.people.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PEOPLE")
public class PeopleEntity implements Serializable {

    @Id
    private UUID id;

    @Column(name = "nick_name", nullable = false, unique = true, length = 32)
    @NotBlank(message = "Nickname is required")
    private String apelido;
    @Column(name = "people_name", nullable = false, length = 100)
    @NotBlank(message = "Name is required")
    private String nome;
    @Column(name = "dat_birth", nullable = false)
    @NotNull(message = "Date of birth is required")
    private LocalDate nascimento;

    @Convert(converter = StringListConverter.class)
    @Column(name = "stacks", length = 32)
    private List<@NotBlank @Size(max = 32) String> stack;

    public PeopleEntity() {
    }

    public PeopleEntity(UUID id, String apelido, String nome, LocalDate nascimento, List<@NotBlank @Size(max = 32) String> stack) {
        this.id = id;
        this.apelido = apelido;
        this.nome = nome;
        this.nascimento = nascimento;
        this.stack = stack;
    }

    public UUID getId() {
        return id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public List<String> getStack() {
        return stack;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
