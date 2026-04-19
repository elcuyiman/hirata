package com.hirata.controller;

import com.hirata.dao.TrabajadorDAO;
import com.hirata.model.Trabajador;
import com.hirata.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TrabajadorController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtRut;

    @FXML
    private Label lblMensaje;

    @FXML
    private TableView<Trabajador> tableTrabajadores;

    @FXML
    private TableColumn<Trabajador, Integer> colId;

    @FXML
    private TableColumn<Trabajador, String> colNombre;

    @FXML
    private TableColumn<Trabajador, String> colRut;

    private final TrabajadorDAO trabajadorDAO = new TrabajadorDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());

        colNombre.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));

        colRut.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getRut()));

        cargarTrabajadores();
    }

    @FXML
    public void guardarTrabajador() {
        String nombre = txtNombre.getText();
        String rut = txtRut.getText();

        if (nombre == null || nombre.isBlank() || rut == null || rut.isBlank()) {
            lblMensaje.setText("Completa nombre y RUT");
            return;
        }

        if (trabajadorDAO.existeRut(rut)) {
            lblMensaje.setText("El RUT ya existe");
            return;
        }

        Trabajador trabajador = new Trabajador(nombre, rut);

        if (trabajadorDAO.guardarTrabajador(trabajador)) {
            lblMensaje.setText("Trabajador guardado correctamente");
            txtNombre.clear();
            txtRut.clear();
            cargarTrabajadores();
        } else {
            lblMensaje.setText("No se pudo guardar el trabajador");
        }
    }

    @FXML
    public void eliminarTrabajador() {
        Trabajador seleccionado = tableTrabajadores.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            lblMensaje.setText("Selecciona un trabajador");
            return;
        }

        if (trabajadorDAO.eliminarTrabajador(seleccionado.getId())) {
            lblMensaje.setText("Trabajador eliminado correctamente");
            cargarTrabajadores();
        } else {
            lblMensaje.setText("No se pudo eliminar el trabajador");
        }
    }

    @FXML
    public void actualizarTabla() {
        cargarTrabajadores();
        lblMensaje.setText("Lista actualizada");
    }

    private void cargarTrabajadores() {
        tableTrabajadores.setItems(
                FXCollections.observableArrayList(trabajadorDAO.listarTrabajadores())
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
    public void irKilometraje() {
        SceneManager.loadScene("/view/kilometraje.fxml", "Hirata - Kilometraje", 1100, 720);
    }

    @FXML
    public void irMantenimientos() {
        SceneManager.loadScene("/view/mantenimientos.fxml", "Hirata - Mantenimientos", 1280, 800);
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