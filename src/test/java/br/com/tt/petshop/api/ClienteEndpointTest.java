package br.com.tt.petshop.api;

import br.com.tt.petshop.config.ModelMapperConfig;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ClienteEndpoint.class)
@Import(ModelMapperConfig.class)
public class ClienteEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

//    @MockBean
//    private ModelMapper mapper;

    @Test
    public void deveRetornarListaClienteComSucesso() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/clientes")
        ).andExpect(status().is(200))
        .andExpect(content().string("[]"))
        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deveRetornarListaComClienteFulano() throws Exception {

        Mockito.when(clienteService.listar())
                .thenReturn(Arrays.asList(new Cliente(77L, "Fulano Silva", "000.111.222-33")));

        //MockMvcResultMatchers
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
        .andExpect(status().isOk())
        .andExpect(header().string("Content-Type","application/json;charset=UTF-8"))
        .andExpect(jsonPath("$.[0].id").value(CoreMatchers.equalTo(77)))
        .andDo(MockMvcResultHandlers.print());

    }


}