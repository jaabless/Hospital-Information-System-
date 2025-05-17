
// NurseUI.java
package ui;

import dao.DoctorDAO;
import dao.NurseDAO;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Doctor;
import model.Nurse;
import state.AppState;
import util.TableUtils;
import util.UiUtils;

public class NurseUI {
    private VBox view;
    public NurseUI() {
        HBox topControls = UiUtils.createTopControls_Nurse();
        VBox tableSection = TableUtils.createNurseTableSection();
        view = new VBox();
        view.getChildren().addAll(topControls,tableSection);
    }

    public static void showAddDialog_Nurse() {
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
        TextField rotationField = new TextField();
        TextField departmentIdField = new TextField();

        grid.add(new Label("Nurse ID:"), 0, 0); grid.add(idField, 1, 0);
        grid.add(new Label("First Name:"), 0, 1); grid.add(fnameField, 1, 1);
        grid.add(new Label("Last Name:"), 0, 2); grid.add(lnameField, 1, 2);
        grid.add(new Label("Address:"), 0, 3); grid.add(addressField, 1, 3);
        grid.add(new Label("Phone:"), 0, 4); grid.add(phoneField, 1, 4);
        grid.add(new Label("Rotation:"), 0, 5); grid.add(rotationField, 1, 5);
        grid.add(new Label("Salary:"), 0, 6); grid.add(salaryField, 1, 6);
        grid.add(new Label("Department ID:"), 0, 7); grid.add(departmentIdField, 1, 7);


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
                        rotationField.getText().trim().isEmpty() ||
                        departmentIdField.getText().trim().isEmpty()) {

                    AlertUtils.showError("All fields are required. Please fill in all fields.");
                    return null;
                }

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
                    AppState.observableNurseList.setAll(NurseDAO.getAllNurses());
                    AlertUtils.showInfo("Doctor added successfully.");

                } catch (Exception ex) {
                    AlertUtils.showError("Error adding doctor: " + ex.getMessage());
                }
            }
            return null;
        });
    }


    public VBox getView() {
        return view;
    }
}
