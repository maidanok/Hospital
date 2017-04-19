package by.hospital.DAO.mysql;

import by.hospital.DAO.AbstractJDBCDao;
import by.hospital.DAO.DaoFactory;
import by.hospital.DAO.mysql.interfaces.GenericDaoForPrescription;
import by.hospital.domain.Entity;
import by.hospital.domain.Prescription;
import by.hospital.domain.SurveyHistory;
import by.hospital.exception.PersistentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 18.04.2017.
 */
public class MySqlPrescriptionDao extends AbstractJDBCDao <Prescription, Integer> implements GenericDaoForPrescription {
    public MySqlPrescriptionDao(DaoFactory parentFactory, Connection connection) {
        super(parentFactory, connection);
        addRelation(Prescription.class,"surveyHistory");
    }

    @Override
    public Prescription create() throws PersistentException {
        Prescription prescription =new Prescription();
        return prescription;
    }



    private class PersistPrescription extends Prescription {
        public void setPrimaryKey(int id) {
            super.setPrimaryKey(id);
        }
    }

    @Override
    protected String getPrimaryKeyQuery() {
        return "prescription_id";
    }

    @Override
    protected String getSelectedQuery() {
        return "SELECT \n" +
                "    prescription_id, survey_history_id, description, quantity, completed, \n" +
                "    prescription_type_name\n" +
                "FROM\n" +
                "    prescription\n" +
                "JOIN prescription_type on prescription_type.prescription_type_id=prescription.prescription_type_id";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO prescription\n" +
                "(prescription_type_id, survey_history_id, description,quantity)\n" +
                "VALUES(?, ?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE prescription\n" +
                "SET\n" +
                "prescription_id` = ?, prescription_type_id` = ? survey_history_id = ? description = ? quantity` = ?" +
                "WHERE prescription_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM prescription WHERE prescription_id = ?;";
    }

    @Override
    protected List <Prescription> parseResultSet(ResultSet resultSet) throws PersistentException {
        List <Prescription> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                PersistPrescription persistP = new PersistPrescription();
                persistP.setPrimaryKey(resultSet.getInt("prescription_id"));
                persistP.setSurveyHistory((SurveyHistory) getDependence(SurveyHistory.class,resultSet.getInt("survey_history_id")));
                persistP.setDescription(resultSet.getString("description"));
                persistP.setQuantity(resultSet.getInt("quantity"));
                persistP.setCompleted(resultSet.getInt("completed"));
                result.add(persistP);
            }
        } catch (Exception e) {
            throw new PersistentException(e);
        }
        return result;

    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Prescription object) throws PersistentException {
        try {
            statement.setInt(1,object.getPrescriptionType().getId());
            statement.setInt(2,object.getSurveyHistory().getPrimaryKey());
            statement.setString(3, object.getDescription());
            statement.setInt(4,object.getQuantity());
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Prescription object) throws PersistentException {
        try {
            statement.setInt(1,object.getPrescriptionType().getId());
            statement.setInt(2,object.getSurveyHistory().getPrimaryKey());
            statement.setString(3, object.getDescription());
            statement.setInt(4,object.getQuantity());
            statement.setInt(5,object.getPrimaryKey());
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public List<Prescription> getAllNotDone() throws PersistentException {
        List<Prescription> list;
        String sql = getSelectedQuery();
        sql += " WHERE quantity>completed;";
        try {
            PreparedStatement statement = super.connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            list=parseResultSet(resultSet);
        }catch (Exception e) {
            throw new PersistentException(e);
        }
        return list;
    }
}
