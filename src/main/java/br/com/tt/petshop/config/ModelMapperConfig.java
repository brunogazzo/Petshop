package br.com.tt.petshop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getBean(){
        System.out.println("Iniciei o modelmapper");
        return new ModelMapper();
    }

}
