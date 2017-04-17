package by.hospital.test;

import by.hospital.DAO.mysql.MySqlDaoFactory;
import by.hospital.domain.Diagnose;
import by.hospital.domain.SickList;
import by.hospital.domain.Staff;
import by.hospital.domain.SurveyHistory;
import by.hospital.exception.PersistentException;
import by.hospital.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Created by Pasha on 10.04.2017.
 */
public class TestPatient {
    public static void main(String [] args) throws SQLException, PersistentException, NoSuchFieldException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        MySqlDaoFactory mySqlDaoFactory = new MySqlDaoFactory();
        Connection connection = connectionPool.getConnection();

        Staff staff = (Staff) mySqlDaoFactory.getDao(connection,Staff.class).getByPrimaryKey(Integer.valueOf(25));
        System.out.println(staff);

        SickList sickList = (SickList) mySqlDaoFactory.getDao(connection,SickList.class).getByPrimaryKey(Integer.valueOf(14));
        System.out.println(sickList);

        Diagnose diagnose = (Diagnose) mySqlDaoFactory.getDao(connection,Diagnose.class).getByPrimaryKey(Integer.valueOf(2));

        System.out.println(diagnose);


        SurveyHistory surveyHistory= (SurveyHistory) mySqlDaoFactory.getDao(connection,SurveyHistory.class).getByPrimaryKey(Integer.valueOf(1));

        surveyHistory.setStaff(staff);
        surveyHistory.setSurveyDate(Date.valueOf(LocalDate.now()));
        surveyHistory.setDescription("проверочка");
        surveyHistory.setPrimaryKey(0);

        surveyHistory = (SurveyHistory) mySqlDaoFactory.getDao(connection,SurveyHistory.class).persist(surveyHistory);
    }
}
