package ru.zagarazhi.entities;

import javax.persistence.*;

import lombok.Data;

//Сущность, представляющая попытку
@Entity
@Data
@Table(name = "attempts")
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attempt_id")
    private Long id;

    @Column(length = 4, nullable = false)
    private String answear;

    @Column(nullable = false)
    private Long time;

    @Column(nullable = false)
    private boolean success;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
}
