package br.com.tt.petshop.model;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.model.vo.DataNascimento;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TB_ANIMAL")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long id;

    private String nome;

    @Embedded
    private DataNascimento dataNascimento;

    @Enumerated(EnumType.STRING)
    private EspecieEnum especie;

//    @Column(name = "client_id",
// updatable = false, insertable = false)
//    private Long clientId;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Cliente cliente;

    public Animal() {
        this.dataNascimento = new DataNascimento();
    }

    public Animal(String nome, LocalDate dataNascimento, EspecieEnum especie, Long clientId) {
        this.nome = nome;
        this.dataNascimento = new DataNascimento(dataNascimento);
        this.especie = especie;
        this.cliente = new Cliente(clientId, null, null);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public EspecieEnum getEspecie() {
        return especie;
    }

    public void setEspecie(EspecieEnum especie) {
        this.especie = especie;
    }


    public DataNascimento getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(DataNascimento dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_animal")
//    @SequenceGenerator(schema = "schema", sequenceName = "seqeqew", name = "seq_animal")

}
