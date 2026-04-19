package com.hirata.dao;

import com.hirata.model.Usuario;
import com.hirata.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public Usuario login(String username, String password) {
        String sql = "SELECT username, password, rol FROM usuarios WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("rol")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
