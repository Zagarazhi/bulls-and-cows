package ru.zagarazhi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ru.zagarazhi.entities.Attempt;
import ru.zagarazhi.entities.Game;
import ru.zagarazhi.entities.User;
import ru.zagarazhi.entities.dto.AttemptDto;
import ru.zagarazhi.entities.dto.GameDto;
import ru.zagarazhi.repositories.AttemptRepository;
import ru.zagarazhi.repositories.GameRepository;
import ru.zagarazhi.repositories.UserRepository;

@Service
public class GameService {
    @Autowired
    private AttemptRepository attemptRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;

    public Page<Game> findUserGames(Pageable pageable){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName()).get();
        return gameRepository.findByUser_id(user.getId(), pageable);
    }

    public boolean save(GameDto gameDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName()).get();
        Game game = new Game();
        List<Attempt> attempts = new ArrayList<>();
        AttemptDto[] tempAttempts = gameDto.getAttempts();
        long time = 0;
        for(int i = 0; i < tempAttempts.length; i++){
            Attempt attempt = new Attempt();
            attempt.setAnswear(tempAttempts[i].getAnswear());
            time += tempAttempts[i].getTime();
            attempt.setTime(tempAttempts[i].getTime());
            attempt.setSuccess(tempAttempts[i].getSuccess());
            attempt.setGame(game);
            attempts.add(attempt);
        }
        game.setAttempts(attempts);
        game.setUser(user);
        game.setHiddenNumber(gameDto.getHiddenNumber());
        game.setRealAttempts(tempAttempts.length);
        game.setTime(time);
        game.setStartTime(gameDto.getStartTime());
        game.setSuccess(gameDto.getSuccess());
        game.setWasAttemptsConstraint(gameDto.getWasAttemptsConstraint());
        if(gameDto.getWasAttemptsConstraint() && gameDto.getMaxAttempts() != null){
            game.setMaxAttempts(gameDto.getMaxAttempts());
        }
        game.setWasTimeConstraint(gameDto.getWasTimeConstraint());
        if(gameDto.getWasTimeConstraint() && gameDto.getMaxTime() != null){
            game.setMaxTime(gameDto.getMaxTime());
        }
        user.getGames().add(game);
        gameRepository.save(game);
        attemptRepository.saveAll(attempts);
        return true;
    }
}
