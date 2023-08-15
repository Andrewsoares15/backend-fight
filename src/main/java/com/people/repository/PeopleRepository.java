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
            value = "SELECT DISTINCT p.*" +
                    " FROM PEOPLE p " +
                    " INNER JOIN STACK ps ON p.id = ps.people_id " +
                    " WHERE " +
                    " LOWER(p.nick_name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
                    " LOWER(p.people_name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
                    " LOWER(ps.stack_name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
                    " LIMIT 50;"
    )
    List<PeopleEntity> findAllBySearchTerm(String searchTerm);
}
