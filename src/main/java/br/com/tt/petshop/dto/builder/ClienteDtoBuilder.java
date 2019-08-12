package br.com.tt.petshop.dto.builder;

import br.com.tt.petshop.dto.AnimalDto;
import br.com.tt.petshop.dto.ClienteDto;

import java.util.Arrays;

public class ClienteDtoBuilder {

    private ClienteDto dto = new ClienteDto();

    public ClienteDtoBuilder setId(Long id) {
        dto.setId(id);
        return this;
    }

    public ClienteDtoBuilder setNome(String nome) {
        dto.setNome(nome);
        return this;
    }

    public ClienteDtoBuilder adicionaAnimal(AnimalDto animal) {
        if(dto.getAnimais() == null){
            dto.setAnimais(Arrays.asList(animal));
        }
        dto.getAnimais().add(animal);
        return this;
    }

    public ClienteDto build() {
        return dto;
    }
}
