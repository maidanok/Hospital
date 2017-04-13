package by.hospital.domain;

import java.util.Date;

/**
 * Created by Pasha on 12.04.2017.
 */
public class PrescriptionExecution implements Entity<Integer> {
    private int prescriptionExecutionID;
    private Prescription prescription;
    private Date prescriptionExecutionDate;
    private boolean done;
    @Override
    public Integer getPrimaryKey() {
        return prescriptionExecutionID;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        prescriptionExecutionID=primaryKey;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Date getPrescriptionExecutionDate() {
        return prescriptionExecutionDate;
    }

    public void setPrescriptionExecutionDate(Date prescriptionExecutionDate) {
        this.prescriptionExecutionDate = prescriptionExecutionDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
