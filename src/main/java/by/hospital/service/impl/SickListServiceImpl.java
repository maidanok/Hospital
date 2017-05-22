package by.hospital.service.impl;

import by.hospital.dao.GenericDAO;
import by.hospital.dao.conditions.*;
import by.hospital.domain.Diagnose;
import by.hospital.domain.Patient;
import by.hospital.domain.SickList;
import by.hospital.domain.SurveyHistory;
import by.hospital.domain.comparator.SortSickListByRoom;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.SickListService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public class SickListServiceImpl implements SickListService {
    private Logger logger = Logger.getLogger(SickListService.class);
    private GenericDAO<SurveyHistory, Integer> surveyHistoryDao;
    private GenericDAO<SickList, Integer> sickListDao;

    public SickListServiceImpl(GenericDAO<SickList, Integer> sickListDao, GenericDAO<SurveyHistory, Integer> surveyHistoryDao) throws PersistentException {
        this.sickListDao = sickListDao;
        this.surveyHistoryDao = surveyHistoryDao;
    }

    @Override
    public List<SickList> findAllActive() {
        List<SickList> result = null;
        try {
            result = sickListDao.FindByCondition(new DateOutNotNull());
            result.sort(new SortSickListByRoom());
        } catch (PersistentException e) {
            logger.error("findAllActive()" + e.getLocalizedMessage());
        }
        return result;
    }

    @Override
    public SickList getSickList(SickList sickList) {
        try {
            sickList = sickListDao.getByPrimaryKey(sickList.getPrimaryKey());
        } catch (PersistentException e) {
            logger.error("getSickList()" + e.getLocalizedMessage());
        }
        return sickList;
    }

    @Override
    public List<SickList> findByPatient(Patient patient) {
        List<SickList> result = null;
        try {
            result = sickListDao.FindByCondition(new PersonPersonID(patient.getPrimaryKey()));
        } catch (PersistentException e) {
            logger.error("findByPatient()" + e.getLocalizedMessage());
        }
        return result;
    }

    @Override
    public List<SickList> findByPatientAndDAte(String patientFirstName, Date dateIn) {
        List<SickList> result = null;
        try {
            if (patientFirstName != null && dateIn != null) {
                result = sickListDao.FindByCondition(new FirstNameAndDateIN(patientFirstName, dateIn));
                return result;
            } else {
                if (patientFirstName == null && dateIn != null) {
                    result = sickListDao.FindByCondition(new DateIN(dateIn));
                    return result;
                } else {
                    if (patientFirstName != null && dateIn == null) {
                        result = sickListDao.FindByCondition(new FirstNameLike(patientFirstName));
                        return result;
                    } else {
                        result = findAllActive();
                        return result;
                    }
                }
            }
        } catch (PersistentException e) {
            logger.error("findByPatientAndDAte()()" + e.getLocalizedMessage());
        }
        return result;
    }

    @Override
    public SickList createNewSickIst(SickList sickList) {
        SickList newSickList = new SickList();
        if (sickList.getDateIN().after(new Date())) {
            return newSickList;
        }
        try {
            newSickList = sickListDao.persist(sickList);
        } catch (PersistentException e) {
            logger.error("createNewSickIst()" + e.getLocalizedMessage());
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
        } catch (PersistentException e) {
            logger.error("deleteSickList()" + e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public List<SickList> findByDiagnoseID(Diagnose diagnose) {
        List<SickList> list = new ArrayList<>();
        try {
            list = sickListDao.FindByCondition(new FindFinalDiagnoseID(diagnose.getPrimaryKey()));
        } catch (PersistentException e) {
            logger.error("findByDiagnoseID()" + e.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public void saveSickList(SickList sickList) {
        if (sickList.getPrimaryKey() != 0 && sickList.getDateIN().before(new Date())) {
            try {
                sickListDao.update(sickList);
            } catch (PersistentException e) {
                logger.error("saveSickList()" + e.getLocalizedMessage());
            }
        } else {
            createNewSickIst(sickList);
        }
    }
}
