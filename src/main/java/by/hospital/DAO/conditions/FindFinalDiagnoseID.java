package by.hospital.DAO.conditions;

/**
 * Created by Admin on 06.05.2017.
 */
public class FindFinalDiagnoseID implements Condition {
    private int id;

    public FindFinalDiagnoseID(int id) {
        this.id = id;
    }

    @Override
    public String getValue() {
        return " WHERE final_diagnose_id = " + id + ";";
    }
}
