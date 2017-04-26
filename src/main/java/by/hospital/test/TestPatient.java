package by.hospital.test;

import by.hospital.DAO.mysql.MySqlDaoFactory;
import by.hospital.domain.PrescriptionExecution;
import by.hospital.exception.PersistentException;
import by.hospital.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Pasha on 10.04.2017.
 */
public class TestPatient {
    public static void main(String [] args) throws SQLException, PersistentException, NoSuchFieldException {

        MySqlDaoFactory mySqlDaoFactory = new MySqlDaoFactory();

        ConnectionPool connectionPool=  ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PrescriptionExecution prescriptionExecution =
                (PrescriptionExecution) mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(),
                        PrescriptionExecution.class).getByPrimaryKey(Integer.valueOf(1));
                System.out.println(prescriptionExecution.getPrescriptionExecutionDate());

        //mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(),PrescriptionExecution.class).persist(prescriptionExecution);







        connection.close();
    }
}
