package ru.zagarazhi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.zagarazhi.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{
    Page<Game> findByUser_id(Long user_id, Pageable pageable);
}
