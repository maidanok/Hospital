package by.hospital.dao.conditions;

/**
 * Created by Admin on 14.05.2017.
 */
public class NotFieldStaff extends Condition {
    boolean value;

    public NotFieldStaff(boolean field) {
        value = field;
    }

    @Override
    public String getValue() {
        return " WHERE fired = " + value;
    }
}
