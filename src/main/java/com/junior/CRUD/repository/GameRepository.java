package com.junior.CRUD.repository;

import com.junior.CRUD.model.Games;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GameRepository extends JpaRepository<Games, Long> {

    @Query(value = "SELECT * FROM games WHERE description ILIKE %?1% ", nativeQuery = true)
    Page<Games> searchDescriptionLike(Pageable page, String description);

    @Query(value = "SELECT * FROM games WHERE name ILIKE %?1%", nativeQuery = true)
    Page<Games> searchNameLike(Pageable page, String name);

}
