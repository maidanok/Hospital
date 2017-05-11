package by.hospital.dao.mysql;

import by.hospital.dao.AbstractJDBCDao;
import by.hospital.dao.mysql.interfaces.GenericDAOForPrescriptionExecution;
import by.hospital.domain.PrescriptionExecution;
import by.hospital.domain.Staff;
import by.hospital.exception.PersistentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 14.04.2017.
 */
public class MySqlPrescriptionExecutionDao extends AbstractJDBCDao<PrescriptionExecution, Integer> implements GenericDAOForPrescriptionExecution {

    public MySqlPrescriptionExecutionDao(Connection connection) {
        super(connection);

    }

    private class PersistPrescriptionExecution extends PrescriptionExecution {
        public void setPrimaryKey(int id) {
            super.setPrimaryKey(id);
        }
    }

    @Override
    public PrescriptionExecution create() throws PersistentException {
        PrescriptionExecution persistentExecution = new PrescriptionExecution();
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
                "posts.post_name, \n" +
                "staff.login, staff.password, staff.fired\n" +
                "FROM prescription_execution \n" +
                "join person on staff_id=person.person_id\n" +
                "join staff on staff.person_id=prescription_execution.staff_id\n" +
                "join posts on staff.post_id=posts.post_id";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO prescription_execution\n" +
                "(prescription_id, staff_id, prescription_execution_date)\n" +
                "VALUES (?, ?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE prescription_execution \n" +
                "SET  prescription_id=?, staff_id = ?, prescription_execution_date = ?\n" +
                "WHERE prescription_execution_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM prescription_execution WHERE prescription_execution_id = ? ";
    }

    @Override
    protected List<PrescriptionExecution> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<PrescriptionExecution> result = new ArrayList<PrescriptionExecution>();
        try {
            while (resultSet.next()) {
                PersistPrescriptionExecution pPE = new PersistPrescriptionExecution();
                pPE.setPrescriptionID(resultSet.getInt("prescription_id"));
                pPE.setPrimaryKey(resultSet.getInt("prescription_execution_id"));
                pPE.setPrescriptionExecutionDate(resultSet.getDate("prescription_execution_date"));
                Staff staff= new Staff();
                staff.setPrimaryKey(resultSet.getInt("person_id"));
                staff.setFirstName(resultSet.getString("first_name"));
                staff.setLastName(resultSet.getString("last_name"));
                staff.setMiddleName(resultSet.getString("middle_name"));
                staff.setBirthday(resultSet.getDate("birthday"));
                staff.setPost(resultSet.getString("post_name"));
                pPE.setStaff(staff);
                result.add(pPE);
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
            statement.close();
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
            statement.setInt(4, object.getPrimaryKey());
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }
}
