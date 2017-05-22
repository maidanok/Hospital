package by.hospital.service;

/**
 * Created by Pasha on 12.05.2017.
 */
public class DateConvertor {

    public static java.sql.Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }
}
