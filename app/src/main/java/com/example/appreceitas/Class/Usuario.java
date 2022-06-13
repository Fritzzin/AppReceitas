package com.example.appreceitas.Class;

/**
 * @author Fritzzin
 */
public class Usuario {

    private int id;
    private String nome;
    private String senha;
    private String tipo;

    public Usuario() {
        this.id = 0;
        this.nome = "";
        this.senha = "";
        this.tipo = "";
    }

    public Usuario(int id, String nome, String tipo) {
        this.id = id;
        this.nome = nome;
        this.senha = "";
        this.tipo = tipo;
    }

    public Usuario(String nome, String senha, String tipo) {
        this.id = 0;
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Usuario(int id, String nome, String senha, String tipo) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
