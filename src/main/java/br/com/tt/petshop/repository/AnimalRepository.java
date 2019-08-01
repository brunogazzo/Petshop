package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByClientId(Long clientId);

/*
    List<Animal> animais = new ArrayList<>(Arrays.asList(
            new Animal("Rex", LocalDate.now(),
                    EspecieEnum.MAMIFERO, 1L),
            new Animal("Nemo", LocalDate.now().minusMonths(1),
                    EspecieEnum.PEIXE, 1L)
    ));

    public List<Animal> listar(Long clientId) {
        List<Animal> animaisDoCliente = new ArrayList<>();
        for (Animal animal: animais) {
            if(animal.getClientId().equals(clientId)){
                animaisDoCliente.add(animal);
            }
        }
        return animaisDoCliente;
    }

    public void save(Animal animal) {
        animais.add(animal);
    }*/
}
