package by.hospital.dao.conditions;

/**
 * Created by Admin on 06.05.2017.
 */
public class QuantityMoreCompleted extends Condition {

    @Override
    public String getValue() {
        return " WHERE quantity>completed;";
    }
}
