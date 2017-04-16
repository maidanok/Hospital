package by.hospital.DAO.mysql;

import by.hospital.DAO.AbstractJDBCDao;
import by.hospital.DAO.DaoFactory;
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
public class MySqlPrescriptionExecutionDao extends AbstractJDBCDao<PrescriptionExecution,Integer> {

    public MySqlPrescriptionExecutionDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
        addRelation(PrescriptionExecution.class, "staff");
    }

    private class PersistPrescriptionExecution extends PrescriptionExecution{
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
        return "SELECT prescription_execution_id, staff_id, prescription_execution_date, done FROM prescription_execution";
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

    @Override
    protected List<PrescriptionExecution> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<PrescriptionExecution> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int prescriptionID = resultSet.getInt("prescription_id");
                PersistPrescriptionExecution pPE = new PersistPrescriptionExecution(prescriptionID);
                pPE.setStaff((Staff) getDependence(Staff.class, resultSet.getInt("staff_id")));
                pPE.setPrimaryKey(resultSet.getInt("prescription_execution_id"));
                pPE.setPrescriptionExecutionDate(resultSet.getDate("prescription_execution_date"));
                pPE.setDone(resultSet.getBoolean("done"));
            }
        }catch (Exception e) {
            throw new PersistentException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, PrescriptionExecution object) throws PersistentException {
        try {
            int StaffID = (object.getStaff() == null || object.getStaff().getPrimaryKey() == null) ? -1
                    : object.getStaff().getPrimaryKey();
            statement.setInt(1,object.getPrescriptionID());
            statement.setInt(2,StaffID);
            statement.setDate(3, convert(object.getPrescriptionExecutionDate()));
            statement.setBoolean(4,object.isDone());
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, PrescriptionExecution object) throws PersistentException {
        try {
            int StaffID = (object.getStaff() == null || object.getStaff().getPrimaryKey() == null) ? -1
                    : object.getStaff().getPrimaryKey();
            statement.setInt(1,object.getPrescriptionID());
            statement.setInt(2,StaffID);
            statement.setDate(3, convert(object.getPrescriptionExecutionDate()));
            statement.setBoolean(4,object.isDone());
            statement.setInt(5,object.getPrimaryKey());
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }
}
