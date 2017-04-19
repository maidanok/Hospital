package by.hospital.DAO.mysql;

import by.hospital.DAO.AbstractJDBCDao;
import by.hospital.DAO.DaoFactory;
import by.hospital.domain.Patient;
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
        return "SELECT\n" +
/*пациент*/                "person.person_id, person.first_name, person.last_name, person.middle_name, person.birthday, person.sex, person.address, person.passport_number,\n" +
/*sick_list*/                "sick_list.sick_list_id, date_in, date_out, room, symptoms,\n" +
/*final_diagnose*/                "final_diagnose_id, findiagn.diagnose_name as findiagn_name, findiagn.therapy as findiagn_therapy,\n" +
/*survey_history*/                "survey_history_id, survey_date, survey_description,\n" +
/*diagnose*/                "survey_history.diagnose_id as s_diagnose_id, s_diagnose.diagnose_name as s_diagnose_name, s_diagnose.therapy as s_diagnose_therapy,\n" +
/*staff*/                "staff_id, staff.first_name as staff_fn, staff.last_name as staff_ln, staff.middle_name as staff_mn, staff.birthday as staff_bd, staff.sex as staff_sex, staff.address as staff_addr, staff.passport_number as staff_passport\n" +
                "FROM person\n" +
                "join sick_list  on sick_list.person_id=person.person_id\n" +
                "join diagnose as findiagn on final_diagnose_id = diagnose_id\n" +
                "join survey_history on survey_history.sick_list_id=sick_list.sick_list_id\n" +
                "join diagnose as s_diagnose on survey_history.diagnose_id = s_diagnose.diagnose_id\n" +
                "join person as staff on staff_id=staff.person_id";
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
                sh.setSurveyDate(resultSet.getDate("survey_date"));
                sh.setDescription(resultSet.getString("survey_description"));

                sh.getDiagnose().setPrimaryKey(resultSet.getInt("s_diagnose_id"));
                sh.getDiagnose().setDiagnoseName(resultSet.getString("s_diagnose_name"));
                sh.getDiagnose().setTherapy(resultSet.getString("s_diagnose_therapy"));

                Staff staff = new Staff();
                staff.setPrimaryKey(resultSet.getInt("staff_id"));
                staff.setFirstName(resultSet.getString("staff_fn"));
                staff.setLastName(resultSet.getString("staff_ln"));
                staff.setMiddleName(resultSet.getString("staff_mn"));
                staff.setBirthday(resultSet.getDate("staff_bd"));
                staff.setSex(resultSet.getString("staff_sex"));
                staff.setAddress(resultSet.getString("staff_addr"));
                sh.setStaff(staff);

                SickList sickList = new SickList();
                sickList.setPrimaryKey(resultSet.getInt("sick_list_id"));
                sickList.setDateIN(resultSet.getDate("date_in"));
                sickList.setDateOUT(resultSet.getDate("date_out"));
                sickList.setRoom(resultSet.getString("room"));
                sickList.setSymptoms(resultSet.getString("symptoms"));
                sickList.getFinalDiagnose().setPrimaryKey(resultSet.getInt("final_diagnose_id"));
                sickList.getFinalDiagnose().setDiagnoseName(resultSet.getString("findiagn_name"));
                sickList.getFinalDiagnose().setTherapy(resultSet.getString("findiagn_therapy"));
                sickList.getPatient().setPrimaryKey(resultSet.getInt("person_id"));
                sickList.getPatient().setFirstName(resultSet.getString("first_name"));
                sickList.getPatient().setLastName(resultSet.getString("last_name"));
                sickList.getPatient().setMiddleName(resultSet.getString("middle_name"));
                sickList.getPatient().setBirthday(resultSet.getDate("birthday"));
                sickList.getPatient().setSex(resultSet.getString("sex"));
                sickList.getPatient().setAddress(resultSet.getString("address"));
                sickList.getPatient().setPassportNumber(resultSet.getString("passport_number"));
                sh.setSickList(sickList);

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
