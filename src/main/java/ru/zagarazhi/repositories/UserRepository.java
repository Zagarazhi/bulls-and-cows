package ru.zagarazhi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.zagarazhi.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
