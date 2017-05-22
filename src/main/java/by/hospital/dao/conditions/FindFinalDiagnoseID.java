package by.hospital.dao.conditions;

/**
 * Created by Admin on 06.05.2017.
 */
public class FindFinalDiagnoseID extends Condition {
    public FindFinalDiagnoseID(int value) {
        super(value);
    }

    @Override
    public String getValue() {
        return " WHERE final_diagnose_id = " + value + ";";
    }
}
