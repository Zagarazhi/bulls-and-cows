package ru.zagarazhi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;

import ru.zagarazhi.entities.dto.GameDto;
import ru.zagarazhi.entities.res.ResGame;
import ru.zagarazhi.services.GameService;
import ru.zagarazhi.services.UserService;

@RestController
@RequestMapping("api/v1")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public String save(@RequestBody GameDto gameDto) throws JsonParseException{
        gameService.save(gameDto);
        return "game";
    }

    @GetMapping("/profile_games")
    ResponseEntity<List<ResGame>> getGames(){
        return ResponseEntity.ok(gameService.findUserGames());
    }

    @GetMapping("/profile_info")
    ResponseEntity<List<String>> getInfo(){
        return ResponseEntity.ok(userService.find());
    }
}
