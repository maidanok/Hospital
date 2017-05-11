package by.hospital.domain;

import java.util.Date;

/**
 * Created by Pasha on 10.04.2017.
 */
public class SickList implements Entity<Integer> {
    private int sickListID=0;
    private Patient patient;
    private Date dateIN;
    private Date dateOUT;
    private String room;
    private String symptoms;
    private Diagnose finalDiagnose;

    public SickList() {
        patient = new Patient();
        finalDiagnose = new Diagnose();
    }

    public Diagnose getFinalDiagnose() {
        return finalDiagnose;
    }

    public void setFinalDiagnose(Diagnose finalDiagnose) {
        this.finalDiagnose = finalDiagnose;
    }

    @Override
    public Integer getPrimaryKey() {
        return sickListID;
    }

    public void setPrimaryKey(int primaryKey) {
        sickListID = primaryKey;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDateIN() {
        return dateIN;
    }

    public void setDateIN(Date dateIN) {
        this.dateIN = dateIN;
    }

    public Date getDateOUT() {
        return dateOUT;
    }

    public void setDateOUT(Date dateOUT) {
        this.dateOUT = dateOUT;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }


    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Sick List [").append(getPrimaryKey()).append("]\n");
        stringBuilder.append(getPatient().toString()).append("\n");
        stringBuilder.append("Date in ").append(getDateIN()).append("\n");
        stringBuilder.append("Date out ").append(getDateOUT()).append("\n");
        stringBuilder.append("Room ").append(getRoom()).append("\n");
        stringBuilder.append("Symptoms ").append(getSymptoms()).append("\n");
        stringBuilder.append("Discharge ").append(getDateOUT()==null ? "не выписан" : "выписан").append("\n");
        stringBuilder.append("DiagnoseService ").append(getFinalDiagnose()).append("\n");
        return stringBuilder.toString();
    }
}
