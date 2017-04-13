package by.hospital.DAO.mysql;

import by.hospital.DAO.AbstractJDBCDao;
import by.hospital.DAO.DaoFactory;
import by.hospital.domain.Patient;
import by.hospital.domain.SickList;
import by.hospital.exception.PersistentException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pasha on 10.04.2017.
 */
public class MySqlSickListDao extends AbstractJDBCDao<SickList, Integer> {

    private class PersistSickList extends SickList {
        public void setPrimaryKey(int id) {
            super.setPrimaryKey(id);
        }
    }

    public MySqlSickListDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
        addRelation(SickList.class, "patient");
    }

    @Override
    public SickList create() throws PersistentException {
        SickList sickList = new SickList();
        return sickList;
    }

    @Override
    protected String getPrimaryKeyQuery() {
        return "sick_list_id";
    }

    @Override
    protected String getSelectedQuery() {
        return "SELECT sick_list_id, patient_id, date_in, date_out, room, symptoms, discharge\n" +
                "FROM hospital.sick_list ";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO hospital.sick_list\n" +
                "(patient_id, date_in, date_out, room, symptoms, discharge)\n" +
                "VALUES (?, ?, ?, ?, ?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE hospital.sick_list \n" +
                "SET patient_id = ?, date_in = ?, date_out = ?, room = ?, symptoms = ?, discharge = ?\n" +
                "WHERE sick_list_id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM sick_list WHERE sick_list_id = ?";
    }

    @Override
    protected List<SickList> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<SickList> result = new ArrayList<SickList>();
        try {
            while (resultSet.next()) {
                PersistSickList sickList = new PersistSickList();
                sickList.setPrimaryKey(resultSet.getInt("sick_list_id"));
                sickList.setPatient((Patient) getDependence(Patient.class, resultSet.getInt("patient_id")));
                sickList.setDateIN(resultSet.getDate("date_in"));
                sickList.setDateOUT(resultSet.getDate("date_out"));
                sickList.setRoom(resultSet.getString("room"));
                sickList.setSymptoms(resultSet.getString("symptoms"));
                sickList.setDischarge(resultSet.getBoolean("discharge"));
                result.add(sickList);
            }
        } catch (Exception e) {
            throw new PersistentException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, SickList object) throws PersistentException {
        try {
            int patientID = (object.getPatient() == null || object.getPatient().getPrimaryKey() == null) ? 1
                    : object.getPatient().getPrimaryKey();
            statement.setInt(1, patientID);
            statement.setDate(2, convert(object.getDateIN()));
            statement.setDate(3, convert(object.getDateOUT()));
            statement.setString(4, object.getRoom());
            statement.setString(5, object.getSymptoms());
            statement.setBoolean(6, object.isDischarge());
        } catch (Exception e) {
            throw new PersistentException(e);
        }

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, SickList object) throws PersistentException {
        try {
            int patientID = (object.getPatient() == null || object.getPatient().getPrimaryKey() == null) ? -1
                    : object.getPatient().getPrimaryKey();
            statement.setInt(1, patientID);
            statement.setDate(2, convert(object.getDateIN()));
            statement.setDate(3, convert(object.getDateOUT()));
            statement.setString(4, object.getRoom());
            statement.setString(5, object.getSymptoms());
            statement.setBoolean(6, object.isDischarge());
            statement.setInt(7, object.getPrimaryKey());
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }
}
