package com.people.repository;

import com.people.domain.entity.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PeopleRepository extends JpaRepository<PeopleEntity, UUID> {
}
