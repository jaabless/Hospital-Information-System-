
// NurseUI.java
package ui;

import controller.DepartmentController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Nurse;
import dao.NurseDAO;

import java.util.ArrayList;
import java.util.List;

public class NurseUI {
    private VBox view;
    private TextField idField, fnameField, lnameField, addressField, phoneField, salaryField, rotationField, departmentIdField;
    private TextArea displayArea;

    public NurseUI() {
        view = new VBox(10);
        view.setPadding(new Insets(10));
        view.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Nurse Management");

        idField = new TextField();
        idField.setPromptText("Nurse ID");
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
        rotationField = new TextField();
        rotationField.setPromptText("Rotation");
        departmentIdField = new TextField();
        departmentIdField.setPromptText("Department ID");

        Button addBtn = UIFactory.createStyledButton("Add Nurse");
        Button updateBtn = new Button("Update Nurse");
        Button deleteBtn = new Button("Delete Nurse");
        Button viewBtn = new Button("View All Nurses");

        displayArea = new TextArea();
        displayArea.setEditable(false);

        addBtn.setOnAction(e -> {
            try {
                Nurse nurse = new Nurse(
                        Integer.parseInt(idField.getText()),
                        fnameField.getText(),
                        lnameField.getText(),
                        addressField.getText(),
                        phoneField.getText(),
                        rotationField.getText(),
                        Double.parseDouble(salaryField.getText()),
                        Integer.parseInt(departmentIdField.getText())
                );
                NurseDAO.addNurse(nurse);
//                DepartmentController.loadDepartmentData();
                AlertUtils.showInfo("Nurse added successfully.");
            } catch (Exception ex) {
                AlertUtils.showError( ex.getMessage());
            }
        });

        updateBtn.setOnAction(e -> {
            try {
                Nurse nurse = new Nurse(
                        Integer.parseInt(idField.getText()),
                        fnameField.getText(),
                        lnameField.getText(),
                        addressField.getText(),
                        phoneField.getText(),
                        rotationField.getText(),
                        Double.parseDouble(salaryField.getText()),
                        Integer.parseInt(departmentIdField.getText())
                );
                NurseDAO.updateNurse(nurse);
                AlertUtils.showInfo("Nurse updated successfully.");
            } catch (Exception ex) {
                AlertUtils.showError("Error updating nurse: " + ex.getMessage());
            }
        });

        deleteBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                NurseDAO.deleteNurse(id);
                AlertUtils.showInfo("Nurse deleted successfully.");
            } catch (Exception ex) {
                AlertUtils.showError("Error deleting nurse: " + ex.getMessage());
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
                rotationField, salaryField, departmentIdField, buttons, displayArea);
    }

//    public static void showAddDialog() {
//        Dialog<Nurse> dialog = new Dialog<>();
//        dialog.setTitle("Add Employee");
//
//        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(20));
//
//        TextField idField = new TextField();
//        idField = new TextField();
//        idField.setPromptText("Nurse ID");
//        TextField fnameField = new TextField();
//        fnameField.setPromptText("First Name");
//        TextField lnameField = new TextField();
//        lnameField.setPromptText("Last Name");
//        TextField addressField = new TextField();
//        addressField.setPromptText("Address");
//        TextField phoneField = new TextField();
//        phoneField.setPromptText("Phone");
//        TextField salaryField = new TextField();
//        salaryField.setPromptText("Salary");
//        TextField rotationField = new TextField();
//        rotationField.setPromptText("Rotation");
//        TextField departmentIdField = new TextField();
//        departmentIdField.setPromptText("Department ID");
//
//        grid.add(new Label("Nurse ID:"), 0, 0); grid.add(idField, 1, 0);
//        grid.add(new Label("First Name:"), 0, 1); grid.add(fnameField, 1, 1);
//        grid.add(new Label("Last Name:"), 0, 2); grid.add(lnameField, 1, 2);
//        grid.add(new Label("Address:"), 0, 3); grid.add(addressField, 1, 3);
//        grid.add(new Label("Phone:"), 0, 4); grid.add(phoneField, 1, 4);
//        grid.add(new Label("Salary:"), 0, 5); grid.add(salaryField, 1, 5);
//        grid.add(new Label("Rotation:"), 0, 6); grid.add(rotationField, 1, 6);
//        grid.add(new Label("Department ID:"), 0, 7); grid.add(departmentIdField, 1, 7);
//
//
//        ButtonType addBtnType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
//        dialog.getDialogPane().getButtonTypes().addAll(addBtnType, ButtonType.CANCEL);
//        dialog.getDialogPane().setContent(grid);
//
//        dialog.setResultConverter(button -> {
//            if (button == addBtnType) {
//                // Check for empty fields
//                if (idField.getText().trim().isEmpty() ||
//                        fnameField.getText().trim().isEmpty() ||
//                        lnameField.getText().trim().isEmpty() ||
//                        addressField.getText().trim().isEmpty() ||
//                        phoneField.getText().trim().isEmpty() ||
//                        rotationField.getText().trim().isEmpty() ||
//                        salaryField.getText().trim().isEmpty() ||
//                        departmentIdField.getText().trim().isEmpty() ||
//                        experienceField.getText().trim().isEmpty()) {
//
//                    AlertUtils.showError("All fields are required. Please fill in all fields.");
//                    return null;
//                }
//
//                try {
//                    int id = Integer.parseInt(idField.getText().trim());
//                    String name = nameField.getText().trim();
//                    String dept = deptField.getText().trim();
//                    double salary = Double.parseDouble(salaryField.getText().trim());
//                    double rating = Double.parseDouble(ratingField.getText().trim());
//                    int exp = Integer.parseInt(experienceField.getText().trim());
//                    boolean active = activeCheck.isSelected();
//
//                    if (salary < 0) {
//                        throw new InvalidSalaryException("Salary cannot be negative.");
//                    }
//                    return new Employee<>(id, name, dept, salary, rating, exp, active);
//                } catch (NumberFormatException ex) {
//                    AlertUtils.showError("Please enter valid numeric values for ID, Salary, Rating, and Experience.");
//                } catch (InvalidSalaryException ex) {
//                    AlertUtils.showError(ex.getMessage());
//                } catch (Exception ex) {
//                    AlertUtils.showError("Something went wrong. Please check your input.");
//                } finally {
//                    dialog.close();
//                }
//            }
//            return null;
//        });
//
//        dialog.showAndWait().ifPresent(emp -> {
//            AppState.database.addEmployee(emp);
//            AppState.refreshTable();
//        });
//    }

    public VBox getView() {
        return view;
    }
}
