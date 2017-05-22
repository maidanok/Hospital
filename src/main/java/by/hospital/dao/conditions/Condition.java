package by.hospital.dao.conditions;

/**
 * Created by Admin on 06.05.2017.
 */


public abstract class Condition {

    protected String value;

    public Condition() {
    }

    public Condition(Integer value) {
        this.value = Integer.toString(value);
    }

    public Condition(String value) {
        this.value = value;
    }

    public abstract String getValue();

}
