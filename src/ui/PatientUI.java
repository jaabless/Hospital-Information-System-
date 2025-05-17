package ui;

import dao.DoctorDAO;
import dao.NurseDAO;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Doctor;
import model.Nurse;
import model.Patient;
import dao.PatientDAO;
import state.AppState;
import util.TableUtils;
import util.UiUtils;

import java.util.ArrayList;
import java.util.List;

import static ui.AlertUtils.*;

public class PatientUI {
    private VBox view;

    public PatientUI() {
        HBox topControls = UiUtils.createTopControls_Patient();
        VBox tableSection = TableUtils.createPatientTableSection();
        view = new VBox();
        view.getChildren().addAll(topControls,tableSection);
    }

    public static void showAddDialog_Patient() {
        Dialog<Doctor> dialog = new Dialog<>();
        dialog.setTitle("Add Employee");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField idField = new TextField();
        TextField fnameField = new TextField();
        TextField lnameField = new TextField();
        TextField addressField = new TextField();
        TextField phoneField = new TextField();
        TextField salaryField = new TextField();
        TextField specialtyField = new TextField();
        TextField departmentField = new TextField();

        grid.add(new Label("Doctor ID:"), 0, 0); grid.add(idField, 1, 0);
        grid.add(new Label("First Name:"), 0, 1); grid.add(fnameField, 1, 1);
        grid.add(new Label("Last Name:"), 0, 2); grid.add(lnameField, 1, 2);
        grid.add(new Label("Address:"), 0, 3); grid.add(addressField, 1, 3);
        grid.add(new Label("Phone:"), 0, 4); grid.add(phoneField, 1, 4);
        grid.add(new Label("Salary:"), 0, 5); grid.add(salaryField, 1, 5);
        grid.add(new Label("Specialty:"), 0, 6); grid.add(specialtyField, 1, 6);
        grid.add(new Label("Department ID:"), 0, 7); grid.add(departmentField, 1, 7);


        ButtonType addBtnType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addBtnType, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(button -> {
            if (button == addBtnType) {
                // Check for empty fields
                if (idField.getText().trim().isEmpty() ||
                        fnameField.getText().trim().isEmpty() ||
                        lnameField.getText().trim().isEmpty() ||
                        salaryField.getText().trim().isEmpty() ||
                        addressField.getText().trim().isEmpty() ||
                        phoneField.getText().trim().isEmpty() ||
                        specialtyField.getText().trim().isEmpty() ||
                        departmentField.getText().trim().isEmpty()) {

                    AlertUtils.showError("All fields are required. Please fill in all fields.");
                    return null;
                }

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
                    AppState.observableDoctorList.setAll(DoctorDAO.getAllDoctors());
                    AlertUtils.showInfo("Doctor added successfully.");

                } catch (Exception ex) {
                    AlertUtils.showError("Error adding doctor: " + ex.getMessage());
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(emp -> {
//            AppState.database.addEmployee(emp);
//            AppState.refreshTable();
        });
    }


    public VBox getView() {
        return view;
    }

}

