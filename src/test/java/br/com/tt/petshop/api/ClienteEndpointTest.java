package br.com.tt.petshop.api;

import br.com.tt.petshop.config.ModelMapperConfig;
import br.com.tt.petshop.dto.ClienteDto;
import br.com.tt.petshop.dto.factory.ClienteDtoFactory;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ClienteEndpoint.class)
@Import(ModelMapperConfig.class)
@ActiveProfiles("test")
public class ClienteEndpointTest {

    private final String CPF_VALIDO = "333.444.555-66";
    private final String NOME_VALIDO = "Fulano Santos";
    private final Integer ID_VALIDO = 55;
    private final Cliente CLIENTE_VALIDO = new Cliente(Long.valueOf(ID_VALIDO), NOME_VALIDO, CPF_VALIDO);

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
        mockMvc.perform(MockMvcRequestBuilders.post("/clientes"))
        .andExpect(status().isOk())
        .andExpect(header().string("Content-Type","application/json;charset=UTF-8"))
        .andExpect(jsonPath("$[0].id").value(CoreMatchers.equalTo(77)))
        .andExpect(jsonPath("$[0].nome").value(CoreMatchers.equalTo("Fulano Silva")))
        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deveCriarClienteComSucesso() throws Exception {

        Cliente clienteSalvar = new Cliente(56L, "Fulano Silva Silva", "111.222.333-44");

        Mockito.when(
                clienteService.adicionar(clienteSalvar))
                .thenReturn(clienteSalvar);

        ClienteDto clienteDto = ClienteDtoFactory.from(clienteSalvar);
//        ClienteDto clienteDto2 = new ClienteDto();
//        clienteDto2.setCpf(clienteSalvar.getCpf().getValor());
//        clienteDto2.setId(clienteSalvar.getId());
//        clienteDto2.setNome(clienteDto2.getNome());

        byte[] objectToJson = new ObjectMapper().writeValueAsBytes(clienteDto);

        mockMvc.perform(
                MockMvcRequestBuilders
                .post("/clientes")
                .content(objectToJson)
                .header("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
        ).andExpect(status().isCreated())
        .andExpect(header().string("Location", "/clientes/56"))
        .andExpect(content().string(""));
    }

    @Test
    public void deveRetornarFulanoComSucesso() throws Exception {

        Mockito.when(clienteService.findById(Long.valueOf(ID_VALIDO))).thenReturn(Optional.of(CLIENTE_VALIDO));

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/"+ID_VALIDO))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type","application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(CoreMatchers.equalTo(ID_VALIDO)))
                .andExpect(jsonPath("$.nome").value(CoreMatchers.equalTo(NOME_VALIDO)))
                .andExpect(jsonPath("$.cpf").value(CoreMatchers.equalTo(CPF_VALIDO)))
                .andDo(MockMvcResultHandlers.print());

    }


}