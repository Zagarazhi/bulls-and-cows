package ru.zagarazhi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.zagarazhi.entities.Attempt;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Long>{
    
}
