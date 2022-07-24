package com.Rpg.repository;

import com.Rpg.entity.Hero;
import com.Rpg.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

    Hero findByName(String name);

    void deleteByName(String name);

    List<Hero> findAllByMyUserLogin(String name);

    Optional<Hero> findHeroByName(String name);

    Hero findHeroByMyUserAndName(MyUser myUser, String name);

}
