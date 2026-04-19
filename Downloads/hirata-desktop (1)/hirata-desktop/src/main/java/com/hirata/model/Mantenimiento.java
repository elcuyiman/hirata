package com.hirata.model;

public class Mantenimiento {

    private int id;
    private int camionId;
    private String tipo;
    private String descripcion;
    private String fecha;
    private String estado;

    public Mantenimiento() {
    }

    public Mantenimiento(int id, int camionId, String tipo, String descripcion, String fecha, String estado) {
        this.id = id;
        this.camionId = camionId;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Mantenimiento(int camionId, String tipo, String descripcion, String estado) {
        this.camionId = camionId;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public int getCamionId() {
        return camionId;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCamionId(int camionId) {
        this.camionId = camionId;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}