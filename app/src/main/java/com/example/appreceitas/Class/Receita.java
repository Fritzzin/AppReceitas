package com.example.appreceitas.Class;

import java.util.ArrayList;

/**
 * @author Fritzzin
 */
public class Receita {

    private int id;
    private String nome;
    private int idAutor;
    private String modoPreparo;
    private ArrayList<Ingrediente> ingredientes;

    public Receita(int id, String nome, int idAutor, String modoPreparo) {
        this.id = id;
        this.nome = nome;
        this.idAutor = idAutor;
        this.modoPreparo = modoPreparo;
    }

    public Receita(int id, String nome, int idAutor, String modoPreparo, ArrayList<Ingrediente> ingredientes) {
        this.id = id;
        this.nome = nome;
        this.idAutor = idAutor;
        this.modoPreparo = modoPreparo;
        this.ingredientes = ingredientes;
    }

    public Receita(int id, String nome, int idUsuario, ArrayList<Ingrediente> ingredientes) {
        this.id = id;
        this.nome = nome;
        this.idAutor = idUsuario;
        this.ingredientes = ingredientes;
    }

    public Receita(int id, String nome, int idUsuario) {
        this.id = id;
        this.nome = nome;
        this.idAutor = idUsuario;
    }

    public Receita(String nome, int idUsuario) {
        this.nome = nome;
        this.idAutor = idUsuario;
    }

    public Receita(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Receita() {
        this.id = 0;
        this.nome = "";
        this.idAutor = 0;
        this.ingredientes = null;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idUsuario) {
        this.idAutor = idUsuario;
    }

}
