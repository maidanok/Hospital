package by.hospital.DAO.conditions;

/**
 * Created by Admin on 06.05.2017.
 */
public class FindSdiagnoseID implements Condition {
    private int id;

    public FindSdiagnoseID(int id) {
        this.id = id;
    }

    @Override
    public String getValue() {
        return " WHERE s_diagnose_id = " + id + ";";
    }
}
