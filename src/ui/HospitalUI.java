package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HospitalUI extends Application {

    private TabPane tabPane;
    private Tab patientTab, doctorTab, nurseTab, departmentTab, wardTab;
    private StackPane dashboard;
    private BorderPane root;

    @Override
    public void start(Stage stage) {
        // Initialize TabPane and Tabs
        tabPane = new TabPane();

        // Create tabs with back button wrapper
        patientTab = createTabWithBack("Patient", new PatientUI().getView(), stage);
        doctorTab = createTabWithBack("Doctor", new DoctorUI().getView(), stage);
        nurseTab = createTabWithBack("Nurse", new NurseUI().getView(), stage);
        departmentTab = createTabWithBack("Department", new DepartmentUI().getView(), stage);
        wardTab = createTabWithBack("Ward", new WardUI().getView(), stage);

        tabPane.getTabs().addAll(patientTab, doctorTab, nurseTab, departmentTab, wardTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Dashboard Buttons
        Button patientBtn = new Button("Go to Patient");
        Button doctorBtn = new Button("Go to Doctor");
        Button nurseBtn = new Button("Go to Nurse");
        Button deptBtn = new Button("Go to Department");
        Button wardBtn = new Button("Go to Ward");

        patientBtn.setOnAction(e -> showTab(patientTab));
        doctorBtn.setOnAction(e -> showTab(doctorTab));
        nurseBtn.setOnAction(e -> showTab(nurseTab));
        deptBtn.setOnAction(e -> showTab(departmentTab));
        wardBtn.setOnAction(e -> showTab(wardTab));

        VBox buttonBox = new VBox(15, patientBtn, doctorBtn, nurseBtn, deptBtn, wardBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20));

        dashboard = new StackPane(buttonBox);

        // Root Layout
        root = new BorderPane();
        root.setCenter(dashboard);

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("Hospital Management Dashboard");
        stage.show();
    }

    // Helper to wrap content with a back button
    private Tab createTabWithBack(String title, javafx.scene.Node content, Stage stage) {
        Button backBtn = new Button("Back to Dashboard");
        backBtn.setOnAction(e -> root.setCenter(dashboard));

        VBox container = new VBox(10, backBtn, content);
        container.setPadding(new Insets(10));

        Tab tab = new Tab(title, container);
        tab.setClosable(false);
        return tab;
    }

    // Show selected tab
    private void showTab(Tab tab) {
        root.setCenter(tabPane);
        tabPane.getSelectionModel().select(tab);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
