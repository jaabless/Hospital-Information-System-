// DoctorDAO.java
package dao;
import db_config.DBConnection;
import model.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private static Connection conn = DBConnection.getConnection();
    // Adds a new doctor (inserts into Employee then Doctor tables)
    public static void addDoctor(Doctor doctor) {
        String empSql = "INSERT INTO employee (employee_id, first_name, last_name, address, phone) VALUES (?, ?, ?, ?, ?)";
        String docSql = "INSERT INTO doctor (employee_id, specialization, department_id) VALUES (?, ?, ?)";

        try {
            conn.setAutoCommit(false);
            // Insert base employee
            try (PreparedStatement empStmt = conn.prepareStatement(empSql)) {
                empStmt.setInt(1, doctor.getEmployeeId());
                empStmt.setString(2, doctor.getFirstName());
                empStmt.setString(3, doctor.getLastName());
                empStmt.setString(4, doctor.getAddress());
                empStmt.setString(5, doctor.getPhone());
                empStmt.executeUpdate();
            }
            // Insert doctor-specific
            try (PreparedStatement docStmt = conn.prepareStatement(docSql)) {
                docStmt.setInt(1, doctor.getEmployeeId());
                docStmt.setString(2, doctor.getSpecialty());
//                docStmt.setDouble(3, doctor.getSalary());
                docStmt.setInt(3, doctor.getDepartmentID());
                docStmt.executeUpdate();
            }
            conn.commit();
            System.out.println("Doctor added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding doctor: " + e.getMessage(), e);
        }
    }

    // Retrieves all doctors with employee info
    public static List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT e.employee_id, e.first_name, e.last_name, e.address, e.phone, " +
                "d.specialization,d.department_id " +
                "FROM Employee e JOIN Doctor d ON e.employee_id = d.employee_id";
        try (
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Doctor doctor = new Doctor(
                        rs.getInt("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("specialization"),
//                        rs.getDouble("salary"),
                        rs.getInt("department_id")
                );
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    // Updates both Employee and Doctor tables
    public static boolean updateDoctor(Doctor doctor) {
        String empSql = "UPDATE Employee SET first_name = ?, last_name = ?, address = ?, phone = ? WHERE employee_id = ?";
        String docSql = "UPDATE Doctor SET specialization = ?, department_id = ? WHERE employee_id = ?";

        try  {
            conn.setAutoCommit(false);
            try (PreparedStatement empStmt = conn.prepareStatement(empSql)) {
                empStmt.setString(1, doctor.getFirstName());
                empStmt.setString(2, doctor.getLastName());
                empStmt.setString(3, doctor.getAddress());
                empStmt.setString(4, doctor.getPhone());
                empStmt.setInt(5, doctor.getEmployeeId());
                empStmt.executeUpdate();
            }
            try (PreparedStatement docStmt = conn.prepareStatement(docSql)) {
                docStmt.setString(1, doctor.getSpecialty());
//                docStmt.setDouble(2, doctor.getSalary());
                docStmt.setInt(2, doctor.getDepartmentID());
                docStmt.setInt(3, doctor.getEmployeeId());
                docStmt.executeUpdate();
            }
            conn.commit();
            System.out.println("Doctor updated successfully.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Deletes doctor record and corresponding employee
    public static boolean deleteDoctor(int id) {
        String docSql = "DELETE FROM Doctor WHERE employee_id = ?";
        String empSql = "DELETE FROM Employee WHERE employee_id = ?";

        try {
            conn.setAutoCommit(false);
            try (PreparedStatement docStmt = conn.prepareStatement(docSql)) {
                docStmt.setInt(1, id);
                docStmt.executeUpdate();
            }
            try (PreparedStatement empStmt = conn.prepareStatement(empSql)) {
                empStmt.setInt(1, id);
                empStmt.executeUpdate();
            }
            conn.commit();
            System.out.println("Doctor deleted successfully.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}