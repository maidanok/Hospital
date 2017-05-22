package by.hospital.dao.conditions;

/**
 * Created by Admin on 07.05.2017.
 */
public class SickListID extends Condition {

    public SickListID(int value) {
        super(value);
    }

    @Override
    public String getValue() {
        return " WHERE sick_list.sick_list_id = " + value + ";";
    }
}
