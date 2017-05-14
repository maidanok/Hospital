package by.hospital.service.impl;

import by.hospital.dao.GenericDAO;
import by.hospital.dao.conditions.FindSdiagnoseID;
import by.hospital.dao.conditions.SickListID;
import by.hospital.dao.conditions.SurveyHistoryID;
import by.hospital.domain.Diagnose;
import by.hospital.domain.Prescription;
import by.hospital.domain.SickList;
import by.hospital.domain.SurveyHistory;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.SurveyHistoryService;

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
    public List<SurveyHistory> getAllbySickList(SickList sickList){
        List<SurveyHistory> surveyHistoryList = new ArrayList<>();
        try {
            surveyHistoryList=surveyHistoryDao.FindByCondition(new SickListID(sickList.getPrimaryKey()));
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return surveyHistoryList;
    }

    @Override
    public SurveyHistory returnSurveyHistoru(SurveyHistory surveyHistory){
        try {
            return surveyHistoryDao.getByPrimaryKey(surveyHistory.getPrimaryKey());
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return new SurveyHistory();
    }

    @Override
    public SurveyHistory createNewSurveyHistory(SurveyHistory surveyHistory){
        try {
            surveyHistory = surveyHistoryDao.persist(surveyHistory);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
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

    @Override
    public SurveyHistory saveSurveyHistory(SurveyHistory surveyHistory) {
        if (surveyHistory.getPrimaryKey()==0){
            surveyHistory=this.createNewSurveyHistory(surveyHistory);
        }else {
            try {
                surveyHistoryDao.update(surveyHistory);
                surveyHistory=surveyHistoryDao.getByPrimaryKey(surveyHistory.getPrimaryKey());
            } catch (PersistentException e) {
                e.printStackTrace();
            }
        }
        return surveyHistory;
    }
}
