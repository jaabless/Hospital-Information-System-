package model;

public class Patient {
    private int patientId;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private int wardId;
    private int bedNumber;
    private String diagnosis;
    private int doctorId;

    public Patient(int patientId, String firstName, String lastName, String address, String phone,
                   int wardId, int bedNumber, String diagnosis, int doctorId) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.wardId = wardId;
        this.bedNumber = bedNumber;
        this.diagnosis = diagnosis;
        this.doctorId = doctorId;
    }

    // Getters and setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return String.format("| %-4d | %-10s | %-10s | %-10d | %-12s | $%-8.2f |",
                patientId, firstName, lastName, address, phone, wardId,bedNumber,diagnosis,doctorId);
    }
}
