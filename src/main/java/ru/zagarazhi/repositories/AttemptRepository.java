package ru.zagarazhi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.zagarazhi.entities.Attempt;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Long>{
    List<Attempt> findAllByGame_id(Long game_id);
}
