package by.hospital.domain;

/**
 * Created by Pasha on 12.04.2017.
 */
public class Prescription implements Entity<Integer> {
    private int prescriptionID;
    private PrescriptionType prescriptionType;
    private SurveyHistory surveyHistory;
    private String description;

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

    public void setPrescriptionType(PrescriptionType prescriptionType) {
        this.prescriptionType = prescriptionType;
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
