//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package by.hospital.DAO.mysql;

import by.hospital.DAO.DaoFactory;
import by.hospital.DAO.GenericDAO;
import by.hospital.DAO.DaoFactory.DaoCreator;
import by.hospital.domain.Patient;
import by.hospital.domain.Post;
import by.hospital.domain.SickList;
import by.hospital.exception.PersistentException;
import by.hospital.pool.ConnectionPool;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class MySqlDaoFactory implements DaoFactory<Connection> {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Map<Class, DaoCreator> creators = new HashMap();

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

    public MySqlDaoFactory() {

        //Patient
        this.creators.put(Patient.class, new DaoCreator<Connection>() {
            @Override
            public GenericDAO create(Connection connection) {
                return new MySqlPatientDao(MySqlDaoFactory.this, connection);
            }
        });

        //SickList
        this.creators.put(SickList.class, new DaoCreator<Connection>() {
            @Override
            public GenericDAO create(Connection connection) {
                return new MySqlSickListDao(MySqlDaoFactory.this, connection);
            }
        });

        //Post
        this.creators.put(Post.class, new DaoCreator<Connection>() {
            @Override
            public GenericDAO create(Connection connection) {
                return new MySqlPostDao(MySqlDaoFactory.this, connection);
            }
        });


    }
}
