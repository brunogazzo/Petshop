package br.com.tt.petshop.model;

import br.com.tt.petshop.enums.EspecieEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Animal {
    private String nome;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;
    private EspecieEnum especie;
    private Long clientId;

    public Animal() {
    }

    public Animal(String nome, LocalDate dataNascimento, EspecieEnum especie, Long clientId) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.especie = especie;
        this.clientId = clientId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public EspecieEnum getEspecie() {
        return especie;
    }

    public void setEspecie(EspecieEnum especie) {
        this.especie = especie;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
