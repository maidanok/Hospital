//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package by.hospital.dao.mysql;

import by.hospital.dao.DaoFactory;
import by.hospital.dao.GenericDAO;
import by.hospital.dao.mysql.interfaces.*;
import by.hospital.domain.*;
import by.hospital.exception.PersistentException;
import by.hospital.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class MySqlDaoFactory implements DaoFactory<Connection> {
    Logger logger = Logger.getLogger(MySqlDaoFactory.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Map<Class, DaoCreator> creators = new HashMap();
    private static MySqlDaoFactory instance = null;

    public Connection getContext() throws PersistentException {
        try {
            return this.connectionPool.getConnection();
        } catch (Exception var2) {
            logger.error("getContext"+var2.getLocalizedMessage());
            throw new PersistentException(var2);
        }
    }

    public GenericDAO getDao(Connection connection, Class dtoClass) throws PersistentException {
        DaoCreator creator = (DaoCreator) this.creators.get(dtoClass);
        if (creator == null) {
            logger.error("dao object for " + dtoClass + "not found.");
            throw new PersistentException("dao object for " + dtoClass + "not found.");
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

