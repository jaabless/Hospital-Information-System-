package model;

public class Nurse extends Employee {
    private String rotation;
    private double salary;
    private int departmentId; // since each nurse is assigned to one department

    public Nurse(int employeeId, String firstName, String lastName, String address, String phone, String rotation, double salary, int departmentId) {
        super(employeeId, firstName, lastName, address, phone);
        this.rotation = rotation;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public String getRotation() {
        return rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("| %-4d | %-10s | %-10s | %-10d | %-12s | $%-8.2f |",
                employeeId, firstName, lastName, departmentId, rotation, salary);
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
