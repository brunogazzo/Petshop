package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Unidade;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
@Sql("classpath:insere_animal_por_unidade.sql")
public class UnidadeRepositoryTest {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Test
    public void findByAnimaisNomePluto() {
        List<Unidade> unidades = unidadeRepository.findByAnimaisNome("Pluto");
        Assert.assertEquals(2, unidades.size());
        Unidade unidade1 = unidades.get(0);
        Assert.assertEquals(Long.valueOf(1), unidade1.getId());
        Assert.assertEquals("Cristovão", unidade1.getNome());
        Assert.assertEquals("Rua Cristovão Colombo", unidade1.getEndereco());

        Unidade unidade2 = unidades.get(1);
        Assert.assertEquals("Centro", unidade2.getNome());
        Assert.assertEquals("Rua Alberto Bins", unidade2.getEndereco());
    }

    @Test
    public void findByAnimaisNomeRex() {
        List<Unidade> unidades = unidadeRepository.findByAnimaisNome("Rex");
        Assert.assertEquals(1, unidades.size());
        Unidade unidade = unidades.get(0);
        Assert.assertEquals("Centro", unidade.getNome());
        Assert.assertEquals("Rua Alberto Bins", unidade.getEndereco());
    }

    @Test
    public void findByAnimaisNomeSemResultados() {
        List<Unidade> unidades = unidadeRepository.findByAnimaisNome("Totó");
        Assert.assertEquals(0, unidades.size());
    }

}