package by.hospital.dao.conditions;

/**
 * Created by Admin on 07.05.2017.
 */
public class DateOutNotNull extends Condition {
    @Override
    public String getValue() {
        return " WHERE date_out is null;";
    }
}
