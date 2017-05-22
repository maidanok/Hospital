package by.hospital.domain;

import java.util.Date;

/**
 * Created by Pasha on 12.04.2017.
 */
public class PrescriptionExecution implements Entity<Integer> {
    private int prescriptionExecutionID;
    private Staff staff;
    private Date prescriptionExecutionDate;
    private Prescription prescription;

    public PrescriptionExecution() {
        prescription = new Prescription();
        staff = new Staff();
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Prescription getPrescription() {
        return prescription;
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

    public String toString() {
        return getStaff().toString();
    }


}
