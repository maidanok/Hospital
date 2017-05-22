package by.hospital.dao.conditions;

/**
 * Created by Pasha on 15.05.2017.
 */
public class PersonFirstName extends Condition {
    private String value;

    public PersonFirstName(String firstName) {
        value = firstName;
    }

    @Override
    public String getValue() {
        return " WHERE person.first_name like '" + value + "%';";
    }
}

