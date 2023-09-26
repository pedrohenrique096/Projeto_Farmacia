package com.generation.farmacia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity // indica que que essa classe será uma entidade no meu banco de dados, ou seja, uma tabela.
@Table(name = "tb_categoria") // criação de tabela
public class Categoria {
    @Id // Indica que essa será minha chave primária
    @GeneratedValue (strategy = GenerationType.IDENTITY) // Indica a forma como essa chave será gerada, a estratégia que utilizaremos
    private Long id;
    @NotNull(message = "Este atributo 'tipo' é obrigatório ") // Indeica que este atributo não pode deicxar de ser preenchido.
    @Size (min = 6, max = 500) // Determina a quantidade de caracteres permitida no preenchimento
    private String tipo;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("categoria")
    private List<Produto> produtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
