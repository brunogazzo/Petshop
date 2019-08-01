package br.com.tt.petshop.service;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        return animalRepository.findByClientId(clientId);
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
        clienteService.validarSeAdimplente(animal.getClientId());

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

    private void validarSeDataNascimentoMenorOuIgualHoje(LocalDate dataNascimento) throws BusinessException {

        if(Objects.isNull(dataNascimento) ||
                LocalDate.now().isBefore(dataNascimento)){
//            if(Objects.isNull(animal.getDataNascimento())
//                    || animal.getDataNascimento().isAfter(LocalDate.now())){
          throw new BusinessException("A data de nascimento deve ser anterior ou igual a hoje!");
        }



    }
}
