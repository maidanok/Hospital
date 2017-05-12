package by.hospital.service;

import by.hospital.dao.mysql.MySqlDaoFactory;

/**
 * Created by Pasha on 12.05.2017.
 */
public class DateConvertor {
    public static DateConvertor instance = null;
    private DateConvertor(){

    }
    public java.sql.Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    public static DateConvertor getInstanse(){
        if (instance==null){
            instance=new DateConvertor();
        }
        return instance;
    }
}
