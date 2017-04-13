package by.hospital.domain;

import java.util.Date;

/**
 * Created by Pasha on 10.04.2017.
 */
public class SickList implements Entity<Integer> {
    private int sickListID;
    private Patient patient;
    private Date dateIN;
    private Date dateOUT;
    private String room;
    private String symptoms;
    private boolean discharge;


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

    public boolean isDischarge() {
        return discharge;
    }

    public void setDischarge(boolean discharge) {
        this.discharge = discharge;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Sick List [").append(sickListID).append("]\n");
        stringBuilder.append(patient.toString());
        stringBuilder.append("Date in ").append(dateIN).append("\n");
        stringBuilder.append("Date out ").append(dateOUT).append("\n");
        stringBuilder.append("Room ").append(room).append("\n");
        stringBuilder.append("Symptoms ").append(symptoms).append("\n");
        stringBuilder.append("Discharge ").append(discharge ? "выписан" : "не выписан").append("\n");
        return stringBuilder.toString();
    }
}
