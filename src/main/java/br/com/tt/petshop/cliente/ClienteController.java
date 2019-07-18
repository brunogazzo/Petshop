package br.com.tt.petshop.cliente;

import br.com.tt.petshop.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("sistema", "PetShop");
        List<Cliente> listaClientes = clienteService.listar();
        model.addAttribute("clientes", listaClientes);
        return "index";
    }

//    @GetMapping("/")
//    public String index2(Model model) {
//        return "";
//    }


//        public ModelAndView index(Model model){
//        return new ModelAndView("index");
//    }

}
