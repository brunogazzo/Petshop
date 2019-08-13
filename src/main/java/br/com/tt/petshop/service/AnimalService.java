package br.com.tt.petshop.service;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.model.vo.DataNascimento;
import br.com.tt.petshop.repository.AnimalRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnimalService {

    private static final int TAMANHO_MININO_NOME = 3;

    private final AnimalRepository animalRepository;
    private final ClienteService clienteService;

    public AnimalService(AnimalRepository animalRepository, ClienteService clienteService) {
        this.animalRepository = animalRepository;
        this.clienteService = clienteService;
    }

    public List<Animal> listar(Long clientId){
        return animalRepository.findByClienteId(clientId);
    }

    public List<String> listarEspecies() {

        List<String> especies = new ArrayList<>();
        for (EspecieEnum especie : EspecieEnum.values()) {
            especies.add(especie.name());
        }
        return especies;

    }

    public void salvar(Animal animal) throws BusinessException {
        if(Objects.isNull(animal)){
            throw new IllegalArgumentException("Animal deve ser informado!");
        }

        validarSeDataNascimentoMenorOuIgualHoje(animal.getDataNascimento());
        validarTamanhoMinimoNome(animal.getNome());
        clienteService.validarSeAdimplente(animal.getCliente().getId());

        animalRepository.save(animal);
    }

    private void validarTamanhoMinimoNome(String nome) throws BusinessException {
        if(Objects.isNull(nome)){
            throw new BusinessException("Nome deve ser informado!");
        }

        if(nome.length() < TAMANHO_MININO_NOME){
            throw new BusinessException(
                    String.format(
                            "O nome deve conter ao menos %d caracteres!",
                            TAMANHO_MININO_NOME));
        }
    }

    private void validarSeDataNascimentoMenorOuIgualHoje(DataNascimento dataNascimento) throws BusinessException {
        if(!dataNascimento.isValida()){
          throw new BusinessException("A data de nascimento deve ser anterior ou igual a hoje!");
        }
    }

    public List<Animal> listar(Optional<Long> clienteId, Optional<String> nome) {

        if(clienteId.isPresent() && nome.isPresent()){
            return animalRepository.findByClienteIdAndNomeOrderByNome(clienteId.get(), nome.get());

        } else if(clienteId.isPresent()){
            return animalRepository.findByClienteId(clienteId.get());

        } else if(nome.isPresent()){
            return animalRepository.findByNome(nome.get());
        }

        return animalRepository.findAll();
    }

    public List<Animal> listarByExample(Optional<Long> clienteId, Optional<String> nome) {

        Animal animal = new Animal();
        if (clienteId.isPresent()) {
            animal.setCliente(new Cliente(clienteId.get()));;
        }
        if (nome.isPresent()) {
            animal.setNome(nome.get());
        }
        return animalRepository.findAll(Example.of(animal), Sort.by("nome"));
    }
}
