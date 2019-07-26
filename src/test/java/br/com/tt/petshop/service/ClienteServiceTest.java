package br.com.tt.petshop.service;

import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.repository.ClienteRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class ClienteServiceTest {

    private ClienteService clienteService;
    private ClienteRepository clienteRepository;

    @Test
    public void deveriaRetornarListaVazia(){

        clienteRepository = new ClienteRepository();
        clienteService = new ClienteService(clienteRepository);

        List<Cliente> clientes = clienteService.listar();

        assertNotNull("A lista não deveria ser nula",
                clientes);
        assertEquals("A lista deveria ter 2 clientes",
                2, clientes.size());
//        fail("O nome é igual a ");
    }
}