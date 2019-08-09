package br.com.tt.petshop.api;
//Pode ser chamado de somente AnimalController (usamos só por causa do Controller MVC)

import br.com.tt.petshop.model.Unidade;
import br.com.tt.petshop.service.UnidadeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/unidades",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class UnidadeEndpoint {

    private final UnidadeService unidadeService;

    public UnidadeEndpoint(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    //    /unidades
//    @ResponseBody
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Unidade> findAll(){
        return unidadeService.findAll();
    }

//    /unidades/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Unidade> findById(@PathVariable Long id){
        Optional<Unidade> unidadeOptional = unidadeService.findById(id);
        if (unidadeOptional.isPresent()){
            return ResponseEntity.ok(unidadeOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

//    /unidades
    @PostMapping
    public ResponseEntity create(@RequestBody Unidade unidade){
        Unidade unidadeCriada = unidadeService.create(unidade);
        URI uri = URI.create(String.format("/unidades/%d", unidadeCriada.getId()));
        return ResponseEntity.created(uri).build();
    }

//    /unidades/{id}
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Unidade unidade, @PathVariable Long id){

        unidade.setId(id);//TODO quando trocar para DTO...
        unidadeService.update(unidade);

        return ResponseEntity.noContent().build();
    }
}
