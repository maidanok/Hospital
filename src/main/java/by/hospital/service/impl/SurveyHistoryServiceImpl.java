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
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 23.04.2017.
 */
public class SurveyHistoryServiceImpl implements SurveyHistoryService {
    Logger logger = Logger.getLogger(SurveyHistoryServiceImpl.class);
    private GenericDAO<SurveyHistory, Integer> surveyHistoryDao;
    private GenericDAO<Prescription, Integer> prescriptionDao;
    private GenericDAO<SickList,Integer> sickListDao;


    public SurveyHistoryServiceImpl(GenericDAO<SurveyHistory, Integer> surveyHistoryDao,
                                    GenericDAO<Prescription, Integer> prescriptionDao, GenericDAO<SickList,Integer> sickListDao){
        this.surveyHistoryDao = surveyHistoryDao;
        this.prescriptionDao = prescriptionDao;
        this.sickListDao=sickListDao;
    }

    @Override
    public List<SurveyHistory> getAllbySickList(SickList sickList){
        List<SurveyHistory> surveyHistoryList = new ArrayList<>();
        try {
            surveyHistoryList=surveyHistoryDao.FindByCondition(new SickListID(sickList.getPrimaryKey()));
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        return surveyHistoryList;
    }

    @Override
    public SurveyHistory returnSurveyHistoru(SurveyHistory surveyHistory){
        try {
            return surveyHistoryDao.getByPrimaryKey(surveyHistory.getPrimaryKey());
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        return new SurveyHistory();
    }

    @Override
    public SurveyHistory createNewSurveyHistory(SurveyHistory surveyHistory){
        try {
            surveyHistory = surveyHistoryDao.persist(surveyHistory);
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        return surveyHistory;
    }

    @Override
    public boolean deleteSurveyHistory(SurveyHistory surveyHistory) {
        List<Prescription> list = new ArrayList<>();
        try {
            list = prescriptionDao.FindByCondition(new SurveyHistoryID(surveyHistory.getPrimaryKey()));
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        if (list.isEmpty()) {
            try {
                surveyHistoryDao.delete(surveyHistory);
                return true;
            } catch (PersistentException e) {
                logger.error(e.getLocalizedMessage());
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
            logger.error(e.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public SurveyHistory saveSurveyHistory(SurveyHistory surveyHistory) {
        try {
            sickListDao.update(surveyHistory.getSickList());
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        if (surveyHistory.getPrimaryKey()==0){
            surveyHistory=this.createNewSurveyHistory(surveyHistory);
        }else {
            try {
                surveyHistoryDao.update(surveyHistory);
                surveyHistory=surveyHistoryDao.getByPrimaryKey(surveyHistory.getPrimaryKey());
            } catch (PersistentException e) {
                logger.error(e.getLocalizedMessage());
            }
        }
        return surveyHistory;
    }
}
