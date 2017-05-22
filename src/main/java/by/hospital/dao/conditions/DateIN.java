package by.hospital.dao.conditions;

import java.util.Date;

/**
 * Created by Admin on 07.05.2017.
 */
public class DateIN extends Condition {
    private Date dateIn;

    public DateIN(Date dateIn) {
        super();
        this.dateIn = dateIn;
    }

    @Override
    public String getValue() {
        return " WHERE date_in = '" + dateIn + "';";
    }
}
