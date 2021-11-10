package com.cursoandroid.firebaseapp;

public class Produto {

    private String descricao;
    private String marca;
    private Double preo;

    public Produto() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPreo() {
        return preo;
    }

    public void setPreo(Double preo) {
        this.preo = preo;
    }
}
