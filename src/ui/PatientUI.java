package ui;

import dao.NurseDAO;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Nurse;
import model.Patient;
import dao.PatientDAO;

import java.util.ArrayList;
import java.util.List;

import static ui.AlertUtils.*;

public class PatientUI {

    private TextField idField = new TextField();
    private TextField fnameField = new TextField();
    private TextField lnameField = new TextField();
    private TextField addressField = new TextField();
    private TextField phoneField = new TextField();
    private TextField wardIdField = new TextField();
    private TextField bedNumberField = new TextField();
    private TextField diagnosisField = new TextField();
    private TextField doctorIdField = new TextField();
    private TextArea displayArea = new TextArea();

    public BorderPane getView() {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));

        GridPane inputGrid = new GridPane();
        inputGrid.setVgap(10);
        inputGrid.setHgap(10);
        inputGrid.setPadding(new Insets(10));

        inputGrid.add(new Label("ID:"), 0, 0);
        inputGrid.add(idField, 1, 0);
        inputGrid.add(new Label("First Name:"), 0, 1);
        inputGrid.add(fnameField, 1, 1);
        inputGrid.add(new Label("Last Name:"), 0, 2);
        inputGrid.add(lnameField, 1, 2);
        inputGrid.add(new Label("Address:"), 0, 3);
        inputGrid.add(addressField, 1, 3);
        inputGrid.add(new Label("Phone:"), 0, 4);
        inputGrid.add(phoneField, 1, 4);
        inputGrid.add(new Label("Ward ID:"), 0, 5);
        inputGrid.add(wardIdField, 1, 5);
        inputGrid.add(new Label("Bed Number:"), 0, 6);
        inputGrid.add(bedNumberField, 1, 6);
        inputGrid.add(new Label("Diagnosis:"), 0, 7);
        inputGrid.add(diagnosisField, 1, 7);
        inputGrid.add(new Label("Doctor ID:"), 0, 8);
        inputGrid.add(doctorIdField, 1, 8);

        HBox buttonBox = new HBox(10);
        Button addBtn = new Button("Add");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");
        Button viewBtn = new Button("View All");
        buttonBox.getChildren().addAll(addBtn, updateBtn, deleteBtn, viewBtn);

        displayArea.setEditable(false);
        displayArea.setPrefHeight(200);

        VBox centerBox = new VBox(10, inputGrid, buttonBox, displayArea);
        layout.setCenter(centerBox);

        addBtn.setOnAction(e -> addPatient());
        updateBtn.setOnAction(e -> updatePatient());
        deleteBtn.setOnAction(e -> deletePatient());
        viewBtn.setOnAction(e -> displayPatients());

        return layout;
    }

    private void addPatient() {
        try {
            Patient patient = new Patient(
                    Integer.parseInt(idField.getText()),
                    fnameField.getText(),
                    lnameField.getText(),
                    addressField.getText(),
                    phoneField.getText(),
                    Integer.parseInt(wardIdField.getText()),
                    Integer.parseInt(bedNumberField.getText()),
                    diagnosisField.getText(),
                    Integer.parseInt(doctorIdField.getText())
            );
            PatientDAO.addPatient(patient);
            showInfo("Patient added successfully");
        } catch (Exception ex) {
            showError("Failed to add patient: " + ex.getMessage());
        }
    }

    private void updatePatient() {
        try {
            Patient patient = new Patient(
                    Integer.parseInt(idField.getText()),
                    fnameField.getText(),
                    lnameField.getText(),
                    addressField.getText(),
                    phoneField.getText(),
                    Integer.parseInt(wardIdField.getText()),
                    Integer.parseInt(bedNumberField.getText()),
                    diagnosisField.getText(),
                    Integer.parseInt(doctorIdField.getText())
            );
            PatientDAO.updatePatient(patient);
            showInfo("Patient updated successfully");
        } catch (Exception ex) {
            showError("Failed to update patient: " + ex.getMessage());
        }
    }

    private void deletePatient() {
        try {
            int id = Integer.parseInt(idField.getText());
//            PatientDAO.deletePatient(id);
            showInfo("Patient deleted successfully");
        } catch (Exception ex) {
            showError("Failed to delete patient: " + ex.getMessage());
        }
    }

    private void displayPatients() {
        try {
            // Prepare headers and rows
            List<Patient> patients = PatientDAO.getAllPatients();
            List<String> headers = List.of("ID", "FirstName", "LastName", "DeptID", "Rotation", "Salary");
            List<String[]> rows = new ArrayList<>();
            for (Patient n : patients) {
                rows.add(new String[]{
                        String.valueOf(n.getPatientId()),
                        n.getFirstName(),
                        n.getLastName(),
                        n.getAddress(),
                        n.getPhone(),
                        String.valueOf(n.getWardId()),
                        String.valueOf(n.getBedNumber()),
                        n.getDiagnosis(),
                        String.valueOf(n.getDoctorId()),
                });
            }
            String table = util.TableUtil.formatTable(headers, rows);
            displayArea.setText(table);
        } catch (Exception ex) {
            AlertUtils.showError("Error loading nurses: " + ex.getMessage());
        }
    }
}
