// Ward.java
package model;

public class Ward {
    private int deptId;    // Foreign key to Department
    private int wardNumber; // Local within department
    private int bedCount;
    private int supervisorId; // Nurse.employeeId

    public Ward(int deptId, int wardNumber, int bedCount, int supervisorId) {
        this.deptId = deptId;
        this.wardNumber = wardNumber;
        this.bedCount = bedCount;
        this.supervisorId = supervisorId;
    }

    // Getters and setters
    public int getDeptId() { return deptId; }
    public void setDeptId(int deptId) { this.deptId = deptId; }

    public int getWardNumber() { return wardNumber; }
    public void setWardNumber(int wardNumber) { this.wardNumber = wardNumber; }

    public int getBedCount() { return bedCount; }
    public void setBedCount(int bedCount) { this.bedCount = bedCount; }

    public int getSupervisorId() { return supervisorId; }
    public void setSupervisorId(int supervisorId) { this.supervisorId = supervisorId; }
}