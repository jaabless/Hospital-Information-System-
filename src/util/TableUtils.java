package util;

import dao.DoctorDAO;
import dao.NurseDAO;
import dao.PatientDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import model.Doctor;
import model.Employee;
import model.Nurse;
import model.Patient;
import state.AppState;

import javax.print.Doc;
import java.util.List;

public class TableUtils {

    public static VBox createDoctorTableSection() {
        TableView<Doctor> tableView = new TableView<>();

        // Fetch data from the DoctorDAO
        List<Doctor> doctorList = DoctorDAO.getAllDoctors();
        ObservableList<Doctor> observableDoctorList = FXCollections.observableArrayList(doctorList);
        tableView.setItems(observableDoctorList);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Save to AppState for dynamic updates
        AppState.observableDoctorList = observableDoctorList;
        AppState.doctorTableView = tableView;

        // ID Column
        TableColumn<Doctor, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEmployeeId()));

        // First Name Column
        TableColumn<Doctor, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getFirstName()));

        // Last Name Column
        TableColumn<Doctor, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getLastName()));

        // Department ID Column
        TableColumn<Doctor, Integer> deptCol = new TableColumn<>("Department ID");
        deptCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDepartmentID()));

        // Specialization Column
        TableColumn<Doctor, String> specCol = new TableColumn<>("Specialization");
        specCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getSpecialty()));

        // Salary Column
//        TableColumn<Doctor, Double> salaryCol = new TableColumn<>("Salary");
//        salaryCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getSalary()));

        // Add columns to the table
        tableView.getColumns().addAll(idCol, firstNameCol, lastNameCol, deptCol, specCol);

        VBox tableBox = new VBox(10, tableView);
        tableBox.setPadding(new Insets(20, 0, 0, 0));

        return tableBox;
    }

    //Nurse table
    public static VBox createNurseTableSection() {
        TableView<Nurse> tableView = new TableView<>();

        // Fetch data from the DoctorDAO
        List<Nurse> nurseList = NurseDAO.getAllNurses();
        ObservableList<Nurse> observableNurseList = FXCollections.observableArrayList(nurseList);
        tableView.setItems(observableNurseList);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Save to AppState for dynamic updates
        AppState.observableNurseList = observableNurseList;
        AppState.nurseTableView = tableView;

        // ID Column
        TableColumn<Nurse, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEmployeeId()));

        // First Name Column
        TableColumn<Nurse, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getFirstName()));

        // Last Name Column
        TableColumn<Nurse, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getLastName()));

        // Specialization Column
        TableColumn<Nurse, String> specCol = new TableColumn<>("Rotation");
        specCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getRotation()));

        // Salary Column
        TableColumn<Nurse, Double> salaryCol = new TableColumn<>("Salary");
        salaryCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getSalary()));

        // Department ID Column
        TableColumn<Nurse, Integer> deptCol = new TableColumn<>("Department ID");
        deptCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDepartmentId()));




        // Add columns to the table
        tableView.getColumns().addAll(idCol, firstNameCol, lastNameCol, deptCol, specCol);

        VBox tableBox = new VBox(10, tableView);
        tableBox.setPadding(new Insets(20, 0, 0, 0));

        return tableBox;
    }

    //Patient Table
    public static VBox createPatientTableSection() {
        TableView<Patient> tableView = new TableView<>();

        // Fetch data from the DoctorDAO
        List<Patient> patientList = PatientDAO.getAllPatients();
        ObservableList<Patient> observablePatientList = FXCollections.observableArrayList(patientList);
        tableView.setItems(observablePatientList);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Save to AppState for dynamic updates
        AppState.observablePatientList = observablePatientList;
        AppState.patientTableView = tableView;

        // ID Column
        TableColumn<Patient, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPatientId()));

        // First Name Column
        TableColumn<Patient, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getFirstName()));

        // Last Name Column
        TableColumn<Patient, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getLastName()));

        // Specialization Column
        TableColumn<Patient, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getAddress()));

        // Salary Column
        TableColumn<Patient, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPhone()));


        // Department ID Column
        TableColumn<Patient, Integer> wardCol = new TableColumn<>("Ward ID");
        wardCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getWardId()));




        // Add columns to the table
        tableView.getColumns().addAll(idCol, firstNameCol, lastNameCol, addressCol, phoneCol,wardCol);

        VBox tableBox = new VBox(10, tableView);
        tableBox.setPadding(new Insets(20, 0, 0, 0));

        return tableBox;
    }

}
