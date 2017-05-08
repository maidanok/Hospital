//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package by.hospital.DAO.mysql;

import by.hospital.DAO.DaoFactory;
import by.hospital.DAO.GenericDAO;
import by.hospital.DAO.mysql.interfaces.*;
import by.hospital.domain.*;
import by.hospital.exception.PersistentException;
import by.hospital.pool.ConnectionPool;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class MySqlDaoFactory implements DaoFactory<Connection> {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Map<Class, DaoCreator> creators = new HashMap();
    private static MySqlDaoFactory instance = null;

    public Connection getContext() throws PersistentException {
        try {
            return this.connectionPool.getConnection();
        } catch (Exception var2) {
            throw new PersistentException(var2);
        }
    }

    public GenericDAO getDao(Connection connection, Class dtoClass) throws PersistentException {
        DaoCreator creator = (DaoCreator) this.creators.get(dtoClass);
        if (creator == null) {
            throw new PersistentException("DAO object for " + dtoClass + "not found.");
        } else {
            return creator.create(connection);
        }
    }

    private MySqlDaoFactory() {

        //Patient
        this.creators.put(Patient.class, new DaoCreator<Connection>() {
            @Override
            public GenericDAOForPatient create(Connection connection) {
                return new MySqlPatientDao(connection);
            }
        });

        //SickList
        this.creators.put(SickList.class, new DaoCreator<Connection>() {
            @Override
            public GenericDaoForSickList create(Connection connection) {
                return new MySqlSickListDao(connection);
            }
        });

        //DiagnoseService
        this.creators.put(Diagnose.class, new DaoCreator<Connection>() {
            @Override
            public GenericDAO create(Connection connection) {
                return new MySqlDiagnoseDao(connection);
            }
        });

        //Staff
        this.creators.put(Staff.class, new DaoCreator<Connection>() {
            @Override
            public GenericDAOForStaff create(Connection connection) {
                return new MySqlStaffDao(connection);
            }
        });
        //SurveyHistory
        this.creators.put(SurveyHistory.class, new DaoCreator<Connection>() {
            @Override
            public GenericDAOForSurveyHistory create(Connection connection) {
                return new MySqlSurveyHistoryDao(connection);
            }
        });

        //Prescription
        this.creators.put(Prescription.class, new DaoCreator<Connection>() {
            @Override
            public GenericDaoForPrescription create(Connection connection) {
                return new MySqlPrescriptionDao(connection);
            }
        });

        //PrescriptionExecution
        this.creators.put(PrescriptionExecution.class, new DaoCreator<Connection>() {
            @Override
            public GenericDAOForPrescriptionExecution create(Connection connection) {
                return new MySqlPrescriptionExecutionDao(connection);
            }
        });
    }

    public static MySqlDaoFactory getInstance(){
        if (instance==null){
            instance=new MySqlDaoFactory();
        }
        return instance;
    }
}

