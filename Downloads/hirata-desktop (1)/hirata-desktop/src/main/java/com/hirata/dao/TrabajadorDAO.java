package com.hirata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hirata.model.Trabajador;
import com.hirata.util.DBConnection;

public class TrabajadorDAO {

    public boolean guardarTrabajador(Trabajador trabajador) {
        String sql = "INSERT INTO trabajadores (nombre, rut) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trabajador.getNombre());
            ps.setString(2, trabajador.getRut());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Trabajador> listarTrabajadores() {
        List<Trabajador> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, rut FROM trabajadores ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Trabajador(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("rut")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean eliminarTrabajador(int id) {
        String sql = "DELETE FROM trabajadores WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean existeRut(String rut) {
        String sql = "SELECT COUNT(*) FROM trabajadores WHERE rut = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, rut);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Trabajador buscarPorRut(String rut) {
        String sql = "SELECT id, nombre, rut FROM trabajadores WHERE rut = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, rut);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Trabajador(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("rut")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
