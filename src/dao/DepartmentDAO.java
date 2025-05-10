// DepartmentDAO.java
package dao;

import db_config.DBConnection;
import model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private static Connection conn = DBConnection.getConnection();
    public static void addDepartment(Department dept) {
        String sql = "INSERT INTO department (dept_id, name, building, director_id) VALUES (?, ?, ?, ?)";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dept.getDeptId());
            stmt.setString(2, dept.getName());
            stmt.setString(3, dept.getBuilding());
            stmt.setInt(4, dept.getDirectorId());
            stmt.executeUpdate();
            System.out.println("Department Added Successfully");
        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            throw new RuntimeException( e.getMessage(), e);
        }
    }

    public static List<Department> getAllDepartments() {
        String sql = "SELECT * FROM Department";
        List<Department> list = new ArrayList<>();
        try (
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Department(
                        rs.getInt("dept_id"),
                        rs.getString("name"),
                        rs.getString("building"),
                        rs.getInt("director_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching departments", e);
        }
        return list;
    }

    // Update and delete stubs
    public static boolean updateDepartment(Department dept) {
        String sql = "UPDATE Department SET name=?, building=?, director_id=? WHERE dept_id=?";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dept.getName());
            stmt.setString(2, dept.getBuilding());
            stmt.setInt(3, dept.getDirectorId());
            stmt.setInt(4, dept.getDeptId());
            System.out.println("Update Successful");
            return stmt.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean deleteDepartment(int id) {
        String sql = "DELETE FROM Department WHERE dept_id=?";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            System.out.println("Delete Successful");
            return stmt.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
