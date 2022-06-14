package ru.zagarazhi.entities;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

//Сущность, представляющая пользователя
@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 32, nullable = false, unique = true)
    private String username;

    @Column(length = 80, nullable = false)
    private String password;

    @Column(nullable = false)
    private Long rating;

    @Transient
    private String passwordConfirm;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Game> games;
}
