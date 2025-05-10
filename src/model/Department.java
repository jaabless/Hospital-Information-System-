package model;

public class Department {
    private int dept_id;
    private String name;
    private String building;
    private int directorId; // Doctor.employeeId

    public Department(int dept_id, String name, String building, int directorId) {
        this.dept_id = dept_id;
        this.name = name;
        this.building = building;
        this.directorId = directorId;
    }

    // Getters and setters
    public int getDeptId() { return dept_id; }
    public void setDeptId(int dept_id) { this.dept_id = dept_id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBuilding() { return building; }
    public void setBuilding(String building) { this.building = building; }

    public int getDirectorId() { return directorId; }
    public void setDirectorId(int directorId) { this.directorId = directorId; }

    @Override
    public String toString() {
        return String.format("| %-4d | %-10s | %-10s | %-10d|",
                dept_id, name, building, directorId);
    }

}