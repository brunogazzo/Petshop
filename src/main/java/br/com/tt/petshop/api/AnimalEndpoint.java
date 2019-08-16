package br.com.tt.petshop.api;

import br.com.tt.petshop.dto.AnimalDto;
import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.service.AnimalService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/animais")
@Api(tags = "Animal", description = "Animal Controller")
public class AnimalEndpoint {

    private final AnimalService animalService;
    private final ModelMapper mapper;

    public AnimalEndpoint(AnimalService animalService, ModelMapper mapper) {
        this.animalService = animalService;
        this.mapper = mapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Lista os animais ativos do sistema")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista de animais retornada com sucesso"),
            @ApiResponse(code = 400, message = "Parâmetros informados incorretamente")
    })
    public ResponseEntity<List<AnimalDto>> list(
            @ApiParam("Id do Cliente para filtro")
            @RequestParam Optional<Long> clienteId,
            @ApiParam("Nome do Animal")
            @RequestParam Optional<String> nome){
        return ResponseEntity.ok(
                animalService.listarByExample(clienteId, nome)
                .stream()
                .map(a -> mapper.map(a, AnimalDto.class))
                .collect(Collectors.toList()));
    }

    @PostMapping
    @ApiOperation("Salva um animal")
    public ResponseEntity create(
            @ApiParam("Informações do animal a ser criado")
            @RequestBody @Valid AnimalDto animalDto)
            throws BusinessException {

        Animal animalCriado = animalService.salvar(animalDto);

        URI location = URI.create(String.format("/animais/%d", animalCriado.getId()));
        return ResponseEntity.created(location).build();
    }

}
