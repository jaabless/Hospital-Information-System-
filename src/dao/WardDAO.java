// WardDAO.java
package dao;

import db_config.DBConnection;
import model.Ward;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WardDAO {
    private static Connection conn = DBConnection.getConnection();
    public static void addWard(Ward ward) {
        String sql = "INSERT INTO Ward (dept_id, ward_id, bed_count, supervisor_id) VALUES (?, ?, ?, ?)";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ward.getDeptId());
            stmt.setInt(2, ward.getWardNumber());
            stmt.setInt(3, ward.getBedCount());
            stmt.setInt(4, ward.getSupervisorId());
            stmt.executeUpdate();
            System.out.println("Ward added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding ward", e);
        }
    }

    public static List<Ward> getAllWards() {
        String sql = "SELECT * FROM Ward";
        List<Ward> list = new ArrayList<>();
        try (
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Ward(
                        rs.getInt("dept_id"),
                        rs.getInt("ward_id"),
                        rs.getInt("bed_count"),
                        rs.getInt("supervisor_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching wards", e);
        }
        return list;
    }

    // Update and delete stubs
    public static boolean updateWard(Ward ward) {
        String sql = "UPDATE Ward SET bed_count=?, supervisor_id=? WHERE dept_id=? AND ward_id=?";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ward.getBedCount());
            stmt.setInt(2, ward.getSupervisorId());
            stmt.setInt(3, ward.getDeptId());
            stmt.setInt(4, ward.getWardNumber());
            return stmt.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean deleteWard(int deptId, int wardNumber) {
        String sql = "DELETE FROM Ward WHERE dept_id=? AND ward_id=?";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, deptId);
            stmt.setInt(2, wardNumber);
            return stmt.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}