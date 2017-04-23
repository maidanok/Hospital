package by.hospital.service.impl;

import by.hospital.DAO.mysql.MySqlDaoFactory;
import by.hospital.DAO.mysql.MySqlSurveyHistoryDao;
import by.hospital.domain.SurveyHistory;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.SurveyHistoryService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 23.04.2017.
 */
public class SurveyHistoryServiceImpl implements SurveyHistoryService {
    private MySqlDaoFactory mySqlDaoFactory = new MySqlDaoFactory();
    private MySqlSurveyHistoryDao surveyHistoryDao = (MySqlSurveyHistoryDao) mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(),SurveyHistory.class);

    public SurveyHistoryServiceImpl() throws PersistentException {
    }

    @Override
    public List<SurveyHistory> getAllbySickList(int sickListID) throws PersistentException {
        return surveyHistoryDao.FindByCondition( "WHERE sick_list.sick_list_id = "+sickListID+";");
    }

    @Override
    public SurveyHistory returnSurveyHistoru(int id) throws PersistentException {
        return surveyHistoryDao.getByPrimaryKey(id);
    }

    @Override
    public SurveyHistory createNewSurveyHistory(int sickID, int diagnoseID, int staffID, Date date, String description) throws PersistentException {
        SurveyHistory surveyHistory = new SurveyHistory();
        surveyHistory.getSickList().setPrimaryKey(sickID);
        surveyHistory.getDiagnose().setPrimaryKey(diagnoseID);
        surveyHistory.getStaff().setPrimaryKey(staffID);
        surveyHistory.setSurveyDate(date);
        surveyHistory.setDescription(description);
        surveyHistory=surveyHistoryDao.persist(surveyHistory);
        return surveyHistory;
    }

    @Override
    public boolean deleteSurveyHistory(int id) {
        return false;
    }

    @Override
    public List<SurveyHistory> findByDiagnoseID(int id) {
        List<SurveyHistory> list = new ArrayList<>();
        try {
            list = surveyHistoryDao.FindByCondition(" WHERE s_diagnose_id = "+id+";");
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return list;
    }
    //TODO
}
