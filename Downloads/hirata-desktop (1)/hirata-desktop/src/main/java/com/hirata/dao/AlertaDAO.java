package com.hirata.dao;

import com.hirata.model.Alerta;
import com.hirata.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlertaDAO {

    public boolean guardarAlerta(Alerta alerta) {
        String sql = "INSERT INTO alertas (camion_id, tipo, mensaje, kilometraje, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, alerta.getCamionId());
            ps.setString(2, alerta.getTipo());
            ps.setString(3, alerta.getMensaje());
            ps.setInt(4, alerta.getKilometraje());
            ps.setString(5, alerta.getEstado());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Alerta> listarAlertas() {
        List<Alerta> lista = new ArrayList<>();
        String sql = "SELECT id, camion_id, tipo, mensaje, kilometraje, fecha, estado FROM alertas ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Alerta(
                        rs.getInt("id"),
                        rs.getInt("camion_id"),
                        rs.getString("tipo"),
                        rs.getString("mensaje"),
                        rs.getInt("kilometraje"),
                        rs.getString("fecha"),
                        rs.getString("estado")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Alerta> listarAlertasPorEstado(String estado) {
        List<Alerta> lista = new ArrayList<>();
        String sql = "SELECT id, camion_id, tipo, mensaje, kilometraje, fecha, estado FROM alertas WHERE estado = ? ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estado);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Alerta(
                            rs.getInt("id"),
                            rs.getInt("camion_id"),
                            rs.getString("tipo"),
                            rs.getString("mensaje"),
                            rs.getInt("kilometraje"),
                            rs.getString("fecha"),
                            rs.getString("estado")
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public int contarAlertasPendientes() {
        String sql = "SELECT COUNT(*) FROM alertas WHERE estado = 'PENDIENTE'";

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

    public int contarPorTipo(String tipo) {
        String sql = "SELECT COUNT(*) FROM alertas WHERE tipo = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tipo);

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

    public boolean actualizarEstadoAlerta(int id, String estado) {
        String sql = "UPDATE alertas SET estado = ? WHERE id = ?";

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

    public boolean existeAlertaPendiente(int camionId, String tipo) {
        String sql = "SELECT COUNT(*) FROM alertas WHERE camion_id = ? AND tipo = ? AND estado = 'PENDIENTE'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, camionId);
            ps.setString(2, tipo);

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
}