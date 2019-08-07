package br.com.tt.petshop.repository;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.model.Animal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(value = "classpath:limpa.sql", executionPhase = BEFORE_TEST_METHOD)
public class AnimalRepositoryIT {

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    public void deveriaRetornarListaVazia() {
        List<Animal> lista = animalRepository.findByClienteId(1L);
        assertEquals("Deveria ser lista vazia", 0, lista.size());
    }

//    @Before
//    public void setUp() {
//    }

    @Test
    @Sql("classpath:insere_rex.sql")
    public void deveriaRetornarUmAnimal() {
        List<Animal> list = animalRepository.findByClienteId(133L);
        assertEquals("Deveria retornar um animal", 1, list.size());
        Animal rex = list.get(0);
        assertEquals("O nome deveria ser Rex", "Rex", rex.getNome());
        assertEquals("O cliente deveria ser o 133", Long.valueOf(133), rex.getCliente().getId());
        assertEquals("Deveria ser um mamífero", EspecieEnum.MAMIFERO, rex.getEspecie());
    }

    @Test
    @Sql("classpath:insere_rex.sql")
    public void deveriaRetornarUmAnimalPorNome() {
        List<Animal> list = animalRepository.findByNome("Rex");
        assertEquals("Deveria retornar um animal", 1, list.size());
        Animal rex = list.get(0);
        assertEquals("O nome deveria ser Rex", "Rex", rex.getNome());
        assertEquals("O cliente deveria ser o 133", Long.valueOf(133), rex.getCliente().getId());
        assertEquals("Deveria ser um mamífero", EspecieEnum.MAMIFERO, rex.getEspecie());
    }

    @Test
    @Sql("classpath:insere_brutus.sql")
    public void deveriaRetornarAnimaisPorPeriodoEEspecie(){

        LocalDate dataInicio = LocalDate.parse("2019-08-01");
        LocalDate dataFim = LocalDate.parse("2019-08-31");
        EspecieEnum especie = EspecieEnum.MAMIFERO;

        List<Animal> list = animalRepository.findByDataNascimentoDataBetweenAndEspecieIs(dataInicio, dataFim, especie);

        assertEquals("Deveria retornar 1 animal", 1, list.size());
        Animal brutus = list.get(0);
        assertEquals("Deveria ser o Brutus", "Brutus", brutus.getNome());
        assertEquals("Deveria ser Mamífero", EspecieEnum.MAMIFERO, brutus.getEspecie());

    }
}