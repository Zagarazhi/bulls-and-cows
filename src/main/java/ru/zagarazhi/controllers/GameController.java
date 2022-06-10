package ru.zagarazhi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;

import ru.zagarazhi.entities.dto.GameDto;
import ru.zagarazhi.services.GameService;

@RestController
@RequestMapping("api/v1")
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping("/save")
    public String save(@RequestBody GameDto gameDto) throws JsonParseException{
        gameService.save(gameDto);
        return "game";
    }
}
