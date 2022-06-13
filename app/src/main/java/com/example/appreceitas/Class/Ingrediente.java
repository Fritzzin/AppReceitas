package com.example.appreceitas.Class;

/**
 * @author Fritzzin
 */
public class Ingrediente {

    private int id;
    private String nome;
    private String tipo;
    private String quantidade;
    private String tipoQtd;

    public Ingrediente(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Ingrediente(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public Ingrediente(int id, String nome, String tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    public Ingrediente(String nome, String tipo, String quantidade, String tipoQtd) {
        this.nome = nome;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.tipoQtd = tipoQtd;
    }

    public Ingrediente(int id, String nome, String tipo, String quantidade, String tipoQtd) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.tipoQtd = tipoQtd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoQtd() {
        return tipoQtd;
    }

    public void setTipoQtd(String tipoQtd) {
        this.tipoQtd = tipoQtd;
    }

}
