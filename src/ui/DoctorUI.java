// DoctorUI.java
package ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Doctor;
import dao.DoctorDAO;
import state.AppState;
import util.TableUtils;
import util.UiUtils;

public class DoctorUI {
    private VBox view;
    private TextField idField, fnameField, lnameField, addressField, phoneField, salaryField, specialtyField,departmentField;
    private TextArea displayArea;

    public DoctorUI() {
//        view = new HBox(10);
//        view.setPadding(new Insets(10));
//        view.setAlignment(Pos.TOP_CENTER);
//
//        Label title = new Label("Doctor Management");
//
//
//
//        HBox buttons = new HBox(10, addBtn, updateBtn, deleteBtn, viewBtn);
//        buttons.setAlignment(Pos.CENTER);
//
//        view.getChildren().addAll(title, idField, fnameField, lnameField, addressField, phoneField,
//                specialtyField, departmentField, buttons, displayArea);
        HBox topControls = UiUtils.createTopControls_Doctor();
        VBox tableSection = TableUtils.createDoctorTableSection();
        view = new VBox();
        view.getChildren().addAll(topControls,tableSection);
    }

    public static void showAddDialog_Doctor() {
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

    public static void showUpdateDialog_Doctor() {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Update Doctor");
        idDialog.setHeaderText("Enter Doctor ID to update:");

        idDialog.showAndWait().ifPresent(idStr -> {
            try {
                int id = Integer.parseInt(idStr);
                // Find doctor by employeeId from the observable list
                Doctor doctor = AppState.observableDoctorList
                        .stream()
                        .filter(d -> d.getEmployeeId() == id)
                        .findFirst()
                        .orElse(null);

                if (doctor == null) {
                    AlertUtils.showError("Doctor with ID " + id + " not found.");
                    return;
                }

                Dialog<Doctor> dialog = new Dialog<>();
                dialog.setTitle("Update Doctor");
                GridPane grid = UIFactory.createGridPane(20);

                TextField idField = new TextField(String.valueOf(doctor.getEmployeeId()));
                idField.setEditable(false);
                TextField fnameField = new TextField(doctor.getFirstName());
                TextField lnameField = new TextField(doctor.getLastName());
                TextField addressField = new TextField(doctor.getAddress());
                TextField phoneField = new TextField(doctor.getPhone());
//                TextField salaryField = new TextField(String.valueOf(doctor.getSalary()));
                TextField specialtyField = new TextField(doctor.getSpecialty());
                TextField departmentField = new TextField(String.valueOf(doctor.getDepartmentID()));

                grid.add(new Label("Doctor ID:"), 0, 0); grid.add(idField, 1, 0);
                grid.add(new Label("First Name:"), 0, 1); grid.add(fnameField, 1, 1);
                grid.add(new Label("Last Name:"), 0, 2); grid.add(lnameField, 1, 2);
                grid.add(new Label("Address:"), 0, 3); grid.add(addressField, 1, 3);
                grid.add(new Label("Phone:"), 0, 4); grid.add(phoneField, 1, 4);
//                grid.add(new Label("Salary:"), 0, 5); grid.add(salaryField, 1, 5);
                grid.add(new Label("Specialty:"), 0, 5); grid.add(specialtyField, 1, 5);
                grid.add(new Label("Department ID:"), 0, 6); grid.add(departmentField, 1, 6);

                dialog.getDialogPane().setContent(grid);
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                dialog.setResultConverter(button -> {
                    if (button == ButtonType.OK) {
                        try {
                            doctor.setFirstName(fnameField.getText());
                            doctor.setLastName(lnameField.getText());
                            doctor.setAddress(addressField.getText());
                            doctor.setPhone(phoneField.getText());
//                            doctor.setSalary(Double.parseDouble(salaryField.getText()));
                            doctor.setSpecialty(specialtyField.getText());
                            doctor.setDepartmentId(Integer.parseInt(departmentField.getText()));
                            return doctor;
                        } catch (NumberFormatException ex) {
                            AlertUtils.showError("Invalid number format in salary or department.");
                        }
                    }
                    return null;
                });

                dialog.showAndWait().ifPresent(updatedDoctor -> {
                    DoctorDAO.updateDoctor(updatedDoctor);
                    AppState.observableDoctorList.setAll(DoctorDAO.getAllDoctors());
                    AppState.doctorTableView.refresh();
                    AlertUtils.showInfo("Doctor updated successfully.");
                });

            } catch (NumberFormatException e) {
                AlertUtils.showError("Please enter a valid numeric ID.");
            } catch (Exception e) {
                AlertUtils.showError("Error retrieving doctor: " + e.getMessage());
            }
        });
    }

    public static void showDeleteDialog_Doctor() {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Delete Doctor");
        idDialog.setHeaderText("Enter Doctor ID to delete:");

        idDialog.showAndWait().ifPresent(idStr -> {
            try {
                int id = Integer.parseInt(idStr);

                // Find doctor by employeeId from the observable list
                Doctor doctor = AppState.observableDoctorList
                        .stream()
                        .filter(d -> d.getEmployeeId() == id)
                        .findFirst()
                        .orElse(null);

                if (doctor == null) {
                    AlertUtils.showError("Doctor with ID " + id + " not found.");
                    return;
                }

                boolean confirmed = AlertUtils.showConfirmation(
                        "Confirm Deletion",
                        "Are you sure you want to delete \nDr " + doctor.getFirstName() + " " +doctor.getLastName()
                );

                if (confirmed) {
                    DoctorDAO.deleteDoctor(id);
                    AppState.observableDoctorList.setAll(DoctorDAO.getAllDoctors());  // Refresh list
                    AppState.doctorTableView.refresh();
                    AlertUtils.showInfo("Doctor deleted successfully.");
                }

            } catch (NumberFormatException ex) {
                AlertUtils.showError("Invalid ID.");
            } finally {
                idDialog.getEditor().clear();  // Clear input for re-entry
            }
        });
    }



    public VBox getView() {
        return view;
    }
}