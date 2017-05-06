package by.hospital.DAO.conditions;

/**
 * Created by Admin on 06.05.2017.
 */
public class PersonPersonID implements Condition {
    private int patientID;

    public PersonPersonID(int patientID) {
        this.patientID = patientID;
    }

    @Override
    public String getValue() {
        return " WHERE person.person_id = " + patientID + ";";
    }
}
