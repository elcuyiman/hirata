package com.hirata.model;

public class Alerta {

    private int id;
    private int camionId;
    private String tipo;
    private String mensaje;
    private int kilometraje;
    private String fecha;
    private String estado;

    public Alerta() {
    }

    public Alerta(int id, int camionId, String tipo, String mensaje, int kilometraje, String fecha, String estado) {
        this.id = id;
        this.camionId = camionId;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.kilometraje = kilometraje;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Alerta(int camionId, String tipo, String mensaje, int kilometraje, String estado) {
        this.camionId = camionId;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.kilometraje = kilometraje;
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

    public String getMensaje() {
        return mensaje;
    }

    public int getKilometraje() {
        return kilometraje;
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

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}