package com.hirata.controller;

import com.hirata.dao.UsuarioDAO;
import com.hirata.model.Usuario;
import com.hirata.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblMensaje;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    public void login() {
        String username = txtUsuario.getText();
        String password = txtPassword.getText();

        if (username == null || username.isBlank()
                || password == null || password.isBlank()) {
            lblMensaje.setText("Completa usuario y contraseña");
            return;
        }

        Usuario usuario = usuarioDAO.login(username, password);

        if (usuario != null) {
            lblMensaje.setText("");
            SceneManager.loadScene("/view/dashboard.fxml", "Hirata - Dashboard", 1280, 800);
        } else {
            lblMensaje.setText("Credenciales incorrectas");
        }
    }
}