package by.hospital.DAO.mysql;

import by.hospital.DAO.AbstractJDBCDao;
import by.hospital.DAO.DaoFactory;
import by.hospital.domain.Patient;
import by.hospital.exception.PersistentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pasha on 10.04.2017.
 */
public class MySqlPatientDao extends AbstractJDBCDao<Patient, Integer> {

    private class PersistPatient extends Patient {
        public void setPrimaryKey(int id) {
            super.setPrimaryKey(id);
        }
    }

    public MySqlPatientDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
    }

    @Override
    public Patient create() throws PersistentException {
        Patient patient = new Patient();
        return persist(patient);
    }

    @Override
    protected String getPrimaryKeyQuery() {
        return "person_id";
    }

    @Override
    protected String getSelectedQuery() {
        return "SELECT person_id, first_name, last_name, middle_name, birthday, sex, address, passport_number \n" +
                "FROM person";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO person (first_name, last_name, middle_name, birthday, sex, address, passport_number)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE person SET first_name = ?, last_name = ?, middle_name = ?, birthday = ?, sex = ?, " +
                "address = ?, passport_number = ? \n" +
                "WHERE person_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM person WHERE person_id = ?;";
    }

    @Override
    protected List<Patient> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<Patient> result = new ArrayList<Patient>();
        try {
            while (resultSet.next()) {
                PersistPatient patient = new PersistPatient();
                patient.setPrimaryKey(resultSet.getInt("patient_id"));
                patient.setFirstName(resultSet.getString("first_name"));
                patient.setLastName(resultSet.getString("last_name"));
                patient.setMiddleName(resultSet.getString("middle_name"));
                patient.setBirthday(resultSet.getDate("birthday"));
                patient.setSex(resultSet.getString("sex"));
                patient.setAddress(resultSet.getString("address"));
                patient.setPassportNumber(resultSet.getString("passport_number"));
                result.add(patient);
            }

        } catch (Exception e) {
            throw new PersistentException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Patient object) throws PersistentException {
        try {
            statement.setString(1, object.getFirstName());
            statement.setString(2, object.getLastName());
            statement.setString(3, object.getMiddleName());
            statement.setDate(4, convert(object.getBirthday()));
            statement.setString(5, object.getSex().toString());
            statement.setString(6, object.getAddress());
            statement.setString(7, object.getPassportNumber());
        } catch (Exception e) {
            throw new PersistentException(e);
        }

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Patient object) throws PersistentException {
        try {
            statement.setString(1, object.getFirstName());
            statement.setString(2, object.getLastName());
            statement.setString(3, object.getMiddleName());
            statement.setDate(4, convert(object.getBirthday()));
            statement.setString(5, object.getSex().toString());
            statement.setString(6, object.getAddress());
            statement.setString(7, object.getPassportNumber());
            statement.setInt(8, object.getPrimaryKey());
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }
}
