package by.hospital.domain;

/**
 * Created by Pasha on 12.04.2017.
 */
public class Diagnose implements Entity <Integer> {
    private int diagnoseID;
    private String diagnoseName;
    private String therapy;


    @Override
    public Integer getPrimaryKey() {
        return diagnoseID;
    }

    public String getDiagnoseName() {
        return diagnoseName;
    }

    public void setDiagnoseName(String diagnoseName) {
        this.diagnoseName = diagnoseName;
    }

    public String getTherapy() {
        return therapy;
    }

    public void setTherapy(String therapy) {
        this.therapy = therapy;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        diagnoseID=primaryKey;

    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Diagnose [").append(getDiagnoseName()).append("]\n");
        stringBuilder.append("Therapy: ").append(getTherapy()).append("\n");
        return stringBuilder.toString();
    }
}
