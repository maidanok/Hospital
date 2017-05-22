package by.hospital.dao.conditions;

import java.util.Date;

/**
 * Created by Admin on 07.05.2017.
 */
public class FirstNameAndDateIN extends Condition {
    private String name;
    private Date dateIn;

    public FirstNameAndDateIN(String name, Date dateIn) {
        super();
        this.name = name;
        this.dateIn = dateIn;
    }

    @Override
    public String getValue() {
        return " WHERE first_name like '" + name + "%' AND date_in = '" + dateIn + "';";
    }
}
