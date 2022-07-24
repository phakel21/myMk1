package com.Rpg.repository;

import com.Rpg.entity.MyCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyCharacterRepository extends JpaRepository<MyCharacter, Long> {

    MyCharacter findByName(String name);

    void deleteByName(String name);

    Optional<MyCharacter> findMyCharacterByName(String name);


}
