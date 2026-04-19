package com.hirata.dao;

import com.hirata.model.Mantenimiento;
import com.hirata.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MantenimientoDAO {

    public List<Mantenimiento> listarMantenimientos() {
        List<Mantenimiento> lista = new ArrayList<>();

        String sql = "SELECT id, camion_id, tipo, descripcion, fecha, estado FROM mantenimientos ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Mantenimiento m = new Mantenimiento(
                        rs.getInt("id"),
                        rs.getInt("camion_id"),
                        rs.getString("tipo"),
                        rs.getString("descripcion"),
                        rs.getString("fecha"),
                        rs.getString("estado")
                );

                lista.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean guardarMantenimiento(Mantenimiento mantenimiento) {
        String sql = "INSERT INTO mantenimientos (camion_id, tipo, descripcion, estado) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, mantenimiento.getCamionId());
            ps.setString(2, mantenimiento.getTipo());
            ps.setString(3, mantenimiento.getDescripcion());
            ps.setString(4, mantenimiento.getEstado());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean actualizarEstadoMantenimiento(int id, String estado) {
        String sql = "UPDATE mantenimientos SET estado = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public int contarTotal() {
        String sql = "SELECT COUNT(*) FROM mantenimientos";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int contarPorEstado(String estado) {
        String sql = "SELECT COUNT(*) FROM mantenimientos WHERE estado = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estado);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public boolean eliminarMantenimiento(int id) {
        String sql = "DELETE FROM mantenimientos WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}