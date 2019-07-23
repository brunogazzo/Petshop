package br.com.tt.petshop.service;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> listar(Long clientId){
        return animalRepository.listar(clientId);
    }

    public List<String> listarEspecies() {

        List<String> especies = new ArrayList<>();
        for (EspecieEnum especie : EspecieEnum.values()) {
            especies.add(especie.name());
        }
        return especies;

    }
}
