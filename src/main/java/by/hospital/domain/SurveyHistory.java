package by.hospital.domain;

import java.util.Date;

/**
 * Created by Pasha on 12.04.2017.
 */
public class SurveyHistory implements Entity <Integer>{
    private int surveyHistoryID;
    private SickList sickList;
    private Diagnose diagnose;
    private Staff staff;
    private Date surveyDate;
    private String description;

    public SurveyHistory(){
        sickList = new SickList();
        diagnose = new Diagnose();
        staff=new Staff();
    }
    @Override
    public Integer getPrimaryKey() {
        return surveyHistoryID;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        surveyHistoryID=primaryKey;
    }

    public SickList getSickList() {
        return sickList;
    }

    public void setSickList(SickList sickList) {
        this.sickList = sickList;
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Date getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(Date surveyDate) {
        this.surveyDate = surveyDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SurveyHistory №").append(getPrimaryKey()).append("\n");
        stringBuilder.append("Date ").append(getSurveyDate()).append("\n");
        stringBuilder.append("Врач ").append(getStaff().getFirstName()).append(" ").append(getStaff().getLastName()).append("\n");
        stringBuilder.append("Осмотрел пациента").append(getSickList().getPatient()).append("\n");
        stringBuilder.append(getDiagnose()).append("\n");
        stringBuilder.append("Description: ").append(getDescription()).append("\n");

        return stringBuilder.toString();
    }
}
