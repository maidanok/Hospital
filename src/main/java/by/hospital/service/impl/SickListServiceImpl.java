package by.hospital.service.impl;

import by.hospital.dao.GenericDAO;
import by.hospital.dao.conditions.*;
import by.hospital.domain.Diagnose;
import by.hospital.domain.Patient;
import by.hospital.domain.SickList;
import by.hospital.domain.SurveyHistory;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.SickListService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public class SickListServiceImpl implements SickListService {
    private GenericDAO<SurveyHistory,Integer> surveyHistoryDao;
    private GenericDAO<SickList,Integer> sickListDao;

    public SickListServiceImpl(GenericDAO<SickList,Integer> sickListDao,GenericDAO<SurveyHistory,Integer> surveyHistoryDao) throws PersistentException {
        this.sickListDao=sickListDao;
        this.surveyHistoryDao=surveyHistoryDao;
    }

    @Override
    public List<SickList> findAllActive() {
        List<SickList> result = null;
        try {
            result = sickListDao.FindByCondition(new DateOutNotNull());
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public SickList findById(SickList sickList) {
        try {
            sickList=sickListDao.getByPrimaryKey(sickList.getPrimaryKey());
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return sickList;
    }

    @Override
    public List<SickList> findByPatient(Patient patient) {
        List<SickList> result = null;
        try {
            result = sickListDao.FindByCondition(new PersonPersonID(patient.getPrimaryKey()));
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<SickList> findByPatientAndDAte(String patientFirstName, Date dateIn) {
        List<SickList> result = null;
        try {
            if (patientFirstName == null) {
                result = sickListDao.FindByCondition(new DateIN(dateIn));
            } else {
                if (dateIn == null) {
                    result = sickListDao.FindByCondition(new FirstNameLike(patientFirstName));
                }else {
                    if (patientFirstName==null && dateIn==null){
                        result=findAllActive();
                    }else {
                        result = sickListDao.FindByCondition(new FirstNameAndDateIN(patientFirstName,dateIn));
                    }
                }
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public SickList createNewSickIst(SickList sickList){
        SickList newSickList = null;
        try {
            newSickList=sickListDao.persist(sickList);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return newSickList;
    }

    @Override
    public boolean deleteSickList(SickList sickList) {
        try {
            List<SurveyHistory> list = surveyHistoryDao.FindByCondition(new SickListID(sickList.getPrimaryKey()));
            if (list.isEmpty()) {
                sickListDao.delete(sickListDao.getByPrimaryKey(sickList.getPrimaryKey()));
                return true;
            }
        }catch (PersistentException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<SickList> findByDiagnoseID(Diagnose diagnose) {
        List<SickList> list=new ArrayList<>();
        try {
            list= sickListDao.FindByCondition(new FindFinalDiagnoseID(diagnose.getPrimaryKey()));
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void saveSickList(SickList sickList) {
        if (sickList.getPrimaryKey()!=0){
            try {
                sickListDao.update(sickList);
            } catch (PersistentException e) {
                e.printStackTrace();
            }
        }
        else {
            createNewSickIst(sickList);
        }
    }
}
