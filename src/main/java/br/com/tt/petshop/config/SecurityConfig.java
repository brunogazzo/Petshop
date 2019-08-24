package br.com.tt.petshop.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import static com.sun.org.apache.xml.internal.serialize.LineSeparator.Web;
//@CrossOrigin("*")
@Configuration
@RestController
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().antMatcher("/unidades").anonymous()
                .and().antMatcher("/animal").httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(getPasswordEWncoder().encode("admin")).authorities("boss")
                .and().withUser("instrutor").password(getPasswordEWncoder().encode("linux"))
                .roles("instrutor");

    }
@Bean
    public PasswordEncoder getPasswordEWncoder(){
        return new BCryptPasswordEncoder();

}

}
