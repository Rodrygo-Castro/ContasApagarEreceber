package com.rodrigo.contas.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="categorias")
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="nome")
    @NotBlank(message= "* Preencha o campo nome")
    private String nome;

    @OneToMany(targetEntity= Contas.class, mappedBy= "categoria", fetch=FetchType.LAZY)
    private List<Contas> contas;

    public Categoria() {
    }

    public Categoria(Long id, @NotBlank(message = "* Preencha o campo nome") String nome, List<Contas> contas) {
        this.id = id;
        this.nome = nome;
        this.contas = contas;
    }

    public List<Contas> getContas() {
        return contas;
    }

    public void setContas(List<Contas> contas) {
        this.contas = contas;
    }

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

    
}
