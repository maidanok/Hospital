package by.hospital.DAO.mysql;

import by.hospital.DAO.AbstractJDBCDao;
import by.hospital.DAO.DaoFactory;
import by.hospital.domain.Diagnose;
import by.hospital.domain.SickList;
import by.hospital.domain.Staff;
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
        return "SELECT survey_history_id, sick_list_id, diagnose_id, staff_id, survey_date, survey_description FROM survey_history";
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
                sh.setDiagnose((Diagnose) getDependence(Diagnose.class, resultSet.getInt("diagnose_id")));
                sh.setStaff((Staff) getDependence(Staff.class, resultSet.getInt("staff_id")));
                sh.setSurveyDate(resultSet.getDate("survey_date"));
                sh.setDescription(resultSet.getString("survey_description"));
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
