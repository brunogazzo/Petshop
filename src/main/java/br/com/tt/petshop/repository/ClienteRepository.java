package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

//    List<Cliente> clientes = new ArrayList<>(
//            Arrays.asList(
//            new Cliente(1L, "Fulano", "000.111.222-33"),
//            new Cliente(2L, "Ciclano", "111.222.333-44")
//    ));
//
//    public void delete(Cliente client) {
//        clientes.remove(client);
//    }
//
//    public void save(Cliente client) {
//        try {
//            client.setId(SecureRandom.getInstanceStrong().nextLong());
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        clientes.add(client);
//    }
//
//    public List<Cliente> findAll() {
//        return clientes;
//    }
//
//    public Cliente find(Long clientId) {
////        Cliente client = new Cliente();
////        client.setId(clientId);
////        int index = clientes.indexOf(client);
////        if(index > 0){
////            return clientes.get(index);
////        }
//
//        for (Cliente client: clientes) {
//            if(client.getId().equals(clientId)){
//                return client;
//            }
//        }
//        return null;
//    }
}
