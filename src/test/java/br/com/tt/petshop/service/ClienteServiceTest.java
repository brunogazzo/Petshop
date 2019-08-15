package br.com.tt.petshop.service;

import br.com.tt.petshop.client.CreditoApiClient;
import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.repository.ClienteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private CreditoApiClient creditoApiClient;

    @Before
    public void setUp() {
        clienteService = new ClienteService(clienteRepository, creditoApiClient);
    }

    @Test
    public void deveriaRetornarListaVazia() {

        //Arrange

        //Act
        List<Cliente> clientes = clienteService.listar();

        //Assert
        assertNotNull("A lista não deveria ser nula",
                clientes);
        assertEquals("A lista deveria retornar nenhum client",
                0, clientes.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    public void deveriaRetornarListaComClientes() {
        // Arrange - Setup
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(new Cliente(1L, "Fulano", "000.111.222-33"));
        listaClientes.add(new Cliente(2L, "Ciclano", "111.222.333-44"));
        when(clienteRepository.findAll()).thenReturn(listaClientes);

        // Act - Execução!
        List<Cliente> clientes = clienteService.listar();

        // Assert - Verificação
        assertEquals("Deveria retornar 2 clientes", 2, clientes.size());
        assertEquals("Deveria retornar o Fulano", "Fulano", clientes.get(0).getNome());
    }

    @Test
    public void deveriaRemoverComSucesso() {
        //Arrange
        Cliente clienteDeletado = new Cliente(12L, null, null);

        //Act
        clienteService.remover(12L);

        //Assert
        verify(clienteRepository)
                .delete(clienteDeletado);
        verifyNoMoreInteractions(clienteRepository);
    }

    @Test
    public void deveriaAdicionarComSucesso() throws BusinessException {
        Cliente cliente = new Cliente(1L, "Fulano da Silva Silva", "000.211.344-02");

        clienteService.adicionar(cliente);

        verify(clienteRepository).save(cliente);
    }

    @Test
    public void deveriaFalharPorNaoTerNome() {
        Cliente cliente = new Cliente(1L, null, "000.211.344-02");
        try {
            clienteService.adicionar(cliente);
            fail("Deveria ter lançado exceção por não ter nome!");
        } catch (BusinessException e) {
            assertEquals("Nome deve ser informado!", e.getMessage());
        }
    }

    @Test
    public void deveriaFalharPorNaoTerNomeCompleto() {
        Cliente cliente = new Cliente(1L, "Fulano", "000.211.344-02");
        try {
            clienteService.adicionar(cliente);
            fail("Deveria ter lançado exceção por não ter o nome completo!");
        } catch (BusinessException e) {
            assertEquals("Informe seu nome completo!", e.getMessage());
         }
    }

    @Test
    public void deveriaFalharPorTerNomeAbreviado() {
        Cliente cliente = new Cliente(1L, "Fulano S", "000.211.344-02");
        try {
            clienteService.adicionar(cliente);
            fail("Deveria ter lançado exceção por ter abreviações!");
        } catch (BusinessException e) {
            assertEquals("Informe seu nome sem abreviações!", e.getMessage());
        }
    }

    @Test
    public void deveriaFalharPorNaoTerCPF() {
        Cliente cliente = new Cliente(1L, "Fulano Silva", null);
        try {
            clienteService.adicionar(cliente);
            fail("Deveria ter lançado exceção por não ter CPF!");
        } catch (BusinessException e) {
            assertEquals("Informe seu CPF!", e.getMessage());
        }
    }

    @Test
    public void deveriaFalharPorTerCPFInvalido() {
        Cliente cliente = new Cliente(1L, "Fulano Silva", "112.000.111");
        try {
            clienteService.adicionar(cliente);
            fail("Deveria ter lançado exceção por não ter CPF válido!");
        } catch (BusinessException e) {
            assertEquals("Informe seu CPF com 11 dígitos!", e.getMessage());
        }
    }

    @Test
    public void deveriaValidarSeAdimplente() throws BusinessException {
        Cliente cliente = new Cliente(1L, "Fulano Silva", "112.000.111");
        when(clienteRepository.getOne(1L)).thenReturn(cliente);

        clienteService.validarSeAdimplente(1L);

        verify(clienteRepository).getOne(1L);
    }

    @Test
    public void deveriaFalharSeInadimplente() throws BusinessException {
        Cliente cliente = new Cliente(1L, "Fulano Silva", "112.000.111");
        cliente.setInadimplente(true);
        when(clienteRepository.getOne(1L)).thenReturn(cliente);

        try {
            clienteService.validarSeAdimplente(1L);
            fail("Deveria ter falhado pois é inadimplente!");
        }catch (BusinessException e){
            assertEquals("Cliente não está adimplente!", e.getMessage());
        }

        verify(clienteRepository).getOne(1L);
    }

}