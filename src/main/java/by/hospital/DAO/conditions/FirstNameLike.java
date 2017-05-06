package by.hospital.DAO.conditions;

/**
 * Created by Admin on 06.05.2017.
 */
public class FirstNameLike implements Condition {
    private String lastName;

    public FirstNameLike(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getValue() {
        return " where first_name like '" + lastName + "';";
    }
}
