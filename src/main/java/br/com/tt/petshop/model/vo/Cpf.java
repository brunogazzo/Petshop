package br.com.tt.petshop.model.vo;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Cpf {

    @Column(name = "CPF_CLIENTE")
    @CPF
    private String valor;

    public boolean isValid(){
        return valor != null
                && valor.replaceAll("\\D", "").length() == 11;
    }

//    @Override
//    public String toString() {
//        return valor;
//    }

    public Cpf(){
    }

    public Cpf(String valor){
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
