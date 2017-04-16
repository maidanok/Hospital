package by.hospital.DAO.mysql;

import by.hospital.DAO.AbstractJDBCDao;
import by.hospital.DAO.DaoFactory;
import by.hospital.DAO.mysql.interfaces.GenericDAOForStaff;
import by.hospital.domain.Staff;
import by.hospital.exception.PersistentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pasha on 13.04.2017.
 */
public class MySqlStaffDao extends AbstractJDBCDao<Staff, Integer> implements GenericDAOForStaff {

    public MySqlStaffDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
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
                "INSERT INTO person (first_name, last_name, middle_name, birthday, sex, address, passport_number)\n " +
                        "VALUE (?, ?, ?, ?, ?, ?, ?);\n" +
                        "INSERT INTO staff (post_id, login, password, person_id)\n" +
                        "VALUE ((SELECT post_id FROM posts WHERE post_name = ?), ?, ?, " +
                        "(SELECT person_id FROM person WHERE person_id = last_insert_id()));";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE staff \n" +
                "SET post_id = (SELECT post_id FROM posts WHERE post_name = ?), login = ?, password = ?, fired = ?\n" +
                "WHERE staff.person_id = ?;" +
                "UPDATE person \n" +
                "SET first_name = ?, last_name = ?, middle_name = ?, birthday = ?, sex = ?, address = ?, passport_number = ?\n" +
                "WHERE person.person_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM staff WHERE staff.person_id = ? ;" +
                "DELETE FROM person WHERE person.person_id = ? ;";
    }


    @Override
    public List<Staff> getAllForField(Boolean field) throws PersistentException {
        List<Staff> list;
        String sql = getSelectedQuery();
        sql += " WHERE fired = " + field;
        try {
            PreparedStatement statement = super.connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
        return list;
    }

    public void delete(Staff entity) throws PersistentException {
        String sql = getDeleteQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, entity.getPrimaryKey());
            statement.setObject(2, entity.getPrimaryKey());
            int cont = statement.executeUpdate();
            if (cont != 1) {
                throw new PersistentException("On delete modify more then 1 record: " + cont);
            }
            statement.close();
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    @Override
    protected List<Staff> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<Staff> result = new ArrayList<Staff>();
        try {
            while (resultSet.next()) {
                PersistStaff staff = new PersistStaff();
                staff.setPrimaryKey(resultSet.getInt("person.person_id"));
                staff.setFirstName(resultSet.getString("person.first_name"));
                staff.setLastName(resultSet.getString("person.last_name"));
                staff.setMiddleName(resultSet.getString("person.middle_name"));
                staff.setSex(resultSet.getString("person.sex"));
                staff.setAddress(resultSet.getString("person.address"));
                staff.setPassportNumber(resultSet.getString("person.passport_number"));
                staff.setPost(resultSet.getString("posts.post_name"));
                staff.setLogin(resultSet.getString("staff.login"));
                staff.setPassword(resultSet.getString("staff.password"));
                staff.setFired(resultSet.getBoolean("staff.fired"));
                result.add(staff);
            }
        } catch (Exception e) {
            throw new PersistentException(e);
        }
        return result;
    }

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
            throw new PersistentException(e);
        }
    }


    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Staff object) throws PersistentException {
        try {
            statement.setString(1, object.getPost().toString());
            statement.setString(2, object.getLogin());
            statement.setString(3, object.getPassword());
            statement.setBoolean(4, object.isFired());
            statement.setInt(5, object.getPrimaryKey());
            statement.setString(6, object.getFirstName());
            statement.setString(7, object.getLastName());
            statement.setString(8, object.getMiddleName());
            statement.setDate(9, convert(object.getBirthday()));
            statement.setString(10, object.getSex().toString());
            statement.setString(11, object.getPassportNumber());
            statement.setInt(12, object.getPrimaryKey());
        } catch (Exception e) {
            throw new PersistentException(e);
        }

    }


}
