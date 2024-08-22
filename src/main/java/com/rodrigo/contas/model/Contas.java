package com.rodrigo.contas.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="contas")
public class Contas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="descricao")
    @NotBlank(message= "* Preencha o campo descrição")
    private String descricao;

    @DecimalMin(value="10", message= "* Preencha com um valor minimo de 10")
    private Float valor; // Alterado de float para Float

    @NotNull(message= "* Preencha o campo data")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Formato usado no formulário
    private Date data;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="contas_categoria_id", referencedColumnName= "id")
    private Categoria categoria;

    // Getters and setters

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

     // Construtor padrão
     public Contas() {
    }
    
    public Contas(Long id, @NotBlank(message = "* Preencha o campo descrição") String descricao,
            @DecimalMin(value = "10", message = "* Preencha com um valor minimo de 10") Float valor, // Alterado para Float
            @NotNull(message = "* Preencha o campo data") Date data, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor; // Alterado para Float
        this.data = data;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Float getValor() { // Alterado para Float
        return valor;
    }
    public void setValor(Float valor) { // Alterado para Float
        this.valor = valor;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
}
