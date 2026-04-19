package com.hirata.controller;

import com.hirata.dao.KilometrajeDAO;
import com.hirata.model.Kilometraje;
import com.hirata.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class KilometrajeController {

    @FXML
    private TextField txtCamionId;

    @FXML
    private TextField txtKilometraje;

    @FXML
    private Label lblMensaje;

    @FXML
    private TableView<Kilometraje> tableKilometrajes;

    @FXML
    private TableColumn<Kilometraje, Integer> colId;

    @FXML
    private TableColumn<Kilometraje, Integer> colCamionId;

    @FXML
    private TableColumn<Kilometraje, Integer> colKilometraje;

    @FXML
    private TableColumn<Kilometraje, String> colFecha;

    private final KilometrajeDAO kilometrajeDAO = new KilometrajeDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());

        colCamionId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCamionId()).asObject());

        colKilometraje.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getKilometraje()).asObject());

        colFecha.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getFecha()));

        cargarKilometrajes();
    }

    @FXML
    public void registrarKilometraje() {
        String camionIdTexto = txtCamionId.getText();
        String kilometrajeTexto = txtKilometraje.getText();

        if (camionIdTexto == null || camionIdTexto.isBlank()
                || kilometrajeTexto == null || kilometrajeTexto.isBlank()) {
            lblMensaje.setText("Completa todos los campos");
            return;
        }

        try {
            int camionId = Integer.parseInt(camionIdTexto);
            int kilometraje = Integer.parseInt(kilometrajeTexto);

            if (kilometraje < 0) {
                lblMensaje.setText("El kilometraje no puede ser negativo");
                return;
            }

            Kilometraje registro = new Kilometraje(camionId, kilometraje);

            if (kilometrajeDAO.guardarKilometraje(registro)) {
                lblMensaje.setText("Kilometraje registrado correctamente");
                mostrarAlertaSiCorresponde(camionId, kilometraje);
                txtCamionId.clear();
                txtKilometraje.clear();
                cargarKilometrajes();
            } else {
                lblMensaje.setText("No se pudo registrar el kilometraje");
            }

        } catch (NumberFormatException e) {
            lblMensaje.setText("Debes ingresar valores numéricos válidos");
        }
    }

    private void mostrarAlertaSiCorresponde(int camionId, int kilometraje) {
        if (kilometraje >= 10000) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Emergencia de mantenimiento");
            alert.setHeaderText("Mantenimiento urgente");
            alert.setContentText("El camión ID " + camionId
                    + " alcanzó " + kilometraje
                    + " km y requiere mantenimiento urgente.");
            alert.showAndWait();

        } else if (kilometraje >= 5000) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso de mantenimiento");
            alert.setHeaderText("Revisión recomendada");
            alert.setContentText("El camión ID " + camionId
                    + " alcanzó " + kilometraje
                    + " km. Se recomienda revisión.");
            alert.showAndWait();
        }
    }

    @FXML
    public void actualizarTabla() {
        cargarKilometrajes();
        lblMensaje.setText("Lista actualizada");
    }

    private void cargarKilometrajes() {
        tableKilometrajes.setItems(
                FXCollections.observableArrayList(kilometrajeDAO.listarKilometrajes())
        );
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
    public void irMantenimientos() {
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
