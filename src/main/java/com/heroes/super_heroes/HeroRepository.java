package com.heroes.super_heroes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// provids crud operations
// params specify entity type and primary key type
public interface HeroRepository extends JpaRepository<HeroStats, Long> {

    void deleteByFilmName(String film_name);
    Optional<HeroStats> findByName(String name); // optional handles cases where player might not be found
}
