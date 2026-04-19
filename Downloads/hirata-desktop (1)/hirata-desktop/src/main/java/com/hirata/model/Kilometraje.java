package com.hirata.model;

public class Kilometraje {

    private int id;
    private int camionId;
    private int kilometraje;
    private String fecha;

    public Kilometraje() {
    }

    public Kilometraje(int id, int camionId, int kilometraje, String fecha) {
        this.id = id;
        this.camionId = camionId;
        this.kilometraje = kilometraje;
        this.fecha = fecha;
    }

    public Kilometraje(int camionId, int kilometraje) {
        this.camionId = camionId;
        this.kilometraje = kilometraje;
    }

    public int getId() {
        return id;
    }

    public int getCamionId() {
        return camionId;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCamionId(int camionId) {
        this.camionId = camionId;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}