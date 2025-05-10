
// WardUI.java
package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Ward;
import dao.WardDAO;

public class WardUI {
    private TextField deptIdField = new TextField();
    private TextField wardNumberField = new TextField();
    private TextField bedCountField = new TextField();
    private TextField supervisorIdField = new TextField();
    private TextArea displayArea = new TextArea();

    public BorderPane getView() {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        grid.add(new Label("Dept ID:"), 0, 0);
        grid.add(deptIdField, 1, 0);
        grid.add(new Label("Ward #:"), 0, 1);
        grid.add(wardNumberField, 1, 1);
        grid.add(new Label("Bed Count:"), 0, 2);
        grid.add(bedCountField, 1, 2);
        grid.add(new Label("Supervisor ID:"), 0, 3);
        grid.add(supervisorIdField, 1, 3);

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

        addBtn.setOnAction(e -> addWard());
        updateBtn.setOnAction(e -> updateWard());
        deleteBtn.setOnAction(e -> deleteWard());
        viewBtn.setOnAction(e -> displayWards());

        return layout;
    }

    private void addWard() {
        try {
            Ward ward = new Ward(
                    Integer.parseInt(deptIdField.getText()),
                    Integer.parseInt(wardNumberField.getText()),
                    Integer.parseInt(bedCountField.getText()),
                    Integer.parseInt(supervisorIdField.getText())
            );
            WardDAO.addWard(ward);
            AlertUtils.showInfo("Ward added successfully.");
        } catch (Exception ex) {
            AlertUtils.showError("Error adding ward: " + ex.getMessage());
        }
    }

    private void updateWard() {
        try {
            Ward ward = new Ward(
                    Integer.parseInt(deptIdField.getText()),
                    Integer.parseInt(wardNumberField.getText()),
                    Integer.parseInt(bedCountField.getText()),
                    Integer.parseInt(supervisorIdField.getText())
            );
            WardDAO.updateWard(ward);
            AlertUtils.showInfo("Ward updated successfully.");
        } catch (Exception ex) {
            AlertUtils.showError("Error updating ward: " + ex.getMessage());
        }
    }

    private void deleteWard() {
        try {
            int deptId = Integer.parseInt(deptIdField.getText());
            int wardNum = Integer.parseInt(wardNumberField.getText());
            WardDAO.deleteWard(deptId, wardNum);
            AlertUtils.showInfo("Ward deleted successfully.");
        } catch (Exception ex) {
            AlertUtils.showError("Error deleting ward: " + ex.getMessage());
        }
    }

    private void displayWards() {
        try {
            StringBuilder sb = new StringBuilder();
            for (Ward w : WardDAO.getAllWards()) {
                sb.append(w.toString()).append("\n");
            }
            displayArea.setText(sb.toString());
        } catch (Exception ex) {
            AlertUtils.showError("Error loading wards: " + ex.getMessage());
        }
    }
}
