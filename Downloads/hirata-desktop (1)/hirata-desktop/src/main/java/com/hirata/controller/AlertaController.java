package com.hirata.controller;

import com.hirata.dao.AlertaDAO;
import com.hirata.model.Alerta;
import com.hirata.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class AlertaController {

    @FXML
    private TableView<Alerta> tableAlertas;

    @FXML
    private TableColumn<Alerta, Integer> colId;

    @FXML
    private TableColumn<Alerta, Integer> colCamionId;

    @FXML
    private TableColumn<Alerta, String> colTipo;

    @FXML
    private TableColumn<Alerta, String> colMensaje;

    @FXML
    private TableColumn<Alerta, Integer> colKilometraje;

    @FXML
    private TableColumn<Alerta, String> colFecha;

    @FXML
    private TableColumn<Alerta, String> colEstado;

    @FXML
    private ComboBox<String> comboFiltroEstado;

    @FXML
    private Label lblMensaje;

    @FXML
    private Label lblPendientes;

    @FXML
    private Label lblAvisos;

    @FXML
    private Label lblEmergencias;

    private final AlertaDAO alertaDAO = new AlertaDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());

        colCamionId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCamionId()).asObject());

        colTipo.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getTipo()));

        colMensaje.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getMensaje()));

        colKilometraje.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getKilometraje()).asObject());

        colFecha.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getFecha()));

        colEstado.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getEstado()));

        comboFiltroEstado.setItems(FXCollections.observableArrayList(
                "PENDIENTE",
                "ATENDIDA"
        ));
        comboFiltroEstado.setValue("PENDIENTE");

        cargarAlertas();
        cargarResumen();
    }

    @FXML
    public void marcarAtendida() {
        Alerta seleccionada = tableAlertas.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            lblMensaje.setText("Selecciona una alerta");
            return;
        }

        if (alertaDAO.actualizarEstadoAlerta(seleccionada.getId(), "ATENDIDA")) {
            lblMensaje.setText("Alerta marcada como atendida");
            cargarAlertas();
            cargarResumen();
        } else {
            lblMensaje.setText("No se pudo actualizar la alerta");
        }
    }

    @FXML
    public void filtrarPorEstado() {
        String estado = comboFiltroEstado.getValue();

        if (estado == null || estado.isBlank()) {
            lblMensaje.setText("Selecciona un estado");
            return;
        }

        List<Alerta> lista = alertaDAO.listarAlertasPorEstado(estado);
        tableAlertas.setItems(FXCollections.observableArrayList(lista));
        lblMensaje.setText("Filtro aplicado: " + estado);
    }

    @FXML
    public void mostrarTodas() {
        cargarAlertas();
        lblMensaje.setText("Mostrando todas las alertas");
    }

    private void cargarAlertas() {
        tableAlertas.setItems(FXCollections.observableArrayList(alertaDAO.listarAlertas()));
    }

    private void cargarResumen() {
        if (lblPendientes != null) {
            lblPendientes.setText(String.valueOf(alertaDAO.contarAlertasPendientes()));
        }

        if (lblAvisos != null) {
            lblAvisos.setText(String.valueOf(alertaDAO.contarPorTipo("AVISO")));
        }

        if (lblEmergencias != null) {
            lblEmergencias.setText(String.valueOf(alertaDAO.contarPorTipo("EMERGENCIA")));
        }
    }

    @FXML
    public void volverDashboard() {
        SceneManager.loadScene("/view/dashboard.fxml", "Hirata - Dashboard", 1280, 800);
    }

    @FXML
    public void irCamiones() {
        SceneManager.loadScene("/view/camiones.fxml", "Hirata - Camiones", 1280, 800);
    }

    @FXML
    public void irKilometraje() {
        SceneManager.loadScene("/view/kilometraje.fxml", "Hirata - Kilometraje", 1100, 720);
    }

    @FXML
    public void irMantenimientos() {
        SceneManager.loadScene("/view/mantenimientos.fxml", "Hirata - Mantenimientos", 1280, 800);
    }

    @FXML
    public void irTrabajadores() {
        SceneManager.loadScene("/view/trabajadores.fxml", "Hirata - Trabajadores", 1200, 760);
    }

    @FXML
    public void cerrarSesion() {
        SceneManager.loadScene("/view/login.fxml", "Hirata - Login", 500, 650);
    }
}