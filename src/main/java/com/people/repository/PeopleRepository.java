package com.people.repository;

import com.people.domain.entity.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PeopleRepository extends JpaRepository<PeopleEntity, UUID> {

    @Query(
            nativeQuery = true,
            value = "SELECT p.*" +
                    " FROM PEOPLE p " +
                    " WHERE " +
                    " LOWER(p.nick_name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
                    " LOWER(p.people_name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
                    " LOWER(p.stacks) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
                    " LIMIT 50;"
    )
    List<PeopleEntity> findAllBySearchTerm(String searchTerm);
}
