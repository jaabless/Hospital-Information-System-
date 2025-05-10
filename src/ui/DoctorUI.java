// DoctorUI.java
package ui;

import dao.NurseDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Doctor;
import dao.DoctorDAO;
import model.Nurse;

import java.util.ArrayList;
import java.util.List;

public class DoctorUI {
    private VBox view;
    private TextField idField, fnameField, lnameField, addressField, phoneField, salaryField, specialtyField,departmentField;
    private TextArea displayArea;

    public DoctorUI() {
        view = new VBox(10);
        view.setPadding(new Insets(10));
        view.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Doctor Management");

        idField = new TextField();
        idField.setPromptText("Doctor ID");
        fnameField = new TextField();
        fnameField.setPromptText("First Name");
        lnameField = new TextField();
        lnameField.setPromptText("Last Name");
        addressField = new TextField();
        addressField.setPromptText("Address");
        phoneField = new TextField();
        phoneField.setPromptText("Phone");
        salaryField = new TextField();
        salaryField.setPromptText("Salary");
        specialtyField = new TextField();
        specialtyField.setPromptText("Specialty");
        departmentField = new TextField();
        departmentField.setPromptText("Department ID");

        Button addBtn = new Button("Add Doctor");
        Button updateBtn = new Button("Update Doctor");
        Button deleteBtn = new Button("Delete Doctor");
        Button viewBtn = new Button("View All Doctors");

        displayArea = new TextArea();
        displayArea.setEditable(false);

        addBtn.setOnAction(e -> {
            try {
                Doctor doctor = new Doctor(
                        Integer.parseInt(idField.getText()),
                        fnameField.getText(),
                        lnameField.getText(),
                        addressField.getText(),
                        phoneField.getText(),
                        specialtyField.getText(),
                        Integer.parseInt(departmentField.getText())
//                        Double.parseDouble(salaryField.getText())
                );
                DoctorDAO.addDoctor(doctor);
                AlertUtils.showInfo("Doctor added successfully.");
            } catch (Exception ex) {
                AlertUtils.showError("Error adding doctor: " + ex.getMessage());
            }
        });

        updateBtn.setOnAction(e -> {
            try {
                Doctor doctor = new Doctor(
                        Integer.parseInt(idField.getText()),
                        fnameField.getText(),
                        lnameField.getText(),
                        addressField.getText(),
                        phoneField.getText(),
                        specialtyField.getText(),
                        Integer.parseInt(departmentField.getText())
//                        Double.parseDouble(salaryField.getText())
                );
                DoctorDAO.updateDoctor(doctor);
                AlertUtils.showInfo("Doctor updated successfully.");
            } catch (Exception ex) {
                AlertUtils.showError("Error updating doctor: " + ex.getMessage());
            }
        });

        deleteBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                DoctorDAO.deleteDoctor(id);
                AlertUtils.showInfo("Doctor deleted successfully.");
            } catch (Exception ex) {
                AlertUtils.showError("Error deleting doctor: " + ex.getMessage());
            }
        });

        viewBtn.setOnAction(e -> {
            try {
                // Prepare headers and rows
                List<Nurse> nurses = NurseDAO.getAllNurses();
                List<String> headers = List.of("ID", "FirstName", "LastName", "DeptID", "Rotation", "Salary");
                List<String[]> rows = new ArrayList<>();
                for (Nurse n : nurses) {
                    rows.add(new String[]{
                            String.valueOf(n.getEmployeeId()),
                            n.getFirstName(),
                            n.getLastName(),
                            String.valueOf(n.getDepartmentId()),
                            n.getRotation(),
                            String.format("%.2f", n.getSalary())
                    });
                }
                String table = util.TableUtil.formatTable(headers, rows);
                displayArea.setText(table);
            } catch (Exception ex) {
                AlertUtils.showError("Error loading nurses: " + ex.getMessage());
            }
        });

        HBox buttons = new HBox(10, addBtn, updateBtn, deleteBtn, viewBtn);
        buttons.setAlignment(Pos.CENTER);

        view.getChildren().addAll(title, idField, fnameField, lnameField, addressField, phoneField,
                specialtyField, departmentField, buttons, displayArea);
    }

    public VBox getView() {
        return view;
    }
}