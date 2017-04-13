package by.hospital.test;

import by.hospital.DAO.ManyToOne;
import by.hospital.DAO.mysql.MySqlDaoFactory;
import by.hospital.DAO.mysql.MySqlPatientDao;
import by.hospital.DAO.mysql.MySqlStaffDao;
import by.hospital.domain.*;
import by.hospital.exception.PersistentException;
import by.hospital.pool.ConnectionPool;
import javafx.geometry.Pos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pasha on 10.04.2017.
 */
public class TestPatient {
    public static void main(String [] args) throws SQLException, PersistentException, NoSuchFieldException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        MySqlDaoFactory mySqlDaoFactory = new MySqlDaoFactory();
        Connection connection = connectionPool.getConnection();


        List<Post> staff = new ArrayList<>();
        staff = mySqlDaoFactory.getDao(connection,Post.class).getAll();
        System.out.println(staff);


    }
}
