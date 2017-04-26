package by.hospital.domain;

import java.util.Date;

/**
 * Created by Pasha on 12.04.2017.
 */
public class PrescriptionExecution implements Entity<Integer> {
    private int prescriptionExecutionID;
    private Staff staff;
    private Date prescriptionExecutionDate;
    private int prescriptionID;

    public PrescriptionExecution(){
        staff = new Staff();
    }

    public void setPrescriptionID(int prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public int getPrescriptionID() {
        return prescriptionID;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public Integer getPrimaryKey() {
        return prescriptionExecutionID;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        prescriptionExecutionID = primaryKey;
    }

    public Date getPrescriptionExecutionDate() {
        return prescriptionExecutionDate;
    }

    public void setPrescriptionExecutionDate(Date prescriptionExecutionDate) {
        this.prescriptionExecutionDate = prescriptionExecutionDate;
    }

    public String toString(){
        return getStaff().toString();
    }


}
