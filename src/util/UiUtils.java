package util;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import ui.UIFactory;

import static ui.DoctorUI.*;
import static ui.NurseUI.showAddDialog_Nurse;
import static ui.PatientUI.showAddDialog_Patient;

public class UiUtils {

    public static HBox createTopControls_Doctor() {
        TextField searchField = new TextField();
        searchField.setPromptText("Search Here...");

        ComboBox<String> searchTypeCombo = new ComboBox<>();
        searchTypeCombo.getItems().addAll("ID", "Name", "Department");
        searchTypeCombo.setValue("ID");

        Button searchBtn = UIFactory.createStyledButton("Search");
//        searchBtn.setOnAction(e -> handleSearch(searchField.getText(), searchTypeCombo.getValue()));

        HBox leftBox = new HBox(10, searchField, searchTypeCombo, searchBtn);
        leftBox.setAlignment(Pos.CENTER_LEFT);

        ComboBox<String> actionCombo = new ComboBox<>();
        actionCombo.getItems().addAll("Add", "Update", "Delete");
        actionCombo.setPromptText("Select Action");

        Button actionBtn = UIFactory.createStyledButton("Execute");
        actionBtn.setOnAction(e -> handleAction_Doctor(actionCombo.getValue()));

        HBox rightBox = new HBox(10, actionCombo, actionBtn);
        rightBox.setAlignment(Pos.CENTER_RIGHT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        return new HBox(20, leftBox, spacer, rightBox);
    }

    public static void handleAction_Doctor(String actionType) {
        switch (actionType) {
            case "Add": showAddDialog_Doctor(); break;
            case "Update": showUpdateDialog_Doctor(); break;
            case "Delete": showDeleteDialog_Doctor(); break;
        }
    }

    //Nurse Ui
    public static HBox createTopControls_Nurse() {
        TextField searchField = new TextField();
        searchField.setPromptText("Search Here...");

        ComboBox<String> searchTypeCombo = new ComboBox<>();
        searchTypeCombo.getItems().addAll("ID", "Name", "Department");
        searchTypeCombo.setValue("ID");

        Button searchBtn = UIFactory.createStyledButton("Search");
//        searchBtn.setOnAction(e -> handleSearch(searchField.getText(), searchTypeCombo.getValue()));

        HBox leftBox = new HBox(10, searchField, searchTypeCombo, searchBtn);
        leftBox.setAlignment(Pos.CENTER_LEFT);

        ComboBox<String> actionCombo = new ComboBox<>();
        actionCombo.getItems().addAll("Add", "Update", "Delete");
        actionCombo.setPromptText("Select Action");

        Button actionBtn = UIFactory.createStyledButton("Execute");
        actionBtn.setOnAction(e -> handleAction_Nurse(actionCombo.getValue()));

        HBox rightBox = new HBox(10, actionCombo, actionBtn);
        rightBox.setAlignment(Pos.CENTER_RIGHT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        return new HBox(20, leftBox, spacer, rightBox);
    }

    public static void handleAction_Nurse(String actionType) {
        switch (actionType) {
            case "Add": showAddDialog_Nurse(); break;
//            case "Update": showUpdateDialog_Nurse(); break;
//            case "Delete": showDeleteDialog_Nurse(); break;
        }
    }

    //Patient UI

    public static HBox createTopControls_Patient() {
        TextField searchField = new TextField();
        searchField.setPromptText("Search Here...");

        ComboBox<String> searchTypeCombo = new ComboBox<>();
        searchTypeCombo.getItems().addAll("ID", "Name", "Department");
        searchTypeCombo.setValue("ID");

        Button searchBtn = UIFactory.createStyledButton("Search");
//        searchBtn.setOnAction(e -> handleSearch(searchField.getText(), searchTypeCombo.getValue()));

        HBox leftBox = new HBox(10, searchField, searchTypeCombo, searchBtn);
        leftBox.setAlignment(Pos.CENTER_LEFT);

        ComboBox<String> actionCombo = new ComboBox<>();
        actionCombo.getItems().addAll("Add", "Update", "Delete");
        actionCombo.setPromptText("Select Action");

        Button actionBtn = UIFactory.createStyledButton("Execute");
        actionBtn.setOnAction(e -> handleAction_Patient(actionCombo.getValue()));

        HBox rightBox = new HBox(10, actionCombo, actionBtn);
        rightBox.setAlignment(Pos.CENTER_RIGHT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        return new HBox(20, leftBox, spacer, rightBox);
    }

    public static void handleAction_Patient(String actionType) {
        switch (actionType) {
            case "Add": showAddDialog_Patient(); break;
//            case "Update": showUpdateDialog_Nurse(); break;
//            case "Delete": showDeleteDialog_Nurse(); break;
        }
    }
}
