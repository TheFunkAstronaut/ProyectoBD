package com.ecodeup.jdbc.Entidades;
import java.util.List;
import java.util.ArrayList;
public class Materia {
    private int id;
    private String nombre;
    private int numCreditos;

    public Materia(){

    }
    public Materia(int id, String nombre, int numCreditos) {
        this.id = id;
        this.nombre = nombre;
        this.numCreditos = numCreditos;
    }
    public Materia(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumCreditos() {
        return numCreditos;
    }

    public void setNumCreditos(int numCreditos) {
        this.numCreditos = numCreditos;
    }
}

