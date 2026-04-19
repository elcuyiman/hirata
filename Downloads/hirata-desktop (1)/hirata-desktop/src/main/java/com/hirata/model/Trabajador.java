package com.hirata.model;

public class Trabajador {

    private int id;
    private String nombre;
    private String rut;

    public Trabajador() {
    }

    public Trabajador(int id, String nombre, String rut) {
        this.id = id;
        this.nombre = nombre;
        this.rut = rut;
    }

    public Trabajador(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
}