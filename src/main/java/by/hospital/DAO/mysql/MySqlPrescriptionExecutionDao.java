package by.hospital.DAO.mysql;

import by.hospital.DAO.AbstractJDBCDao;
import by.hospital.DAO.DaoFactory;
import by.hospital.DAO.mysql.interfaces.GenericDAOForPrescriptionExecutionDate;
import by.hospital.domain.PrescriptionExecution;
import by.hospital.exception.PersistentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 14.04.2017.
 */
public class MySqlPrescriptionExecutionDao extends AbstractJDBCDao<PrescriptionExecution, Integer> implements GenericDAOForPrescriptionExecutionDate{

    public MySqlPrescriptionExecutionDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
        addRelation(PrescriptionExecution.class, "staff");
    }

    private class PersistPrescriptionExecution extends PrescriptionExecution {
        public PersistPrescriptionExecution(int prescriptionID) {
            super(prescriptionID);
        }

        public void setPrimaryKey(int id) {
            super.setPrimaryKey(id);
        }
    }

    @Override
    public PrescriptionExecution create() throws PersistentException {
        PrescriptionExecution persistentExecution = new PrescriptionExecution(0);
        return persistentExecution;
    }

    @Override
    protected String getPrimaryKeyQuery() {
        return "prescription_execution_id";
    }

    @Override
    protected String getSelectedQuery() {
        return "SELECT prescription_execution_id, prescription_id, prescription_execution_date,\n" +
                "person.person_id, person.first_name, person.last_name, person.middle_name, person.birthday,\n" +
                "person.sex, person.address, person.passport_number,\n" +
                "posts.post_name, staff.login, staff.password, staff.fired\n" +
                "FROM prescription_execution join person on staff_id=person.person_id\n" +
                "join staff on staff.person_id=staff_id\n" +
                "join posts on staff.post_id=posts.post_id ";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO prescription_execution\n" +
                "(prescription_id, staff_id, prescription_execution_date, done)\n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE prescription_execution \n" +
                "SET  prescription_id=?, staff_id = ?, prescription_execution_date = ?, done = ?\n" +
                "WHERE prescription_execution_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM prescription_execution WHERE prescription_execution_id = ? ";
    }

    /*"SELECT prescription_execution_id, prescription_id, prescription_execution_date,\n" +
            "person.person_id, person.first_name, person.last_name, person.middle_name, person.birthday,\n" +
            "person.sex, person.address, person.passport_number,\n" +
            "posts.post_name, staff.login, staff.password, staff.fired\n" +
            "FROM prescription_execution join person on staff_id=person.person_id\n" +
            "join staff on staff.person_id=staff_id\n" +
            "join posts on staff.post_id=posts.post_id ";*/
    @Override
    protected List<PrescriptionExecution> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<PrescriptionExecution> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int prescriptionID = resultSet.getInt("prescription_id");
                PersistPrescriptionExecution pPE = new PersistPrescriptionExecution(prescriptionID);
                pPE.setPrimaryKey(resultSet.getInt("prescription_execution_id"));
                pPE.setPrescriptionExecutionDate(resultSet.getDate("prescription_execution_date"));

                pPE.getStaff().setPrimaryKey(resultSet.getInt("person_id"));
                pPE.getStaff().setPost(resultSet.getString("post_name"));
                pPE.getStaff().setFirstName(resultSet.getString("first_name"));
                pPE.getStaff().setLastName(resultSet.getString("last_name"));
                pPE.getStaff().setMiddleName(resultSet.getString("middle_name"));
                pPE.getStaff().setSex(resultSet.getString("sex"));
                pPE.getStaff().setBirthday(resultSet.getDate("birthday"));
                pPE.getStaff().setAddress(resultSet.getString("address"));
                pPE.getStaff().setPassportNumber(resultSet.getString("passport_number"));
                pPE.getStaff().setFired(resultSet.getBoolean("fired"));
                pPE.getStaff().setLogin(resultSet.getString("login"));
                pPE.getStaff().setPassword(resultSet.getString("password"));
            }
        } catch (Exception e) {
            throw new PersistentException(e);
        }
        return result;
    }

    public List<PrescriptionExecution> getAllFromPrescription(int prescriptionPK) throws PersistentException {
        List<PrescriptionExecution> list;
        String sql = getSelectedQuery();
        sql += "WHERE prescription_id = " + prescriptionPK + ";";
        try {
            PreparedStatement statement = super.connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, PrescriptionExecution object) throws PersistentException {
        try {
            int StaffID = (object.getStaff() == null || object.getStaff().getPrimaryKey() == null) ? -1
                    : object.getStaff().getPrimaryKey();
            statement.setInt(1, object.getPrescriptionID());
            statement.setInt(2, StaffID);
            statement.setDate(3, convert(object.getPrescriptionExecutionDate()));
            statement.setBoolean(4, object.isDone());
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, PrescriptionExecution object) throws PersistentException {
        try {
            int StaffID = (object.getStaff() == null || object.getStaff().getPrimaryKey() == null) ? -1
                    : object.getStaff().getPrimaryKey();
            statement.setInt(1, object.getPrescriptionID());
            statement.setInt(2, StaffID);
            statement.setDate(3, convert(object.getPrescriptionExecutionDate()));
            statement.setBoolean(4, object.isDone());
            statement.setInt(5, object.getPrimaryKey());
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }
}
