package br.com.tt.petshop;

import br.com.tt.petshop.service.ClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class Configuracao {

//    @Bean
    public String getStringPetshop(){
        return "Teste da minha string...";
    }

//    @Bean
    public ClienteService asdasdasdas(){
        return new ClienteService();
    }
}
