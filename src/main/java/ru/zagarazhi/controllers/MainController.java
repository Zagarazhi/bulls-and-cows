package ru.zagarazhi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonParseException;

import ru.zagarazhi.entities.dto.GameDto;
import ru.zagarazhi.services.GameService;

@Controller
public class MainController {

    @Autowired
    GameService gameService;
    
    @GetMapping("/")
    public String root() {
        return "game";
    }

    @GetMapping("/game")
    public String game() {
        return "game";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/save")
    public String save(@RequestBody GameDto gameDto) throws JsonParseException{
        gameService.save(gameDto);
        return "game";
    }
}
