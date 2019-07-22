package br.com.tt.petshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnimalController {

    @GetMapping("/animais-listar")
    public String listar(Model model, @RequestParam Long clientId){


        return "animais-listar";
    }
}
