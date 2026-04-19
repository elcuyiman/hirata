package com.hirata.controller;

import com.hirata.dao.CamionDAO;
import com.hirata.dao.TrabajadorDAO;
import com.hirata.model.Camion;
import com.hirata.model.Trabajador;
import com.hirata.util.SceneManager;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CamionController {

    @FXML
    private TableView<Camion> tableCamiones;

    @FXML
    private TableColumn<Camion, Integer> colId;

    @FXML
    private TableColumn<Camion, String> colPlaca;

    @FXML
    private TableColumn<Camion, String> colModelo;

    @FXML
    private TableColumn<Camion, Integer> colAnio;

    @FXML
    private TableColumn<Camion, Integer> colTrabajadorId;

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtAnio;

    @FXML
    private TextField txtRutTrabajador;

    @FXML
    private Label lblMensaje;

    private final CamionDAO camionDAO = new CamionDAO();
    private final TrabajadorDAO trabajadorDAO = new TrabajadorDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());

        colPlaca.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getPlaca()));

        colModelo.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getModelo()));

        colAnio.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAnio()).asObject());

        if (colTrabajadorId != null) {
            colTrabajadorId.setCellValueFactory(data -> {
                Integer trabajadorId = data.getValue().getTrabajadorId();
                return new javafx.beans.property.SimpleObjectProperty<>(trabajadorId);
            });
        }

        cargarCamiones();
    }

    @FXML
    public void guardarCamion() {
        String placa = txtPlaca.getText();
        String modelo = txtModelo.getText();
        String anioTexto = txtAnio.getText();

        if (placa == null || placa.isBlank()
                || modelo == null || modelo.isBlank()
                || anioTexto == null || anioTexto.isBlank()) {
            lblMensaje.setText("Completa todos los campos");
            return;
        }

        try {
            int anio = Integer.parseInt(anioTexto);

            Camion camion = new Camion(placa, modelo, anio);

            if (camionDAO.guardarCamion(camion)) {
                lblMensaje.setText("Camión guardado correctamente");
                txtPlaca.clear();
                txtModelo.clear();
                txtAnio.clear();
                cargarCamiones();
            } else {
                lblMensaje.setText("No se pudo guardar el camión");
            }

        } catch (NumberFormatException e) {
            lblMensaje.setText("El año debe ser numérico");
        }
    }

    @FXML
    public void asignarTrabajadorPorRut() {
        Camion seleccionado = tableCamiones.getSelectionModel().getSelectedItem();
        String rut = txtRutTrabajador.getText();

        if (seleccionado == null) {
            lblMensaje.setText("Selecciona un camión");
            return;
        }

        if (rut == null || rut.isBlank()) {
            lblMensaje.setText("Ingresa el RUT del trabajador");
            return;
        }

        Trabajador trabajador = trabajadorDAO.buscarPorRut(rut);

        if (trabajador == null) {
            lblMensaje.setText("No existe un trabajador con ese RUT");
            return;
        }

        if (camionDAO.asignarTrabajador(seleccionado.getId(), trabajador.getId())) {
            lblMensaje.setText("Trabajador asignado correctamente");
            txtRutTrabajador.clear();
            cargarCamiones();
        } else {
            lblMensaje.setText("No se pudo asignar el trabajador");
        }
    }

    @FXML
    public void quitarTrabajador() {
        Camion seleccionado = tableCamiones.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            lblMensaje.setText("Selecciona un camión");
            return;
        }

        if (camionDAO.quitarTrabajador(seleccionado.getId())) {
            lblMensaje.setText("Trabajador quitado del camión");
            cargarCamiones();
        } else {
            lblMensaje.setText("No se pudo quitar el trabajador");
        }
    }

    @FXML
    public void eliminarCamion() {
        Camion seleccionado = tableCamiones.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            lblMensaje.setText("Selecciona un camión");
            return;
        }

        if (camionDAO.eliminarCamion(seleccionado.getId())) {
            lblMensaje.setText("Camión eliminado correctamente");
            cargarCamiones();
        } else {
            lblMensaje.setText("No se pudo eliminar el camión");
        }
    }

    @FXML
    public void actualizarTabla() {
        cargarCamiones();
        lblMensaje.setText("Lista actualizada");
    }

    private void cargarCamiones() {
        tableCamiones.setItems(
                FXCollections.observableArrayList(camionDAO.listarCamiones())
        );
    }

    @FXML
    public void volverDashboard() {
        SceneManager.loadScene("/view/dashboard.fxml", "Hirata - Dashboard", 1280, 800);
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
    public void irAlertas() {
        SceneManager.loadScene("/view/alertas.fxml", "Hirata - Alertas", 1280, 800);
    }

    @FXML
    public void cerrarSesion() {
        SceneManager.loadScene("/view/login.fxml", "Hirata - Login", 500, 650);
    }
}