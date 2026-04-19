package com.hirata.model;

public class Camion {

    private int id;
    private String placa;
    private String modelo;
    private int anio;
    private Integer trabajadorId;

    public Camion() {
    }

    public Camion(int id, String placa, String modelo, int anio, Integer trabajadorId) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.anio = anio;
        this.trabajadorId = trabajadorId;
    }

    public Camion(String placa, String modelo, int anio) {
        this.placa = placa;
        this.modelo = modelo;
        this.anio = anio;
    }

    public int getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public Integer getTrabajadorId() {
        return trabajadorId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setTrabajadorId(Integer trabajadorId) {
        this.trabajadorId = trabajadorId;
    }
}
