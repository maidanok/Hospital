package by.hospital.dao.mysql;

import by.hospital.dao.AbstractJDBCDao;
import by.hospital.dao.mysql.interfaces.GenericDAOForStaff;
import by.hospital.domain.Staff;
import by.hospital.exception.PersistentException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Pasha on 13.04.2017.
 */
public class MySqlStaffDao extends AbstractJDBCDao<Staff, Integer> implements GenericDAOForStaff {

    private Logger logger = Logger.getLogger(MySqlStaffDao.class);

    public MySqlStaffDao(Connection connection) {
        super(connection);
    }


    private class PersistStaff extends Staff {
        public void setPrimaryKey(int id) {
            super.setPrimaryKey(id);
        }
    }

    @Override
    public Staff create() throws PersistentException {
        Staff staff = new Staff();
        return staff;
    }

    @Override
    protected String getPrimaryKeyQuery() {
        return "staff.person_id";
    }

    @Override
    protected String getSelectedQuery() {
        return "SELECT  person.person_id, person.first_name, person.last_name, person.middle_name, person.birthday, \n" +
                "person.sex, person.address, person.passport_number,\n" +
                "posts.post_name, staff.login, staff.password, staff.fired \n" +
                "FROM staff JOIN posts ON posts.post_id=staff.post_id\n" +
                "JOIN person on person.person_id=staff.person_id";
    }

    @Override
    protected String getCreateQuery() {
        return
                "INSERT INTO hospital.person (first_name, last_name, middle_name, birthday, sex, address, passport_number)\n" +
                "VALUE (?, ?, ?, ?, ?, ?, ?);\n" +
                "INSERT INTO hospital.staff (post_id, login, password, person_id)\n" +
                "VALUE ((SELECT post_id FROM posts WHERE post_name = ?), ?, ?,\n" +
                "(SELECT person_id FROM person WHERE person_id = last_insert_id()));";
    }

    @Override
    protected String getUpdateQuery() {
        return
                "UPDATE staff, person \n" +
                "SET post_id = ?, login = ?, password = ?, fired = ?,\n" +
                "first_name = ?, last_name = ?, middle_name = ?, birthday = ?, sex = ?, address = ?, passport_number = ?\n" +
                "WHERE staff.person_id = person.person_id and staff.person_id = ? ;";
    }

    @Override
    protected String getDeleteQuery() {
        return
                "DELETE FROM staff WHERE person_id = ?;\n" +
                "DELETE FROM person WHERE person_id = ?; ";
    }

    public Staff persist(Staff entity) throws PersistentException {
        if (!entity.getPrimaryKey().equals(0)) {
            throw new PersistentException("Object is already persist.");
        }
        Staff persistInstanse;
        String sql = getCreateQuery();

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForInsert(statement, entity);
            int count = statement.executeUpdate();
            connection.commit();
            if (count != 1) {
                throw new PersistentException("On persist modify more then 1 record " + count);
            }
            connection.setAutoCommit(true);
            statement.close();
        } catch (Exception e) {
            logger.error("Error" + e.getLocalizedMessage());
            logger.error(sql);
        }

        //получаем только что вставленную запись
        sql = getSelectedQuery() + " WHERE " + getPrimaryKeyQuery() + " = LAST_INSERT_ID();";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<Staff> list = parseResultSet(resultSet);
            if ((list == null) || (list.size() != 1)) {
                throw new PersistentException("Exception on findByPrimaryKey new persist data. " + list.size());
            }
            persistInstanse = list.iterator().next();
            statement.close();
        } catch (Exception e) {
            logger.error("Error" + e.getLocalizedMessage());
            throw new PersistentException(e);
        }

        return persistInstanse;
    }

    public void delete(Staff staff) {
        String sql = getDeleteQuery();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement1 = connection.prepareStatement(sql);
            statement1.setInt(1, staff.getPrimaryKey());
            statement1.setInt(2, staff.getPrimaryKey());
            int cont = statement1.executeUpdate();
            connection.commit();
            if (cont != 1) {
                throw new PersistentException("On delete modify more then 1 record: " + cont);
            }
            statement1.close();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            logger.error("Error delete Staff " + e.getLocalizedMessage());
        }
    }


    public void update(Staff entity) throws PersistentException {

        String sql = getUpdateQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForUpdate(statement, entity);
            int count = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            logger.error("Error" + e.getLocalizedMessage());
            throw new PersistentException(e);
        }
    }

    @Override
    protected List<Staff> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<Staff> result = new ArrayList<Staff>();
        try {
            while (resultSet.next()) {
                PersistStaff staff = new PersistStaff();
                staff.setPrimaryKey(resultSet.getInt("person_id"));
                staff.setFirstName(resultSet.getString("first_name"));
                staff.setLastName(resultSet.getString("last_name"));
                staff.setMiddleName(resultSet.getString("middle_name"));
                staff.setSex(resultSet.getString("sex"));
                staff.setAddress(resultSet.getString("address"));
                staff.setPassportNumber(resultSet.getString("passport_number"));
                staff.setPost(resultSet.getString("post_name"));
                staff.setLogin(resultSet.getString("login"));
                staff.setPassword(resultSet.getString("password"));
                staff.setFired(resultSet.getBoolean("fired"));
                staff.setBirthday(resultSet.getDate("birthday"));
                result.add(staff);
            }
        } catch (Exception e) {
            logger.error("Error" + e.getLocalizedMessage());
            throw new PersistentException(e);
        }
        return result;
    }

    /*               "START TRANSACTION;\n" +
                            "INSERT INTO person (first_name, last_name, middle_name, birthday, sex, address, passport_number)\n" +
                            "VALUE ('1', '2', '3', '2010-11-11', 'MALE', '12', '123');\n" +
                            "INSERT INTO staff (post_id, login, password, person_id)\n" +
                            "VALUE ((SELECT post_id FROM posts WHERE post_name = 'ADMINISTRATOR'), '1', '1',\n" +
                            "(SELECT person_id FROM person WHERE person_id = last_insert_id()));\n" +
                            "COMMIT;";*/
    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Staff object) throws PersistentException {
        try {
            statement.setString(1, object.getFirstName());
            statement.setString(2, object.getLastName());
            statement.setString(3, object.getMiddleName());
            statement.setDate(4, convert(object.getBirthday()));
            statement.setString(5, object.getSex().toString());
            statement.setString(6, object.getAddress());
            statement.setString(7, object.getPassportNumber());
            statement.setString(8, object.getPost().toString());
            statement.setString(9, object.getLogin());
            statement.setString(10, object.getPassword());
        } catch (Exception e) {
            logger.error("Error" + e.getLocalizedMessage());
            throw new PersistentException(e);
        }
    }

    /*"START TRANSACTION;" +
            "UPDATE staff\n" +
            "SET login = ?, password = ?, fired = ?\n" +
            "WHERE person_id = ?;" +
            "UPDATE person \n" +
            "SET first_name = ?, last_name = ?, middle_name = ?, birthday = ?, sex = ?, address = ?, passport_number = ?\n" +
            "WHERE person_id = ?;\n" +
            "COMMIT;";*/
    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Staff object) throws PersistentException {
        try {
            statement.setInt(1, object.getPost().getID());
            statement.setString(2, object.getLogin());
            statement.setString(3, object.getPassword());
            statement.setBoolean(4, object.isFired());

            statement.setString(5, object.getFirstName());
            statement.setString(6, object.getLastName());
            statement.setString(7, object.getMiddleName());
            statement.setDate(8, convert(object.getBirthday()));
            statement.setString(9, object.getSex().toString());
            statement.setString(10, object.getAddress());
            statement.setString(11, object.getPassportNumber());
            statement.setInt(12, object.getPrimaryKey());
        } catch (Exception e) {
            logger.error("Error" + e.getLocalizedMessage());
            throw new PersistentException(e);
        }
    }

}
