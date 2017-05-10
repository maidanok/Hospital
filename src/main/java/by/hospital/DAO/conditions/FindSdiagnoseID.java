package by.hospital.DAO.conditions;

/**
 * Created by Admin on 06.05.2017.
 */
public class FindSdiagnoseID extends Condition {
    public FindSdiagnoseID(int value){
        super(value);
    }
    @Override
    public String getValue() {
        return " WHERE s_diagnose_id = " + value + ";";
    }
}
