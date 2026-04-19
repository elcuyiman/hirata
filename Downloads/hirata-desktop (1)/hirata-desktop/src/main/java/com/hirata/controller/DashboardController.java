package com.hirata.controller;

import com.hirata.dao.AlertaDAO;
import com.hirata.dao.CamionDAO;
import com.hirata.dao.MantenimientoDAO;
import com.hirata.dao.TrabajadorDAO;
import com.hirata.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label lblAlertas;

    @FXML
    private Label lblListaEspera;

    @FXML
    private Label lblEnMantenimiento;

    @FXML
    private Label lblFinalizados;

    @FXML
    private Label lblResumenCamiones;

    @FXML
    private Label lblResumenMantenimientos;

    @FXML
    private Label lblResumenTrabajadores;

    private final CamionDAO camionDAO = new CamionDAO();
    private final MantenimientoDAO mantenimientoDAO = new MantenimientoDAO();
    private final AlertaDAO alertaDAO = new AlertaDAO();
    private final TrabajadorDAO trabajadorDAO = new TrabajadorDAO();

    @FXML
    public void initialize() {
        cargarDashboard();
    }

    @FXML
    public void actualizarDashboard() {
        cargarDashboard();
    }

    private void cargarDashboard() {
        if (lblAlertas != null) {
            lblAlertas.setText(String.valueOf(alertaDAO.contarAlertasPendientes()));
        }

        if (lblListaEspera != null) {
            lblListaEspera.setText(String.valueOf(mantenimientoDAO.contarPorEstado("LISTA_ESPERA")));
        }

        if (lblEnMantenimiento != null) {
            lblEnMantenimiento.setText(String.valueOf(mantenimientoDAO.contarPorEstado("EN_MANTENIMIENTO")));
        }

        if (lblFinalizados != null) {
            lblFinalizados.setText(String.valueOf(mantenimientoDAO.contarPorEstado("FINALIZADO")));
        }

        if (lblResumenCamiones != null) {
            lblResumenCamiones.setText(String.valueOf(camionDAO.contarCamiones()));
        }

        if (lblResumenMantenimientos != null) {
            lblResumenMantenimientos.setText(String.valueOf(mantenimientoDAO.contarTotal()));
        }

        if (lblResumenTrabajadores != null) {
            lblResumenTrabajadores.setText(String.valueOf(trabajadorDAO.listarTrabajadores().size()));
        }
    }

    @FXML
    public void irDashboard() {
        SceneManager.loadScene("/view/dashboard.fxml", "Hirata - Dashboard", 1280, 800);
    }

    @FXML
    public void abrirCamiones() {
        SceneManager.loadScene("/view/camiones.fxml", "Hirata - Camiones", 1280, 800);
    }

    @FXML
    public void abrirKilometraje() {
        SceneManager.loadScene("/view/kilometraje.fxml", "Hirata - Kilometraje", 1100, 720);
    }

    @FXML
    public void abrirMantenimientos() {
        SceneManager.loadScene("/view/mantenimientos.fxml", "Hirata - Mantenimientos", 1280, 800);
    }

    @FXML
    public void irTrabajadores() {
        SceneManager.loadScene("/view/trabajadores.fxml", "Hirata - Trabajadores", 1200, 760);
    }

    @FXML
    public void irAlertas() {
        SceneManager.loadScene("/view/alertas.fxml", "Hirata - Alertas", 1280, 800);
    }

    @FXML
    public void cerrarSesion() {
        SceneManager.loadScene("/view/login.fxml", "Hirata - Login", 500, 650);
    }
}