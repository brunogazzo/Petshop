package br.com.tt.petshop.dto.factory;

import br.com.tt.petshop.dto.ClienteDto;
import br.com.tt.petshop.dto.builder.ClienteDtoBuilder;
import br.com.tt.petshop.model.Cliente;

public class ClienteDtoFactory {

    public static ClienteDto from(Cliente cliente){
        return new ClienteDtoBuilder()
                .setId(cliente.getId())
                .setNome(cliente.getNome())
                .build();
    }

}
