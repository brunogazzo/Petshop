package br.com.tt.petshop.service;

import br.com.tt.petshop.cliente.Cliente;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ClienteService {

    public List<Cliente> listar(){
        List<Cliente> listaClientes = Arrays.asList(
                new Cliente("Fulano", "000.111.222-33"),
                new Cliente("Ciclano", "111.222.333-44")
        );
        return listaClientes;
    }
}
