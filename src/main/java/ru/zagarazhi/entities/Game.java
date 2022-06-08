package ru.zagarazhi.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "game_id")
    private Long id;

    @Column(name = "hidden_number", nullable = false)
    private Short hiddenNumber;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "max_attempts")
    private Integer maxAttempts;

    @Column(name = "real_attempts")
    private Integer realAttempts;

    @Column(name = "max_time")
    private Long maxTime;

    @Column(name = "real_time")
    private Long time;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "game")
    private List<Attempt> attempts;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
