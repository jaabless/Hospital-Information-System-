package state;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.Doctor;
import model.Nurse;
import model.Patient;

public class AppState {
    public static ObservableList<Doctor> observableDoctorList;
    public static TableView<Doctor> doctorTableView;

    public static ObservableList<Nurse> observableNurseList;
    public static TableView<Nurse> nurseTableView;

    public static ObservableList<Patient> observablePatientList;
    public static TableView<Patient> patientTableView;
}
