package ru.zagarazhi.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

//Сущность, представляющая данные игры
@Entity
@Data
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "game_id")
    private Long id;

    @Column(length = 4, name = "hidden_number", nullable = false)
    private String hiddenNumber;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "was_attempts_constraint", nullable = false)
    private boolean wasAttemptsConstraint;

    @Column(name = "max_attempts")
    private Integer maxAttempts;

    @Column(name = "real_attempts")
    private Integer realAttempts;

    @Column(name = "was_time_constraint", nullable = false)
    private boolean wasTimeConstraint;

    @Column(name = "max_time")
    private Long maxTime;

    @Column(name = "real_time")
    private Long time;

    @Column(nullable = false)
    private boolean success;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "game")
    private List<Attempt> attempts;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
