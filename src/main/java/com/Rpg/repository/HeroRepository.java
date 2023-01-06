package com.Rpg.repository;

import com.Rpg.entity.Hero;
import com.Rpg.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeroRepository extends JpaRepository<Hero, Long> {

    Hero findByName(String name);

    void deleteByName(String name);

    Optional<Hero> findHeroByName(String name);

    Integer countHeroByMyUser(MyUser myUser);

    boolean existsHeroByName(String name);

    List<Hero> findAllByMyUser(MyUser myUser);
}
