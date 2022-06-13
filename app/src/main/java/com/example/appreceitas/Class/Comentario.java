package com.example.appreceitas.Class;

/**
 * @author Fritzzin
 */
public class Comentario {

    private int id;
    private int idUsuario;
    private String nomeUsuario;
    private int idReceita;
    private String texto;

    public Comentario(int id, int idUsuario, String nomeUsuario, int idReceita, String texto) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.idReceita = idReceita;
        this.texto = texto;
    }

    public Comentario(int id, int idUsuario, int idReceita, String texto) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idReceita = idReceita;
        this.texto = texto;
    }

    public Comentario(int idUsuario, int idReceita, String texto) {
        this.idUsuario = idUsuario;
        this.idReceita = idReceita;
        this.texto = texto;
    }

    public Comentario() {
        this.id = 0;
        this.idUsuario = 0;
        this.idReceita = 0;
        this.texto = "";
    }

    public Comentario(int codigo, int idUsuario, int idReceita, String texto, String nomeUsuario) {
        this.id = codigo;
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.idReceita = idReceita;
        this.texto = texto;
        this.nomeUsuario = nomeUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(int idReceita) {
        this.idReceita = idReceita;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}
