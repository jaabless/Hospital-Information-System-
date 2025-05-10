//nurseDAO
package dao;
import db_config.DBConnection;
import model.Nurse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NurseDAO {
    private static Connection conn = DBConnection.getConnection();
    // Adds nurse (employee + nurse tables)
    public static void addNurse(Nurse nurse) {
        String empSql = "INSERT INTO Employee (employee_id, first_name, last_name, address, phone) VALUES (?, ?, ?, ?, ?)";
        String nSql = "INSERT INTO Nurse (employee_id, rotation, salary, department_id) VALUES (?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false);
            try (PreparedStatement empStmt = conn.prepareStatement(empSql)) {
                empStmt.setInt(1, nurse.getEmployeeId());
                empStmt.setString(2, nurse.getFirstName());
                empStmt.setString(3, nurse.getLastName());
                empStmt.setString(4, nurse.getAddress());
                empStmt.setString(5, nurse.getPhone());
                empStmt.executeUpdate();
            }
            //insert into Nurse
            try (PreparedStatement nStmt = conn.prepareStatement(nSql)) {
                nStmt.setInt(1, nurse.getEmployeeId());
                nStmt.setString(2, nurse.getRotation());
                nStmt.setDouble(3, nurse.getSalary());
                nStmt.setInt(4, nurse.getDepartmentId());
                nStmt.executeUpdate();
            }
            conn.commit();
            System.out.println("Nurse added successfully.");
        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            throw new RuntimeException("Error adding nurse: " + e.getMessage(), e);
        }

    }

    // Retrieves nurses
    public static List<Nurse> getAllNurses() {
        List<Nurse> nurses = new ArrayList<>();
        String sql =
                "SELECT e.employee_id, e.first_name, e.last_name, e.address, e.phone, " +
                        "n.rotation, n.salary, n.department_id " +
                        "FROM Employee e " +
                        "JOIN Nurse n ON e.employee_id = n.employee_id";

        try (
                Statement stmt = conn.createStatement();
                ResultSet rs   = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                Nurse nurse = new Nurse(
                        rs.getInt("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("rotation"),
                        rs.getDouble("salary"),
                        rs.getInt("department_id")
                );
                nurses.add(nurse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // you might want to throw a custom exception here
        }
        return nurses;
    }

    // Updates nurse
    public static boolean updateNurse(Nurse nurse) {
        String empSql = "UPDATE Employee SET first_name = ?, last_name = ?, address = ?, phone = ? WHERE employee_id = ?";
        String nSql = "UPDATE Nurse SET rotation = ?, salary = ?, department_id = ? WHERE employee_id = ?";

        try {
            conn.setAutoCommit(false);
            try (PreparedStatement empStmt = conn.prepareStatement(empSql)) {
                empStmt.setString(1, nurse.getFirstName());
                empStmt.setString(2, nurse.getLastName());
                empStmt.setString(3, nurse.getAddress());
                empStmt.setString(4, nurse.getPhone());
                empStmt.setInt(5, nurse.getEmployeeId());
                empStmt.executeUpdate();
            }
            try (PreparedStatement nStmt = conn.prepareStatement(nSql)) {
                nStmt.setString(1, nurse.getRotation());
                nStmt.setDouble(2, nurse.getSalary());
                nStmt.setInt(3, nurse.getDepartmentId());
                nStmt.setInt(4, nurse.getEmployeeId());
                nStmt.executeUpdate();
            }
            conn.commit();
            System.out.println("Nurse updated successfully.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Deletes nurse
    public static boolean deleteNurse(int id) {
        String nSql = "DELETE FROM Nurse WHERE employee_id = ?";
        String empSql = "DELETE FROM Employee WHERE employee_id = ?";
//        (Connection conn = DBConnection.getConnection())
        try  {
            conn.setAutoCommit(false);
            try (PreparedStatement nStmt = conn.prepareStatement(nSql)) {
                nStmt.setInt(1, id);
                nStmt.executeUpdate();
            }
            try (PreparedStatement empStmt = conn.prepareStatement(empSql)) {
                empStmt.setInt(1, id);
                empStmt.executeUpdate();
            }
            conn.commit();
            System.out.println("Nurse deleted successfully.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}