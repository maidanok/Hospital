package by.hospital.DAO.conditions;

/**
 * Created by Admin on 06.05.2017.
 */
public class PersonPersonID extends Condition {

    public PersonPersonID(int value){
        super(value);
    }

    @Override
    public String getValue() {
        return " WHERE person.person_id = " + value + ";";
    }
}
