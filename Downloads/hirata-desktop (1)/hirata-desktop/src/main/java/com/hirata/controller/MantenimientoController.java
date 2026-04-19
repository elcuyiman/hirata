package com.hirata.controller;

import com.hirata.dao.MantenimientoDAO;
import com.hirata.model.Mantenimiento;
import com.hirata.util.ExportarExcel;
import com.hirata.util.ExportarPDF;
import com.hirata.util.SceneManager;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MantenimientoController {

    @FXML
    private TableView<Mantenimiento> tableMantenimientos;

    @FXML
    private TableColumn<Mantenimiento, Integer> colId;

    @FXML
    private TableColumn<Mantenimiento, Integer> colCamionId;

    @FXML
    private TableColumn<Mantenimiento, String> colTipo;

    @FXML
    private TableColumn<Mantenimiento, String> colDescripcion;

    @FXML
    private TableColumn<Mantenimiento, String> colFecha;

    @FXML
    private TableColumn<Mantenimiento, String> colEstado;

    @FXML
    private TextField txtCamionId;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private ComboBox<String> comboTipo;

    @FXML
    private ComboBox<String> comboEstado;

    @FXML
    private Label lblMensaje;

    private final MantenimientoDAO mantenimientoDAO = new MantenimientoDAO();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());

        colCamionId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCamionId()).asObject());

        colTipo.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getTipo()));

        colDescripcion.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getDescripcion()));

        colFecha.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getFecha()));

        colEstado.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getEstado()));

        comboTipo.setItems(FXCollections.observableArrayList(
                "PREVENTIVO",
                "CORRECTIVO"
        ));
        comboTipo.setValue("PREVENTIVO");

        comboEstado.setItems(FXCollections.observableArrayList(
                "LISTA_ESPERA",
                "EN_MANTENIMIENTO",
                "FINALIZADO"
        ));
        comboEstado.setValue("LISTA_ESPERA");

        cargarMantenimientos();
    }

    @FXML
    public void guardarMantenimiento() {
        String camionIdTexto = txtCamionId.getText();
        String descripcion = txtDescripcion.getText();
        String tipo = comboTipo.getValue();
        String estado = comboEstado.getValue();

        if (camionIdTexto.isBlank() || descripcion.isBlank()) {
            lblMensaje.setText("Completa todos los campos");
            return;
        }

        try {
            int camionId = Integer.parseInt(camionIdTexto);

            Mantenimiento mantenimiento = new Mantenimiento(
                    camionId,
                    tipo,
                    descripcion,
                    estado
            );

            if (mantenimientoDAO.guardarMantenimiento(mantenimiento)) {
                lblMensaje.setText("Mantenimiento guardado correctamente");
                txtCamionId.clear();
                txtDescripcion.clear();
                cargarMantenimientos();
            } else {
                lblMensaje.setText("Error al guardar");
            }

        } catch (NumberFormatException e) {
            lblMensaje.setText("ID debe ser número");
        }
    }

    @FXML
    public void cambiarAListaEspera() {
        actualizarEstado("LISTA_ESPERA");
    }

    @FXML
    public void cambiarAEnMantenimiento() {
        actualizarEstado("EN_MANTENIMIENTO");
    }

    @FXML
    public void cambiarAFinalizado() {
        actualizarEstado("FINALIZADO");
    }

    @FXML
    public void eliminarMantenimiento() {
        Mantenimiento seleccionado = tableMantenimientos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            lblMensaje.setText("Selecciona un registro");
            return;
        }

        if (mantenimientoDAO.eliminarMantenimiento(seleccionado.getId())) {
            lblMensaje.setText("Eliminado correctamente");
            cargarMantenimientos();
        } else {
            lblMensaje.setText("Error al eliminar");
        }
    }

    private void actualizarEstado(String estado) {
        Mantenimiento seleccionado = tableMantenimientos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            lblMensaje.setText("Selecciona un registro");
            return;
        }

        if (mantenimientoDAO.actualizarEstadoMantenimiento(seleccionado.getId(), estado)) {
            lblMensaje.setText("Estado actualizado");
            cargarMantenimientos();
        } else {
            lblMensaje.setText("Error al actualizar");
        }
    }

    private void cargarMantenimientos() {
        tableMantenimientos.setItems(
                FXCollections.observableArrayList(
                        mantenimientoDAO.listarMantenimientos()
                )
        );
    }

    // 🔥 EXPORTAR PDF
    @FXML
    public void exportarPDF() {
        ExportarPDF.exportarMantenimientos(
                mantenimientoDAO.listarMantenimientos()
        );
        lblMensaje.setText("PDF generado correctamente");
    }

    // 🔥 EXPORTAR EXCEL
    @FXML
    public void exportarExcel() {
        ExportarExcel.exportarMantenimientos(
                mantenimientoDAO.listarMantenimientos()
        );
        lblMensaje.setText("Excel generado correctamente");
    }

    // 🔹 NAVEGACIÓN
    @FXML
    public void volverDashboard() {
        SceneManager.loadScene("/view/dashboard.fxml", "Dashboard", 1280, 800);
    }

    @FXML
    public void irCamiones() {
        SceneManager.loadScene("/view/camiones.fxml", "Camiones", 1280, 800);
    }

    @FXML
    public void irKilometraje() {
        SceneManager.loadScene("/view/kilometraje.fxml", "Kilometraje", 1200, 800);
    }

    @FXML
    public void irTrabajadores() {
        SceneManager.loadScene("/view/trabajadores.fxml", "Trabajadores", 1200, 800);
    }

    @FXML
    public void irAlertas() {
        SceneManager.loadScene("/view/alertas.fxml", "Alertas", 1280, 800);
    }

    @FXML
    public void cerrarSesion() {
        SceneManager.loadScene("/view/login.fxml", "Login", 500, 650);
    }
}