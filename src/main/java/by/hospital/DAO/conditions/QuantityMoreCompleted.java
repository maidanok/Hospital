package by.hospital.DAO.conditions;

/**
 * Created by Admin on 06.05.2017.
 */
public class QuantityMoreCompleted implements Condition {

    @Override
    public String getValue() {
        return " WHERE quantity>completed;";
    }
}
