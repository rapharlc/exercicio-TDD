package com.tech4me.aulahandson.model;

import javax.persistence.Entity;

@Entity
public class Produto {

    private Integer id;
    private String nome;
    private Double quantidade;
    private Double desconto;
    private Double acrescimo;
    private Double valor;
   

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Double getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
    public Double getDesconto() {
        return desconto;
    }
    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
    public Double getAcrescimo() {
        return acrescimo;
    }
    public void setAcrescimo(Double acrescimo) {
        this.acrescimo = acrescimo;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }


    public Double valorTotal(Double quantidade, Double valor){
        Double valorTotal= quantidade * valor;
        return valorTotal;
    }
    


    
    
}
