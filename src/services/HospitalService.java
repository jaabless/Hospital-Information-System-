package services;

import dao.DoctorDAO;
import dao.NurseDAO;
import dao.PatientDAO;
import model.Doctor;
import model.Nurse;
import model.Patient;

import java.util.List;

public class HospitalService {

    private final PatientDAO patientDAO;
    private final DoctorDAO doctorDAO;
    private final NurseDAO nurseDAO;

    public HospitalService() {
        this.patientDAO = new PatientDAO();
        this.doctorDAO = new DoctorDAO();
        this.nurseDAO = new NurseDAO();
    }

    // Patient Operations
    public void registerPatient(Patient patient) {
        patientDAO.addPatient(patient);
    }

    public List<Patient> listAllPatients() {
        return patientDAO.getAllPatients();
    }

//    public void updatePatientDiagnosis(int patientId, String newDiagnosis) {
//        patientDAO.updatePatientDiagnosis(patientId, newDiagnosis);
//    }

    public void dischargePatient(int patientId) {
        patientDAO.deletePatient(patientId);
    }

    // Doctor Operations
    public void registerDoctor(Doctor doctor) {
        doctorDAO.addDoctor(doctor);
    }

    public List<Doctor> listAllDoctors() {
        return doctorDAO.getAllDoctors();
    }

    // Nurse Operations
    public void registerNurse(Nurse nurse) {
        nurseDAO.addNurse(nurse);
    }

    public List<Nurse> listAllNurses() {
        return nurseDAO.getAllNurses();
    }
}
