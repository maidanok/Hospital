package by.hospital.dao.conditions;

/**
 * Created by Admin on 07.05.2017.
 */
public class FindStaffID extends Condition {

    public FindStaffID(int value) {
        super(value);
    }

    @Override
    public String getValue() {
        return " WHERE staff_id = " + value + ";";
    }
}
