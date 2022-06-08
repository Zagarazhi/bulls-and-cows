package ru.zagarazhi.entities;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "attempts")
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attempt_id")
    private Long id;

    private Short answear;

    private Long time;

    private boolean success;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
}
