package br.com.tt.petshop.service;

import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void remover(Long id) {
        //TODO alterar no JPA.
        Cliente cliente = new Cliente();
        cliente.setId(id);
        clienteRepository.delete(cliente);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public void adicionar(Cliente novoCliente) throws BusinessException {
        validaNome(novoCliente);
        validaCpf(novoCliente);
        clienteRepository.save(novoCliente);
    }

//    O nome da pessoa deve ser composta de pelo menos duas partes.
//    Cada parte do nome da pessoa deve conter ao menos 2 letras.
    private void validaNome(Cliente novoCliente) throws BusinessException{
        if (Objects.isNull(novoCliente) || Objects.isNull(novoCliente.getNome())) {
            throw new BusinessException("Nome deve ser informado!");
        }

        String[] partes = novoCliente.getNome().split(" ");
        if(partes.length < 2){
            throw new BusinessException("Informe seu nome completo!");
        }
        for (String parte : partes){
            if(parte.length() < 2){
                throw new BusinessException("Informe seu nome sem abreviações!");
            }
        }
    }

    //    O CPF da pessoa deve conter 11 dígitos (desconsiderando a formatação).
    private void validaCpf(Cliente novoCliente) throws BusinessException{
        if (Objects.isNull(novoCliente)
                || Objects.isNull(novoCliente.getCpf().getValor())) {
            throw new BusinessException("Informe seu CPF!");
        }
        //String cpf = novoCliente.getCpf().replaceAll("\\D", "");
        if(!novoCliente.getCpf().isValid()){
            throw new BusinessException("Informe seu CPF com 11 dígitos!");
//        }else{
//            novoCliente.setCpf(cpf);
        }
    }

    public void validarSeAdimplente(Long clientId) throws BusinessException {
        Cliente cliente = clienteRepository.getOne(clientId);

        if(cliente.isInadimplente()){
            throw new BusinessException("Cliente não está adimplente!");
        }
    }
}
