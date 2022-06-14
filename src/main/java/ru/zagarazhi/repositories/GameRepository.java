package ru.zagarazhi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.zagarazhi.entities.Game;

//Обращение к базе данных игр
@Repository
public interface GameRepository extends JpaRepository<Game, Long>{
    List<Game> findByUser_id(Long user_id);
}
