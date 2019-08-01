package br.com.tt.petshop.service;

import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.repository.AnimalRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static br.com.tt.petshop.enums.EspecieEnum.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnimalServiceTest {

    private AnimalService animalService;

    @Mock
    private AnimalRepository animalRepository;
    @Mock
    private ClienteService clienteService;

    @Before
    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this); // OU @RunWith(MockitoJUnitRunner.class)
//        animalRepository = Mockito.mock(AnimalRepository.class);
//        clienteService = Mockito.mock(ClienteService.class);

        animalService = new AnimalService(animalRepository, clienteService);
    }

    @Test
    public void deveriaRetornarListaVazia() {
        List<Animal> lista = animalService.listar(0L);
        assertEquals("Deveria retornar lista vazia!", 0, lista.size());
    }

    @Test
    public void deveriaRetornarListaComAnimais(){
        //Arrange
        List<Animal> listaCliente01 = Arrays.asList(
                new Animal("Rex", LocalDate.now(), MAMIFERO, 01L),
                new Animal("Totó", LocalDate.now().minusYears(1), MAMIFERO, 01L)
        );
        List<Animal> listaCliente02 = Arrays.asList(
                new Animal("Rex", LocalDate.now(), MAMIFERO, 02L)
        );
        when(animalRepository.findByClientId(1L)).thenReturn(listaCliente01);
        when(animalRepository.findByClientId(2L)).thenReturn(listaCliente02);

        //Act
        List<Animal> animaisCliente01 = animalService.listar(1L);
        List<Animal> animaisCliente02 = animalService.listar(2L);
        animalService.listar(null);

        //Asserts
        assertEquals("Deveria retornar o animal Rex do Client Id 01",
                Long.valueOf(1L),
                animaisCliente01.get(0).getClientId());

        assertEquals("Deveria retornar a lista do cliente02",
                listaCliente02,
                animaisCliente02);
    }

    @Test
    public void deveriaRetornarEspecies(){
        List<String> especies = animalService.listarEspecies();
        assertArrayEquals("Deveria ter o mesmo array de espécies",
                especies.toArray(),
                new String[]{REPTIL.name(), MAMIFERO.name(), PEIXE.name()});
    }

//    @Test
//    public void deveriaFalharClienteInadinplente() throws BusinessException {
//        when(clienteService.validarSeAdimplente(01L)).thenThrow(BusinessException.class);
//        Em métodos VOID para retornar exceção:
//        doThrow(BusinessException.class).when(clienteService).validarSeAdimplente(01L);
//    }
}