package br.com.tt.petshop.controller;

import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/animais-listar")
    public String listar(Model model, @RequestParam Long clientId){

        model.addAttribute("animais", animalService.listar(clientId));

        return "animais-listar";
    }

    @GetMapping("/animais-adicionar")
    public String adicionar(Model model){

        model.addAttribute("especies", listarEspecies());
        if(model.containsAttribute("animal") == false){
            model.addAttribute("animal", new Animal());
            model.addAttribute("cliente", new Cliente());
        }

        return "/animais-adicionar";
    }

    @PostMapping("/animais-form")
    public String salvar(Model model, Animal animal,
                         @RequestParam Long clientId){

        try {
            animal.setCliente(new Cliente(clientId, null,null));
            animalService.salvar(animal);
            return String.format("redirect:/animais-listar?clientId=%s", animal.getCliente().getId());

        } catch (BusinessException e) {
            model.addAttribute("erro", e.getMessage());

            return adicionar(model);
        }
    }

    private List<String> listarEspecies() {
        return animalService.listarEspecies();
    }

}
