package dao;

import db_config.DBConnection;
import model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    private static Connection conn = DBConnection.getConnection();

    public static void addPatient(Patient patient) {
        String sql = "INSERT INTO patient (patient_id, first_name, last_name, address, phone, ward_id, bed_number, diagnosis, doctor_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patient.getPatientId());
            stmt.setString(2, patient.getFirstName());
            stmt.setString(3, patient.getLastName());
            stmt.setString(4, patient.getAddress());
            stmt.setString(5, patient.getPhone());
            stmt.setInt(6, patient.getWardId());
            stmt.setInt(7, patient.getBedNumber());
            stmt.setString(8, patient.getDiagnosis());
            stmt.setInt(9, patient.getDoctorId());

            stmt.executeUpdate();
            System.out.println("Patient added successfully.");
        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            throw new RuntimeException( e.getMessage(), e);
        }
    }

    public static List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient";
        try (
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getInt("ward_id"),
                        rs.getInt("bed_number"),
                        rs.getString("diagnosis"),
                        rs.getInt("doctor_id")
                );
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

//    public void updatePatientDiagnosis(int patientId, String newDiagnosis) {
//        String sql = "UPDATE patient SET diagnosis = ? WHERE patient_id = ?";
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, newDiagnosis);
//            stmt.setInt(2, patientId);
//            stmt.executeUpdate();
//            System.out.println("Diagnosis updated successfully.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static void updatePatient(Patient patient) {
        String sql = "UPDATE patient SET first_name = ?, last_name = ?, address = ?, phone = ?, " +
                "ward_id = ?, bed_number = ?, diagnosis = ?, doctor_id = ? WHERE patient_id = ?";

        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setString(3, patient.getAddress());
            stmt.setString(4, patient.getPhone());
            stmt.setInt(5, patient.getWardId());
            stmt.setInt(6, patient.getBedNumber());
            stmt.setString(7, patient.getDiagnosis());
            stmt.setInt(8, patient.getDoctorId());
            stmt.setInt(9, patient.getPatientId());

            stmt.executeUpdate();
            conn.commit();
            System.out.println("Nurse updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating patient: " + e.getMessage());
        }
    }
    public void deletePatient(int patientId) {
        String sql = "DELETE FROM patient WHERE patient_id = ?";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            stmt.executeUpdate();
            System.out.println("Patient deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
