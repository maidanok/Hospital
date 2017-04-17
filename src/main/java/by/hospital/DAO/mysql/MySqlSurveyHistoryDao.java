package by.hospital.DAO.mysql;

import by.hospital.DAO.AbstractJDBCDao;
import by.hospital.DAO.DaoFactory;
import by.hospital.domain.SickList;
import by.hospital.domain.SurveyHistory;
import by.hospital.exception.PersistentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pasha on 13.04.2017.
 */
public class MySqlSurveyHistoryDao extends AbstractJDBCDao<SurveyHistory, Integer> {

    public MySqlSurveyHistoryDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
        addRelation(SurveyHistory.class, "sickList");
        addRelation(SurveyHistory.class, "staff");
        addRelation(SurveyHistory.class, "diagnose");
    }

    private class PersistSurveyHistory extends SurveyHistory {
        public void setPrimaryKey(int id) {
            super.setPrimaryKey(id);
        }
    }

    @Override
    public SurveyHistory create() throws PersistentException {
        SurveyHistory surveyHistory = new SurveyHistory();
        return surveyHistory;
    }

    @Override
    protected String getPrimaryKeyQuery() {
        return "survey_history_id";
    }

    @Override
    protected String getSelectedQuery() {
        return "SELECT\n" +
                "sick_list_id,\n" +
                "survey_history_id, survey_date, survey_description,\n" +
                "survey_history.diagnose_id, diagnose_name, therapy, \n" +
                "staff.person_id, posts.post_name, staff.login, staff.password, staff.fired, person.first_name, person.last_name, person.middle_name, person.birthday, person.sex, person.address, person.passport_number\n" +
                "FROM survey_history join diagnose on survey_history.diagnose_id=diagnose.diagnose_id\n" +
                "join staff on survey_history.staff_id=staff.person_id\n" +
                "join person on staff.person_id=person.person_id\n" +
                "join posts on staff.post_id=posts.post_id";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO survey_history\n" +
                "(sick_list_id, diagnose_id, staff_id, survey_date, survey_description)\n" +
                "VALUES (?, ?, ?, ?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE survey_history \n" +
                "SET sick_list_id = ?, diagnose_id = ?, staff_id = ?, survey_date = ?, survey_description = ?\n" +
                "WHERE survey_history_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM staff WHERE staff_id = ? ;";
    }

    @Override
    protected List<SurveyHistory> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<SurveyHistory> result = new ArrayList<SurveyHistory>();
        try {
            while (resultSet.next()) {
                PersistSurveyHistory sh = new PersistSurveyHistory();
                sh.setPrimaryKey(resultSet.getInt("survey_history_id"));
                sh.setSickList((SickList) getDependence(SickList.class, resultSet.getInt("sick_list_id")));
                sh.setSurveyDate(resultSet.getDate("survey_date"));
                sh.setDescription(resultSet.getString("survey_description"));

                sh.getDiagnose().setPrimaryKey(resultSet.getInt("diagnose_id"));
                sh.getDiagnose().setDiagnoseName(resultSet.getString("diagnose_name"));
                sh.getDiagnose().setTherapy(resultSet.getString("therapy"));

                sh.getStaff().setPrimaryKey(resultSet.getInt("person_id"));
                sh.getStaff().setPost(resultSet.getString("post_name"));
                sh.getStaff().setLogin(resultSet.getString("login"));
                sh.getStaff().setPassword(resultSet.getString("password"));
                sh.getStaff().setFired(resultSet.getBoolean("fired"));
                sh.getStaff().setFirstName(resultSet.getString("first_name"));
                sh.getStaff().setLastName(resultSet.getString("last_name"));
                sh.getStaff().setMiddleName(resultSet.getString("middle_name"));
                sh.getStaff().setBirthday(resultSet.getDate("birthday"));
                sh.getStaff().setSex(resultSet.getString("sex"));
                sh.getStaff().setAddress(resultSet.getString("address"));
                sh.getStaff().setPassportNumber(resultSet.getString("passport_number"));
                result.add(sh);

            }
        } catch (Exception e) {
            throw new PersistentException(e);
        }
        return result;
    }


    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, SurveyHistory object) throws PersistentException {
        try {
            int staffID = (object.getStaff() == null || object.getStaff().getPrimaryKey() == null) ? -1
                    : object.getStaff().getPrimaryKey();
            int sickListID = (object.getSickList() == null || object.getSickList().getPrimaryKey() == null) ? -1
                    : object.getSickList().getPrimaryKey();
            int diagnoseID = (object.getDiagnose() == null || object.getDiagnose().getPrimaryKey() == null) ? -1
                    : object.getDiagnose().getPrimaryKey();
            statement.setInt(1, sickListID);
            statement.setInt(2, diagnoseID);
            statement.setInt(3, staffID);
            statement.setDate(4, convert(object.getSurveyDate()));
            statement.setString(5, object.getDescription());
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, SurveyHistory object) throws PersistentException {
        try {
            int staffID = (object.getStaff() == null || object.getStaff().getPrimaryKey() == null) ? -1
                    : object.getStaff().getPrimaryKey();
            int sickListID = (object.getSickList() == null || object.getSickList().getPrimaryKey() == null) ? -1
                    : object.getSickList().getPrimaryKey();
            int diagnoseID = (object.getDiagnose() == null || object.getDiagnose().getPrimaryKey() == null) ? -1
                    : object.getDiagnose().getPrimaryKey();
            statement.setInt(1, sickListID);
            statement.setInt(2, diagnoseID);
            statement.setInt(3, staffID);
            statement.setDate(4, convert(object.getSurveyDate()));
            statement.setString(5, object.getDescription());
            statement.setInt(6, object.getPrimaryKey());
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }
}
