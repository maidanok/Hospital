package by.hospital.dao.conditions;

/**
 * Created by Admin on 06.05.2017.
 */
public class FirstNameLike extends Condition {

    public FirstNameLike (String value){
        super(value);
    }

    @Override
    public String getValue() {
        return " where first_name like '" + value + "';";
    }
}
