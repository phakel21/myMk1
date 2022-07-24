package com.Rpg.repository;

import com.Rpg.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findByName(String name);

    void deleteByName(String name);

    Optional<Location> findLocationByName(String name);

//    Integer countByName(String name);
}
