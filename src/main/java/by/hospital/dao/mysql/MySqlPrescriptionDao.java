package by.hospital.dao.mysql;

import by.hospital.dao.AbstractJDBCDao;
import by.hospital.dao.mysql.interfaces.GenericDaoForPrescription;
import by.hospital.domain.Prescription;
import by.hospital.domain.SickList;
import by.hospital.domain.Staff;
import by.hospital.domain.SurveyHistory;
import by.hospital.exception.PersistentException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 18.04.2017.
 */
public class MySqlPrescriptionDao extends AbstractJDBCDao<Prescription, Integer> implements GenericDaoForPrescription {
    private Logger logger = Logger.getLogger(MySqlPrescriptionDao.class);

    public MySqlPrescriptionDao(Connection connection) {
        super(connection);
    }

    @Override
    public Prescription create() throws PersistentException {
        Prescription prescription = new Prescription();
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
        return "SELECT\n" +
                "person.person_id, person.first_name, person.last_name, person.middle_name, person.birthday, person.sex, person.address, person.passport_number,\n" +
                "sick_list.sick_list_id, date_in, date_out, room, symptoms,\n" +
                "final_diagnose_id, findiagn.diagnose_name as findiagn_name, findiagn.therapy as findiagn_therapy,\n" +
                "survey_history.survey_history_id, survey_date, survey_description,\n" +
                "survey_history.diagnose_id as s_diagnose_id, s_diagnose.diagnose_name as s_diagnose_name, s_diagnose.therapy as s_diagnose_therapy,\n" +
                "survey_history.staff_id, staff.first_name as staff_fn, staff.last_name as staff_ln, staff.middle_name as staff_mn, staff.birthday as staff_bd, staff.sex as staff_sex, staff.address as staff_addr, staff.passport_number as staff_passport,\n" +
                "prescription_id, description, quantity, completed,\n" +
                "prescription_type_name\n" +
                "FROM person\n" +
                "join sick_list  on sick_list.person_id=person.person_id\n" +
                "join diagnose as findiagn on final_diagnose_id = diagnose_id\n" +
                "join survey_history on survey_history.sick_list_id=sick_list.sick_list_id\n" +
                "join diagnose as s_diagnose on survey_history.diagnose_id = s_diagnose.diagnose_id\n" +
                "join person as staff on survey_history.staff_id=staff.person_id\n" +
                "join prescription on prescription.survey_history_id=survey_history.survey_history_id\n" +
                "join prescription_type on prescription.prescription_type_id=prescription_type.prescription_type_id";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO prescription\n" +
                "(prescription_type_id, survey_history_id, description, quantity)\n" +
                "VALUES(?, ?, ?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE prescription \n" +
                "SET \n" +
                "prescription_type_id = ?, survey_history_id = ?, description = ?, quantity = ?\n" +
                "WHERE prescription_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM prescription WHERE prescription_id = ?;";
    }

    @Override
    protected List<Prescription> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<Prescription> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                PersistPrescription persistP = new PersistPrescription();
                SurveyHistory sh = new SurveyHistory();
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

                persistP.setPrimaryKey(resultSet.getInt("prescription_id"));
                persistP.setDescription(resultSet.getString("description"));
                persistP.setQuantity(resultSet.getInt("quantity"));
                persistP.setCompleted(resultSet.getInt("completed"));
                persistP.setPrescriptionType(resultSet.getString("prescription_type_name"));

                persistP.setSurveyHistory(sh);
                result.add(persistP);
            }
        } catch (Exception e) {
            logger.error("Error" + e.getLocalizedMessage());
            throw new PersistentException(e);
        }
        return result;

    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Prescription object) throws PersistentException {
        try {
            statement.setInt(1, object.getPrescriptionType().getId());
            statement.setInt(2, object.getSurveyHistory().getPrimaryKey());
            statement.setString(3, object.getDescription());
            statement.setInt(4, object.getQuantity());
        } catch (Exception e) {
            logger.error("Error prepareStatementForInsert" + e.getLocalizedMessage());
            throw new PersistentException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Prescription object) throws PersistentException {
        try {
            statement.setInt(1, object.getPrescriptionType().getId());
            statement.setInt(2, object.getSurveyHistory().getPrimaryKey());
            statement.setString(3, object.getDescription());
            statement.setInt(4, object.getQuantity());
            statement.setInt(5, object.getPrimaryKey());
        } catch (Exception e) {
            logger.error("Error prepareStatementForUpdate " + e.getLocalizedMessage());
            throw new PersistentException(e);
        }
    }
}
