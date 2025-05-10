package model;

public class Doctor extends Employee {
    private String specialty;
    private int departmentID;

    public Doctor(int employeeId, String firstName, String lastName, String address, String phone, String specialty, int departmentID) {
        super(employeeId, firstName, lastName, address, phone);
        this.specialty = specialty;
        this.departmentID = departmentID;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentID = departmentId;
    }

    @Override
    public String toString() {
        return String.format("| %-4d | %-10s | %-10s | %-10d | %-12s | $%-8.2f |",
                employeeId, firstName, lastName, specialty, departmentID);
    }



//    public double getSalary() {
//        return salary;
//    }
//
//    public void setSalary(double salary) {
//        this.salary = salary;
//    }




}
