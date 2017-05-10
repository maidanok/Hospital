package by.hospital.DAO.mysql;

import by.hospital.DAO.AbstractJDBCDao;
import by.hospital.DAO.mysql.interfaces.GenericDaoForSickList;
import by.hospital.domain.SickList;
import by.hospital.exception.PersistentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pasha on 10.04.2017.
 */
public class MySqlSickListDao extends AbstractJDBCDao<SickList, Integer> implements GenericDaoForSickList {


    private class PersistSickList extends SickList {
        public void setPrimaryKey(int id) {
            super.setPrimaryKey(id);
        }
    }

    public MySqlSickListDao(Connection connection) {
        super(connection);

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
        return "SELECT sick_list_id, date_in, date_out, room, symptoms, \n" +
                "person.person_id, first_name, last_name, middle_name, birthday, sex, address, passport_number,\n" +
                "diagnose_id, diagnose_name, therapy\n" +
                "FROM sick_list JOIN person on person.person_id=sick_list.person_id\n" +
                "JOIN diagnose on diagnose_id=final_diagnose_id";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO hospital.sick_list\n" +
                "(person_id, date_in, date_out, room, symptoms, final_diagnose_id)\n" +
                "VALUES (?, ?, ?, ?, ?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE hospital.sick_list \n" +
                "SET person_id = ?, date_in = ?, date_out = ?, room = ?, symptoms = ?, final_diagnose_id = ?\n" +
                "WHERE sick_list_id = ? ;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM sick_list WHERE sick_list_id = ? ;";
    }

    @Override
    protected List<SickList> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<SickList> result = new ArrayList<SickList>();
        try {
            while (resultSet.next()) {
                PersistSickList sickList = new PersistSickList();
                sickList.setPrimaryKey(resultSet.getInt("sick_list_id"));
                sickList.setDateIN(resultSet.getDate("date_in"));
                sickList.setDateOUT(resultSet.getDate("date_out"));
                sickList.setRoom(resultSet.getString("room"));
                sickList.setSymptoms(resultSet.getString("symptoms"));

                sickList.getPatient().setPrimaryKey(resultSet.getInt("person.person_id"));
                sickList.getPatient().setFirstName(resultSet.getString("first_name"));
                sickList.getPatient().setLastName(resultSet.getString("last_name"));
                sickList.getPatient().setMiddleName(resultSet.getString("middle_name"));
                sickList.getPatient().setBirthday(resultSet.getDate("birthday"));
                sickList.getPatient().setSex(resultSet.getString("sex"));
                sickList.getPatient().setAddress(resultSet.getString("address"));
                sickList.getPatient().setPassportNumber(resultSet.getString("passport_number"));

                sickList.getFinalDiagnose().setPrimaryKey(resultSet.getInt("diagnose_id"));
                sickList.getFinalDiagnose().setDiagnoseName(resultSet.getString("diagnose_name"));
                sickList.getFinalDiagnose().setTherapy(resultSet.getString("therapy"));
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
            int patientID = (object.getPatient() == null || object.getPatient().getPrimaryKey() == null) ? -1
                    : object.getPatient().getPrimaryKey();
            statement.setInt(1, patientID);
            statement.setDate(2, convert(object.getDateIN()));
            statement.setDate(3, convert(object.getDateOUT()));
            statement.setString(4, object.getRoom());
            statement.setString(5, object.getSymptoms());
            statement.setInt(6, object.getFinalDiagnose().getPrimaryKey());
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
            statement.setInt(6, object.getFinalDiagnose().getPrimaryKey());
            statement.setInt(7,object.getPrimaryKey());
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }


}