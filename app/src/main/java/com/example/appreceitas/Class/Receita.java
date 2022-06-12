/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.appreceitas.Class;

import java.util.ArrayList;

/**
 *
 * @author Fritzzin
 */
public class Receita {

    private int id;
    private String nome;
    private int idAutor;
    private ArrayList<Ingrediente> ingredientes;

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
