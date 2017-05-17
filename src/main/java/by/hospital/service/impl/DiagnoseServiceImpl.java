package by.hospital.service.impl;

import by.hospital.dao.GenericDAO;
import by.hospital.dao.conditions.FindFinalDiagnoseID;
import by.hospital.dao.conditions.FindSdiagnoseID;
import by.hospital.domain.Diagnose;
import by.hospital.domain.SickList;
import by.hospital.domain.SurveyHistory;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.DiagnoseService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Admin on 23.04.2017.
 */
public class DiagnoseServiceImpl implements DiagnoseService {
    private Logger logger = Logger.getLogger(DiagnoseServiceImpl.class);
    private GenericDAO<Diagnose, Integer> diagnoseDao;
    private GenericDAO<SurveyHistory, Integer> surveyHistoryDao;
    private GenericDAO<SickList, Integer> sickListDao;

    public DiagnoseServiceImpl(GenericDAO<Diagnose, Integer> diagnoseDao, GenericDAO<SurveyHistory, Integer> surveyHistoryDao, GenericDAO<SickList, Integer> sickListDao) throws PersistentException {
        this.diagnoseDao = diagnoseDao;
        this.sickListDao = sickListDao;
        this.surveyHistoryDao = surveyHistoryDao;
    }

    @Override
    public Diagnose createNewDiagnose(Diagnose diagnose) {
        Diagnose newDiagnose = diagnose;
        try {
            newDiagnose = diagnoseDao.persist(diagnose);
        } catch (PersistentException e) {
            logger.error("createNewDiagnose()"+e.getLocalizedMessage());
        }
        return newDiagnose;
    }

    @Override
    public Diagnose getDiagnose(Diagnose diagnose) {
        try {
            diagnose=diagnoseDao.getByPrimaryKey(diagnose.getPrimaryKey());
        } catch (PersistentException e) {
            logger.error("getDiagnose()"+e.getLocalizedMessage());
        }
        return diagnose;
    }

    @Override
    public List<Diagnose> getAll() {
        try {
            return diagnoseDao.getAll();
        } catch (PersistentException e) {
            logger.error("getAll()"+e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public boolean deleteDiagnose(Diagnose diagnose) {
        if (diagnose.getPrimaryKey()==0){
            return false;
        }
        try {
            if (surveyHistoryDao.FindByCondition(new FindSdiagnoseID(diagnose.getPrimaryKey())).isEmpty()
                    && sickListDao.FindByCondition(new FindFinalDiagnoseID(diagnose.getPrimaryKey())).isEmpty()) {
                diagnoseDao.delete(diagnoseDao.getByPrimaryKey(diagnose.getPrimaryKey()));
                return true;
            }
        } catch (PersistentException e) {
            logger.error("deleteDiagnose()"+e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public void saveDiagnose(Diagnose diagnose) {
        if (diagnose.getPrimaryKey()!=0){
            try {
                diagnoseDao.update(diagnose);
            } catch (PersistentException e) {
                logger.error("saveDiagnose()"+e.getLocalizedMessage());
            }
        }
        if (diagnose.getPrimaryKey()==0){
            createNewDiagnose(diagnose);
        }
    }
}
