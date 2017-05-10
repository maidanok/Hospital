package by.hospital.service.impl;

import by.hospital.DAO.GenericDAO;
import by.hospital.DAO.conditions.FindSdiagnoseID;
import by.hospital.DAO.conditions.SickListID;
import by.hospital.DAO.conditions.SurveyHistoryID;
import by.hospital.domain.Diagnose;
import by.hospital.domain.Prescription;
import by.hospital.domain.SickList;
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
    private GenericDAO<SurveyHistory, Integer> surveyHistoryDao;
    private GenericDAO<Prescription, Integer> prescriptionDao;


    public SurveyHistoryServiceImpl(GenericDAO<SurveyHistory, Integer> surveyHistoryDao, GenericDAO<Prescription, Integer> prescriptionDao) throws PersistentException {
        this.surveyHistoryDao = surveyHistoryDao;
        this.prescriptionDao = prescriptionDao;
    }

    @Override
    public List<SurveyHistory> getAllbySickList(SickList sickList) throws PersistentException {
        return surveyHistoryDao.FindByCondition(new SickListID(sickList.getPrimaryKey()));
    }

    @Override
    public SurveyHistory returnSurveyHistoru(SurveyHistory surveyHistory) throws PersistentException {
        return surveyHistoryDao.getByPrimaryKey(surveyHistory.getPrimaryKey());
    }

    @Override
    public SurveyHistory createNewSurveyHistory(int sickID, int diagnoseID, int staffID, Date date, String description) throws PersistentException {
        SurveyHistory surveyHistory = new SurveyHistory();
        surveyHistory.getSickList().setPrimaryKey(sickID);
        surveyHistory.getDiagnose().setPrimaryKey(diagnoseID);
        surveyHistory.getStaff().setPrimaryKey(staffID);
        surveyHistory.setSurveyDate(date);
        surveyHistory.setDescription(description);
        surveyHistory = surveyHistoryDao.persist(surveyHistory);
        return surveyHistory;
    }

    @Override
    public boolean deleteSurveyHistory(SurveyHistory surveyHistory) {
        List<Prescription> list = new ArrayList<>();
        try {
            list = prescriptionDao.FindByCondition(new SurveyHistoryID(surveyHistory.getPrimaryKey()));
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        if (list.isEmpty()) {
            try {
                surveyHistoryDao.delete(surveyHistory);
                return true;
            } catch (PersistentException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<SurveyHistory> findByDiagnoseID(Diagnose diagnose) {
        List<SurveyHistory> list = new ArrayList<>();
        try {
            list = surveyHistoryDao.FindByCondition(new FindSdiagnoseID(diagnose.getPrimaryKey()));
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return list;
    }
}
