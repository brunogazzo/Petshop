package br.com.tt.petshop.dto;

import br.com.tt.petshop.enums.EspecieEnum;

import java.time.LocalDate;

public class AnimalDto {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private EspecieEnum especie;
}
