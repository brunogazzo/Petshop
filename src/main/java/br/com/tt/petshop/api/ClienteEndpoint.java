package br.com.tt.petshop.api;

import br.com.tt.petshop.dto.ClienteDto;
import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/clientes")
public class ClienteEndpoint {

    private final ClienteService clienteService;
    private final ModelMapper mapper;

    public ClienteEndpoint(ClienteService clienteService, ModelMapper mapper) {
        this.clienteService = clienteService;
        this.mapper = mapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> findAll(){
        return ok(clienteService.listar());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Cliente cliente) throws BusinessException {
        URI location = URI.create(
                format("/clientes/%d",
                clienteService.adicionar(cliente).getId()));
        return created(location).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDto> findById(@PathVariable Long id){
        Optional<Cliente> clienteOptional = clienteService.findById(id);
        if(clienteOptional.isPresent()){
            ClienteDto dto = mapper.map(clienteOptional.get(), ClienteDto.class);
            return ok(dto);
        }
        return notFound().build();
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody Cliente cliente, @PathVariable Long id) throws NotFound {
        clienteService.update(id, cliente);
        return noContent().build();
    }
}
