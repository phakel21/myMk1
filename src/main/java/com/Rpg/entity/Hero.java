package com.Rpg.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hero")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "currentHp", nullable = false)
    private Integer currentHp;

    @Column(name = "score", nullable = false)
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false)
    private MyCharacter myCharacter;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private MyUser myUser;


    @PrePersist
    public void onCreate(){
        this.currentHp = myCharacter.getHp();
        this.score = 0;
    }

}
