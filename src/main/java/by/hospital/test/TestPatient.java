package by.hospital.test;

import by.hospital.DAO.mysql.MySqlDaoFactory;
import by.hospital.DAO.mysql.MySqlDiagnoseDao;
import by.hospital.DAO.mysql.MySqlSickListDao;
import by.hospital.DAO.mysql.MySqlStaffDao;
import by.hospital.domain.Diagnose;
import by.hospital.domain.SickList;
import by.hospital.domain.Staff;
import by.hospital.domain.SurveyHistory;
import by.hospital.exception.PersistentException;
import by.hospital.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Pasha on 10.04.2017.
 */
public class TestPatient {
    public static void main(String [] args) throws SQLException, PersistentException, NoSuchFieldException {

        MySqlDaoFactory mySqlDaoFactory = new MySqlDaoFactory();

        ConnectionPool connectionPool=  ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        SurveyHistory surveyHistory = (SurveyHistory) mySqlDaoFactory.getDao(connection,SurveyHistory.class).getByPrimaryKey(Integer.valueOf(1));


        System.out.println(surveyHistory);







        connection.close();
    }
}
