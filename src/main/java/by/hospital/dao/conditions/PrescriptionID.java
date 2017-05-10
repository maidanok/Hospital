package by.hospital.dao.conditions;

/**
 * Created by Admin on 07.05.2017.
 */
public class PrescriptionID extends Condition {

    public PrescriptionID(int value){
        super(value);
    }
    @Override
    public String getValue() {
        return "WHERE prescription_id = " + value + ";";
    }
}
