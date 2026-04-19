package com.hirata.dao;

import com.hirata.model.Alerta;
import com.hirata.model.Kilometraje;
import com.hirata.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KilometrajeDAO {

    private final AlertaDAO alertaDAO = new AlertaDAO();

    public boolean guardarKilometraje(Kilometraje registro) {
        String sql = "INSERT INTO kilometrajes (camion_id, kilometraje) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, registro.getCamionId());
            ps.setInt(2, registro.getKilometraje());

            boolean guardado = ps.executeUpdate() > 0;

            if (guardado) {
                generarAlertasSiCorresponde(registro.getCamionId(), registro.getKilometraje());
            }

            return guardado;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Kilometraje> listarKilometrajes() {
        List<Kilometraje> lista = new ArrayList<>();
        String sql = "SELECT id, camion_id, kilometraje, fecha FROM kilometrajes ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Kilometraje(
                        rs.getInt("id"),
                        rs.getInt("camion_id"),
                        rs.getInt("kilometraje"),
                        rs.getString("fecha")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Integer obtenerUltimoKilometraje(int camionId) {
        String sql = "SELECT kilometraje FROM kilometrajes WHERE camion_id = ? ORDER BY id DESC LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, camionId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("kilometraje");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void generarAlertasSiCorresponde(int camionId, int kilometraje) {
        if (kilometraje >= 10000) {
            if (!alertaDAO.existeAlertaPendiente(camionId, "EMERGENCIA")) {
                alertaDAO.guardarAlerta(new Alerta(
                        camionId,
                        "EMERGENCIA",
                        "El camión alcanzó 10.000 km y requiere mantenimiento urgente.",
                        kilometraje,
                        "PENDIENTE"
                ));
            }
        } else if (kilometraje >= 5000) {
            if (!alertaDAO.existeAlertaPendiente(camionId, "AVISO")) {
                alertaDAO.guardarAlerta(new Alerta(
                        camionId,
                        "AVISO",
                        "El camión alcanzó 5.000 km. Se recomienda revisión.",
                        kilometraje,
                        "PENDIENTE"
                ));
            }
        }
    }
}