package br.com.tt.petshop.api;
//Pode ser chamado de somente AnimalController (usamos só por causa do Controller MVC)

import br.com.tt.petshop.model.Unidade;
import br.com.tt.petshop.service.UnidadeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/unidades",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class UnidadeEndpoint {

    private UnidadeService unidadeService;

//    /unidades
//    @ResponseBody
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Unidade> findAll(){
        return null;
    }

//    /unidades/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Unidade> findById(@PathVariable Long id){
        return null;
    }
//    /unidades
    @PostMapping
    public ResponseEntity create(@RequestBody Unidade unidade){
        return null;
    }

//    /unidades/{id}
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Unidade unidade, @PathVariable Long id){
        return null;
    }
}
