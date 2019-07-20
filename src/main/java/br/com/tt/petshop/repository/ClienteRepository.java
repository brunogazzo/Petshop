package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Cliente;
import org.springframework.stereotype.Repository;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ClienteRepository {

    List<Cliente> clientes = new ArrayList<>(
            Arrays.asList(
            new Cliente(1L, "Fulano", "000.111.222-33"),
            new Cliente(2L, "Ciclano", "111.222.333-44")
    ));

    public void delete(Cliente cliente) {
        clientes.remove(cliente);
    }

    public void save(Cliente cliente) {
        try {
            cliente.setId(SecureRandom.getInstanceStrong().nextLong());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        clientes.add(cliente);
    }

    public List<Cliente> findAll() {
        return clientes;
    }
}