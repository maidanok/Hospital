package by.hospital.domain;

import by.hospital.domain.enumeration.PrescriptionType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pasha on 12.04.2017.
 */
public class Prescription implements Entity<Integer> {
    private int prescriptionID;
    private PrescriptionType prescriptionType;
    private SurveyHistory surveyHistory;
    private String description;
    private int quantity;
    private List<PrescriptionExecution> executions = new ArrayList<>();

    public List<PrescriptionExecution> getExecutions() {
        return executions;
    }

    public void setExecutions(List<PrescriptionExecution> executions) {
        this.executions = executions;
    }

    @Override
    public Integer getPrimaryKey() {
        return prescriptionID;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        prescriptionID = primaryKey;
    }

    public PrescriptionType getPrescriptionType() {
        return prescriptionType;
    }

    public void setPrescriptionType(String prescriptionType) {
        this.prescriptionType = PrescriptionType.valueOf(prescriptionType);
    }

    public SurveyHistory getSurveyHistory() {
        return surveyHistory;
    }

    public void setSurveyHistory(SurveyHistory surveyHistory) {
        this.surveyHistory = surveyHistory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Prescription ").append(getPrescriptionType()).append("\n");
        stringBuilder.append(getSurveyHistory()).append("\n");
        stringBuilder.append(getDescription()).append("\n");
        return stringBuilder.toString();
    }
}
