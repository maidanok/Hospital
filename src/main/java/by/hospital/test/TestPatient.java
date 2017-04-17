package by.hospital.test;

import by.hospital.DAO.mysql.MySqlDaoFactory;
import by.hospital.DAO.mysql.MySqlDiagnoseDao;
import by.hospital.DAO.mysql.MySqlSickListDao;
import by.hospital.DAO.mysql.MySqlStaffDao;
import by.hospital.domain.*;
import by.hospital.exception.PersistentException;
import by.hospital.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Pasha on 10.04.2017.
 */
public class TestPatient {
    public static void main(String [] args) throws SQLException, PersistentException, NoSuchFieldException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        MySqlDaoFactory mySqlDaoFactory = new MySqlDaoFactory();

        Connection connection = connectionPool.getConnection();
        MySqlSickListDao mySqlSickListDao = (MySqlSickListDao) mySqlDaoFactory.getDao(connection,SickList.class);
        MySqlStaffDao mySqlStaffDao = (MySqlStaffDao) mySqlDaoFactory.getDao(connection, Staff.class);
        MySqlDiagnoseDao mySqlDiagnoseDao = (MySqlDiagnoseDao) mySqlDaoFactory.getDao(connection,Diagnose.class);
        Diagnose diagnose = mySqlDiagnoseDao.getByPrimaryKey(5);
        SickList sickList = mySqlSickListDao.getByPrimaryKey(7);
        List<Staff> staff = mySqlStaffDao.getAllForField(false);



        System.out.println(staff);



    }
}
