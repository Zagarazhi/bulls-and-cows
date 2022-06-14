package ru.zagarazhi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    
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

    @GetMapping("/profile")
    public String pageProfile(Model model){
        return "profile";
    }
}
