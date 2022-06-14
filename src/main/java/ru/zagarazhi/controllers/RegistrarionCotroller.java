package ru.zagarazhi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.zagarazhi.entities.dto.UserRegistrationDto;
import ru.zagarazhi.services.UserService;

//Контроллер регистрации
@Controller
@RequestMapping("/registration")
public class RegistrarionCotroller {
    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String registration(Model model){
        return "registration";
    }

    @PostMapping
    public String createUser(@ModelAttribute("userForm") @Valid UserRegistrationDto userDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "registration";
        }
        if(!userService.save(userDto)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/game";
    }

}
