package br.com.tt.petshop.dto;

import br.com.tt.petshop.enums.EspecieEnum;

import java.time.LocalDate;

public class AnimalDto {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private EspecieEnum especie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
