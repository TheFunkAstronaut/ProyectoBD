package com.ecodeup.jdbc.Entidades;

public class EstudianteEnMateria {
    private int idEstudiante;
    private int idMateria;
    private String tipoNota;
    private float valorNota;

    public EstudianteEnMateria() {

    }
    public EstudianteEnMateria(int idEstudiante, String tipoNota, float valorNota) {
        this.idEstudiante = idEstudiante;
        this.tipoNota = tipoNota;
        this.valorNota = valorNota;
    }
    public EstudianteEnMateria(int idEstudiante, int idMateria, String tipoNota, float valorNota) {
        this.idEstudiante = idEstudiante;
        this.idMateria = idMateria;
        this.tipoNota = tipoNota;
        this.valorNota = valorNota;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(String tipoNota) {
        this.tipoNota = tipoNota;
    }

    public float getValorNota() {
        return valorNota;
    }

    public void setValorNota(float valorNota) {
        this.valorNota = valorNota;
    }

}

