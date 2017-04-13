package by.hospital.DAO.mysql;

import by.hospital.DAO.AbstractJDBCDao;
import by.hospital.DAO.DaoFactory;
import by.hospital.DAO.mysql.interfaces.GenericDAOForStaff;
import by.hospital.domain.Post;
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
        addRelation(Staff.class, "post");
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
        return "staff_id";
    }

    @Override
    protected String getSelectedQuery() {
        return "SELECT staff_id, post_id, first_name, last_name, middle_name, birthday, sex, login, password, fired FROM staff";
    }


    @Override
    protected String getCreateQuery() {
        return "INSERT INTO staff\n" +
                "(post_id, first_name, last_name, middle_name, birthday, sex, login, password, fired)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE staff \n" +
                "SET post_id = ?, first_name = ?, last_name = ?, middle_name = ?, birthday = ?, sex = ?, login = ?, password = ?, fired = ?\n" +
                "WHERE staff_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM staff WHERE staff_id = ? ;";
    }


    @Override
    public List<Staff> getAllForField(Boolean field) throws PersistentException {
        List <Staff> list;
        String sql = getSelectedQuery();
        sql+= " WHERE fired = "+field;
        try {
            PreparedStatement statement = super.connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        }catch (Exception e) {
            throw new PersistentException(e);
        }
        return list;
    }

    @Override
    protected List<Staff> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<Staff> result = new ArrayList<Staff>();
        try {
            while (resultSet.next()) {
                PersistStaff staff = new PersistStaff();
                staff.setPrimaryKey(resultSet.getInt("staff_id"));
                staff.setPost((Post) getDependence(Post.class, resultSet.getInt("post_id")));
                staff.setFirstName(resultSet.getString("first_name"));
                staff.setLastName(resultSet.getString("last_name"));
                staff.setMiddleName(resultSet.getString("middle_name"));
                staff.setBirthday(resultSet.getDate("birthday"));
                staff.setSex(resultSet.getString("sex"));
                staff.setLogin(resultSet.getString("login"));
                staff.setPassword(resultSet.getString("password"));
                staff.setFired(resultSet.getBoolean("fired"));
                result.add(staff);
            }
        } catch (Exception e) {
            throw new PersistentException(e);
        }
        return result;
    }

    /*
                     "INSERT INTO staff\n" +
                    "(post_id, first_name, last_name, middle_name, birthday, sex, login, password, fired)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
     */
    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Staff object) throws PersistentException {
        try {
            int postID = (object.getPost() == null || object.getPost().getPrimaryKey() == null) ? -1
                    : object.getPost().getPrimaryKey();
            statement.setInt(1, postID);
            statement.setString(2, object.getFirstName());
            statement.setString(3, object.getLastName());
            statement.setString(4, object.getMiddleName());
            statement.setDate(5, convert(object.getBirthday()));
            statement.setString(6, object.getSex().toString());
            statement.setString(7, object.getLogin());
            statement.setString(8, object.getPassword());
            statement.setBoolean(9, object.isFired());
        } catch (Exception e) {
            throw new PersistentException(e);
        }

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Staff object) throws PersistentException {
        try {
            int postID = (object.getPost() == null || object.getPost().getPrimaryKey() == null) ? -1
                    : object.getPost().getPrimaryKey();
            statement.setInt(1, postID);
            statement.setString(2, object.getFirstName());
            statement.setString(3, object.getLastName());
            statement.setString(4, object.getMiddleName());
            statement.setDate(5, convert(object.getBirthday()));
            statement.setString(6, object.getSex().toString());
            statement.setString(7, object.getLogin());
            statement.setString(8, object.getPassword());
            statement.setBoolean(9, object.isFired());
            statement.setInt(10, object.getPrimaryKey());
        } catch (Exception e) {
            throw new PersistentException(e);
        }

    }


}
