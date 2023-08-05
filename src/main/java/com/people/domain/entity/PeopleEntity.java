package com.people.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "people")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleEntity {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "people_name")
    private String name;
    @Column(name = "dat_birth")
    private LocalDate dateNascimento;

    @Column(name = "stack")
    @ElementCollection
    private List<String> stacks;
}
