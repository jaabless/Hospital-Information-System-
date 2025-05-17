// DepartmentUI.java
package ui;

import dao.NurseDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Department;
import dao.DepartmentDAO;
import model.Nurse;

import java.util.ArrayList;
import java.util.List;

public class DepartmentUI {
    private TextField idField = new TextField();
    private TextField nameField = new TextField();
    private TextField buildingField = new TextField();
    private TextField directorIdField = new TextField();
    private TextArea displayArea = new TextArea();

    public BorderPane getView() {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        grid.add(new Label("Dept ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Building:"), 0, 2);
        grid.add(buildingField, 1, 2);
        grid.add(new Label("Director ID:"), 0, 3);
        grid.add(directorIdField, 1, 3);

        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);
        Button addBtn = new Button("Add");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");
        Button viewBtn = new Button("View All");
        buttons.getChildren().addAll(addBtn, updateBtn, deleteBtn, viewBtn);

        displayArea.setEditable(false);
        displayArea.setPrefHeight(200);

        VBox centerBox = new VBox(10, grid, buttons, displayArea);
        layout.setCenter(centerBox);

        addBtn.setOnAction(e -> addDepartment());
        updateBtn.setOnAction(e -> updateDepartment());
        deleteBtn.setOnAction(e -> deleteDepartment());
        viewBtn.setOnAction(e -> displayDepartments());

        return layout;
    }

    private void addDepartment() {
        try {
            Department dept = new Department(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    buildingField.getText(),
                    Integer.parseInt(directorIdField.getText())
            );
            DepartmentDAO.addDepartment(dept);
            AlertUtils.showInfo("Department added successfully.");
        } catch (Exception ex) {
            AlertUtils.showError("Error adding department: " + ex.getMessage());
        }
    }

    private void updateDepartment() {
        try {
            Department dept = new Department(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    buildingField.getText(),
                    Integer.parseInt(directorIdField.getText())
            );
            DepartmentDAO.updateDepartment(dept);
            AlertUtils.showInfo("Department updated successfully.");
        } catch (Exception ex) {
            AlertUtils.showError("Error updating department: " + ex.getMessage());
        }
    }

    private void deleteDepartment() {
        try {
            int id = Integer.parseInt(idField.getText());
            DepartmentDAO.deleteDepartment(id);
            AlertUtils.showInfo("Department deleted successfully.");
        } catch (Exception ex) {
            AlertUtils.showError("Error deleting department: " + ex.getMessage());
        }
    }

    private void displayDepartments() {
//        try {
//            StringBuilder sb = new StringBuilder();
//            for (Department d : DepartmentDAO.getAllDepartments()) {
//                sb.append(d.toString()).append("\n");
//            }
//            displayArea.setText(sb.toString());
//        } catch (Exception ex) {
//            AlertUtils.showError("Error loading departments: " + ex.getMessage());
//        }
        try {
            // Prepare headers and rows
            List<Department> department = DepartmentDAO.getAllDepartments();
            List<String> headers = List.of("Department ID", "Department Name", "Building", "Director ID" );
            List<String[]> rows = new ArrayList<>();
            for (Department n : department) {
                rows.add(new String[]{
                        String.valueOf(n.getDeptId()),
                        n.getName(),
                        String.valueOf(n.getBuilding()),
                        String.valueOf(n.getDirectorId())
                });
            }
//            String table = util.TableUtil.formatTable(headers, rows);
//            displayArea.setText(table);
        } catch (Exception ex) {
            AlertUtils.showError("Error loading nurses: " + ex.getMessage());
        }
    }
}