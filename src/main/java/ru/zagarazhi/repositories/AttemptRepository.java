package ru.zagarazhi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.zagarazhi.entities.Attempt;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Long>{
    Page<Attempt> findAllByGame_id(Long game_id, Pageable pageable);
}
