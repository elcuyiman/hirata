package com.hirata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hirata.model.Camion;
import com.hirata.util.DBConnection;

public class CamionDAO {

    public List<Camion> listarCamiones() {
        List<Camion> lista = new ArrayList<>();
        String sql = "SELECT id, placa, modelo, anio, trabajador_id FROM camiones ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Integer trabajadorId = (Integer) rs.getObject("trabajador_id");

                lista.add(new Camion(
                        rs.getInt("id"),
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getInt("anio"),
                        trabajadorId
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean guardarCamion(Camion camion) {
        String sql = "INSERT INTO camiones (placa, modelo, anio) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, camion.getPlaca());
            ps.setString(2, camion.getModelo());
            ps.setInt(3, camion.getAnio());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminarCamion(int id) {
        String sql = "DELETE FROM camiones WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public int contarCamiones() {
        String sql = "SELECT COUNT(*) FROM camiones";

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

    public boolean asignarTrabajador(int camionId, int trabajadorId) {
        String sql = "UPDATE camiones SET trabajador_id = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, trabajadorId);
            ps.setInt(2, camionId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean quitarTrabajador(int camionId) {
        String sql = "UPDATE camiones SET trabajador_id = NULL WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, camionId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}